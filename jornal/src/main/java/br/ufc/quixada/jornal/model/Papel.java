package br.ufc.quixada.jornal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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

	@ManyToMany(mappedBy = "papeis")
	private List<Usuario> usuarios;

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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
