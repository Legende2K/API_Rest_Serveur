package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {

	public ArrayList<Ville> getInfoVilles(String codePostal);

	public void addVille(Ville ville);

	public void updateVille(Ville ville);

	public void deleteVille(String codePostal);
}
