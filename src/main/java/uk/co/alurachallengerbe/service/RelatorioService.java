package uk.co.alurachallengerbe.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uk.co.alurachallengerbe.dto.RelatorioDTO;
import uk.co.alurachallengerbe.entities.Despesa;
import uk.co.alurachallengerbe.entities.Receita;
import uk.co.alurachallengerbe.entities.enums.Categoria;
import uk.co.alurachallengerbe.repositories.DespesaRepository;
import uk.co.alurachallengerbe.repositories.ReceitaRepository;

@Service
public class RelatorioService {

	@Autowired
	public DespesaRepository despesaRepository;

	@Autowired
	public ReceitaRepository receitaRepository;

	public RelatorioDTO getRelatorio(int year, int month, Pageable paginacao) {

		List<Despesa> despesaList = despesaRepository.findByDataGreaterThanEqualAndDataLessThan(
				LocalDate.of(year, month, 1), LocalDate.of(year, month + 1, 1));
		List<Receita> receitaList = receitaRepository.findByDataGreaterThanEqualAndDataLessThan(
				LocalDate.of(year, month, 1), LocalDate.of(year, month + 1, 1));

		// ALIMENTACAO
		double TotalAlimentacao = despesaList.stream().filter(d -> {
			try {
				return d.getCategoria().equals(Categoria.ALIMENTACAO);
			} catch (IllegalAccessException e) {

				return false;
			}
		}).map(d -> d.getValor()).reduce(.0, (alimentacao, despesa) -> alimentacao + despesa);

		// SAUDE
		double TotalSaude = despesaList.stream().filter(d -> {
			try {
				return d.getCategoria().equals(Categoria.SAUDE);
			} catch (IllegalAccessException e) {

				return false;
			}
		}).map(d -> d.getValor()).reduce(.0, (saude, despesa) -> saude + despesa);

		// MORADIA
		double TotalMoradia = despesaList.stream().filter(d -> {
			try {
				return d.getCategoria().equals(Categoria.MORADIA);
			} catch (IllegalAccessException e) {

				return false;
			}
		}).map(d -> d.getValor()).reduce(.0, (moradia, despesa) -> moradia + despesa);

		// TRANSPORTE
		double TotalTransporte = despesaList.stream().filter(d -> {
			try {
				return d.getCategoria().equals(Categoria.TRANSPORTE);
			} catch (IllegalAccessException e) {

				return false;
			}
		}).map(d -> d.getValor()).reduce(.0, (transporte, despesa) -> transporte + despesa);

		// EDUCACAO
		double TotalEducacao = despesaList.stream().filter(d -> {
			try {
				return d.getCategoria().equals(Categoria.EDUCACAO);
			} catch (IllegalAccessException e) {

				return false;
			}
		}).map(d -> d.getValor()).reduce(.0, (educacao, despesa) -> educacao + despesa);

		// LAZER
		double TotalLazer = despesaList.stream().filter(d -> {
			try {
				return d.getCategoria().equals(Categoria.LAZER);
			} catch (IllegalAccessException e) {

				return false;
			}
		}).map(d -> d.getValor()).reduce(.0, (lazer, despesa) -> lazer + despesa);

		// IMPREVISTOS
		double TotalImprevistos = despesaList.stream().filter(d -> {
			try {
				return d.getCategoria().equals(Categoria.IMPREVISTOS);
			} catch (IllegalAccessException e) {

				return false;
			}
		}).map(d -> d.getValor()).reduce(.0, (imprevistos, despesa) -> imprevistos + despesa);

		// OUTRAS
		double TotalOutras = despesaList.stream().filter(d -> {
			try {
				return d.getCategoria().equals(Categoria.OUTRAS);
			} catch (IllegalAccessException e) {

				return false;
			}
		}).map(d -> d.getValor()).reduce(.0, (outras, despesa) -> outras + despesa);

		// stream para receber um por um (dados)/ filter pra filtar pela categoria/ map
		// para transformar em valor
		// reduce acumulador, para somar os valores

		// TOTAL DESPESA
		double TotalDespesa = despesaList.stream().map(d -> d.getValor()).reduce(.0,
				(totalD, despesa) -> totalD + despesa);

		// TOTAL RECEITA
		double TotalReceita = receitaList.stream().map(r -> r.getValor()).reduce(.0,
				(totalR, receita) -> totalR + receita);

		RelatorioDTO relatoriodto = new RelatorioDTO(TotalDespesa, TotalReceita, TotalAlimentacao, TotalSaude,
				TotalMoradia, TotalTransporte, TotalEducacao, TotalLazer, TotalImprevistos, TotalOutras);

		return relatoriodto;
	}
}
