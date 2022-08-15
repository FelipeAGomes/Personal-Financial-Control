package uk.co.alurachallengerbe.entities.enums;

public enum Categoria {

	ALIMENTACAO(1),
	SAUDE(2),
	MORADIA(3),
	TRANSPORTE(4),
	EDUCACAO(5),
	LAZER(6),
	IMPREVISTOS(7),
	OUTRAS(8);
	
	private int code;
	
	private Categoria(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	//Para retornar o valor do codigo // Fazendo um looping para checar os codigos e retornar um valor
	public static Categoria valueOf(int code) throws IllegalAccessException{
		for(Categoria value : Categoria.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalAccessException("Categoria Invalida");
	}
}
