package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turistas_Pois;

import ar.edu.unju.edm.service.IPoiService;
import ar.edu.unju.edm.service.ITuristaService;
import ar.edu.unju.edm.service.IValoracionService;


@Controller
public class FotoController {
// este controller cuenta como valoración contrller,
	
	@Autowired 
	@Qualifier("impmysqlpoi")
	IPoiService iPoiService;
	
	@Autowired 
	@Qualifier("impmysql")
	ITuristaService iTuristaService;
	
	@Autowired
	PoI poiSeleccionado;
	@Autowired
	IValoracionService iValoracion;

	@GetMapping("/poi/foto")
	public String cargarPoi(Model model) {
		model.addAttribute("pois", iPoiService.obtenerTodosPoi());
		return("foto");
	}
	
	
	@GetMapping("/poi/foto/{idPoi}")	
	public String realizarValoracion(Model model, @PathVariable(name="idPoi") Integer id) throws Exception {
		Turistas_Pois valoracion = new Turistas_Pois();		
		try {		
			poiSeleccionado = iPoiService.obtenerPoiID(id);			
			valoracion = iValoracion.crearValoracion();	
			//revisar esto, en caso de fallos
			valoracion.setPoi(poiSeleccionado);
		
			model.addAttribute("valoracion",valoracion);
			model.addAttribute("clientes", iTuristaService.obtenerTodosTuristas());
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "modal-venta";
	}
	
	@PostMapping("/poi/foto/valorar")
	public String guardarNuevaValoracion(@ModelAttribute("valoracion") Turistas_Pois unaValoracion, Model model){
		iValoracion.guardarValoracion(unaValoracion);
		return("redirect:/poi/foto");
	}
	
}
