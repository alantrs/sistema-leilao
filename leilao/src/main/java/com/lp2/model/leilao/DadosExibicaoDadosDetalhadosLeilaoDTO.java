package com.lp2.model.leilao;

import com.lp2.model.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.model.dispositivo.DispositivoInformatica;
import com.lp2.model.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.model.enums.StatusLeilao;
import com.lp2.model.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.model.veiculo.Veiculo;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoDadosDetalhadosLeilaoDTO {

    private Long id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private BigDecimal valorMinimo;
    private String local;
    private StatusLeilao statusLeilao;
    private List<DadosExibicaoDispositivoDTO> dispositivos;
    private List<DadosExibicaoVeiculoDTO> veiculos;
    private DadosExibicaoEntidadeFinanceiraDTO entidade;

    public DadosExibicaoDadosDetalhadosLeilaoDTO(Leilao leilao){
        this.id = leilao.getId();
        this.dataOcorrencia = leilao.getDataOcorrencia();
        this.dataEncerramento = leilao.getDataEncerramento();
        this.dataVisitacao = leilao.getDataVisitacao();
        this.valorMinimo = leilao.getValorMinimo();
        this.local = leilao.getLocal();
        this.statusLeilao = leilao.getStatusLeilao();
        if(!leilao.getDispositivos().isEmpty()){
            this.dispositivos = leilao.getDispositivos().stream().map(dispositivo -> new DadosExibicaoDispositivoDTO(dispositivo)).toList();
        }
        if (!leilao.getVeiculos().isEmpty()){
            this.veiculos = leilao.getVeiculos().stream().map(veiculo -> new DadosExibicaoVeiculoDTO(veiculo)).toList();
        }
        this.entidade = new DadosExibicaoEntidadeFinanceiraDTO(leilao.getEntidadeFinanceira());
    }
}
