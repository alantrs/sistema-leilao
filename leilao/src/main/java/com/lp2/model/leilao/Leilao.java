package com.lp2.model.leilao;

import com.lp2.model.dispositivo.DispositivoInformatica;
import com.lp2.model.enums.StatusLeilao;
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

    @OneToMany(mappedBy = "leilao")
    private List<DispositivoInformatica> dispositivos;

    @OneToMany(mappedBy = "leilao")
    private List<Veiculo> veiculos;

    public Leilao(){}

    public Leilao(DadosEntradaLeilaoDTO cadastro){
        this.dataOcorrencia = cadastro.getDataOcorrencia();
        this.dataEncerramento = cadastro.getDataEncerramento();
        this.dataVisitacao = cadastro.getDataVisitacao();
        this.valorMinimo = cadastro.getValorMinimo();
        this.local =cadastro.getLocal();
        this.statusLeilao = StatusLeilao.EM_ABERTO;
    }
}
