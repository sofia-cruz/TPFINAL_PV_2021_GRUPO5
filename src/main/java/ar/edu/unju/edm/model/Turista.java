package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table (name="TURISTAS")
@Component
public class Turista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
private Integer idTurista;
	@Column
private String nombre;
	@Column
private String apellido;
	@Column
private String paisProcedencia;
//cuenta de google (como va? 


public Turista() {
	//constructor
}


public Integer getIdTurista() {
	return idTurista;
}


public void setIdTurista(Integer idTurista) {
	this.idTurista = idTurista;
}


public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public String getApellido() {
	return apellido;
}


public void setApellido(String apellido) {
	this.apellido = apellido;
}


public String getPaisProcedencia() {
	return paisProcedencia;
}
public void setPaisProcedencia(String paisProcedencia) {
	this.paisProcedencia = paisProcedencia;
}



}
