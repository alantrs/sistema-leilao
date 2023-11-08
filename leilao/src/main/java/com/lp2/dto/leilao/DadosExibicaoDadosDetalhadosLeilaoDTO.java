package com.lp2.dto.leilao;

import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.dto.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.dto.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.enums.StatusLeilao;
import com.lp2.model.Leilao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoDadosDetalhadosLeilaoDTO {

    private Long id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private BigDecimal valorMinimo;
    private String local;
    private StatusLeilao statusLeilao;
    private List<DadosExibicaoDispositivoDTO> dispositivos;
    private List<DadosExibicaoVeiculoDTO> veiculos;
    private List<DadosExibicaoEntidadeFinanceiraDTO> entidades;
    ModelMapper modelMapper = new ModelMapper();

    public DadosExibicaoDadosDetalhadosLeilaoDTO(){}
    public DadosExibicaoDadosDetalhadosLeilaoDTO(Leilao leilao){
        this.id = leilao.getId();
        this.dataOcorrencia = leilao.getDataOcorrencia();
        this.dataEncerramento = leilao.getDataEncerramento();
        this.dataVisitacao = leilao.getDataVisitacao();
        this.valorMinimo = leilao.getValorMinimo();
        this.local = leilao.getLocal();
        this.statusLeilao = leilao.getStatusLeilao();
        if(!leilao.getDispositivos().isEmpty()){
            this.dispositivos = leilao.getDispositivos().stream().map(dispositivo -> modelMapper.map(dispositivo, DadosExibicaoDispositivoDTO.class)).toList();
        }
        if (!leilao.getVeiculos().isEmpty()){
            this.veiculos = leilao.getVeiculos().stream().map(veiculo -> modelMapper.map(veiculo, DadosExibicaoVeiculoDTO.class)).toList();
        }
        this.entidades = leilao.getEntidadesFinanceira().stream().map(entidade -> modelMapper.map(entidade, DadosExibicaoEntidadeFinanceiraDTO.class)).toList();
    }
}
