package br.ufc.quixada.jornal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "O nome é obrigatório")
	@Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;

	@NotEmpty(message = "O login é obrigatório")
	@Column(nullable = false)
	private String login;

	@NotEmpty(message = "A senha é obrigatória")
	@Column(nullable = false)
	private String senha;

	@NotEmpty(message = "O email é obrigatório")
	@Column(nullable = false)
	private String email;

	// mapeando os comentários a partir do atributo usuário na classe comentário
	@OneToMany(mappedBy = "usuario", targetEntity = Comentario.class)
	private List<Comentario> comentarios;

	@OneToMany(mappedBy = "usuario", targetEntity = Noticia.class)
	private List<Noticia> noticias;

	@OneToMany(mappedBy = "usuario", targetEntity = Classificado.class)
	private List<Classificado> classificados;

	@ManyToMany // pega o id_papel da table papel
	@JoinTable(name = "papel_usuario", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_papel", referencedColumnName = "id_papel"))
	private List<Papel> papeis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public List<Classificado> getClassificados() {
		return classificados;
	}

	public void setClassificados(List<Classificado> classificados) {
		this.classificados = classificados;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(Papel papel) {
		if (this.papeis == null) {
			this.papeis = new ArrayList<>();
		}
		this.papeis.add(papel);
	}

}
