package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.service.IPoiService;
import ar.edu.unju.edm.service.ITuristaService;
import ar.edu.unju.edm.service.IValoracionService;

@Controller
public class HomeController {
	
	private static final Log LOGGER = LogFactory.getLog(PoIController.class);
	@Autowired 
	@Qualifier("impmysqlpoi")
	IPoiService iPoiService;
	
	@Autowired
	PoI unPoi;

	
	@Autowired 
	@Qualifier("impmysql")
	ITuristaService iTuristaService;
		
	@Autowired
	PoI poiSeleccionado;
	@Autowired
	IValoracionService iValoracion;
	
	@GetMapping("/home")
	public String cargarHome(Model model) throws Exception{
		if(iPoiService.encontrarPoisMasComentados()==null) {
			//en caso de no haber pois	
			model.addAttribute("pois", iPoiService.poiPorDefecto());
		}	
	    else{
		model.addAttribute("pois", iPoiService.encontrarPoisMasComentados());}
		return ("home");	
	}
	}


