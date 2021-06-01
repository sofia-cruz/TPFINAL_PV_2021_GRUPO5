package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@PostMapping("/turista/guardar/{idTurista}")
	public String guardarTurista (@ModelAttribute("unTurista") Turista nuevoTurista, Model model) {

		BELLA.info("METHOD: Ingresando al metodo Guardar");
		turistaService.guardarTurista(nuevoTurista);
		BELLA.info("Tamaño del listado: "+ turistaService.obtenerTodosTuristas().size());
		return "redirect:/cliente/mostrar";
	}
	@GetMapping("/turista/mostrar")
	public String crearTurista(Model model) {
		model.addAttribute("modoEditar", false);
		model.addAttribute("unTurista", turistaService.crearTurista());
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return "turista";
	}
	
	@PostMapping("/turista/modificar")
	public String modificarTurista(@ModelAttribute("unTurista") Turista turistaModificado, Model model) throws Exception{
		turistaService.modificarTurista(turistaModificado);
		return "redirect:/cliente/mostrar";
	}
	
	@GetMapping("/turista/eliminar/{idTurista}")
	public String eliminarTurista(@PathVariable(name = "idTurista")int id, Model model) throws Exception{
		try {
			turistaService.eliminarTurista(id);
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
		}
		return "redirect:/cliente/mostrar";
	}
	
	@GetMapping("/turista/editar/{idTurista}")
	public String editarTurista(Model model, @PathVariable(name = "idTurista") int id) throws Exception{
		try{
			Turista encontrado = turistaService.encontrarUnTurista(id);
			model.addAttribute("unTurista", encontrado);
			model.addAttribute("modoEditar", true);
		}
		catch(Exception e){
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
			model.addAttribute("modoEditar", false);
		}
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return "turista";
	}
}
