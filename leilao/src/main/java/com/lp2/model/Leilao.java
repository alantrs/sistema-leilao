package com.lp2.model;

import com.lp2.dto.leilao.DadosAtualizacaoLeilaoDTO;
import com.lp2.dto.leilao.DadosEntradaLeilaoDTO;
import com.lp2.enums.StatusLeilao;
import com.lp2.util.CalculoStatusLeilao;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Serdeable
public class Leilao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private String local;
    private StatusLeilao statusLeilao;

    @OneToMany(mappedBy = "leilao", fetch = FetchType.EAGER)
    private List<DispositivoInformatica> dispositivos;

    @OneToMany(mappedBy = "leilao", fetch = FetchType.EAGER)
    private List<Veiculo> veiculos;

    @ManyToMany
    @JoinTable(
            name = "Leilao_EntidadeFinanceira",
            joinColumns = @JoinColumn(name = "leilao_id"),
            inverseJoinColumns = @JoinColumn(name = "entidade_financeira_id")
    )
    private List<EntidadeFinanceira> entidadesFinanceira;

    public Leilao(){}

    public Leilao(DadosEntradaLeilaoDTO cadastro){
        this.dataOcorrencia = cadastro.getDataOcorrencia();
        this.dataEncerramento = cadastro.getDataEncerramento();
        this.dataVisitacao = cadastro.getDataVisitacao();
        this.local =cadastro.getLocal();
        this.statusLeilao = CalculoStatusLeilao.calcularStatusLeilao(LocalDateTime.now(), this);
    }

    public Leilao(Leilao leilao, DadosAtualizacaoLeilaoDTO atualizacaoLeilaoDTO){
        this.id = leilao.getId();
        this.dataOcorrencia = atualizacaoLeilaoDTO.getDataOcorrencia() != null ? atualizacaoLeilaoDTO.getDataOcorrencia() : leilao.getDataOcorrencia();
        this.dataEncerramento = atualizacaoLeilaoDTO.getDataEncerramento() != null ? atualizacaoLeilaoDTO.getDataEncerramento() : leilao.getDataEncerramento();
        this.dataVisitacao = atualizacaoLeilaoDTO.getDataVisitacao() != null ? atualizacaoLeilaoDTO.getDataVisitacao() : leilao.getDataVisitacao();
        this.local = atualizacaoLeilaoDTO.getLocal() != null ? atualizacaoLeilaoDTO.getLocal() : leilao.getLocal();
        this.entidadesFinanceira = leilao.getEntidadesFinanceira();
        this.statusLeilao = leilao.getStatusLeilao();
    }
}
