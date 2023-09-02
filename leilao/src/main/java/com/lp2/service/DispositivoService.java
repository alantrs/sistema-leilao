package com.lp2.service;

import com.lp2.dominio.DispositivoInformatica;

import com.lp2.dominio.dto.dispositivo.DadosEntradaDispositivo;
import com.lp2.dominio.dto.dispositivo.DadosExibicaoDispositivo;
import com.lp2.repository.DispositivoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class DispositivoService {

    @Inject
    private DispositivoRepository dispositivoInformaticaRepository;

    public DadosExibicaoDispositivo salvarDispositivo(DadosEntradaDispositivo cadastro){
        DispositivoInformatica dispositivoInformatica = new DispositivoInformatica(cadastro);
        dispositivoInformaticaRepository.save(dispositivoInformatica);
        return new DadosExibicaoDispositivo(dispositivoInformatica);
    }

    public List<DadosExibicaoDispositivo> listarDispositivos(){
        List<DispositivoInformatica> dispositivosInformatica = dispositivoInformaticaRepository.findAll();
        return dispositivosInformatica.stream().map(dispositivo -> new DadosExibicaoDispositivo(dispositivo)).toList();
    }

    public DadosExibicaoDispositivo atualizarDispositivo(Long idDispositivo, DadosEntradaDispositivo atualizacao){
        Optional<DispositivoInformatica> dispositivoEncontrado = dispositivoInformaticaRepository.findById(idDispositivo);

        DispositivoInformatica dispositivo = new DispositivoInformatica(dispositivoEncontrado.get(), atualizacao);
        dispositivoInformaticaRepository.update(dispositivo);
        return new DadosExibicaoDispositivo(dispositivo);
    }

    public void deletarDispositivo(Long idDispositivo){
        dispositivoInformaticaRepository.deleteById(idDispositivo);
    }
}

