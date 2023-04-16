package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findAllVilles();
	public ArrayList<Ville> findVillesAtCommuneCode(String codeCommune);
	public ArrayList<Ville> findVillesAtPostalCode(String postalCode);
	public void addVille(Ville ville);
	public void updateVille(Ville ville);
	public void deleteVille(String codeCommune);
}
