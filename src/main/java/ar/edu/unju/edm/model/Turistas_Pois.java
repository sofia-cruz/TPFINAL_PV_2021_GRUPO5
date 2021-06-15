package ar.edu.unju.edm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="turistas_pois")
public class Turistas_Pois {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column
	private Integer idTuristas_Pois;
	//revisar si es ManyToOne o OneToMany,
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "eMail")
	private Turista turista;
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name = "codigoPoi")
	private PoI poi;
	@Column
	private Integer valoracion;
	@Column
	private String comentario;
	
	
	
	
	
	public Turistas_Pois() {
		
	}
	public Integer getIdTuristas_Pois() {
		return idTuristas_Pois;
	}
	public void setIdTuristas_Pois(Integer idTuristas_Pois) {
		this.idTuristas_Pois = idTuristas_Pois;
	}
	public Turista getTurista() {
		return turista;
	}
	public void setTurista(Turista turista) {
		this.turista = turista;
	}
	public PoI getPoi() {
		return poi;
	}
	public void setPoi(PoI poi) {
		this.poi = poi;
	}
	public Integer getValoracion() {
		return valoracion;
	}
	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
	
}
