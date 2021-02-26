package Step_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOImpl extends AbstractDAO implements ICategorieDAO {

	@Override
	public void add(Categorie obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Categorie obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Categorie obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Categorie getOne(long id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		String sql = "SELECT * FROM produit as p inner join categorie as c on p.id_cat = c.id WHERE p.id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultStatement = preparedStatement.executeQuery();
			// while other data exist go to next to show it
			while(resultStatement.next()) {
				return new Categorie(resultStatement.getLong("id"),resultStatement.getString("code"),resultStatement.getString("intitule"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public List<Categorie> getAll() {
		List<Categorie> list = new ArrayList<Categorie>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;

		// ecrire la requete , executer, remplir la liste
		String sql = "Select * From categorie ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultStatement = preparedStatement.executeQuery();
			// while other data exist go to next to show it
			while (resultStatement.next()) {
				list.add(new Categorie(resultStatement.getLong("id"), resultStatement.getString("code"),
						resultStatement.getString("intitule")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Categorie getAll(String inti) {
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;

		String sql = "Select * From categorie Where intitule = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, inti);
			resultStatement = preparedStatement.executeQuery();
			while (resultStatement.next()) {
				return new Categorie(resultStatement.getLong("id"),resultStatement.getString("code"),resultStatement.getString("intitule"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
