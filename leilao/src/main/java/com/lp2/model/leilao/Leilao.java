package com.lp2.model.leilao;

import com.lp2.model.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.model.dispositivo.DispositivoInformatica;
import com.lp2.model.entidadeFinanceira.EntidadeFinanceira;
import com.lp2.model.enums.StatusLeilao;
import com.lp2.model.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.model.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataEncerramento;
    private LocalDateTime dataVisitacao;
    private BigDecimal valorMinimo;
    private String local;
    private StatusLeilao statusLeilao;

    @OneToMany(mappedBy = "leilao", fetch = FetchType.EAGER)
    private List<DispositivoInformatica> dispositivos;

    @OneToMany(mappedBy = "leilao", fetch = FetchType.EAGER)
    private List<Veiculo> veiculos;
    @ManyToOne
    @JoinColumn(name = "id_entidade")
    private EntidadeFinanceira entidadeFinanceira;

    public Leilao(){}

    public Leilao(DadosEntradaLeilaoDTO cadastro){
        this.dataOcorrencia = cadastro.getDataOcorrencia();
        this.dataEncerramento = cadastro.getDataEncerramento();
        this.dataVisitacao = cadastro.getDataVisitacao();
        this.valorMinimo = cadastro.getValorMinimo();
        this.local =cadastro.getLocal();
        this.statusLeilao = StatusLeilao.EM_ABERTO;
    }

    public Leilao(Leilao leilao, DadosAtualizacaoLeilaoDTO atualizacaoLeilaoDTO){
        this.id = leilao.getId();
        this.dataOcorrencia = atualizacaoLeilaoDTO.getDataOcorrencia() != null ? atualizacaoLeilaoDTO.getDataOcorrencia() : leilao.getDataOcorrencia();
        this.dataEncerramento = atualizacaoLeilaoDTO.getDataEncerramento() != null ? atualizacaoLeilaoDTO.getDataEncerramento() : leilao.getDataEncerramento();
        this.dataVisitacao = atualizacaoLeilaoDTO.getDataVisitacao() != null ? atualizacaoLeilaoDTO.getDataVisitacao() : leilao.getDataVisitacao();
        this.valorMinimo = atualizacaoLeilaoDTO.getValorMinimo() != null ? atualizacaoLeilaoDTO.getValorMinimo() : leilao.getValorMinimo();
        this.local = atualizacaoLeilaoDTO.getLocal() != null ? atualizacaoLeilaoDTO.getLocal() : leilao.getLocal();
    }
}
