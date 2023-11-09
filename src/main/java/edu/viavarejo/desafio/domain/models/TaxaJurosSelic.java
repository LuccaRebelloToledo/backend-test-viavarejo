package edu.viavarejo.desafio.domain.models;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxaJurosSelic {
	
	private Date data;
	private BigDecimal valor;
	
}
