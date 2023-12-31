package com.lp2.dto.dispositivo;

import com.lp2.enums.Condicao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosAtualizacaoDispositivoDTO {

    private String nome;
    private String marca;
    private Integer ano;
    private Condicao condicao;
    private Integer quantidade;
    private BigDecimal valorInicial;
}
