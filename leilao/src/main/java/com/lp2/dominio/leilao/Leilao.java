package com.lp2.dominio.leilao;

import com.lp2.dominio.dispositivo.DispositivoInformatica;
import com.lp2.dominio.enums.StatusLeilao;
import com.lp2.dominio.veiculo.Veiculo;
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



}
