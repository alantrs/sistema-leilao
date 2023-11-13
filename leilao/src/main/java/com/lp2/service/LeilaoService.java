package com.lp2.service;

import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.dto.lance.DadosExibicaoLanceVencedorDTO;
import com.lp2.dto.leilao.*;
import com.lp2.enums.StatusLeilao;
import com.lp2.enums.TipoProduto;
import com.lp2.mapper.DispositivoMapper;
import com.lp2.mapper.VeiculoMapper;
import com.lp2.model.*;
import com.lp2.repository.*;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class LeilaoService {

    @Inject
    private LeilaoRepository leilaoRepository;
    @Inject
    private DispositivoRepository<DispositivoInformatica> dispositivoRepository;
    @Inject
    private VeiculoRepository<Veiculo> veiculoRepository;
    @Inject
    private LanceRepository lanceRepository;
    @Inject
    private EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    ModelMapper modelMapper = new ModelMapper();
    VeiculoMapper veiculoMapper = new VeiculoMapper(modelMapper);
    DispositivoMapper dispositivoMapper = new DispositivoMapper(modelMapper);

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

    public Object exibirInformacoesLeilao(Long idLeilao){
        Optional<Leilao> leilao = leilaoRepository.findById(idLeilao);
        if (!leilao.get().getStatusLeilao().equals(StatusLeilao.FINALIZADO)){
            return new DadosExibicaoDadosDetalhadosLeilaoDTO(leilao.get());
        }
        return processarLeilao(leilao.get());
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
        return modelMapper.map(leilaoAtualizado, DadosExibicaoDadosResumidosLeilaoDTO.class);
    }

    public Object buscarProdutoLeilao(Long idLeilão, Long idProduto, TipoProduto tipoProduto){
        if (tipoProduto == TipoProduto.DISPOSITIVO){
            return buscarDispositivoPorIdEmLeilao(idLeilão, idProduto);
        } else if (tipoProduto == TipoProduto.VEICULO) {
            return buscarVeiculoPorIdEmLeilao(idLeilão, idProduto);
        }
        return null;
    }
    public List<Object> buscarProdutosEmLeilaoPorFaixaValor(Long idLeilao, BigDecimal min, BigDecimal max) {
        List<Object> produtos = new ArrayList<>();

        veiculoRepository.findByLeilaoIdAndValorInicialBetween(idLeilao, min, max)
                .forEach(veiculo -> produtos.add(veiculoMapper.mapearVeiculoParaDTO(veiculo)));

        dispositivoRepository.findByLeilaoIdAndValorInicialBetween(idLeilao, min, max)
                .forEach(dispositivo -> produtos.add(dispositivoMapper.mapearDispositivoParaDTO(dispositivo)));

        return produtos;
    }

    public List<Object> buscarProdutoEmLeilaoPorNome(Long idLeilao, String nome){
        List<Object> produtos = new ArrayList<>();

        veiculoRepository.findAllByLeilaoIdAndModeloContaining(idLeilao, nome)
                .forEach(veiculo -> produtos.add(veiculoMapper.mapearVeiculoParaDTO(veiculo)));

        dispositivoRepository.findAllByLeilaoIdAndNomeContaining(idLeilao, nome)
                .forEach(dispositivo -> produtos.add(dispositivoMapper.mapearDispositivoParaDTO(dispositivo)));

        return produtos;
    }

    public List<Object> buscarProdutoEmLeilaoPorTipo (Long idLeilao, TipoProduto tipoProduto){
        List<Object> produtos = new ArrayList<>();
        if(tipoProduto.equals(TipoProduto.VEICULO)){
            veiculoRepository.findAllByLeilaoId(idLeilao)
                    .forEach(veiculo -> produtos.add(veiculoMapper.mapearVeiculoParaDTO(veiculo)));
        }else {
            dispositivoRepository.findAllByLeilaoId(idLeilao)
                    .forEach(dispositivo -> produtos.add(dispositivoMapper.mapearDispositivoParaDTO(dispositivo)));

        }
        return produtos;
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
                return dispositivoMapper.mapearDispositivoParaDTO(dispositivo);
            }

        return null;
    }

    @Transactional
    public DadosExportacaoLeilaoDTO exportarLeilao(Long idLeilao){
        Optional<Leilao> leilao = leilaoRepository.findById(idLeilao);
        return new DadosExportacaoLeilaoDTO(leilao.get());
    }

    public DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO processarLeilao(Leilao leilao) {
        DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO dto = new DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO();

        dto.setId(leilao.getId());
        dto.setDataOcorrencia(leilao.getDataOcorrencia());
        dto.setDataEncerramento(leilao.getDataEncerramento());
        dto.setDataVisitacao(leilao.getDataVisitacao());
        dto.setLocal(leilao.getLocal());
        dto.setStatusLeilao(StatusLeilao.FINALIZADO);
        dto.setLancesVencedores(new ArrayList<>());

        processarLancesDispositivos(leilao, dto);
        processarLancesVeiculos(leilao, dto);

        dto.setEntidades(leilao.getEntidadesFinanceira().stream()
                .map(entidade -> new DadosExibicaoEntidadeFinanceiraDTO(entidade))
                .collect(Collectors.toList()));

        return dto;
    }

    private void processarLancesDispositivos(Leilao leilao, DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO dto) {
        if (!leilao.getDispositivos().isEmpty()) {
            for (DispositivoInformatica dispositivo : leilao.getDispositivos()) {
                Optional<Lance> lanceVencedorOpt = lanceRepository.findTopByDispositivoInformaticaOrderByValorDesc(dispositivo);
                lanceVencedorOpt.ifPresent(lanceVencedor -> {
                    DadosExibicaoLanceVencedorDTO dadosRetorno = new DadosExibicaoLanceVencedorDTO(lanceVencedor);
                    Object dispositivoDto = dispositivoMapper.mapearDispositivoParaDTO(dispositivo);
                    if (dispositivoDto != null) {
                        dadosRetorno.setProduto(dispositivoDto);
                    }
                    dto.getLancesVencedores().add(dadosRetorno);
                });
            }
        }
    }

    private void processarLancesVeiculos(Leilao leilao, DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO dto) {
        if (!leilao.getVeiculos().isEmpty()) {
            for (Veiculo veiculo : leilao.getVeiculos()) {
                Optional<Lance> lanceVencedorOpt = lanceRepository.findTopByVeiculoOrderByValorDesc(veiculo);
                lanceVencedorOpt.ifPresent(lanceVencedor -> {
                    DadosExibicaoLanceVencedorDTO dadosRetorno = new DadosExibicaoLanceVencedorDTO(lanceVencedor);
                    Object veiculoDto = veiculoMapper.mapearVeiculoParaDTO(veiculo);
                    if (veiculoDto != null) {
                        dadosRetorno.setProduto(veiculoDto);
                    }
                    dto.getLancesVencedores().add(dadosRetorno);
                });
            }
        }
    }


}


