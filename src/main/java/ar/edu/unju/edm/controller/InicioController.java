package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class InicioController {
	@GetMapping("/")
	public String mostrarInicio() {
		return ("inicio");
	}
}
