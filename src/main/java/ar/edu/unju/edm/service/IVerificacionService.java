package ar.edu.unju.edm.service;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;

@Service
public interface IVerificacionService {
public Integer verificarValoracionAnterio(Turista turista, Integer idPoi);
}
