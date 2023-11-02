package com.lp2.service;

import com.lp2.dto.entidadeFinanceira.DadosAtualizaEntidadeFinanceiraDTO;
import com.lp2.dto.entidadeFinanceira.DadosEntradaEntidadeFinanceiraDTO;
import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.model.EntidadeFinanceira;
import com.lp2.repository.EntidadeFinanceiraRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class EntidadeFinanceiraService {

    @Inject
    private EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    public DadosExibicaoEntidadeFinanceiraDTO salvarEntidadeFinaceira(DadosEntradaEntidadeFinanceiraDTO cadastro){
        EntidadeFinanceira entidadeFinanceira = new EntidadeFinanceira(cadastro);
        entidadeFinanceiraRepository.save(entidadeFinanceira);
        return new DadosExibicaoEntidadeFinanceiraDTO(entidadeFinanceira);
    }

    public List<DadosExibicaoEntidadeFinanceiraDTO> listarEntidadesFinanceiras(){
        List<EntidadeFinanceira> entidadesFinanceiras = entidadeFinanceiraRepository.findAll();
        return entidadesFinanceiras.stream().map(entidadeFinanceira -> new DadosExibicaoEntidadeFinanceiraDTO(entidadeFinanceira)).toList();
    }

    public DadosExibicaoEntidadeFinanceiraDTO atualizarEntidadeFinanceira(Long idEntidade, DadosAtualizaEntidadeFinanceiraDTO atualizacao){
        Optional<EntidadeFinanceira> entidadeFinanceiraEncontrada = entidadeFinanceiraRepository.findById(idEntidade);

        EntidadeFinanceira entidadeFinanceira = new EntidadeFinanceira(entidadeFinanceiraEncontrada.get(), atualizacao);
        entidadeFinanceiraRepository.update(entidadeFinanceira);
        return new DadosExibicaoEntidadeFinanceiraDTO(entidadeFinanceira);
    }

    public void deletarEntidade(Long idEntidade){
        entidadeFinanceiraRepository.deleteById(idEntidade);
    }
}
