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
	private ArrayList<Ville> executeQuery(String queryRequest) {
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		Database database = new Database();
		PreparedStatement statementRequest = null;
		ResultSet statementResult = null;
		try {
			statementRequest = database.getConnection().prepareStatement(queryRequest);
			statementResult = statementRequest.executeQuery();
			while (statementResult.next()) {
				Ville ville = new Ville();
				ville.setCodePostal(statementResult.getString("Code_postal"));
				ville.setLigne(statementResult.getString("Ligne_5"));
				ville.setNomCommune(statementResult.getString("Nom_commune"));
				listVille.add(ville);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statementResult != null) {
					statementResult.close();
				}
				if (statementRequest != null) {
					statementRequest.close();
				}
				database.closeDatabase();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listVille;
	}

	@Override
	public ArrayList<Ville> findAllVilles() {
		String queryRequest = "SELECT * FROM ville_france;";
		return executeQuery(queryRequest);
	}

	@Override
	public ArrayList<Ville> findVillesAtPostalCode(String postalCode) {
		String queryRequest = "SELECT * FROM ville_france WHERE Code_postal=" + postalCode + ";";
		return executeQuery(queryRequest);
	}

}
