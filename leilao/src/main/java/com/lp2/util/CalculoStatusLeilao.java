package com.lp2.util;

import com.lp2.enums.StatusLeilao;
import com.lp2.model.Leilao;

import java.time.LocalDateTime;

public class CalculoStatusLeilao {

    public static StatusLeilao calcularStatusLeilao(LocalDateTime agora, Leilao leilao) {
        if (agora.isBefore(leilao.getDataOcorrencia())) {
            leilao.setStatusLeilao(StatusLeilao.EM_ABERTO);
            return StatusLeilao.EM_ABERTO;
        } else if (agora.isAfter(leilao.getDataOcorrencia()) && agora.isBefore(leilao.getDataEncerramento())) {
            leilao.setStatusLeilao(StatusLeilao.EM_ANDAMENTO);
            return StatusLeilao.EM_ANDAMENTO;
        } else if (agora.isAfter(leilao.getDataEncerramento())) {
            leilao.setStatusLeilao(StatusLeilao.FINALIZADO);
            return StatusLeilao.FINALIZADO;
        }
        return null;
    }
}
