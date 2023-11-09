package edu.viavarejo.desafio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.viavarejo.desafio.domain.models.Parcela;
import edu.viavarejo.desafio.domain.models.Produto;
import edu.viavarejo.desafio.exceptions.ProdutoException;
import edu.viavarejo.desafio.services.ParcelaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/calcular")
@Tag(name = "Parcela", description = "Endpoint para simular a compra de um produto e retornar uma lista de parcelas com ou sem taxa de juros.")
public class ParcelaController {

	@Autowired
	private ParcelaService parcelaService;

	@GetMapping
	public ResponseEntity<?> calcularParcelas(@RequestBody Produto produto) throws ProdutoException {
		
		List<Parcela> parcelas = parcelaService.calcularParcelas(produto);
		return ResponseEntity.ok().body(parcelas);
		
	}

}
