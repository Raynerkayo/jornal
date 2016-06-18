package br.ufc.quixada.jornal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "noticia")
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "O titulo é obrigatório")
	@Column(nullable = false)
	private String titulo;
	
	@NotEmpty(message = "O subtitulo é obrigatório")
	@Column(name="sub_titulo", nullable = false)
	private String subTitulo;
	
	@NotEmpty(message = "O texto é obrigatório")
	@Column(nullable = false)
	@Lob
	private String texto;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", referencedColumnName="id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "noticia", targetEntity = Comentario.class)
	private List<Comentario> comentarios;
	
	@NotNull(message="Data de vencimento é obrigatória.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNoticia;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn ( name =  "id_secao" ) 
	private Secao secao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataNoticia() {
		return dataNoticia;
	}

	public void setDataNoticia(Date dataNoticia) {
		this.dataNoticia = dataNoticia;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
}
	