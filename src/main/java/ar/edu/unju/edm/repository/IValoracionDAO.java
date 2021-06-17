package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Turistas_Pois;


@Repository
public interface IValoracionDAO  extends CrudRepository<Turistas_Pois, Integer>{

}
