package edu.viavarejo.desafio.domain.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CondicaoPagamento {
	
	private BigDecimal valorEntrada;
	private Integer qtdeParcelas;

}
