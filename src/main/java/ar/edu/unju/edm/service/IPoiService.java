package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;



@Service
public interface IPoiService {

	public void guardarPoi(PoI unPoi);
	public PoI crearPoi();
	public List<PoI> obtenerTodosPoi();
	public PoI encontrarUnPoi(int id) throws Exception;
	public void modificarPoi(PoI poiModificado) throws Exception;
	public void eliminarPoi(int id) throws Exception;
}
