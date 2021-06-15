
package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;



@Service
public interface ITuristaService {
	
	public void guardarTurista(Turista unTurista);
	public Turista crearTurista();
	public List<Turista> obtenerTodosTuristas();
	public Turista encontrarUnTurista(int id) throws Exception;
	public Turista encontrarConCorreo(String email) throws Exception;
	public void modificarTurista(Turista turistaModificado) throws Exception;
	public void eliminarTurista(int id) throws Exception;
}
