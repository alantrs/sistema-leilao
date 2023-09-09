package com.lp2.service;

import com.lp2.model.dispositivo.DispositivoInformatica;

import com.lp2.model.dispositivo.DadosEntradaDispositivoDTO;
import com.lp2.model.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.repository.DispositivoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class DispositivoService {

    @Inject
    private DispositivoRepository dispositivoInformaticaRepository;

    public DadosExibicaoDispositivoDTO salvarDispositivo(DadosEntradaDispositivoDTO cadastro){
        DispositivoInformatica dispositivoInformatica = new DispositivoInformatica(cadastro);
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

        DispositivoInformatica dispositivo = new DispositivoInformatica(dispositivoEncontrado.get(), atualizacao);
        dispositivoInformaticaRepository.update(dispositivo);
        return new DadosExibicaoDispositivoDTO(dispositivo);
    }

    public void deletarDispositivo(Long idDispositivo){
        dispositivoInformaticaRepository.deleteById(idDispositivo);
    }
}

