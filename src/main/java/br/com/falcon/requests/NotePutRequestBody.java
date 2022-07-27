package br.com.falcon.requests;

import lombok.Data;

@Data
public class NotePutRequestBody {
    
	private Long id;
	
	private String descricao;
    private String categoria;
    private double preco;
    private int quantidade;
    private String processador;
    private int memoriaRam;
    private int capacidadeSSD;
}
