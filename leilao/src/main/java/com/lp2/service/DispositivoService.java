package com.lp2.service;

import com.lp2.dto.dispositivo.*;
import com.lp2.model.*;
import com.lp2.repository.DispositivoRepository;
import com.lp2.repository.LeilaoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class DispositivoService {

    @Inject
    private DispositivoRepository<DispositivoInformatica> dispositivoRepository;
    @Inject
    private DispositivoRepository<Notebook> notebookRepository;
    @Inject
    private DispositivoRepository<Monitor> monitorRepository;
    @Inject
    private DispositivoRepository<Roteador> roteadorRepository;
    @Inject
    private DispositivoRepository<Hub> hubRepository;
    @Inject
    private DispositivoRepository<Switch> switchRepository;
    @Inject
    private LeilaoRepository leilaoRepository;

    ModelMapper modelMapper = new ModelMapper();

    public DadosExibicaoNotebookDTO registrarDispositivoNotebook(DadosEntradaNotebookDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Notebook notebook = new Notebook(cadastro);
        notebook.setLeilao(leilao.get());
        notebookRepository.save(notebook);
        return modelMapper.map(notebook, DadosExibicaoNotebookDTO.class);
    }

    public DadosExibicaoMonitorDTO registrarDispositivoMonitor(DadosEntradaMonitorDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Monitor monitor = new Monitor(cadastro);
        monitor.setLeilao(leilao.get());
        monitorRepository.save(monitor);
        return modelMapper.map(monitor, DadosExibicaoMonitorDTO.class);
    }

    public DadosExibicaoRoteadorDTO registrarDispositivoRoteador(DadosEntradaRoteadorDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Roteador roteador = new Roteador(cadastro);
        roteador.setLeilao(leilao.get());
        roteadorRepository.save(roteador);
        return modelMapper.map(roteador, DadosExibicaoRoteadorDTO.class);
    }

    public DadosExibicaoHubDTO registrarDispositivoHub(DadosEntradaHubDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Hub hub = new Hub(cadastro);
        hub.setLeilao(leilao.get());
        hubRepository.save(hub);
        return modelMapper.map(hub, DadosExibicaoHubDTO.class);
    }

    public DadosExibicaoSwitchDTO registrarDispositivoSwitch(DadosEntradaSwitchDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Switch swit = new Switch(cadastro);
        swit.setLeilao(leilao.get());
        switchRepository.save(swit);
        return modelMapper.map(swit, DadosExibicaoSwitchDTO.class);
    }

    public List<Object> listarDispositivos() {
        return dispositivoRepository.findAll().stream()
                .map(dispositivo -> dispositivo instanceof Notebook ?
                        modelMapper.map((Notebook) dispositivo, DadosExibicaoNotebookDTO.class) :
                        dispositivo instanceof Hub ?
                                modelMapper.map((Hub) dispositivo, DadosExibicaoHubDTO.class) :
                                dispositivo instanceof Roteador ?
                                        modelMapper.map((Roteador) dispositivo, DadosExibicaoRoteadorDTO.class) :
                                        dispositivo instanceof Monitor ?
                                                modelMapper.map((Monitor) dispositivo, DadosExibicaoMonitorDTO.class) :
                                                modelMapper.map((Switch) dispositivo, DadosExibicaoSwitchDTO.class))
                .collect(Collectors.toList());
    }

    public DadosExibicaoNotebookDTO atualizarDispositivoNotebook(Long idDispositivo, DadosAtualizacaoNotebookDTO atualizacaoNotebook){
        Optional<Notebook> dispositivoEncontrado = notebookRepository.findById(idDispositivo);
        Notebook notebook = new Notebook(dispositivoEncontrado.get(), atualizacaoNotebook);
        notebookRepository.update(notebook);
        return modelMapper.map(notebook, DadosExibicaoNotebookDTO.class);
    }

    public DadosExibicaoRoteadorDTO atualizarDispositivoRoteador(Long idDispositivo, DadosAtualizacaoRoteadorDTO atualizacaoRoteador){
        Optional<Roteador> dispositivoEncontrado = roteadorRepository.findById(idDispositivo);
        Roteador roteador = new Roteador(dispositivoEncontrado.get(), atualizacaoRoteador);
        roteadorRepository.update(roteador);
        return modelMapper.map(roteador, DadosExibicaoRoteadorDTO.class);
    }

    public DadosExibicaoHubDTO atualizarDispositivoHub(Long idDispositivo, DadosAtualizacaoHubDTO atualizacaoHub){
        Optional<Hub> dispositivoEncontrado = hubRepository.findById(idDispositivo);
        Hub hub = new Hub(dispositivoEncontrado.get(), atualizacaoHub);
        hubRepository.update(hub);
        return modelMapper.map(hub, DadosExibicaoHubDTO.class);
    }

    public DadosExibicaoMonitorDTO atualizarDispositivoMonitor(Long idDispositivo, DadosAtualizacaoMonitorDTO atualizacaoMonitor){
        Optional<Monitor> dispositivoEncontrado = monitorRepository.findById(idDispositivo);
        Monitor monitor = new Monitor(dispositivoEncontrado.get(), atualizacaoMonitor);
        monitorRepository.update(monitor);
        return modelMapper.map(monitor, DadosExibicaoMonitorDTO.class);
    }

    public DadosExibicaoSwitchDTO atualizarDispositivoSwitch(Long idDispositivo, DadosAtualizacaoSwitchDTO atualizacaoSwitch){
        Optional<Switch> dispositivoEncontrado = switchRepository.findById(idDispositivo);
        Switch swit = new Switch(dispositivoEncontrado.get(), atualizacaoSwitch);
        switchRepository.update(swit);
        return modelMapper.map(swit, DadosExibicaoSwitchDTO.class);
    }

    public void deletarDispositivo(Long idDispositivo){
        dispositivoRepository.deleteById(idDispositivo);
    }
//
//    public void manipularDispostivoLeilao(Long idDispositivo, Long idLeilao){
//        Optional<DispositivoInformatica> dispositivoEncontrado = dispositivoInformaticaRepository.findById(idDispositivo);
//        Optional<Leilao> leilaoEncontrado = leilaoRepository.findById(idLeilao);
//
//        if (!dispositivoEncontrado.get().getLances().isEmpty()){
//            System.out.println("Esse produto ja recebeu lance");
//            return;
//        }
//        dispositivoEncontrado.get().setLeilao(leilaoEncontrado.get());
//        dispositivoInformaticaRepository.update(dispositivoEncontrado.get());
//
//    }
}

