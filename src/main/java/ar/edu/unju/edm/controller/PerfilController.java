package ar.edu.unju.edm.controller;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPoiService;
import ar.edu.unju.edm.service.ITuristaService;


@Controller
public class PerfilController {
	private static final Log BELLA = LogFactory.getLog(PerfilController.class);
	
	@Autowired
	@Qualifier("impmysql")
	ITuristaService turistaService;
	@Autowired
	@Qualifier("impmysqlpoi")
    IPoiService poiService;
	
	
	@GetMapping("/turista/perfil")
	public String verPerfil(Model model, Principal principal) throws Exception{
		try{
			Turista existente = turistaService.encontrarConCorreo(principal.getName());
			BELLA.info("Turista " + existente.getIdTurista() + "encontrado");
			model.addAttribute("turistaActual", existente);
			model.addAttribute("poisActual", poiService.obtenerMisPois(existente));
			return "perfil";
		}
		catch(Exception e){
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
		}
    return "redirect:/inicio";
	}
	

}