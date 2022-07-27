package br.com.falcon.domain;


import javax.persistence.MappedSuperclass;

import lombok.Data;



@Data
@MappedSuperclass
public abstract class Equipamento {
		protected String nome;
		protected double valor;
	
}
