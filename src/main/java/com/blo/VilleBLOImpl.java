package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;

	@Override
	public ArrayList<Ville> getInfoVilles(String codePostal) {

		ArrayList<Ville> listVille = new ArrayList<Ville>();

		if (codePostal != null) {
			listVille = villeDAO.findVillesAtPostalCode(codePostal);
		} else {
			listVille = villeDAO.findAllVilles();
		}

		return listVille;
	}

	@Override
	public void addVille(Ville ville) {
		villeDAO.addVille(ville);
	}

	@Override
	public void updateVille(Ville ville) {
	    villeDAO.updateVille(ville);
	}

}
