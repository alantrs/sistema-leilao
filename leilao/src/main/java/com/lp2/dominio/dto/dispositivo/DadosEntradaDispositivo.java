package com.lp2.dominio.dto.dispositivo;

import com.lp2.dominio.enums.Condicao;
import com.lp2.dominio.enums.TipoDispositivo;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaDispositivo {

    private String nome;
    private String marca;
    private Integer ano;
    private Condicao condicao;
    private TipoDispositivo tipoDispositivo;

}
