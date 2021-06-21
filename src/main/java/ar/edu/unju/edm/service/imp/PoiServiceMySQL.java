package ar.edu.unju.edm.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

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
	@SuppressWarnings("null")
	@Override
	public ArrayList<PoI> encontrarPoisMasComentados () throws Exception {
	
		//esta va a ser la lista de todos los pois, para revisar uno por uno
		ArrayList<PoI> losPois= (ArrayList<PoI>) poiDAO.findAll();
		//esta va a ser la lista de los poi mas  comentados
		List<PoI> losmasc = new ArrayList<>();
		
		// TODO Auto-generated method stub
		if(poiDAO.count()==0) {
			
			losmasc=null;
		}else if(poiDAO.count()==1){
			losmasc.add(0, poiDAO.findById(1).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
	System.out.println("Solo habia un poi cargado");
		}
		else if(poiDAO.count()==2){
			losmasc.add(0, poiDAO.findById(1).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
			losmasc.add(1, poiDAO.findById(2).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
			System.out.println("Solo hay 2 pois cargados");
		}
		else if(poiDAO.count()==3){
			losmasc.add(0, poiDAO.findById(1).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
			losmasc.add(1, poiDAO.findById(2).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
			losmasc.add(2, poiDAO.findById(3).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
			System.out.println("Justo tres pois cargados");
		}
		else{
			//las id uno,dos y tres inician en 1, para que no de error al buscar id=0
		int uno=1,dos=1, tres=1, pri=0, sec=0, ter=0, max=0;
		System.out.println("mas de tres pois cargados");
				
				for(int i=0; i<poiDAO.count();i++) {
					for(int j=0; j<poiDAO.count();j++) {
						if(losPois.get(j).getNumeroDeComentarios()>pri) {
							uno=losPois.get(j).getIdPoi();
							pri=losPois.get(j).getNumeroDeComentarios();
						}
						if(losPois.get(j).getNumeroDeComentarios()>sec) {
							if(losPois.get(j).getNumeroDeComentarios()<pri) {
								dos=losPois.get(j).getIdPoi();
								sec=losPois.get(j).getNumeroDeComentarios();
							}
							
						}
						
						if(losPois.get(j).getIdPoi()>ter) {
							if(losPois.get(j).getIdPoi()<pri) {
								if(losPois.get(j).getIdPoi()<sec) {
									tres=losPois.get(j).getIdPoi();
									ter=losPois.get(j).getNumeroDeComentarios();
								}
							}
						}	
					}
				}		
				//seria util una variable que dependiendo de la cantidad de pois, cmabie de 1 a 3
				System.out.println("id de pos mas valorados con id: uno: "+uno+" ,dos: "+dos+" ,tres: "+tres);
				
				int id=uno;
				
				int cPois=(int) poiDAO.count();
				if(cPois==1) {
					 max=1;
				}
				else if(cPois==2) {
					 max=2;
				}
				else if(cPois>=3)
				{
					 max=3;
				}

				for(int i=0; i<max;i++) {
			
					losmasc.add(i, poiDAO.findById(id).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
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
		

		
		

		return (ArrayList<PoI>) losmasc;
	

}

	@Override
	public PoI poiPorDefecto() {
		// TODO Auto-generated method stub
	   //unPoi.setImagen2(new File("/img/pastel.jpg"));
		unPoi.setNumeroDeComentarios(0);
		unPoi.setNombrePoi("Un Punto de Interés");
		unPoi.setEtiqueta("Aventura");
		unPoi.setIdPoi(0);
		unPoi.setLatitud(-60.1);
		unPoi.setLongitud(-10.3);
		//unNuevoPoi.setImagen(null);
		
		
		guardarPoi(unPoi);
		return unPoi;
	}
	

/*Encontrar mis pois*/
	@Override
	public List<PoI> obtenerMisPois(Turista turista) {
		// TODO Auto-generated method stub
		return (List<PoI>) poiDAO.findAllByTurista(turista) ;
	}


}
