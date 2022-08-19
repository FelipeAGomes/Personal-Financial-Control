package uk.co.alurachallengerbe.dto;

public class RelatorioDTO {

	private Double totalDespesa;
	private Double totalReceita;
	private Double totalAlimentacao;
	private Double totalSaude;
	private Double totalMoradia;
	private Double totalTransporte;
	private Double totalEducacao;
	private Double totalLazer;
	private Double totalImprevistos;
	private Double totalOutras;
	public RelatorioDTO(Double totalDespesa, Double totalReceita, Double totalAlimentacao, Double totalSaude,
			Double totalMoradia, Double totalTransporte, Double totalEducacao, Double totalLazer,
			Double totalImprevistos, Double totalOutras) {
		super();
		this.totalDespesa = totalDespesa;
		this.totalReceita = totalReceita;
		this.totalAlimentacao = totalAlimentacao;
		this.totalSaude = totalSaude;
		this.totalMoradia = totalMoradia;
		this.totalTransporte = totalTransporte;
		this.totalEducacao = totalEducacao;
		this.totalLazer = totalLazer;
		this.totalImprevistos = totalImprevistos;
		this.totalOutras = totalOutras;
	}
	
	public Double getTotalDespesa() {
		return totalDespesa;
	}
	public Double getTotalReceita() {
		return totalReceita;
	}
	public Double getTotalAlimentacao() {
		return totalAlimentacao;
	}
	public Double getTotalSaude() {
		return totalSaude;
	}
	public Double getTotalMoradia() {
		return totalMoradia;
	}
	public Double getTotalTransporte() {
		return totalTransporte;
	}
	public Double getTotalEducacao() {
		return totalEducacao;
	}
	public Double getTotalLazer() {
		return totalLazer;
	}
	public Double getTotalImprevistos() {
		return totalImprevistos;
	}
	public Double getTotalOutras() {
		return totalOutras;
	}
	
	public Double getSaldoMes() {
		return totalReceita - totalDespesa;
	}
}
