package com.lp2.service;

import com.lp2.dto.dispositivo.*;
import com.lp2.exception.CustomException;
import com.lp2.mapper.DispositivoMapper;
import com.lp2.model.*;
import com.lp2.repository.DispositivoRepository;
import com.lp2.repository.LeilaoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
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

    DispositivoMapper dispositivoMapper = new DispositivoMapper(modelMapper);

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
        List<Object> dispositivosDTO = new ArrayList<>();

        dispositivoRepository.findAll().forEach(dispositivoInformatica -> {
            Object dispositivoDTO = dispositivoMapper.mapearDispositivoParaDTO(dispositivoInformatica);
            if (dispositivoDTO != null) {
                dispositivosDTO.add(dispositivoDTO);
            }
        });

        return dispositivosDTO;
    }


    public DadosExibicaoNotebookDTO atualizarDispositivoNotebook(Long idDispositivo, DadosAtualizacaoNotebookDTO atualizacaoNotebook){
        Object dispositivoEncontrado = notebookRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("dispositivo não existe"));
        if (dispositivoEncontrado instanceof Notebook){
            Notebook notebook = new Notebook((Notebook) dispositivoEncontrado, atualizacaoNotebook);
            notebookRepository.update(notebook);
            return modelMapper.map(notebook, DadosExibicaoNotebookDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é um notebook");
        }

    }

    public DadosExibicaoRoteadorDTO atualizarDispositivoRoteador(Long idDispositivo, DadosAtualizacaoRoteadorDTO atualizacaoRoteador){
        Object dispositivoEncontrado = roteadorRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("dispositivo não existe"));
        if(dispositivoEncontrado instanceof Roteador){
            Roteador roteador = new Roteador((Roteador) dispositivoEncontrado, atualizacaoRoteador);
            roteadorRepository.update(roteador);
            return modelMapper.map(roteador, DadosExibicaoRoteadorDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é um roteador");
        }

    }

    public DadosExibicaoHubDTO atualizarDispositivoHub(Long idDispositivo, DadosAtualizacaoHubDTO atualizacaoHub){
        Object dispositivoEncontrado = hubRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("dispositivo não existe"));
        if(dispositivoEncontrado instanceof Hub){
            Hub hub = new Hub((Hub) dispositivoEncontrado, atualizacaoHub);
            hubRepository.update(hub);
            return modelMapper.map(hub, DadosExibicaoHubDTO.class);
        }else{
            throw new CustomException("Não foi possivel atualizar. Id passado não é um hub");
        }

    }

    public DadosExibicaoMonitorDTO atualizarDispositivoMonitor(Long idDispositivo, DadosAtualizacaoMonitorDTO atualizacaoMonitor){
        Object dispositivoEncontrado = monitorRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("dispositivo não existe"));
        if (dispositivoEncontrado instanceof Monitor){
            Monitor monitor = new Monitor((Monitor) dispositivoEncontrado, atualizacaoMonitor);
            monitorRepository.update(monitor);
            return modelMapper.map(monitor, DadosExibicaoMonitorDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é um monitor");
        }

    }

    public DadosExibicaoSwitchDTO atualizarDispositivoSwitch(Long idDispositivo, DadosAtualizacaoSwitchDTO atualizacaoSwitch){
        Object dispositivoEncontrado = switchRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("dispositivo não existe"));
        if (dispositivoEncontrado instanceof Switch){
            Switch swit = new Switch((Switch) dispositivoEncontrado, atualizacaoSwitch);
            switchRepository.update(swit);
            return modelMapper.map(swit, DadosExibicaoSwitchDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é um switch");
        }

    }

    public void deletarDispositivo(Long idDispositivo){
        dispositivoRepository.deleteById(idDispositivo);
    }

    public void manipularDispostivoLeilao(Long idDispositivo, Long idLeilao){
        DispositivoInformatica dispositivoEncontrado = dispositivoRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("dispositivo não existe"));
        Optional<Leilao> leilaoEncontrado = leilaoRepository.findById(idLeilao);

        if (!dispositivoEncontrado.getLances().isEmpty()){
            throw new CustomException("Acao nao permitida. Esse dispositivo ja recebeu lance");
        }

        dispositivoEncontrado.setLeilao(leilaoEncontrado.get());
        dispositivoRepository.update(dispositivoEncontrado);

    }
}

