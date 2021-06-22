package ar.edu.unju.edm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;




@Service
public interface IPoiService {
	/*Ayuda a Aylen*/

	public void guardarPoi(PoI unPoi);
	public PoI crearPoi();
	public List<PoI> obtenerTodosPoi();
	public PoI encontrarUnPoi(Integer id) throws Exception;
	public void modificarPoi(PoI poiModificado) throws Exception;
	public void eliminarPoi(Integer id) throws Exception;
	//nuevo
	public PoI obtenerPoiID(Integer id);
	public PoI cambiarCantidadDeComentarios(Integer cant) throws Exception;
	public ArrayList<PoI> encontrarPoisMasComentados () throws Exception;
	//
	public PoI poiPorDefecto();
	public List<PoI> obtenerMisPois(Turista turista);
	
}
