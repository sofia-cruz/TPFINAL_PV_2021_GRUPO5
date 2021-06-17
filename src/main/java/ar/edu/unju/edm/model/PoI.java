package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table (name="POI")
@Component
public class PoI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Integer idPoi;
	@Column
	private String nombrePoi;
	@Column
	private String calle;
	@Column
	private int numero;
	@Column
	private String barrio;
	@Column
	private String localidad;
	@Column
	private String descripcion;
	@Column
	private String etiqueta;
	@Column
	private String sitioWeb;
	@Column
    private Double latitud;
	@Column
    private Double longitud;
	@Column
	private String email;
	@Lob
	@Column(name = "prod_imagen", columnDefinition = "LONGBLOB")
	private String imagen;
	/*@ManyToOne
	@JoinColumn(name = "email")
	private Turista otroturista;
	*/
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
