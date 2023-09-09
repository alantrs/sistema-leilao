package com.lp2.service;

import com.lp2.model.leilao.DadosEntradaLeilaoDTO;
import com.lp2.model.leilao.DadosExibicaoLeilaoDTO;
import com.lp2.model.leilao.Leilao;
import com.lp2.repository.LeilaoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class LeilaoService {

    @Inject
    private LeilaoRepository leilaoRepository;

    public DadosExibicaoLeilaoDTO salvarLeilao(DadosEntradaLeilaoDTO cadastro){
        Leilao leilao = new Leilao(cadastro);
        leilaoRepository.save(leilao);
        return new DadosExibicaoLeilaoDTO(leilao);
    }

    public List<DadosExibicaoLeilaoDTO> listarLeilao(){
        List<Leilao> leiloes = leilaoRepository.findAll();
        return leiloes.stream().map(leilao-> new DadosExibicaoLeilaoDTO(leilao)).toList();
    }
}
