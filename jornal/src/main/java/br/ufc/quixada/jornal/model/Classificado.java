package br.ufc.quixada.jornal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Classificado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Escreva algum título")
	@Column(nullable = false)
	private String titulo;

	@NotEmpty(message = "O texto é obrigatório")
	@Column(nullable = false)
	@Lob
	private String texto;
	
	@NotNull(message= "Informe o preço")
	@DecimalMin(value="0.00", message="Valor não pode ser menor que R$ 0,00")
	@DecimalMax(value="99999999.99", message="Estourou o limite")
	@NumberFormat(pattern = "#,##0.00")
	private float preco; 
	
	@NotEmpty(message = "O telefone é obrigatório")
	@Column(nullable = false)
	private String telefone;
	
	@NotNull(message= "Dê uma oferta")
	@DecimalMin(value="0.00", message="Valor não pode ser menor que R$ 0,00")
	@DecimalMax(value="99999999.99", message="Estourou o limite")
	@NumberFormat(pattern = "#,##0.00")
	private float melhorOferta;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataOferta;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", referencedColumnName="id")
	private Usuario usuario;	 

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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public float getMelhorOferta() {
		return melhorOferta;
	}

	public void setMelhorOferta(float melhorOferta) {
		this.melhorOferta = melhorOferta;
	}

	public Date getDataOferta() {
		return dataOferta;
	}

	public void setDataOferta(Date dataOferta) {
		this.dataOferta = dataOferta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
