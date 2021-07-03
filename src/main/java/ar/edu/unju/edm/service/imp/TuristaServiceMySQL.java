package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
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
		unTurista.setPuntos(0);

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
		// para corregir el problema de id
		CorreccionIdTuristas();
	}
	/*
	@Override
	public void modificarTurista (Turista unTurista) {
		//String pw = unTurista.getPassword();
		//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		//unTurista.setPassword(bCryptPasswordEncoder.encode(pw));
		Double valorLat =  Math.floor(Math.random()*(24-65+1)+24);
		Double valorLong =  Math.floor(Math.random()*(20-65+1)+24);
		unTurista.setLatitud(valorLat);
		unTurista.setLongitud(valorLong);
		turistaDAO.save(unTurista);
	}
	*/
	
	
	 @Override
	public void modificarTurista(Turista unTurista) throws Exception {
	
		Turista turistaAModificar = turistaDAO.findById(unTurista.getIdTurista()).orElseThrow(()->new Exception("El turista no fue encontrado"));
		cambiarTurista(unTurista, turistaAModificar);
		turistaDAO.save(turistaAModificar);
		
	} 
	 
	private void cambiarTurista (Turista desde, Turista hacia) {
	hacia.setNombre(desde.getNombre());
	hacia.setApellido(desde.getApellido());
	hacia.setEmail(desde.getEmail());
	hacia.setPaisProcedencia(desde.getPaisProcedencia());
	hacia.setLatitud(desde.getLatitud());
	hacia.setLongitud(desde.getLongitud());
	hacia.setPuntos(desde.getPuntos());
	//hacia.setPassword(desde.getPassword());  //dudoso
	}



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
	
	

	@Override
	public ArrayList<Turista> encontrarTuristasPodio() throws Exception {
	
		//esta va a ser la lista de todos los turistas, para revisar uno por uno
		ArrayList<Turista> losTuristas= (ArrayList<Turista>) turistaDAO.findAll();
		//esta va a ser la lista de los turistas que se encuentran en podio por puntos
		List<Turista> podio = new ArrayList<>();
		
		// TODO Auto-generated method stub
				//las id uno,dos y tres inician en 1, para que no de error al buscar id=0
		int uno=0,dos=0, tres=0, pri=0, sec=0, ter=0, max=0;
		System.out.println("mas de tres cuentas de turistas");
				
				for(int i=0; i<turistaDAO.count();i++) {
					for(int j=0; j<turistaDAO.count();j++) {
						if(losTuristas.get(j).getPuntos()>pri) {
							uno=losTuristas.get(j).getIdTurista();
							pri=losTuristas.get(j).getPuntos();
						}
						if(losTuristas.get(j).getPuntos()>sec) {
							if(losTuristas.get(j).getPuntos()<pri) {
								dos=losTuristas.get(j).getIdTurista();
								sec=losTuristas.get(j).getPuntos();
							}
							
						}
						
						if(losTuristas.get(j).getIdTurista()>ter) {
							if(losTuristas.get(j).getIdTurista()<pri) {
								if(losTuristas.get(j).getIdTurista()<sec) {
									tres=losTuristas.get(j).getIdTurista();
									ter=losTuristas.get(j).getPuntos();
								}
							}
						}	
					}
				}		
				//seria util una variable que dependiendo de la cantidad de pois, cmabie de 1 a 3
				System.out.println("id de turistas en podio por puntos con id: uno: "+uno+" ,dos: "+dos+" ,tres: "+tres);
				
				int id=uno;
				int cTuristas=0;
				if(uno>0) {
					cTuristas=1;
					if(dos>0) {
						cTuristas=2;
						if(tres>0) {
							cTuristas=3;
						}
						
					}
				}
				
			//	int cTuristas=(int) turistaDAO.count();
				if(cTuristas==1) {
					podio.add(0, turistaDAO.findById(uno).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
				}
				else if(cTuristas==2) {
					podio.add(0, turistaDAO.findById(uno).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
					podio.add(1, turistaDAO.findById(dos).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
			
				}
				else if(cTuristas==3)
				{
					podio.add(0, turistaDAO.findById(uno).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
					podio.add(1, turistaDAO.findById(dos).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
					podio.add(2, turistaDAO.findById(tres).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
					
				}
				

			
				
				
			
		

		
		

		return (ArrayList<Turista>) podio;
	

}

	@Override
	public void CorreccionIdTuristas() throws Exception {
		System.out.println("entrando a coregir id de turistas");
		// TODO Auto-generated method stub
		int idTurista, posicionLista;
		ArrayList<Turista> losTuristas= (ArrayList<Turista>) turistaDAO.findAll();
		
	
		for (posicionLista=0; posicionLista<turistaDAO.count();posicionLista++) {
		
		idTurista=losTuristas.get(posicionLista).getIdTurista();
		System.out.println("el id del turista: "+idTurista);
		Turista eltur=turistaDAO.findById(idTurista).orElseThrow(()->new Exception("El turista No Existe"));
		eltur.setIdTurista(posicionLista);
		System.out.println("Nuevo id del turista: "+eltur.getIdTurista());
		turistaDAO.save(eltur);
	}
		
	} 
	  
	 
	 
	
}