package com.lp2.dto.leilao;

import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.dto.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.dto.lance.DadosExibicaoLanceProdutoDTO;
import com.lp2.dto.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.enums.StatusLeilao;
import com.lp2.model.Lance;
import com.lp2.model.Leilao;
import com.lp2.util.CalculoStatusLeilao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO {

    private Long id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private BigDecimal valorMinimo;
    private String local;
    private StatusLeilao statusLeilao;
    private List<DadosExibicaoLanceProdutoDTO> lancesVencedores;
    private List<DadosExibicaoDispositivoDTO> dispositivos;
    private List<DadosExibicaoVeiculoDTO> veiculos;
    private List<DadosExibicaoEntidadeFinanceiraDTO> entidades;

    public DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO() {
    }

    public DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO(Leilao leilao) {
        this.id = leilao.getId();
        this.dataOcorrencia = leilao.getDataOcorrencia();
        this.dataEncerramento = leilao.getDataEncerramento();
        this.dataVisitacao = leilao.getDataVisitacao();
        this.local = leilao.getLocal();
        this.statusLeilao = StatusLeilao.FINALIZADO;

        if (!leilao.getDispositivos().isEmpty()) {
            this.lancesVencedores = leilao.getDispositivos().stream().map(dispositivoInformatica -> {
                Optional<Lance> lanceVencedorOpt = dispositivoInformatica.getLances().stream()
                        .max(Comparator.comparing(Lance::getValor));
                var dadosRetorno = lanceVencedorOpt.map(lanceVencedor -> new DadosExibicaoLanceProdutoDTO(Collections.singletonList(lanceVencedor)))
                        .orElse(null);
                dadosRetorno.setProduto(lanceVencedorOpt.get().getDispositivoInformatica().getNome());
                return dadosRetorno;
            }).filter(lanceDto -> lanceDto != null).toList();
        }

        if (!leilao.getVeiculos().isEmpty()) {
            this.lancesVencedores = leilao.getVeiculos().stream().map(veiculo -> {
                Optional<Lance> lanceVencedorOpt = veiculo.getLances().stream()
                        .max(Comparator.comparing(Lance::getValor));

                var dadosRetorno = lanceVencedorOpt.map(lanceVencedor -> new DadosExibicaoLanceProdutoDTO(Collections.singletonList(lanceVencedor)))
                        .orElse(null);
                dadosRetorno.setProduto(lanceVencedorOpt.get().getVeiculo().getModelo());
                return dadosRetorno;
            }).filter(lanceDto -> lanceDto != null).toList();
        }

        this.entidades = leilao.getEntidadesFinanceira().stream()
                .map(entidade -> new DadosExibicaoEntidadeFinanceiraDTO(entidade))
                .toList();
    }
}
