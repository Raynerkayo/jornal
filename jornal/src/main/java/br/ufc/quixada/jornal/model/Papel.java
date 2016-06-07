package br.ufc.quixada.jornal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Papel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_papel", nullable = false)
	private Long id;

	@NotEmpty(message = "Escolha um papel para o usuário")
	private String papel;

	@ManyToMany(mappedBy = "papeis")
	private List<Usuario> usuarios;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

}
