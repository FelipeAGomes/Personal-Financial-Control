package uk.co.alurachallengerbe.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.alurachallengerbe.entities.enums.Categoria;

@Entity
@Table(name = "tb_despesa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Double valor;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
	private LocalDate data;

	private Integer categoria;

	public Despesa() {

	}

	public Despesa(Long id, String descricao, Double valor, LocalDate data, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		setCategoria(categoria);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Categoria getCategoria() throws IllegalAccessException {
		if (categoria != null) {
			return Categoria.valueOf(categoria);
		}
		return null;
	}

	public void setCategoria(Categoria categoria) {
		if (categoria != null) {
			this.categoria = categoria.getCode();
		}
	}
}
