package ar.edu.unju.edm.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
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
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "eMail")
	private Turista turista;
	@Column
	private String tur;
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigoPoi")
	private PoI poi;
	@Column
	private Integer valoracion_user;
	@Column
	private String comentario;
	
	@Column
	private LocalTime tiempo;
	@Column
	private LocalDate date;
	
	public LocalTime getTiempo() {
		return tiempo;
	}
	public void setTiempo(LocalTime tiempo) {
		this.tiempo = tiempo;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTur() {
		return tur;
	}

	
	public void setTur(String tur) {
		this.tur = tur;
	}
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
	
	public Integer getValoracion_user() {
		return valoracion_user;
	}
	public void setValoracion_user(Integer valoracion_user) {
		this.valoracion_user = valoracion_user;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
	
}
