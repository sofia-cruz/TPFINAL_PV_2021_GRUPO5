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
		Double valorEntero =  Math.floor(Math.random()*(24-65+1)+24);
		Double valorEntero1 =  Math.floor(Math.random()*(20-65+1)+24);
		unPoi.setLatitud(valorEntero);
		unPoi.setLongitud(valorEntero1);
		return unPoi;
	}

	@Override
	public List<PoI> obtenerTodosPoi() {
		// TODO Auto-generated method stub
		return (List<PoI>) poiDAO.findAll();
	}

	@Override
	public PoI encontrarUnPoi(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return poiDAO.findById(id).orElseThrow(()->new Exception("El punto de interes no fue encontrado"));
	}

	@Override
	public void modificarPoi(PoI poiModificado) throws Exception {
		// TODO Auto-generated method stub
	System.out.println("Dentro de service imp, modifica. Id= "+ poiModificado.getIdPoi());
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
		poiAModificar.setImagen(poiModificado.getImagen());
		poiAModificar.setImagen2(poiModificado.getImagen2());
		poiAModificar.setImagen3(poiModificado.getImagen3());
	}

	@Override
	public void eliminarPoi(Integer id) throws Exception {
		// TODO Auto-generated method stub
		PoI poiAEliminar= poiDAO.findById(id).orElseThrow(()->new Exception("El punto de interes no fue encontrado"));
		poiDAO.delete(poiAEliminar);
	
	}

	@Override
	public PoI obtenerPoiID(Integer id) {
		// TODO Auto-generated method stub
		return poiDAO.findById(id).orElseThrow();
	}

	@Override
	public PoI cambiarCantidadDeComentarios(Integer cant) throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}
	private void cambiarCantcomentario(PoI poiModificado, PoI poiAModificar) {
		//poiAModificar.setNumeroDeComentarios(poiModificado.getNumeroDeComentarios());
		
	}


	

}
