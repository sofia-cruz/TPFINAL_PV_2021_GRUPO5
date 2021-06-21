package ar.edu.unju.edm.service.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turistas_Pois;
import ar.edu.unju.edm.repository.IPoiDAO;
import ar.edu.unju.edm.repository.IValoracionDAO;
import ar.edu.unju.edm.service.IValoracionService;
@Service
@Qualifier("impmysqlValoracion")
public class ValoracionServiceMySQL implements IValoracionService{
	
	@Autowired
	PoI unPoi;
	
	@Autowired
	IPoiDAO poiDAO; 
@Autowired
Turistas_Pois valoracion;


@Autowired
IValoracionDAO valoracionDAO;
	
private ArrayList<Turistas_Pois> lasValoraciones;
	@Override
	public void guardarValoracion(Turistas_Pois unaValoracion) {
		// TODO Auto-generated method stub
		valoracionDAO.save(unaValoracion);
	}

	@Override
	public Turistas_Pois crearValoracion() {
		// TODO Auto-generated method stub
		return valoracion;
	}

	@Override
	public List<Turistas_Pois> obtenerTodaValoracion() {
		// TODO Auto-generated method stub
		return (List<Turistas_Pois>) valoracionDAO.findAll();
	}

	@Override
	public Turistas_Pois encontrarUnaValoracion(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  void contarValoraciones(Integer idDeLaValoracion) throws Exception {
		//el id debe ser de poi, para contar cuantas veces se valoró ese poi
		lasValoraciones= (ArrayList<Turistas_Pois>) valoracionDAO.findAll();
		//private List<Turistas_Pois> listaDeValoraciones = ;
		
	int contador=0;
		for(int i=0; i<valoracionDAO.count();i++) {
			//aquí retorno l cantidad de comentarios que tiene este poi que se manda
		//System.out.println( lasValoraciones.get(i).getComentario());
		if(lasValoraciones.get(i).getPoi().getIdPoi()==idDeLaValoracion)
		{
			contador=contador+1;
			
		}
		}
		
		PoI poiAModificar = poiDAO.findById(idDeLaValoracion).orElseThrow(()->new Exception("El turista No Fue encontrado"));
		
		 poiAModificar.setNumeroDeComentarios(contador);
		
		poiDAO.save(poiAModificar);
		
		//unPoi.setNumeroDeComentarios(contador);
			
		
		
	}
	
	private void cambiarPoi(PoI poiModificado, PoI poiAModificar) {
		// TODO Auto-generated method stub
		poiAModificar.setNombrePoi(poiModificado.getNombrePoi());
		
	}
	
	
	@Override
	public ArrayList<Turistas_Pois> obtenerComentariosDeUnPoi(Integer id) throws Exception{
		//esta va a ser la lista de todas las valoraciones, para revisar uno por uno
				ArrayList<Turistas_Pois> lasValoraciones= (ArrayList<Turistas_Pois>) valoracionDAO.findAll();
				//esta va a ser la lista de los comentarios de un poi
				List<Turistas_Pois> loscom = new ArrayList<>();
				System.out.println("entrando a obtener comentarios");
				int contador=0;
				if(valoracionDAO.count()==0) {
					
					loscom=null;
				}
				else{
					int laid=0;
				
				System.out.println("Hay: "+valoracionDAO.count()+" comentarios cargados");
						
						for(int i=0; i<valoracionDAO.count();i++) {
							System.out.println("["+i+"]"+"los comentarios: "+lasValoraciones.get(i).getComentario());
							if(lasValoraciones.get(i).getPoi().getIdPoi()==id) {
								laid=lasValoraciones.get(i).getIdTuristas_Pois();
								//loscom.set(i, lasValoraciones.get(i));
						loscom.add(i, valoracionDAO.findById(laid).orElseThrow(()->new Exception("La valoracion No Fue encontrada, poiserviceimp")));
								
								System.out.println("los devueltos: "+lasValoraciones.get(i).getComentario());
								System.out.println("los guardados: "+loscom.get(i).getComentario());
								contador++;
								
						}
						}								


						for(int j=0; j<contador;j++) {
					
						//	losmasc.add(i, poiDAO.findById(ide).orElseThrow(()->new Exception("El poi No Fue encontrado, poiserviceimp")));
	
							System.out.println("["+j+"]"+"el comentario es : "+loscom.get(j).getComentario());
								
						}
						
						
					}
				

				return (ArrayList<Turistas_Pois>) loscom;
	}

	@Override
	public Turistas_Pois valoracionBasica() {
		// TODO Auto-generated method stub
System.out.println("haciendo valoracion básica, serviceimp");
		valoracion.setComentario("aun no hay comentarios");
		valoracion.setValoracion_user(0);
		return valoracion;
	}

}
