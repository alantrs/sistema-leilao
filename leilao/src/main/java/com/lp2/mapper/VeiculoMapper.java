package com.lp2.mapper;

import com.lp2.dto.veiculo.DadosExibicaoCaminhaoDTO;
import com.lp2.dto.veiculo.DadosExibicaoCarroDTO;
import com.lp2.dto.veiculo.DadosExibicaoMotocicletaDTO;
import com.lp2.dto.veiculo.DadosExibicaoUtilitarioDTO;
import com.lp2.model.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class VeiculoMapper {
    private final ModelMapper modelMapper;
    private final Map<Class<? extends Veiculo>, Class<?>> veiculoDto;

    public VeiculoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.veiculoDto = Map.of(
                Carro.class, DadosExibicaoCarroDTO.class,
                Caminhao.class, DadosExibicaoCaminhaoDTO.class,
                Utilitario.class, DadosExibicaoUtilitarioDTO.class,
                Motocicleta.class, DadosExibicaoMotocicletaDTO.class
        );
    }

    public Object mapearVeiculoParaDTO(Veiculo veiculo) {
        Class<?> dtoClass = veiculoDto.get(veiculo.getClass());

        if (dtoClass != null) {
            return modelMapper.map(veiculo, dtoClass);
        }

        return null;
    }
}

