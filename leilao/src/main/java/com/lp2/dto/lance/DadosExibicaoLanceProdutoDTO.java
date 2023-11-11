package com.lp2.dto.lance;

import com.lp2.model.Lance;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoLanceProdutoDTO {

    private String produto;
    private List<DadosExibicaoLanceDTO> lances;

    public DadosExibicaoLanceProdutoDTO(){}

    public DadosExibicaoLanceProdutoDTO(List<Lance> lances){
        this.lances = lances.stream().map(lance -> new DadosExibicaoLanceDTO(lance)).toList();
    }

}
