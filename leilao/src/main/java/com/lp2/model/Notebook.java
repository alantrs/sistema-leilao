package com.lp2.model;

import com.lp2.dto.dispositivo.DadosAtualizacaoNotebookDTO;
import com.lp2.dto.dispositivo.DadosEntradaNotebookDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Notebook extends DispositivoInformatica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;
    private String tamanhoTela;

    public Notebook(){}
    public Notebook(DadosEntradaNotebookDTO cadastroNotebook){
        super(cadastroNotebook);
        this.tamanhoTela = cadastroNotebook.getTamanhoTela();
    }

    public Notebook(Notebook notebook, DadosAtualizacaoNotebookDTO atualizacaoNotebook){
        super(notebook, atualizacaoNotebook);
        this.id = notebook.getId();
        this.tamanhoTela = atualizacaoNotebook.getTamanhoTela() != null ? atualizacaoNotebook.getTamanhoTela() : notebook.getTamanhoTela();
    }

}
