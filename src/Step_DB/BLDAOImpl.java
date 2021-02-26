package Step_DB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BLDAOImpl extends AbstractDAO implements IBLDAO {

	@Override
	public void add(BL obj) {
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO bl (date,idClient) VALUES (?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			Date dateAdded = Date.valueOf(obj.getDate());
			preparedStatement.setDate(1, dateAdded);
			//preparedStatement.setDouble(2, obj.getTotal());
			preparedStatement.setLong(2, obj.getIdclient());
			preparedStatement.executeUpdate();
			System.out.println("Ligne de commande added ..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(BL obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(BL obj) {
		PreparedStatement preparedStatement = null;
		String sql = "Delete From bl Where id= ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, obj.getId());
			preparedStatement.executeUpdate();
			System.out.println("BL deleted ..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public BL getOne(long id) {
return null;
	}

	
	public List<BL> getAll(Client obj) {
		List<BL> list = new ArrayList<BL>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;

		// ecrire la requete , executer, remplir la liste
		String sql = "Select * From bl Where idClient =? ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, obj.getId());

			resultStatement = preparedStatement.executeQuery();
			// while other data exist go to next to show it
			while (resultStatement.next()) {
				Date date = resultStatement.getDate("date");
				double total = getTotal(resultStatement.getLong("id"));
				list.add(new BL(resultStatement.getLong("id"), date.toLocalDate(),total,resultStatement.getLong("idClient")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public List<BL> findAll(String client) {
		List<BL> list = new ArrayList<BL>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		String sql = "SELECT * FROM bl inner join client c on c.id = bl.idClient where c.nom like ? or c.prenom like ? ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%"+client +"%");
			preparedStatement.setString(2, "%"+client +"%");
			resultStatement = preparedStatement.executeQuery();
			// while other data exist go to next to show it
			while(resultStatement.next()) {
				Date date = resultStatement.getDate("date");
				double total = getTotal(resultStatement.getLong("id"));
				list.add(new BL(resultStatement.getLong("id"), date.toLocalDate(),total,resultStatement.getLong("idClient")));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
		}


	public Long getLastid() {
		long id = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		String sql = "SELECT MAX(id) FROM bl";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultStatement = preparedStatement.executeQuery();
			while (resultStatement.next()) {
				id = resultStatement.getLong("MAX(id)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public double getTotal(long id) {
		double total = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		String sql = "select SUM(sousTotal) from lignecommande WHERE idBL = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultStatement = preparedStatement.executeQuery();
			while (resultStatement.next()) {
				total = resultStatement.getDouble("SUM(sousTotal)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
		
	}

	@Override
	public List<BL> getAll() {
		List<BL> list = new ArrayList<BL>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;

		String sql = "Select * From bl ";
		try {
			preparedStatement = connection.prepareStatement(sql);

			resultStatement = preparedStatement.executeQuery();
			// while other data exist go to next to show it
			while (resultStatement.next()) {
				Date date = resultStatement.getDate("date");
				double total = getTotal(resultStatement.getLong("id"));
				list.add(new BL(resultStatement.getLong("id"), date.toLocalDate(),total,resultStatement.getLong("idClient")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
