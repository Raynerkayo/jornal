package br.ufc.quixada.jornal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "papel")
public class Papel {

	@Id
	@GeneratedValue
	@Column(name = "id_papel", nullable = false)
	private Long id;

	@NotEmpty(message = "Este campo não pode ficar em branco!")
	@Column(name = "papel_nome")
	private String papelNome; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPapelNome() {
		return papelNome;
	}

	public void setPapelNome(String papelNome) {
		this.papelNome = papelNome;
	}

	 
	
}
