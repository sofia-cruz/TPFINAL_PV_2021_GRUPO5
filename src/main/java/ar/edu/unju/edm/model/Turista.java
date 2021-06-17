
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
private String email;
	@Column
private String nombre;
	@Column
private String apellido;
	@Column
private String paisProcedencia; 
	@Column
private Double latitud;
	@Column
private Double longitud;
	@Column
private String password; 
	@Column
private Integer puntos;
	@Column
private String rol;
	//agregados password y puntos
public Turista() {
	//constructor
}

public Integer getIdTurista() {
	return idTurista;
}

public void setIdTurista(Integer idTurista) {
	this.idTurista = idTurista;
}


public String getRol() {
	return rol;
}

public void setRol(String rol) {
	this.rol = rol;
}

public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}

public Double getLatitud() {
	return latitud;
}

public void setLatitud(Double latitud) {
	this.latitud = latitud;
}

public Double getLongitud() {
	return longitud;
}

public void setLongitud(Double longitud) {
	this.longitud = longitud;
}

public String getApellido() {
	return apellido;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Integer getPuntos() {
	return puntos;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public void setPuntos(Integer puntos) {
	this.puntos = puntos;
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
