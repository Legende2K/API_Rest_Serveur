package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private Connection con;
	
	public Database() {
	      try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twic_villes_france?serverTimezone=UTC&useSSL=false", "root", "");
	         System.out.println("Connexion réussie !");
	      }
	      catch (SQLException | ClassNotFoundException  e) {
	         System.err.println(e.getMessage());
	      }
	}
	
	public void closeDatabase() {
		try {
			this.con.close();
			System.out.println("Fermeture de la connexion");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.con;
	}
	
}