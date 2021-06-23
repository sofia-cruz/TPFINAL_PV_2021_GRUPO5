
package ar.edu.unju.edm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

@Entity
@Table (name="TURISTAS")
@Component
public class Turista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
private Integer idTurista;
	@NotBlank(message="Ingrese un correo electronico valido")
	@Column
private String email;
	@NotBlank(message="Debe ingresar su nombre de usuario")
	@Column
private String nombre;
	@NotBlank(message="Debe ingresar su apellido de usuario")
	@Column
private String apellido;
	@Column
private String paisProcedencia; 
	@Column
private Double latitud;
	@Column
private Double longitud;
	@NotBlank(message="Debe ingresar una contraseña")//restriccion especial
	@Column
private String password; 
	@Column
private Integer puntos;
	@Column
private String rol;
	//agregados password y puntos
	
/*	@OneToMany(mappedBy = "tur", fetch = FetchType.EAGER,cascade =  CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Turistas_Pois> comentarios;

	
		
	@OneToMany(mappedBy = "turista", fetch = FetchType.EAGER,cascade =  CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<PoI> pois;

*/
	
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
