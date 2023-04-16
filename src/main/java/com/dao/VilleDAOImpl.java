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
	
	private void closeResources(ResultSet statementResult, PreparedStatement statementRequest, Database database) {
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
	
	private Ville createVilleFromResultSet(ResultSet statementResult) throws SQLException {
        Ville ville = new Ville();
        ville.setCodeCommune(statementResult.getString("Code_commune_INSEE"));
        ville.setNomCommune(statementResult.getString("Nom_commune"));
        ville.setCodePostal(statementResult.getString("Code_postal"));
        ville.setLibelleAcheminement(statementResult.getString("Libelle_acheminement"));
        ville.setLigne(statementResult.getString("Ligne_5"));
        ville.setLatitude(statementResult.getString("Latitude"));
        ville.setLongitude(statementResult.getString("Longitude"));
        return ville;
    }
	
	private ArrayList<Ville> executeQuery(String queryRequest) {
        ArrayList<Ville> listVille = new ArrayList<Ville>();
        Database database = new Database();
        PreparedStatement statementRequest = null;
        ResultSet statementResult = null;
        try {
            statementRequest = database.getConnection().prepareStatement(queryRequest);
            statementResult = statementRequest.executeQuery();
            while (statementResult.next()) {
                listVille.add(createVilleFromResultSet(statementResult));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(statementResult, statementRequest, database);
        }
        return listVille;
    }
	

	@Override
	public ArrayList<Ville> findAllVilles() {
		String queryRequest = "SELECT * FROM ville_france;";
		return executeQuery(queryRequest);
	}
	
	@Override
	public ArrayList<Ville> findVillesAtCommuneCode(String codeCommune) {
		String queryRequest = "SELECT * FROM ville_france WHERE Code_commune_INSEE='" + codeCommune + "';";
		return executeQuery(queryRequest);
	}

	@Override
	public ArrayList<Ville> findVillesAtPostalCode(String postalCode) {
		String queryRequest = "SELECT * FROM ville_france WHERE Code_postal='" + postalCode + "';";
		return executeQuery(queryRequest);
	}

	@Override
	public void addVille(Ville ville) {
		Database database = new Database();
		PreparedStatement statementRequest = null;
		try {
			String queryRequest = "INSERT INTO ville_france(Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?)";
			statementRequest = database.getConnection().prepareStatement(queryRequest);
			statementRequest.setString(1, ville.getCodeCommune());
            statementRequest.setString(2, ville.getNomCommune());
            statementRequest.setString(3, ville.getCodePostal());
            statementRequest.setString(4, ville.getLibelleAcheminement());
            statementRequest.setString(5, ville.getLigne());
            statementRequest.setString(6, ville.getLatitude());
            statementRequest.setString(7, ville.getLongitude());
			statementRequest.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(null,statementRequest,database);
		}
	}

	@Override
	public void updateVille(Ville ville) {
	    Database database = new Database();
	    PreparedStatement statementRequest = null;
	    try {
	        String queryRequest = "UPDATE ville_france SET Nom_commune=?, Ligne_5=? WHERE Code_commune_INSEE=?";
	        statementRequest = database.getConnection().prepareStatement(queryRequest);
	        statementRequest.setString(1, ville.getNomCommune());
	        statementRequest.setString(2, ville.getLigne());
	        statementRequest.setString(3, ville.getCodeCommune());
	        statementRequest.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statementRequest != null) {
	                statementRequest.close();
	            }
	            database.closeDatabase();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public void deleteVille(String codeCommune) {
	    Database database = new Database();
	    PreparedStatement statementRequest = null;
	    try {
	        String queryRequest = "DELETE FROM ville_france WHERE Code_commune_INSEE=?";
	        statementRequest = database.getConnection().prepareStatement(queryRequest);
	        statementRequest.setString(1, codeCommune);
	        statementRequest.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	closeResources(null,statementRequest,database);
	    }
	}
	
	

}
