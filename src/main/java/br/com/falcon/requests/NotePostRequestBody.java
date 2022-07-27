package br.com.falcon.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@Data
public class NotePostRequestBody {
	@NotEmpty(message = "descricao not empty")
	@NotNull (message = "descricao not null")
	private String descricao;
    private String categoria;
    private double preco;
    private int quantidade;
    @NotEmpty(message = "processador not empty")
	@NotNull (message = "processador not null")
    private String processador;
    private int memoriaRam;
    private int capacidadeSSD;
}
