package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Turistas_Pois;
import ar.edu.unju.edm.repository.IPoiDAO;
import ar.edu.unju.edm.repository.IValoracionDAO;
import ar.edu.unju.edm.service.IVerificacionService;

@Service
@Qualifier("impVerificacion")
public class VerificacionDeValoracion  implements IVerificacionService{

	@Autowired
	PoI unPoi;
	
	@Autowired
	IPoiDAO poiDAO; 
	@Autowired
	Turistas_Pois valoracion;


	@Autowired
	IValoracionDAO valoracionDAO;
	
	@Override
	public Integer verificarValoracionAnterio(Turista turista, Integer idPoi) {
		// TODO Auto-generated method stub
		System.out.println("dentro de verificacion de valoracion");
		List<Turistas_Pois> todasLasValoraciones=(List<Turistas_Pois>) valoracionDAO.findAll();
		List<Turistas_Pois> losPoisValorados= new ArrayList<>();
		//List<Turistas_Pois> losPoisValorados= new ArrayList<>();
		
		int devolucion=0;
		int i;
		Integer contadorDeComentariosEnUnPoi=0;
		System.out.println("Poi id: "+idPoi);
		
		for(i=0;i<valoracionDAO.count();i++) {
			if(todasLasValoraciones.get(i).getValoracion_user()!=null) {
				
				if(todasLasValoraciones.get(i).getPoi().getIdPoi()==idPoi) {
					
					if(todasLasValoraciones.get(i).getTurista().getIdTurista()==turista.getIdTurista()) {
						contadorDeComentariosEnUnPoi++;
						System.out.println("este turista ya comentó este poi"+contadorDeComentariosEnUnPoi);
					    
					}
				}
				
				
				
				//System.out.println("una valoracion :D: "+todasLasValoraciones.get(i).getValoracion_user());
			}
			
		
			
		}
		if(contadorDeComentariosEnUnPoi>0) {
			System.out.println("Este turista ya valoró este poi "+contadorDeComentariosEnUnPoi+" veces");
		}
		else {
			System.out.println("este turista nunca valoró este poi");
		}
		return contadorDeComentariosEnUnPoi;
	}

}
