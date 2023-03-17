package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.config.Database;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	@Override
	public ArrayList<Ville> findAllVilles() {

		ArrayList<Ville> listVille = new ArrayList<Ville>();
		Database database = new Database();
		String queryRequest = "SELECT * FROM ville_france;";
		PreparedStatement statementRequest;

		try {
			statementRequest = database.getConnection().prepareStatement(queryRequest);
			ResultSet statementResult = statementRequest.executeQuery();
			while (statementResult.next()) {
				Ville ville = new Ville();
				ville.setCodePostal(statementResult.getString("Code_postal"));
				ville.setLigne(statementResult.getString("Ligne_5"));
				ville.setNomCommune(statementResult.getString("Nom_commune"));
				listVille.add(ville);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listVille;
	}
	
	@Override
	public ArrayList<Ville> findVillesAtPostalCode(String postalCode) {

		ArrayList<Ville> listVille = new ArrayList<Ville>();
		Database database = new Database();
		String queryRequest = "SELECT * FROM ville_france WHERE Code_postal="+postalCode+";";
		PreparedStatement statementRequest;

		try {
			statementRequest = database.getConnection().prepareStatement(queryRequest);
			ResultSet statementResult = statementRequest.executeQuery();
			while (statementResult.next()) {
				Ville ville = new Ville();
				ville.setCodePostal(statementResult.getString("Code_postal"));
				ville.setLigne(statementResult.getString("Ligne_5"));
				ville.setNomCommune(statementResult.getString("Nom_commune"));
				listVille.add(ville);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listVille;
	}
	

}
