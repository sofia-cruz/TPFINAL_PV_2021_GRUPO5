package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.repository.IPoiDAO;
import ar.edu.unju.edm.service.IPoiService;

@Service
@Qualifier("impmysqlpoi")
public class PoiServiceMySQL implements IPoiService{

	
	@Autowired
	PoI unPoi;

	@Autowired
	IPoiDAO poiDAO; 
 
	@Override
	public void guardarPoi(PoI unPoi) {
		// TODO Auto-generated method stub
		poiDAO.save(unPoi);
	}

	@Override
	public PoI crearPoi() {
		// TODO Auto-generated method stub
		return unPoi;
	}

	@Override
	public List<PoI> obtenerTodosPoi() {
		// TODO Auto-generated method stub
		return (List<PoI>) poiDAO.findAll();
	}

	@Override
	public PoI encontrarUnPoi(int id) throws Exception {
		// TODO Auto-generated method stub
		return poiDAO.findById(id).orElseThrow(()->new Exception("El punto de interes no fue encontrado"));
	}

	@Override
	public void modificarPoi(PoI poiModificado) throws Exception {
		// TODO Auto-generated method stub
		PoI poiAModificar = poiDAO.findById(poiModificado.getIdPoi()).orElseThrow(()->new Exception("El turista No Fue encontrado"));  
		cambiarPoi(poiModificado, poiAModificar);
		poiDAO.save(poiAModificar);
	}

	private void cambiarPoi(PoI poiModificado, PoI poiAModificar) {
		// TODO Auto-generated method stub
		poiAModificar.setNombrePoi(poiModificado.getNombrePoi());
		poiAModificar.setSitioWeb(poiModificado.getSitioWeb());
		poiAModificar.setDescripcion(poiModificado.getDescripcion());
		poiAModificar.setEtiqueta(poiModificado.getEtiqueta());
	}

	@Override
	public void eliminarPoi(int id) throws Exception {
		// TODO Auto-generated method stub
		PoI poiAEliminar= poiDAO.findById(id).orElseThrow(()->new Exception("El punto de interes no fue encontrado"));
		poiDAO.delete(poiAEliminar);
	
	}

}
