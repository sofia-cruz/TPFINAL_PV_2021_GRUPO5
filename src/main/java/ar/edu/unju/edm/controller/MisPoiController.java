package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.service.IPoiService;
@Controller
public class MisPoiController {
	@Autowired
	@Qualifier("impmysqlpoi")
IPoiService iPoiService;
	
	@GetMapping("/pois")
	public String cargarPoi(Model model) {
		model.addAttribute("pois",iPoiService.obtenerTodosPoi());
		return("mispoi");
	}

}
