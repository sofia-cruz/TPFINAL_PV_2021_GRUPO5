package ar.edu.unju.edm.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristaService;


@Controller

public class TuristaController {
	private static final Log BELLA = LogFactory.getLog(TuristaController.class);
	
	@Autowired
	@Qualifier("impmysql")
	ITuristaService turistaService;
	
	
		
	@PostMapping(value="/turista/guardar")
	public String guardarTurista (@Valid @ModelAttribute("unTurista") Turista nuevoTurista,BindingResult resultado, Model model) {
		if (resultado.hasErrors()) 
		{
			model.addAttribute("unTurista", nuevoTurista);
			model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
			return("turista");
		}
		else {
		BELLA.info("METHOD: Ingresando al metodo Guardar");
		turistaService.guardarTurista(nuevoTurista);
		BELLA.info("Tamaño del listado: "+ turistaService.obtenerTodosTuristas().size());
		return "redirect:/";
	}
		//modificado el guardar turista para las restricciones
	}
	
	@GetMapping("/turista/mostrar")
	public String crearTurista(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		BELLA.info("Nuevo turista generado");
		return "turista";
	}


	@PostMapping("/turista/modificar")
	public String modificarTurista(@ModelAttribute("turistaModificado") Turista turistaMod){
		turistaService.modificarTurista(turistaMod);
		BELLA.info("Turista "+ turistaMod.getIdTurista()+ "modificado");
		return "redirect:/turista/perfil";
	}

	
	
	@GetMapping("/turista/eliminar/{idTurista}")
	public String eliminarTurista(@PathVariable(name = "idTurista")int id, Model model) throws Exception{
		try {
			Turista existente = turistaService.encontrarUnTurista(id);
			BELLA.info("Turista" + existente.getNombre() + "encontrado");
			turistaService.eliminarTurista(id);
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
		}
		return "redirect:/logout";
	}
	
	
	
	@GetMapping("/turista/editar/{idTurista}")
	public String editarTurista(Model model, Principal principal) throws Exception{
		try{
			Turista encontrado = turistaService.encontrarConCorreo(principal.getName());
			BELLA.info("AHHHHHHHHHHHH");
			model.addAttribute("turistaModificado", encontrado);
			BELLA.info("EHHHHHHHHHHHH");
			return "editar-turista";
		}
		catch(Exception e){
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
			BELLA.info("el perfil no me quiere");
		}
		 return "redirect:/turista/perfil";
	}
	

	
}