package ar.edu.unju.edm.controller;


	import java.io.IOException;
import java.security.Principal;
import java.util.Base64;

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
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.multipart.MultipartFile;

	import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPoiService;
import ar.edu.unju.edm.service.ITuristaService;
import ar.edu.unju.edm.service.IValoracionService;

	@Controller
	public class PoIController {
		private static final Log LOGGER = LogFactory.getLog(PoIController.class);
	    
		@Autowired
		@Qualifier("impmysqlpoi")
	    IPoiService iPoiService;
	    
	    @Autowired
		@Qualifier("impmysql")
		ITuristaService turistaService;

	    @GetMapping("/poi/mostrar")
		public String cargarPoi(Model model) {
	    	model.addAttribute("unPoi", iPoiService.crearPoi());
	    	model.addAttribute("pois", iPoiService.obtenerTodosPoi());	
			return("poi");
		}
	    
	    @GetMapping("/poi/editar/{idPoi}")
		public String editarPoi(Model model, @PathVariable(name="idPoi") Integer id) throws Exception{
			try {	LOGGER.info("METHOD: ingresando a editar Poi, id Poi:"+ id);
				//permite realizar una accion, y si ocurre error no se cae el program
				PoI poiEncontrado = iPoiService.encontrarUnPoi(id);
				LOGGER.info("METHOD: Dentro de editar, id: "+ poiEncontrado.getIdPoi());
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
			LOGGER.info("METHOD: Salienjdo de editar Poi, id Poi:"+ id);
			return("poi");
		}

	    

		@PostMapping(value="/poi/guardar",consumes = "multipart/form-data")
		public String guardarNuevoPoi(@Valid @ModelAttribute("unPoi") PoI nuevoPoi,BindingResult resultado,@RequestParam("file") MultipartFile file,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2, Model model, Principal principal) throws IOException{
			LOGGER.info("METHOD: ingresando a Guardar Poi");
		if (resultado.hasErrors())
		{
			model.addAttribute("unPoi",nuevoPoi);
			model.addAttribute("pois", iPoiService.obtenerTodosPoi());
		return ("poi");
		}
		else {
		//deberia haber try
			if(!file.isEmpty()|| file==null)
			{	//obtenemos los bite que el usuario va mandando
				byte[] content = file.getBytes();
				///ruta del achivo o get crea el archivo y colocal los bite en el nuevo archivo a la  ruta 
				String base64 = Base64.getEncoder().encodeToString(content);
				
				nuevoPoi.setImagen(base64);
			}
			if(!file.isEmpty()|| file==null)
			{
				
				byte[] content1 = file1.getBytes();
				String base641 = Base64.getEncoder().encodeToString(content1);
				nuevoPoi.setImagen2(base641);
			}
			if(!file.isEmpty()|| file==null)
			{
			byte[] content2 = file2.getBytes();
			String base642 = Base64.getEncoder().encodeToString(content2);
			nuevoPoi.setImagen3(base642);
			}
			 try {
				Turista turistaActual = turistaService.encontrarConCorreo(principal.getName());
				if (turistaActual != null) {
					
					//turistaActual.setPuntos(turistaActual.getPuntos() + 10);
					nuevoPoi.setTurista(turistaActual);
					iPoiService.guardarPoi(nuevoPoi);
					turistaService.puntosPorPoi(turistaActual);
					}
			 } catch (Exception e) {
				 
				 model.addAttribute("formUsuarioErrorMessage", e.getMessage());
				}
			return "redirect:/poi/mostrar";
			 }
		}
	 
		@PostMapping("/poi/modificar")
		public String modificarPoi(@ModelAttribute("unPoi") PoI poiModificado, Model model) {
			LOGGER.error("METHOD: ingresando el metodo modificar, id: "+poiModificado.getIdPoi());
			LOGGER.error("METHOD: ingresando el metodo modificar, nombre: "+poiModificado.getNombrePoi());
			LOGGER.info("METHOD: ingresando el metodo modificar, numero: "+poiModificado.getNumero());
			try {
				LOGGER.info("METHOD: ingresando a modificar Poi, id Poi: "+poiModificado.getIdPoi());
				
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
		@GetMapping("/turista/eliminarPoi/{idPoi}")
		public String eliminarPoi(Model model, @PathVariable(name="idPoi") Integer id) {		
			LOGGER.info("METHOD: ingresando el metodo Eliminar");
			try {				
			
				iPoiService.eliminarPoi(id);
			}
			catch(Exception e){
				model.addAttribute("listErrorMessage",e.getMessage());
			}			
			return "redirect:/turista/perfil";
		}
		
		
	}
