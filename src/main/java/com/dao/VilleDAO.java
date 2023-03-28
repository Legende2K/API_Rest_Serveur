package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	ArrayList<Ville> findAllVilles();
	ArrayList<Ville> findVillesAtCommuneCode(String codeCommune);
	public ArrayList<Ville> findVillesAtPostalCode(String postalCode);
	void addVille(Ville ville);
	void updateVille(Ville ville);
	void deleteVille(String codePostal);
}
