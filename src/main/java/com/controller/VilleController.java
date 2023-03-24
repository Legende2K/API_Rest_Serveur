package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {
	
	@Autowired
	VilleBLO villeBLOService;

	@RequestMapping(value="/ville", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> get(@RequestParam(required=false, value="codePostal") String codePostal) {
		System.out.println("get :" + codePostal);
		return villeBLOService.getInfoVilles(codePostal);
	}

	@RequestMapping(value="/ville", method=RequestMethod.POST)
	@ResponseBody
	public String post(@RequestBody Ville ville) {
		villeBLOService.addVille(ville);
		return "Ville ajoutée avec succès";
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.PUT)
	@ResponseBody
	public String put(@RequestBody Ville ville) {
	    villeBLOService.updateVille(ville);
	    return "Ville mise à jour avec succès";
	}
}
