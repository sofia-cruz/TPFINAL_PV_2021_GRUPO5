package ar.edu.unju.edm.model;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;

@Entity
@Table (name="POI")
@Component
public class PoI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Integer idPoi;
	@NotBlank( message = "Ingrese el nombre de su POI")
	@Column
	private String nombrePoi;
	@NotBlank( message = "Ingrese el nombre de la Calle")
	@Column
	private String calle;
	@Min(value = 1, message = "El N° debe ser mayor que cero")
	@Column
	private int numero;
	@NotBlank( message = "Ingrese el nombre del Barrio")
	@Column
	private String barrio;
	@NotBlank( message = "Ingrese el nombre de la Localidad")
	@Column
	private String localidad;
	@NotBlank( message = "Ingrese Descripcion de su POI")
	@Column
	private String descripcion;
	@Column
	private String etiqueta;
	@NotBlank(message="La dirección del sitio web debe ser una url válida.")
	@Column
	private String sitioWeb;
	@Column
    private Double latitud;
	@Column
    private Double longitud;
	@Lob
	@Column(name = "prod_imagen", columnDefinition = "LONGBLOB")
	private String imagen;
	@Lob
	@Column(name = "prod_imagen2", columnDefinition = "LONGBLOB")
	private String imagen2;
	@Lob
	@Column(name = "prod_imagen3", columnDefinition = "LONGBLOB")
	private String imagen3;
	@ManyToOne
	@JoinColumn(name = "email")
	private Turista turista;
	@Column
	private Integer numeroDeComentarios=0;
	
	
	
	public PoI() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIdPoi() {
		return idPoi;
	}
	public void setIdPoi(Integer idPoi) {
		this.idPoi = idPoi;
	}
	public String getNombrePoi() {
		return nombrePoi;
	}
	public void setNombrePoi(String nombrePoi) {
		this.nombrePoi = nombrePoi;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getSitioWeb() {
		return sitioWeb;
	}
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getImagen2() {
		return imagen2;
	}
	public void setImagen2(String imagen2) {
		this.imagen2 = imagen2;
	}
	public String getImagen3() {
		return imagen3;
	}
	public void setImagen3(String imagen3) {
		this.imagen3 = imagen3;
	}
	public Turista getTurista() {
		return turista;
	}
	public void setTurista(Turista turista) {
		this.turista = turista;
	}
	
	// esto se actualiza cada vez que valoro un poi
		public Integer getNumeroDeComentarios() {
			return numeroDeComentarios;
		}
		public void setNumeroDeComentarios(Integer numeroDeComentarios) {
			this.numeroDeComentarios = numeroDeComentarios;}

}