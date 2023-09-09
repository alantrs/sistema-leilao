package com.lp2.model.leilao;

import com.lp2.model.enums.StatusLeilao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaLeilaoDTO {

    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private BigDecimal valorMinimo;
    private String local;

}
