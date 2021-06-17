package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Turistas_Pois;

import ar.edu.unju.edm.service.IPoiService;
import ar.edu.unju.edm.service.ITuristaService;
import ar.edu.unju.edm.service.IValoracionService;


@Controller
public class FotoController {
// este controller cuenta como valoración contrller,
	private static final Log LOGGER = LogFactory.getLog(PoIController.class);
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
	
	
	@GetMapping("/poi/valorar/{idPoi}")	
	public String realizarValoracion(Model model, @PathVariable(name="idPoi") Integer id) throws Exception {
		Turistas_Pois valoracion = new Turistas_Pois();		
		try {	
			
			
			/// solo es una prueba
			
			Integer valo= iValoracion.contarValoraciones(id);
			
			LOGGER.error("METHOD: Valoraciones totales de este punto: "+ valo);
		
			///  fin de la prueba
			
			
			poiSeleccionado = iPoiService.obtenerPoiID(id);			
			valoracion = iValoracion.crearValoracion();	
			//revisar esto, en caso de fallos
			valoracion.setPoi(poiSeleccionado);			
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		   
		    Turista turistaEnSesion =  iTuristaService.encontrarConCorreo(userDetail.getUsername());
		    
		    valoracion.setTurista(turistaEnSesion);
		    valoracion.setTur(turistaEnSesion.getEmail());
		    
		    
		    model.addAttribute("valoracion",valoracion);
		    LOGGER.error("METHOD: Saliendo de Valorar: "+turistaEnSesion.getEmail()+" "+turistaEnSesion.getNombre());
		    LOGGER.error("METHOD: valor de valoracion: "+valoracion.getValoracion()+" , id turistas_pois: "+valoracion.getIdTuristas_Pois());
			
		   
		    		
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "modal-valoracion";
	}
	
	@PostMapping("/poi/valorar")
	public String guardarNuevaValoracion(@ModelAttribute("valoracion") Turistas_Pois unaValoracion, Model model) throws Exception{
	
		/*Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	   
	    Turista turistaEnSesion =  iTuristaService.encontrarConCorreo(userDetail.getUsername());
		unaValoracion.setTur(turistaEnSesion.getEmail());		*/
		
	    LOGGER.error("METHOD: Entrando en PostMapping de Valorar, vontenido de tur: "+unaValoracion.getTur());
	    LOGGER.error("METHOD: La valoracion: "+unaValoracion.getValoracion());
	    LOGGER.error("METHOD: id turistas:pois: "+unaValoracion.getIdTuristas_Pois());

	    iValoracion.guardarValoracion(unaValoracion);
		return("redirect:/poi/foto");
	}
	
}
