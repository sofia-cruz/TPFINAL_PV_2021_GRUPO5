package ar.edu.unju.edm.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Turistas_Pois;
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
		poiAModificar.setNumero(poiModificado.getNumero());
		poiAModificar.setSitioWeb(poiModificado.getSitioWeb());
		poiAModificar.setDescripcion(poiModificado.getDescripcion());
		poiAModificar.setCalle(poiModificado.getCalle());
		poiAModificar.setBarrio(poiModificado.getBarrio());
		poiAModificar.setLocalidad(poiModificado.getLocalidad());
		poiAModificar.setEtiqueta(poiModificado.getEtiqueta());
		poiAModificar.setImagen(poiModificado.getImagen());
		poiAModificar.setImagen2(poiModificado.getImagen2());
		poiAModificar.setImagen3(poiModificado.getImagen3());
		//Latitud y Longitud se modifican solos.
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

	@Override
	public ArrayList<PoI> encontrarPoisMasComentados () throws Exception {
	
		int contador=0;
		Integer idpoi=0;
		//esta va a ser la lista de todos los pois, para revisar uno por uno
		ArrayList<PoI> losPois= (ArrayList<PoI>) poiDAO.findAll();
		//esta va a ser la lista de los poi mas  comentados
		List<PoI> losmasc = new ArrayList<>();
		
		// TODO Auto-generated method stub
		if(poiDAO.count()==0) {
			
			losmasc=null;
			System.out.println("no hay pois cargados");
		}
		else if(poiDAO.count()<=3){
			for(int i=0;i<poiDAO.count();i++) {
				if(losPois.get(i).getNumeroDeComentarios()!=0) {
					idpoi=losPois.get(i).getIdPoi();
					losmasc.add(contador, poiDAO.findById(idpoi).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
				contador++;
				}
			}
		}
		else{		int uno=0,dos=0, tres=0, pri=0, sec=0, ter=0, max=0;
				for(int i=0; i<poiDAO.count();i++) {
					for(int j=0; j<poiDAO.count();j++) {
						if(losPois.get(j).getNumeroDeComentarios()>pri) {
							//prueba
						//	/*
							tres=dos;
							ter=sec;	
							System.out.println("nuevo tres: "+ter);
							dos=uno;
							sec=pri;	
							System.out.println("nuevo dos: "+sec);
							//fin prueba 
						//	  */
							uno=losPois.get(j).getIdPoi();
							pri=losPois.get(j).getNumeroDeComentarios();
							System.out.println("guardando un uno // poi mas comentado"+pri);
						}
						if(losPois.get(j).getNumeroDeComentarios()>sec) {
							if(losPois.get(j).getNumeroDeComentarios()<pri) {
						//	prueba	/*
								tres=dos;
								ter=sec;	//fin prueba
							//	 */
								dos=losPois.get(j).getIdPoi();
								sec=losPois.get(j).getNumeroDeComentarios();
								System.out.println("guardando un dos // poi mas comentado"+sec);
							}	
						}		
					if(losPois.get(j).getIdPoi()>ter) {
							if(losPois.get(j).getIdPoi()<pri) {
								if(losPois.get(j).getIdPoi()<sec) {
									System.out.println("guardando un tres // poi mas comentado"+ter);
									tres=losPois.get(j).getIdPoi();
									ter=losPois.get(j).getNumeroDeComentarios();
								}
							}
						}	
					}
				}		
			
				int cPois=0;
				int id=uno;
				
				//int cPois=(int) poiDAO.count();
				if(uno>0) {
					cPois=1;
					if(dos>0) {
						cPois=2;
						if(tres>0) {
							cPois=3;
						}
						
					}
				}
			
				System.out.println("cantidad de pois: "+cPois);
			if(cPois==1) {
				    losmasc.add(0, poiDAO.findById(uno).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
				}
				else if(cPois==2) {
					losmasc.add(0, poiDAO.findById(uno).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
					losmasc.add(1, poiDAO.findById(dos).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
				
				}
				else if(cPois>=3)
				{
					losmasc.add(0, poiDAO.findById(uno).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
					losmasc.add(1, poiDAO.findById(dos).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
					losmasc.add(2, poiDAO.findById(tres).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
				}
		
				
				
			}
		

		
		

		return (ArrayList<PoI>) losmasc;
	

}

	@Override
	public PoI poiPorDefecto() {
		// TODO Auto-generated method stub
	   //unPoi.setImagen2(new File("/img/pastel.jpg"));
		unPoi.setNumeroDeComentarios(0);
		unPoi.setNombrePoi("Aún No hay puntos de interés cargados");
		unPoi.setEtiqueta("Aventura");
		unPoi.setIdPoi(0);
		unPoi.setLatitud(-60.1);
		unPoi.setLongitud(-10.3);
		//unNuevoPoi.setImagen(null);
		
		//no necesito guardarlo
		// guardarPoi(unPoi);
		return unPoi;
	}
	

/*Encontrar mis pois*/
	@Override
	public List<PoI> obtenerMisPois(Turista turista) {
		// TODO Auto-generated method stub
		return (List<PoI>) poiDAO.findAllByTurista(turista) ;
	}


}
