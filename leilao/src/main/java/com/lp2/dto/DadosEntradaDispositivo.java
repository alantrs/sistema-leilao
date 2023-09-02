package com.lp2.dto;

import com.lp2.dominio.TipoDispositivo;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Serdeable.Deserializable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosEntradaDispositivo {

    private String nome;
    private String descricao;
    private TipoDispositivo tipoDispositivo;

}
