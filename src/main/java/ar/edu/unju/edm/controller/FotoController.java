package ar.edu.unju.edm.controller;

//import java.util.ArrayList;
import java.util.List;

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
import ar.edu.unju.edm.service.IVerificacionService;


@Controller
public class FotoController {
// este controller cuenta como valoración contrller,
	private static final Log LOGGER = LogFactory.getLog(PoIController.class);
	@Autowired 
	@Qualifier("impmysqlpoi")
	IPoiService iPoiService;
		
	@Autowired
	@Qualifier("impmysql")
	ITuristaService turistaService;
	
	@Autowired
	PoI poiSeleccionado;
	@Autowired
	IValoracionService iValoracion;
    @Autowired
    IVerificacionService iVerif;
    
	@GetMapping("/poi/foto")
	public String cargarPoi(Model model) {
		model.addAttribute("pois", iPoiService.obtenerTodosPoi());
	
		return("foto");
	}
	
	
	@GetMapping("/poi/valorar/{idPoi}")	
	public String realizarValoracion(Model model, @PathVariable(name="idPoi") Integer id) throws Exception {
		//Turistas_Pois valoracion = new Turistas_Pois();
		String vista="modal-valoracion";
		try {	
			/// solo es una prueba		
			//Manda el id del poi, y debe contar cuantas valoraciones tiene
			//int cantidadValoraciones = iValoracion.contarValoraciones(id);
			//System.out.println("Este poi tiene: "+cantidadValoraciones);
			//poiSeleccionado.setNumeroDeComentarios(cantidadValoraciones);
		
			//System.out.println("cantidad de comentarios guardao: "+ poiSeleccionado.getNumeroDeComentarios());
	      // iValoracion.contarValoraciones(id);
			//fin de la actualizacion de poi
			
			poiSeleccionado = iPoiService.obtenerPoiID(id);			
			Turistas_Pois valoracion = iValoracion.crearValoracion();	
			
			valoracion.setPoi(poiSeleccionado);			
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		   
		    Turista turistaEnSesion =  turistaService.encontrarConCorreo(userDetail.getUsername());
		    //no tocar esto, es para verificar cuantas veces valoró un turista un PoI
		    Integer permiso= iVerif.verificarValoracionAnterio(turistaEnSesion,id);
		   if(permiso>0) {
		    	vista="modal-no";
		    	System.out.println("este poi ya fue valorado por este usuario");
		    }
		  /*  else {
		    	vista="modal-valoracion";
		    }*/
		   
		    //
		    valoracion.setTurista(turistaEnSesion);
		    valoracion.setTur(turistaEnSesion.getEmail());
		    model.addAttribute("valoracion",valoracion);
	    /*
			if(iValoracion.obtenerComentariosDeUnPoi(poiSeleccionado.getIdPoi())==null) {
				System.out.println("no hay comentarios cargados");
				 model.addAttribute("loscom",iValoracion.valoracionBasica());
				
			}else {
				 LOGGER.info("METHOD: Entrando a Ver comentarios, con mas de 1 comentario ");
				//  model.addAttribute("comentarios",iValoracion.obtenerComentariosDeUnPoi(poiSeleccionado.getIdPoi()));	
				  LOGGER.info("METHOD: saliendo de Ver comentarios, con mas de 1 comentario ");
				  List<Turistas_Pois> loscom = iValoracion.obtenerComentariosDeUnPoi(poiSeleccionado.getIdPoi());	
				  for(int i=0;i<3;i++) {
					  System.out.println("contenido: "+loscom.get(i).getComentario());
					 // model.addAttribute(loscom);
					  model.addAttribute("loscom", loscom);
				  }
			}
		    */
		   
		    		
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		//return "modal-valoracion";
		return vista;
	}
	
	@PostMapping("/poi/valorar")
	public String guardarNuevaValoracion(@ModelAttribute("valoracion") Turistas_Pois unaValoracion, Model model) throws Exception{
		
	    LOGGER.error("METHOD: La valoracion: "+unaValoracion.getValoracion_user());
	    LOGGER.error("METHOD: id turistas:pois: "+unaValoracion.getIdTuristas_Pois());
	    LOGGER.error("METHOD: Email de usuario que valora: "+unaValoracion.getTur());

	    iValoracion.guardarValoracion(unaValoracion);
	    //esto manda el id del poi, y actualiza cantidad de comentarios
	    iValoracion.contarValoraciones(unaValoracion.getPoi().getIdPoi());
	    turistaService.puntosPorValoracion(unaValoracion.getTurista());
		return("redirect:/poi/foto");
	}
	
	@GetMapping("/poi/comentar/{idPoi}")	
	public String realizarComentario(Model model, @PathVariable(name="idPoi") Integer id) throws Exception {
		//Turistas_Pois valoracion = new Turistas_Pois();		
		
		try {	
	//parte de formulario para cargar comentario nuevo		
			poiSeleccionado = iPoiService.obtenerPoiID(id);			
			Turistas_Pois valoracion = iValoracion.crearValoracion();	
			
			valoracion.setPoi(poiSeleccionado);			
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		   
		    Turista turistaEnSesion =  turistaService.encontrarConCorreo(userDetail.getUsername());
		   
		    valoracion.setTurista(turistaEnSesion);
		    valoracion.setTur(turistaEnSesion.getEmail());
		  
		    model.addAttribute("valoracion",valoracion);
		    model.addAttribute("poiPorComentar", poiSeleccionado);
		 //  model.addAttribute("promedio",iValoracion.promediovaloraciones(id));
		    
		    /*Turista existente = turistaService.encontrarConCorreo(principal.getName());
			BELLA.info("Turista " + existente.getIdTurista() + "encontrado");
			model.addAttribute("turistaActual", existente);
			model.addAttribute("poisActual", poiService.obtenerMisPois(existente));
			return "*/
//ahora la parte de mostrar comentarios
			if(iValoracion.obtenerComentariosDeUnPoi(poiSeleccionado.getIdPoi())==null) {
				System.out.println("no hay comentarios cargados");
				 model.addAttribute("loscom",iValoracion.valoracionBasica());
				
			}else {
				 LOGGER.info("METHOD: Entrando a Ver comentarios, con mas de 1 comentario ");
				//  model.addAttribute("comentarios",iValoracion.obtenerComentariosDeUnPoi(poiSeleccionado.getIdPoi()));	
				  LOGGER.info("METHOD: saliendo de Ver comentarios, con mas de 1 comentario ");
				  List<Turistas_Pois> loscom = iValoracion.obtenerComentariosDeUnPoi(poiSeleccionado.getIdPoi());	
				  for(int i=0;i<3;i++) {
					  System.out.println("contenido: "+loscom.get(i).getComentario());
					 // model.addAttribute(loscom);
					  model.addAttribute("loscom", loscom);
				  }
			}
			 
		    		
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		
		return "comentarios";
	}
	
	@PostMapping("/poi/comentar")
	public String guardarNuevoCom(@ModelAttribute("valoracion") Turistas_Pois unaValoracion, Model model) throws Exception{
		
	    LOGGER.error("METHOD: La valoracion: "+unaValoracion.getValoracion_user());
	    LOGGER.error("METHOD: id turistas:pois: "+unaValoracion.getIdTuristas_Pois());
	    LOGGER.error("METHOD: Email de usuario que valora: "+unaValoracion.getTur());

	    iValoracion.guardarValoracion(unaValoracion);
	    //esto manda el id del poi, y actualiza cantidad de comentarios
	    iValoracion.contarValoraciones(unaValoracion.getPoi().getIdPoi());
	    turistaService.puntosPorComentario(unaValoracion.getTurista());
		return("redirect:/poi/foto");
	}
	

}