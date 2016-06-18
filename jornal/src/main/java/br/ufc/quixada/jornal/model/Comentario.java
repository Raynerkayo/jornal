package br.ufc.quixada.jornal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Texto não pode ser vazio")
	private String texto;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", referencedColumnName="id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_noticia", referencedColumnName="id")
	private Noticia noticia;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	
}
