package com.lp2.service;

import com.lp2.model.dispositivo.DispositivoInformatica;
import com.lp2.model.entidadeFinanceira.EntidadeFinanceira;
import com.lp2.model.leilao.*;
import com.lp2.model.veiculo.Veiculo;
import com.lp2.repository.DispositivoRepository;
import com.lp2.repository.EntidadeFinanceiraRepository;
import com.lp2.repository.LeilaoRepository;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class LeilaoService {

    @Inject
    private LeilaoRepository leilaoRepository;
    @Inject
    private DispositivoRepository dispositivoRepository;
    @Inject
    private VeiculoRepository veiculoRepository;
    @Inject
    private EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    public DadosExibicaoDadosResumidosLeilaoDTO salvarLeilao(DadosEntradaLeilaoDTO cadastro){
        Optional<EntidadeFinanceira> entidadeFinanceira = entidadeFinanceiraRepository.findById(cadastro.getIdEntidadeFinanceira());
        Leilao leilao = new Leilao(cadastro);
        leilao.setEntidadeFinanceira(entidadeFinanceira.get());
        leilaoRepository.save(leilao);
        return new DadosExibicaoDadosResumidosLeilaoDTO(leilao);
    }

    public List<DadosExibicaoDadosResumidosLeilaoDTO> listarLeiloes(){
        List<Leilao> leiloes = leilaoRepository.findAll();
        return leiloes.stream().map(leilao-> new DadosExibicaoDadosResumidosLeilaoDTO(leilao)).toList();
    }

    public DadosExibicaoDadosDetalhadosLeilaoDTO exibirInformacoesLeilao(Long idLeilao){
        Optional<Leilao> leilao = leilaoRepository.findById(idLeilao);
        return new DadosExibicaoDadosDetalhadosLeilaoDTO(leilao.get());
    }

    /* tive que fazer esse metodo de delete assim
                            porque não funciona o cascade de jeito nenhum, nao sei o motivo*/
    public void deletarLeilao(Long idLeilao){
        Optional<Leilao> leilao = leilaoRepository.findById(idLeilao);
        if (!leilao.get().getDispositivos().isEmpty()) {
            for (DispositivoInformatica dispositivo : leilao.get().getDispositivos()) {
                dispositivoRepository.deleteById(dispositivo.getId());
            }
        }
        if (!leilao.get().getVeiculos().isEmpty()){
            for (Veiculo veiculo : leilao.get().getVeiculos()){
                veiculoRepository.deleteById(veiculo.getId());
            }
        }
        leilaoRepository.deleteById(idLeilao);
    }

    public DadosExibicaoDadosResumidosLeilaoDTO atualizarLeilao(Long idLeilao, DadosAtualizacaoLeilaoDTO atualizacao){
        Optional<Leilao> leilao = leilaoRepository.findById(idLeilao);

        Leilao leilaoAtualizado = new Leilao(leilao.get(), atualizacao);
        if (leilao.get().getDispositivos() != null) {
            leilaoAtualizado.setDispositivos(leilao.get().getDispositivos());
        }
        if (leilao.get().getVeiculos() != null){
            leilaoAtualizado.setVeiculos(leilao.get().getVeiculos());
        }
        leilaoRepository.update(leilaoAtualizado);
        return new DadosExibicaoDadosResumidosLeilaoDTO(leilaoAtualizado);
    }
}
