package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.repository.ITuristaDAO;
import ar.edu.unju.edm.service.ITuristaService;

@Service
@Qualifier("impmysql")
public class TuristaServiceMySQL implements ITuristaService {

@Autowired
	Turista unTurista;

	@Autowired
	ITuristaDAO turistaDAO; 
 

	
	
	@Override
	public void guardarTurista(Turista unTurista) {
		// TODO Auto-generated method stub
		turistaDAO.save(unTurista);
	}

	@Override
	public Turista crearTurista() {
		// TODO Auto-generated method stub
		return unTurista;
	}

	@Override
	public List<Turista> obtenerTodosTuristas() {
		// TODO Auto-generated method stub
		return (List<Turista>) turistaDAO.findAll();
	}

	@Override
	public Turista encontrarUnTurista(int id) throws Exception {
		// TODO Auto-generated method stub
		return turistaDAO.findById(id).orElseThrow(()->new Exception("El turista No Existe"));
	}

	
	@Override
	public void modificarTurista(Turista turistaModificado) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaAModificar = turistaDAO.findById(turistaModificado.getIdTurista()).orElseThrow(()->new Exception("El turista No Fue encontrado"));  
		cambiarTurista(turistaModificado, turistaAModificar);
		turistaDAO.save(turistaAModificar);
	}
	private void cambiarTurista (Turista desde, Turista hacia) {
	hacia.setNombre(desde.getNombre());
	hacia.setApellido(desde.getApellido());
	hacia.setPaisProcedencia(desde.getPaisProcedencia());
	}
	@Override
	public void eliminarTurista(int id) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaAEliminar= turistaDAO.findById(id).orElseThrow(()->new Exception("El turista No Fue encontrado"));
		turistaDAO.delete(turistaAEliminar);
	}

}
