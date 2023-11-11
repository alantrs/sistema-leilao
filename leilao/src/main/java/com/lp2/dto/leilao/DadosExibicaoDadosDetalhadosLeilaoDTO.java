package com.lp2.dto.leilao;

import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.dto.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.dto.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.enums.StatusLeilao;
import com.lp2.model.DispositivoInformatica;
import com.lp2.model.Leilao;
import com.lp2.model.Veiculo;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
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
    private Integer quantidadeProdutos = 0;
    private List<DadosExibicaoDispositivoDTO> dispositivos;
    private List<DadosExibicaoVeiculoDTO> veiculos;
    private List<DadosExibicaoEntidadeFinanceiraDTO> entidades;
    public DadosExibicaoDadosDetalhadosLeilaoDTO(){}
    public DadosExibicaoDadosDetalhadosLeilaoDTO(Leilao leilao){
        this.id = leilao.getId();
        this.dataOcorrencia = leilao.getDataOcorrencia();
        this.dataEncerramento = leilao.getDataEncerramento();
        this.dataVisitacao = leilao.getDataVisitacao();
        this.local = leilao.getLocal();
        this.statusLeilao = leilao.getStatusLeilao();
        if (!leilao.getDispositivos().isEmpty()) {
            this.dispositivos = leilao.getDispositivos().stream()
                    .sorted(Comparator.comparing(DispositivoInformatica::getNome))
                    .map(dispositivo -> new DadosExibicaoDispositivoDTO(dispositivo))
                    .toList();
            quantidadeProdutos += this.dispositivos.size();
        }

        if (!leilao.getVeiculos().isEmpty()) {
            this.veiculos = leilao.getVeiculos().stream()
                    .sorted(Comparator.comparing(Veiculo::getModelo))
                    .map(veiculo -> new DadosExibicaoVeiculoDTO(veiculo))
                    .toList();
            quantidadeProdutos += this.veiculos.size();
        }

        this.entidades = leilao.getEntidadesFinanceira().stream()
                .map(entidade -> new DadosExibicaoEntidadeFinanceiraDTO(entidade))
                .toList();
        }
}
