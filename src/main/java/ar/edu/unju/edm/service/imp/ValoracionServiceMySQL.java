package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turistas_Pois;
import ar.edu.unju.edm.service.IValoracionService;
@Service
public class ValoracionServiceMySQL implements IValoracionService{
@Autowired
Turistas_Pois valoracion;
	
	
	@Override
	public void guardarValoracion(Turistas_Pois unaValoracion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Turistas_Pois crearValoracion() {
		// TODO Auto-generated method stub
		return valoracion;
	}

	@Override
	public List<Turistas_Pois> obtenerTodaValoracion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Turistas_Pois encontrarUnaValoracion(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
