package edu.viavarejo.desafio.services;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.viavarejo.desafio.domain.models.CondicaoPagamento;
import edu.viavarejo.desafio.domain.models.Parcela;
import edu.viavarejo.desafio.domain.models.Produto;
import edu.viavarejo.desafio.exceptions.ProdutoException;
import edu.viavarejo.desafio.utils.ProdutoUtil;

@Service
public class ParcelaService {

	private final Integer CONDICAO_PARCELA_TAXA = 6;

	@Autowired
	private TaxaJurosService taxaJurosService;

	public List<Parcela> calcularParcelas(Produto produto) throws ProdutoException {
		
		try {
			if (ProdutoUtil.validarProduto(produto)) {
				BigDecimal taxaJuros = produto.getCondicaoPagamento().getQtdeParcelas() > CONDICAO_PARCELA_TAXA
						? taxaJurosService.retornarTaxaJurosSelic().getValor()
						: new BigDecimal(0);
				BigDecimal valorParcela = calcularValorParcela(produto.getCondicaoPagamento().getValorEntrada(),
						produto.getCondicaoPagamento().getQtdeParcelas(), taxaJuros);
				return gerarParcelas(produto.getCondicaoPagamento(), taxaJuros, valorParcela, CONDICAO_PARCELA_TAXA);
			}
		} catch (ProdutoException e) {
			throw new ProdutoException(e.getMessage());
		}
		return null;
	}

	private BigDecimal calcularValorParcela(BigDecimal valorEntrada, Integer qtdeParcelas, BigDecimal taxaJuros) {

		BigDecimal valorComJuros = valorEntrada.add(valorEntrada.multiply(taxaJuros));
		BigDecimal valorParcela = valorComJuros.divide(BigDecimal.valueOf(qtdeParcelas), 2, RoundingMode.HALF_UP);
		return valorParcela;

	}

	private List<Parcela> gerarParcelas(CondicaoPagamento condicaoPagamento, BigDecimal taxaJuros,
			BigDecimal valorParcela, Integer condicaoParcelaTaxa) {

		List<Parcela> parcelas = new ArrayList<Parcela>();

		Integer qtdeParcelas = condicaoPagamento.getQtdeParcelas();
		if (qtdeParcelas > condicaoParcelaTaxa) {
			for (int i = 0; i < qtdeParcelas; i++) {
				parcelas.add(new Parcela((i + 1), valorParcela, taxaJuros));
			}
		} else {
			for (int i = 0; i < qtdeParcelas - 1; i++) {
				parcelas.add(new Parcela((i + 1), valorParcela, taxaJuros));
			}
			BigDecimal valorSubtrairUltimaParcela = valorParcela.multiply(new BigDecimal(qtdeParcelas))
					.subtract(condicaoPagamento.getValorEntrada());
			parcelas.add(new Parcela(qtdeParcelas, valorParcela.subtract(valorSubtrairUltimaParcela), taxaJuros));
		}
		return parcelas;
	}

}
