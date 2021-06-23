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
	
	
	 @SuppressWarnings("null")
	@Override
	public ArrayList<Turista> encontrarTuristasPodio() throws Exception {
	
		//esta va a ser la lista de todos los turistas, para revisar uno por uno
		ArrayList<Turista> losTuristas= (ArrayList<Turista>) turistaDAO.findAll();
		//esta va a ser la lista de los turistas que se encuentran en podio por puntos
		List<Turista> podio = new ArrayList<>();
		
		// TODO Auto-generated method stub
		if(turistaDAO.count()==1) {
			
			podio.add(0, turistaDAO.findById(1).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
	System.out.println("Solo existe el turista actual");
		}
		else if(turistaDAO.count()==2){
			podio.add(0, turistaDAO.findById(1).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
			podio.add(1, turistaDAO.findById(2).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
			System.out.println("Solo hay 2 turistas");
		}
		else if(turistaDAO.count()==3){
			podio.add(0, turistaDAO.findById(1).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
			podio.add(1, turistaDAO.findById(2).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
			podio.add(2, turistaDAO.findById(3).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
			System.out.println("Existen 3 turistas exactos");
		}
		else{
			//las id uno,dos y tres inician en 1, para que no de error al buscar id=0
		int uno=1,dos=1, tres=1, pri=0, sec=0, ter=0, max=0;
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
				
				int cTuristas=(int) turistaDAO.count();
				if(cTuristas==1) {
					 max=1;
				}
				else if(cTuristas==2) {
					 max=2;
				}
				else if(cTuristas>=3)
				{
					 max=3;
				}

				for(int i=0; i<max;i++) {
			
					podio.add(i, turistaDAO.findById(id).orElseThrow(()->new Exception("El Turista No Fue encontrado, turistaserviceimp")));
					if(id==uno) {
						System.out.println("Entrando a id==uno, id: "+id);
						id=dos;			
						System.out.println("Saliendo de id==uno, id: "+id);
					}
					else if(id==dos){
						System.out.println("Entrando a id==dos, id: "+id);
						id=tres;
						System.out.println("Saliendo de id==dos, id: "+id);
					}
					System.out.println("["+i+"]"+"el id a buscar es: "+id);
						
				}
				
				
			}
		

		
		

		return (ArrayList<Turista>) podio;
	

} 
	  
	 
	 
	
}