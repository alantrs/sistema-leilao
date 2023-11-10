package com.lp2.dto.leilao;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaLeilaoDTO {

    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private String local;
    private List<Long> idEntidadesFinanceiras;
}
