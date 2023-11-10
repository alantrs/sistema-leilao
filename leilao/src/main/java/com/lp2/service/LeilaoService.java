package com.lp2.service;

import com.lp2.dto.dispositivo.*;
import com.lp2.dto.leilao.DadosAtualizacaoLeilaoDTO;
import com.lp2.dto.leilao.DadosEntradaLeilaoDTO;
import com.lp2.dto.leilao.DadosExibicaoDadosDetalhadosLeilaoDTO;
import com.lp2.dto.leilao.DadosExibicaoDadosResumidosLeilaoDTO;
import com.lp2.dto.veiculo.DadosExibicaoCaminhaoDTO;
import com.lp2.dto.veiculo.DadosExibicaoCarroDTO;
import com.lp2.dto.veiculo.DadosExibicaoMotocicletaDTO;
import com.lp2.dto.veiculo.DadosExibicaoUtilitarioDTO;
import com.lp2.enums.TipoProduto;
import com.lp2.mapper.DispositivoMapper;
import com.lp2.mapper.VeiculoMapper;
import com.lp2.model.*;
import com.lp2.repository.DispositivoRepository;
import com.lp2.repository.EntidadeFinanceiraRepository;
import com.lp2.repository.LeilaoRepository;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
public class LeilaoService {

    @Inject
    private LeilaoRepository leilaoRepository;
    @Inject
    private DispositivoRepository<DispositivoInformatica> dispositivoRepository;
    @Inject
    private VeiculoRepository<Veiculo> veiculoRepository;

    ModelMapper modelMapper = new ModelMapper();
    VeiculoMapper veiculoMapper = new VeiculoMapper(modelMapper);
    DispositivoMapper dispositivoMapper = new DispositivoMapper(modelMapper);

    @Inject
    private EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    public DadosExibicaoDadosResumidosLeilaoDTO salvarLeilao(DadosEntradaLeilaoDTO cadastro){
        List<EntidadeFinanceira> entidadesFinanceira = entidadeFinanceiraRepository.findByIdIn(cadastro.getIdEntidadesFinanceiras());
        Leilao leilao = new Leilao(cadastro);
        leilao.setEntidadesFinanceira(entidadesFinanceira);
        leilaoRepository.save(leilao);
        return modelMapper.map(leilao, DadosExibicaoDadosResumidosLeilaoDTO.class);
    }

    public List<DadosExibicaoDadosResumidosLeilaoDTO> listarLeiloes(){
        List<Leilao> leiloes = leilaoRepository.findAllOrderByDataOcorrencia();
        return leiloes.stream().map(leilao-> modelMapper.map(leilao, DadosExibicaoDadosResumidosLeilaoDTO.class)).toList();
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
        if (leilao.get().getEntidadesFinanceira() != null){
            leilaoAtualizado.setEntidadesFinanceira(leilao.get().getEntidadesFinanceira());
        }
        leilaoRepository.update(leilaoAtualizado);
        return new DadosExibicaoDadosResumidosLeilaoDTO(leilaoAtualizado);
    }

    public Object buscarProdutoLeilao(Long idLeilão, Long idProduto, TipoProduto tipoProduto){
        if (tipoProduto == TipoProduto.DISPOSITIVO){
            return buscarDispositivoPorIdEmLeilao(idLeilão, idProduto);
        } else if (tipoProduto == TipoProduto.VEICULO) {
            return buscarVeiculoPorIdEmLeilao(idLeilão, idProduto);
        }
        return null;
    }

    private Object buscarVeiculoPorIdEmLeilao(Long idLeilao, Long veiculoId) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findByIdAndLeilaoId(veiculoId, idLeilao);

        if (veiculoOptional.isPresent()) {
            Veiculo veiculo = veiculoOptional.get();
            return veiculoMapper.mapearVeiculoParaDTO(veiculo);
        }

        return null;
    }



    private Object buscarDispositivoPorIdEmLeilao(Long leilaoId, Long dispositivoId) {
        Optional<DispositivoInformatica> dispositivoOptional = dispositivoRepository.findByIdAndLeilaoId(dispositivoId, leilaoId);

            if (dispositivoOptional.isPresent()) {
                DispositivoInformatica dispositivo = dispositivoOptional.get();
                return dispositivoMapper.mapearDispositivosParaDTO(dispositivo);
            }

        return null;
    }


//    public List<Object> buscarProdutosPorFaixaValor(Long idLeilao, BigDecimal min, BigDecimal max){
//        List<Object> produtos = buscar produtos(veiculos e dispositivos) de acordo com o valor min e max, esse valor equivale ao campo valorInicial da entidade veiculo e entidade dispositivo
//    }

}
