package com.lp2.dto.leilao;

import com.lp2.enums.StatusLeilao;
import com.lp2.model.Leilao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.time.LocalDateTime;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoDadosResumidosLeilaoDTO {

    private Long id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private BigDecimal valorMinimo;
    private String local;
    private StatusLeilao statusLeilao;

    public DadosExibicaoDadosResumidosLeilaoDTO(){}
    public DadosExibicaoDadosResumidosLeilaoDTO(Leilao leilao){
        this.id = leilao.getId();
        this.dataOcorrencia = leilao.getDataOcorrencia();
        this.dataEncerramento = leilao.getDataEncerramento();
        this.dataVisitacao = leilao.getDataVisitacao();
        this.valorMinimo = leilao.getValorMinimo();
        this.local = leilao.getLocal();
        this.statusLeilao = leilao.getStatusLeilao();
    }

    @Override
    public String toString() {
        return "DadosExibicaoLeilaoDTO{" +
                "id=" + id +
                ", dataOcorrencia=" + dataOcorrencia +
                ", dataEncerramento=" + dataEncerramento +
                ", dataVisitacao=" + dataVisitacao +
                ", valorMinimo=" + valorMinimo +
                ", local='" + local + '\'' +
                ", statusLeilao=" + statusLeilao +
                '}';
    }
}
