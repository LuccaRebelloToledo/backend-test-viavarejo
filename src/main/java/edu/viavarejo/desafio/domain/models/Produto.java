package edu.viavarejo.desafio.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "codigo")
public class Produto {
	
	private Long codigo;
	private String nome;
	private CondicaoPagamento condicaoPagamento;
	
}
