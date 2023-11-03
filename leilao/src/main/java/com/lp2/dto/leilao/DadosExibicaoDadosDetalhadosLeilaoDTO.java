package com.lp2.dto.leilao;

import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.dto.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.dto.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.enums.StatusLeilao;
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
    private List<DadosExibicaoEntidadeFinanceiraDTO> entidades;

//    public DadosExibicaoDadosDetalhadosLeilaoDTO(Leilao leilao){
//        this.id = leilao.getId();
//        this.dataOcorrencia = leilao.getDataOcorrencia();
//        this.dataEncerramento = leilao.getDataEncerramento();
//        this.dataVisitacao = leilao.getDataVisitacao();
//        this.valorMinimo = leilao.getValorMinimo();
//        this.local = leilao.getLocal();
//        this.statusLeilao = leilao.getStatusLeilao();
//        if(!leilao.getDispositivos().isEmpty()){
//            this.dispositivos = leilao.getDispositivos().stream().map(dispositivo -> new DadosExibicaoDispositivoDTO(dispositivo)).toList();
//        }
//        if (!leilao.getVeiculos().isEmpty()){
//            this.veiculos = leilao.getVeiculos().stream().map(veiculo -> new DadosExibicaoVeiculoDTO(veiculo)).toList();
//        }
//        this.entidades = leilao.getEntidadesFinanceira().stream().map(entidade -> new DadosExibicaoEntidadeFinanceiraDTO(entidade)).toList();
//    }
}
