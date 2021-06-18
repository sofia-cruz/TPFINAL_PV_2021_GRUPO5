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

}
