
package ar.edu.unju.edm.service;

import java.util.ArrayList;
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
	public void modificarTurista(Turista unTurista)throws Exception;
	public void eliminarTurista(int id) throws Exception;
	public void puntosPorPoi(Turista unTurista);
	public void puntosPorValoracion(Turista unTurista);
	public void puntosPorComentario(Turista unTurista);
	public ArrayList<Turista> encontrarTuristasPodio() throws Exception;
    //prueba
	public void CorreccionIdTuristas() throws Exception;

}
