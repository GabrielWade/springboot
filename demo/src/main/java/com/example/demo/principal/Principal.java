package com.example.demo.principal;

import com.example.demo.model.DadosEpisodio;
import com.example.demo.model.DadosSerie;
import com.example.demo.model.DadosTemporada;
import com.example.demo.service.ConsumoApi;
import com.example.demo.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c3683c51";

    public void exibMenu(){
        System.out.println("Digite o nome da série para a busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
        temporadas.forEach(System.out::println);

//        for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodios = temporadas.get(i).episodios();
//            for (int j = 0; j < episodios.size(); j++) {
//                System.out.println(episodios.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}