package com.lp2.service;

import com.lp2.model.dispositivo.DadosAtualizacaoDispositivoDTO;
import com.lp2.model.dispositivo.DadosEntradaDispositivoDTO;
import com.lp2.model.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.model.dispositivo.DispositivoInformatica;
import com.lp2.model.enums.StatusLeilao;
import com.lp2.model.leilao.Leilao;
import com.lp2.repository.DispositivoRepository;
import com.lp2.repository.LeilaoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class DispositivoService {

    @Inject
    private DispositivoRepository dispositivoInformaticaRepository;
    @Inject
    private LeilaoRepository leilaoRepository;

    public DadosExibicaoDispositivoDTO registrarDispositivo(DadosEntradaDispositivoDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        DispositivoInformatica dispositivoInformatica = new DispositivoInformatica(cadastro);
        dispositivoInformatica.setLeilao(leilao.get());
        dispositivoInformaticaRepository.save(dispositivoInformatica);
        return new DadosExibicaoDispositivoDTO(dispositivoInformatica);
    }

    public List<DadosExibicaoDispositivoDTO> listarDispositivos(){
        List<DispositivoInformatica> dispositivosInformatica = dispositivoInformaticaRepository.findAll();
        return dispositivosInformatica.stream().map(dispositivo -> new DadosExibicaoDispositivoDTO(dispositivo)).toList();
    }

    public DadosExibicaoDispositivoDTO atualizarDispositivo(Long idDispositivo, DadosAtualizacaoDispositivoDTO atualizacao){
        Optional<DispositivoInformatica> dispositivoEncontrado = dispositivoInformaticaRepository.findById(idDispositivo);
        DispositivoInformatica dispositivo = new DispositivoInformatica(dispositivoEncontrado.get(), atualizacao);
        dispositivoInformaticaRepository.update(dispositivo);
        return new DadosExibicaoDispositivoDTO(dispositivo);
    }

    public void deletarDispositivo(Long idDispositivo){
        dispositivoInformaticaRepository.deleteById(idDispositivo);
    }

    public void manipularDispostivoLeilao(Long idDispositivo, Long idLeilao){
        Optional<DispositivoInformatica> dispositivoEncontrado = dispositivoInformaticaRepository.findById(idDispositivo);
        Optional<Leilao> leilaoEncontrado = leilaoRepository.findById(idLeilao);

        Boolean temLeilaoVinculadoEmAberto = dispositivoEncontrado.get().getLeilao().getStatusLeilao().equals(StatusLeilao.EM_ABERTO);
        Boolean leilaoEstaEmAberto = leilaoEncontrado.get().getStatusLeilao().equals(StatusLeilao.EM_ABERTO);

        if (temLeilaoVinculadoEmAberto && leilaoEstaEmAberto){
            dispositivoEncontrado.get().setLeilao(leilaoEncontrado.get());
        } else if (dispositivoEncontrado.get().getLeilao() == null && leilaoEstaEmAberto) {
            dispositivoEncontrado.get().setLeilao(leilaoEncontrado.get());
        }

    }
}

