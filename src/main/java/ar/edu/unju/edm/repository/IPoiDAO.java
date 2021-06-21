package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;


@Repository
public interface IPoiDAO extends CrudRepository<PoI, Integer>{

	@Query("from PoI t order by t.idPoi")
	public List<PoI> obtenerPois();
	
	public List<PoI> findAllByTurista(Turista turista);
	
}
