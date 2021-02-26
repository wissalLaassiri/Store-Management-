package Step_DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDataAccess {
	// charger le driver 
	// 1- se connecter à la BDD
	// requete SQL
	// envoyer requette
	// recuperer produits
	// renvoyer produits
	
	// ============== 1 =============
	// besoin de :
	// chaine de connexion / nom de bdd / user / pwd
	
	
	String db = "magasin-db";
	String user ="root";
	String pwd ="";
	String url ="jdbc:mysql://localhost:3306/"+db;
	Connection connection= null;
	
	public ProduitDataAccess() {
		try {
			connection = DriverManager.getConnection(url,user,pwd);
			System.out.println("DB connected ...");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	List<Produit> getProduitByKeyWord(String designation){
		List<Produit> list = new ArrayList<Produit>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		// ecrire la requete , executer, remplir la liste
		String sql = "Select * From produit Where designation like ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%"+designation +"%");
			resultStatement = preparedStatement.executeQuery();
			System.out.println("Requete done ..");
			// while other data exist go to next to show it
			while(resultStatement.next()) {
				Date date = resultStatement.getDate("date");
				list.add(new Produit(resultStatement.getLong("id"),resultStatement.getString("designation"),resultStatement.getDouble("prix"),resultStatement.getInt("qte"),date.toLocalDate(),resultStatement.getLong("id_cat")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
}
