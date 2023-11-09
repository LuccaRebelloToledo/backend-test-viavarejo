package edu.viavarejo.desafio.utils;

import edu.viavarejo.desafio.domain.models.CondicaoPagamento;
import edu.viavarejo.desafio.domain.models.Produto;
import edu.viavarejo.desafio.exceptions.ProdutoException;
import lombok.experimental.UtilityClass;

import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class ProdutoUtil {

    private static final Double MIN_VALOR_ENTRADA = 0D;
    private static final Integer MIN_QTDE_PARCELAS = 0;
    private static final Long MIN_CODIGO = 0L;

    public boolean validarProduto(Produto produto) throws ProdutoException {
        if (produto == null) {
            throw new ProdutoException("Produto inexistente!");
        }
        validarCodigo(produto.getCodigo());
        validarNome(produto.getNome());
        validarCondicaoPagamento(produto.getCondicaoPagamento());
		return true;
    }

    private void validarCodigo(Long codigo) throws ProdutoException {
        if (codigo == null || codigo <= MIN_CODIGO) {
            throw new ProdutoException("Código do produto inexistente ou inválido!");
        }
    }

    private void validarNome(String nome) throws ProdutoException {
        if (StringUtils.isEmpty(nome)) {
            throw new ProdutoException("Nome do produto inexistente ou inválido!");
        }
    }

    private void validarCondicaoPagamento(CondicaoPagamento condicaoPagamento) throws ProdutoException {
        if (condicaoPagamento == null ||
            condicaoPagamento.getValorEntrada() == null ||
            condicaoPagamento.getValorEntrada().doubleValue() <= MIN_VALOR_ENTRADA ||
            condicaoPagamento.getQtdeParcelas() == null ||
            condicaoPagamento.getQtdeParcelas() <= MIN_QTDE_PARCELAS) {
            throw new ProdutoException("Condição de Pagamento do produto inexistente ou inválido!");
        }
    }
}
