package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristaService;

@SpringBootApplication
public class TpfinalPv2021Grupo5Application implements CommandLineRunner{
	@Autowired
	@Qualifier("impmysql")
	ITuristaService turistaService; 
	@Autowired
	Turista turista;
	
	public static void main(String[] args) {
		SpringApplication.run(TpfinalPv2021Grupo5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	/*
		
		turista.setApellido("Solis");
		turista.setEmail("a@a");
		turista.setIdTurista(2);
		turista.setNombre("César");
		turista.setPaisProcedencia("Argentina");
		turista.setPassword("1234");
		turista.setPuntos(1);
		turista.setRol("normal");
		turistaService.guardarTurista(turista);
		
*/
	}

}
