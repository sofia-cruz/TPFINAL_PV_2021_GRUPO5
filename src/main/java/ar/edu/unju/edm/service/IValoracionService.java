package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turistas_Pois;


@Service
public interface IValoracionService {
	public void guardarValoracion(Turistas_Pois unaValoracion);
	public Turistas_Pois crearValoracion();
	public List<Turistas_Pois> obtenerTodaValoracion();
	//revisar id ,Interger
	public Turistas_Pois encontrarUnaValoracion(Integer id) throws Exception;
	public  Integer contarValoraciones(Integer idDeLaValoracion);
}
