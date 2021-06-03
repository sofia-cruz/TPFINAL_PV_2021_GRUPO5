package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PerfilController {
	
	@GetMapping("/turista/perfil")
	public String mostrarPerfil() {
		return ("perfil");
	}
}
