package com.lp2.dto.leilao;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosAtualizacaoLeilaoDTO {

    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private String local;

}
