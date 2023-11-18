package com.lp2.dto.leilao;

import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.dto.lance.DadosExibicaoLanceVencedorDTO;
import com.lp2.enums.StatusLeilao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO {

    private Long id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private String local;
    private StatusLeilao statusLeilao;
    private List<DadosExibicaoLanceVencedorDTO> lancesVencedores;
    private List<DadosExibicaoEntidadeFinanceiraDTO> entidades;

    public DadosExibicaoDadosDetalhadosLeilaoFinalizadoDTO() {
    }
}
