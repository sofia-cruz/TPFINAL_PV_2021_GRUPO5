package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		Double valorLat =  Math.floor(Math.random()*(24-65+1)+24);
		Double valorLong =  Math.floor(Math.random()*(20-65+1)+24);
		unTurista.setLatitud(valorLat);
		unTurista.setLongitud(valorLong);
		String pw = unTurista.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		unTurista.setPassword(bCryptPasswordEncoder.encode(pw));
		
		turistaDAO.save(unTurista);
	}

	@Override
	public List<Turista> obtenerTodosTuristas() {
		// TODO Auto-generated method stub
		return (List<Turista>) turistaDAO.findAll();
	}

	@Override
	public Turista crearTurista() {
		
		// revisar
		// TODO Auto-generated method stub
		
		unTurista.setRol("normal");
		unTurista.setPuntos(0);
		return unTurista;
	}


	@Override
	public Turista encontrarUnTurista(int id) throws Exception {
		// TODO Auto-generated method stub
		return turistaDAO.findById(id).orElseThrow(()->new Exception("El turista No Existe"));
		
	}
	

	//otro para buscar por string para ayudarme en el perfil
	@Override
	public Turista encontrarConCorreo(String email) throws Exception{
		 
		return turistaDAO.findByEmail(email).orElseThrow(()->new Exception("El turista No Existe"));       
	}

	

	@Override
	public void eliminarTurista(int id) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaAEliminar= turistaDAO.findById(id).orElseThrow(()->new Exception("El turista No Fue encontrado"));
		turistaDAO.delete(turistaAEliminar);
	}
	@Override
	public void modificarTurista (Turista unTurista) {
		String pw = unTurista.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		unTurista.setPassword(bCryptPasswordEncoder.encode(pw));
		Double valorLat =  Math.floor(Math.random()*(24-65+1)+24);
		Double valorLong =  Math.floor(Math.random()*(20-65+1)+24);
		unTurista.setLatitud(valorLat);
		unTurista.setLongitud(valorLong);
		turistaDAO.save(unTurista);
	}
	/*private void cambiarTurista (Turista desde, Turista hacia) {
	hacia.setNombre(desde.getNombre());
	hacia.setApellido(desde.getApellido());
	hacia.setPaisProcedencia(desde.getPaisProcedencia());
	hacia.setLatitud(desde.getLatitud());
	hacia.setLongitud(desde.getLongitud());
	hacia.setPuntos(desde.getPuntos());
	hacia.setPassword(desde.getPassword());  //dudoso
	}

*/

	@Override
	public void puntosPorPoi(Turista unTurista) {
		// TODO Auto-generated method stub
		unTurista.setPuntos(unTurista.getPuntos() +10);
		turistaDAO.save(unTurista);
	}

	@Override
	public void puntosPorValoracion(Turista unTurista) {
		// TODO Auto-generated method stub
		unTurista.setPuntos(unTurista.getPuntos() +5);
		turistaDAO.save(unTurista);

		
	}

	@Override
	public void puntosPorComentario(Turista unTurista) {
		// TODO Auto-generated method stub
		unTurista.setPuntos(unTurista.getPuntos() +8);
		turistaDAO.save(unTurista);
	}
}