package com.lp2.model.dispositivo;

import com.lp2.model.enums.Condicao;
import com.lp2.model.enums.TipoDispositivo;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaDispositivoDTO {

    private String nome;
    private String marca;
    private Integer ano;
    private Condicao condicao;
    private TipoDispositivo tipoDispositivo;
    private Integer quantidade;
    private Long idLeilao;

}
