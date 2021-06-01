package ar.edu.unju.edm.controller;

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

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.service.IPoiService;

@Controller
public class PoIController {
	private static final Log LOGGER = LogFactory.getLog(PoIController.class);
    @Autowired
	@Qualifier("impmysqlpoi")
    IPoiService iPoiService;
    
    @GetMapping("/poi/mostrar")
	public String cargarPoi(Model model) {
    	model.addAttribute("unPoi", iPoiService.crearPoi());
    	model.addAttribute("pois", iPoiService.obtenerTodosPoi());	
		return("poi");
	}
    
    @GetMapping("/poi/editar/{idPoi}")
	public String editarPoi(Model model, @PathVariable(name="idPoi") int id) throws Exception{
		try {
			//permite realizar una accion, y si ocurre error no se cae el program
			PoI poiEncontrado = iPoiService.encontrarUnPoi(id);
			
			model.addAttribute("unPoi", poiEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch(Exception e)
		{//pasar excepcione a html
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unPoi", iPoiService.crearPoi());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("pois",iPoiService.obtenerTodosPoi());
		return("poi");
	}

    

	@PostMapping("/poi/guardar")
	public String guardarNuevoPoi(@Valid @ModelAttribute("unPoi") PoI nuevoPoi,BindingResult resultado, Model model) {
		LOGGER.info("METHOD: ingresando a Guardar Poi");
	if (resultado.hasErrors())
	{
		model.addAttribute("unPoi",nuevoPoi);
		model.addAttribute("pois", iPoiService.obtenerTodosPoi());
	return ("poi");
	}
	else {
	//deberia haber try
	iPoiService.guardarPoi(nuevoPoi);
		return "redirect:/poi/mostrar";
	}
	}
    

	@PostMapping("/poi/modificar")
	public String modificarPoi(@ModelAttribute("unPoi") PoI poiModificado, Model model) {
	
		try {LOGGER.info("METHOD: ingresando a modificar Poi, id Poi:"+poiModificado.getIdPoi());
			
			iPoiService.modificarPoi(poiModificado);
			model.addAttribute("unPoi", new PoI());
			model.addAttribute("editMode", "false");
		}
		catch(Exception e)
		{
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unPoi", poiModificado);
			model.addAttribute("pois", iPoiService.obtenerTodosPoi());
			model.addAttribute("editMode","true");
		}
		model.addAttribute("pois", iPoiService.obtenerTodosPoi());
		return ("poi");
	}
	
	
	@GetMapping("/poi/cancelar")
	public String cancelar() {
		return "redirect:/poi/mostrar";
	}
	
	//modal peticiones
	@GetMapping("/poi/eliminarPoi/{idPoi}")
	public String eliminarPoi(Model model, @PathVariable(name="idPoi") int id) {		
		try {				
						iPoiService.eliminarPoi(id);
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/poi/mostrar";
	}
	
	
}
