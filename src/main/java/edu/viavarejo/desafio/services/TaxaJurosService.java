package edu.viavarejo.desafio.services;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.viavarejo.desafio.deserializer.CustomDateDeserializer;
import edu.viavarejo.desafio.domain.models.TaxaJurosSelic;
import edu.viavarejo.desafio.exceptions.TaxaJurosNotFoundException;

@Service
public class TaxaJurosService {

	public TaxaJurosSelic retornarTaxaJurosSelic() {

		String resourceUrl = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados/ultimos/1?formato=json";
		HttpClient httpClient = HttpClient.newBuilder().build();
		HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(resourceUrl)).build();
		try {
			HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

			Type taxaListType = new TypeToken<List<TaxaJurosSelic>>() {
			}.getType();

			Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new CustomDateDeserializer()).create();
			List<TaxaJurosSelic> taxaJurosList = gson.fromJson(httpResponse.body(), taxaListType);

			if (!taxaJurosList.isEmpty()) {
				TaxaJurosSelic taxaJuros = taxaJurosList.get(0);
				return taxaJuros;
			} else {
				throw new TaxaJurosNotFoundException("Taxa de juros não encontrada!");
			}
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage() + " " + e.getCause());
		} catch (TaxaJurosNotFoundException e) {
			System.out.println(e.getMessage() + " " + e.getCause());
		}
		return null;
	}
}
