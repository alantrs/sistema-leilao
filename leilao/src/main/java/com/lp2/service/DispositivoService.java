package com.lp2.service;

import com.lp2.model.dispositivo.DispositivoInformatica;

import com.lp2.model.dispositivo.DadosEntradaDispositivoDTO;
import com.lp2.model.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.model.enums.StatusLeilao;
import com.lp2.model.leilao.Leilao;
import com.lp2.repository.DispositivoRepository;
import com.lp2.repository.LeilaoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class DispositivoService {

    @Inject
    private DispositivoRepository dispositivoInformaticaRepository;
    @Inject
    private LeilaoRepository leilaoRepository;

    public DadosExibicaoDispositivoDTO salvarDispositivo(DadosEntradaDispositivoDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        DispositivoInformatica dispositivoInformatica = new DispositivoInformatica(cadastro);
        dispositivoInformatica.setLeilao(leilao.get());
        dispositivoInformaticaRepository.save(dispositivoInformatica);
        return new DadosExibicaoDispositivoDTO(dispositivoInformatica);
    }

    public List<DadosExibicaoDispositivoDTO> salvarDispositivos(List<DadosEntradaDispositivoDTO> cadastro){
        List<DispositivoInformatica> dispositivosSalvos = new ArrayList<>();
        for (DadosEntradaDispositivoDTO dado : cadastro){
            DispositivoInformatica dispositivoNovo = new DispositivoInformatica(dado);
            dispositivoInformaticaRepository.save(dispositivoNovo);
            dispositivosSalvos.add(dispositivoNovo);
        }

        return dispositivosSalvos.stream().map(dispositivo -> new DadosExibicaoDispositivoDTO(dispositivo)).toList();
    }

    public List<DadosExibicaoDispositivoDTO> listarDispositivos(){
        List<DispositivoInformatica> dispositivosInformatica = dispositivoInformaticaRepository.findAll();
        return dispositivosInformatica.stream().map(dispositivo -> new DadosExibicaoDispositivoDTO(dispositivo)).toList();
    }

    public DadosExibicaoDispositivoDTO atualizarDispositivo(Long idDispositivo, DadosEntradaDispositivoDTO atualizacao){
        Optional<DispositivoInformatica> dispositivoEncontrado = dispositivoInformaticaRepository.findById(idDispositivo);
        Optional<Leilao> leilaoEncontrado = leilaoRepository.findById(atualizacao.getIdLeilao());

        DispositivoInformatica dispositivo = new DispositivoInformatica(dispositivoEncontrado.get(), atualizacao);
        manipularDispostivoLeilao(dispositivo.getId(), leilaoEncontrado.get().getId());
        dispositivoInformaticaRepository.update(dispositivo);
        return new DadosExibicaoDispositivoDTO(dispositivo);
    }

    public void deletarDispositivo(Long idDispositivo){
        dispositivoInformaticaRepository.deleteById(idDispositivo);
    }

    public void manipularDispostivoLeilao(Long idDispositivo, Long idLeilao){
        Optional<DispositivoInformatica> dispositivoEncontrado = dispositivoInformaticaRepository.findById(idDispositivo);
        Optional<Leilao> leilaoEncontrado = leilaoRepository.findById(idLeilao);

        if (leilaoEncontrado.get().getStatusLeilao().equals(StatusLeilao.EM_ABERTO)
                && dispositivoEncontrado.get().getLeilao().getStatusLeilao().equals(StatusLeilao.EM_ABERTO)){
            dispositivoEncontrado.get().setLeilao(leilaoEncontrado.get());
        }
    }
}

