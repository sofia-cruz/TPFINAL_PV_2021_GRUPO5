package ar.edu.unju.edm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turistas_Pois;


@Service
public interface IValoracionService {
	public void guardarValoracion(Turistas_Pois unaValoracion);
	public Turistas_Pois crearValoracion();
	public List<Turistas_Pois> obtenerTodaValoracion();
	//revisar id ,Interger
	public Turistas_Pois encontrarUnaValoracion(Integer id) throws Exception;
	public  void contarValoraciones(Integer idDeLaValoracion) throws Exception;
	public ArrayList<Turistas_Pois> obtenerComentariosDeUnPoi(Integer idPoi) throws Exception;
	public  Turistas_Pois valoracionBasica();
	public Turistas_Pois promediovaloraciones(Integer id);
}
