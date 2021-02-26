package Step_DB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOImpl extends AbstractDAO implements IProduitDAO{

	@Override
	public void add(Produit obj) {
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO produit (designation, prix, qte, date,id_cat) VALUES (?,?,?,?,?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, obj.getDesignation());
			preparedStatement.setDouble(2, obj.getPrix());
			preparedStatement.setInt(3,obj.getQte());
			Date dateAdded = Date.valueOf(obj.getDate());
			preparedStatement.setDate(4, dateAdded);
			preparedStatement.setLong(5, obj.getIdCategorie());
			preparedStatement.executeUpdate();

			System.out.println("Data added ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Override
	public void update(Produit obj) {
		PreparedStatement preparedStatement = null;
		String sql = "Update produit set designation=?, prix = ?,qte = ? Where id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, obj.getDesignation());
			preparedStatement.setDouble(2, obj.getPrix());
			preparedStatement.setInt(3, obj.getQte());
			preparedStatement.setLong(4, obj.getId());
			preparedStatement.executeUpdate();
			System.out.println("Data updated ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}	
	@Override
	public void delete(Produit obj) {
		PreparedStatement preparedStatement = null;
		String sql = "Delete From produit Where id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, obj.getId());
			preparedStatement.executeUpdate();
			System.out.println("Data deleted ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public Produit getOne(long id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		// ecrire la requete , executer, remplir la liste
		String sql = "Select * From produit Where id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultStatement = preparedStatement.executeQuery();
			// while other data exist go to next to show it
			while(resultStatement.next()) {
				Date date = resultStatement.getDate("date");
				return new Produit(resultStatement.getLong("id"),resultStatement.getString("designation"),resultStatement.getDouble("prix"),resultStatement.getInt("qte"),date.toLocalDate(),resultStatement.getLong("id_cat"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public List<Produit> getAll() {
		List<Produit> list = new ArrayList<Produit>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		// ecrire la requete , executer, remplir la liste
		String sql = "Select * From produit";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultStatement = preparedStatement.executeQuery();
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

	@Override
	public List<Produit> findAll(String des) {
		List<Produit> list = new ArrayList<Produit>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		// ecrire la requete , executer, remplir la liste
		String sql = "Select * From produit Where designation like ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%"+des +"%");
			resultStatement = preparedStatement.executeQuery();
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
