package com.lp2.mapper;

import com.lp2.dto.dispositivo.*;
import com.lp2.model.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DispositivoMapper {
    private final ModelMapper modelMapper;
    private final Map<Class<? extends DispositivoInformatica>, Class<?>> dispositivoToDtoMap;

    public DispositivoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.dispositivoToDtoMap = Map.of(
                Notebook.class, DadosExibicaoNotebookDTO.class,
                Hub.class, DadosExibicaoHubDTO.class,
                Roteador.class, DadosExibicaoRoteadorDTO.class,
                Monitor.class, DadosExibicaoMonitorDTO.class,
                Switch.class, DadosExibicaoSwitchDTO.class
        );
    }

    public Object mapearDispositivosParaDTO(DispositivoInformatica dispositivo) {
        Class<?> dtoClass = dispositivoToDtoMap.get(dispositivo.getClass());

        if (dtoClass != null) {
            return modelMapper.map(dispositivo, dtoClass);
        }

        return null;
    }
}
