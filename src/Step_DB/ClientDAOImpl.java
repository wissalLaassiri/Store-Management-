package Step_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ClientDAOImpl extends AbstractDAO implements IClientDAO {

	@Override
	public void add(Client obj) {
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO client (nom, prenom, tel,email,adresse) VALUES (?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, obj.getNom());
			preparedStatement.setString(2, obj.getPrenom());
			preparedStatement.setString(3,obj.getTel());
			preparedStatement.setString(4, obj.getEmail());
			preparedStatement.setString(5, obj.getAdresse());
			preparedStatement.executeUpdate();
			System.out.println("Client added ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Override
	public void delete(Client obj) {
		PreparedStatement preparedStatement = null;
		String sql = "Delete From client Where id= ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, obj.getId());
			preparedStatement.executeUpdate();
			System.out.println("Client deleted ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public Client getOne(long id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		String sql = "Select * From client Where id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			resultStatement = preparedStatement.executeQuery();
			while(resultStatement.next()) {
				return new Client(resultStatement.getLong("id"),resultStatement.getString("nom"),resultStatement.getString("prenom"),resultStatement.getString("tel"),resultStatement.getString("email"),resultStatement.getString("adresse"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public List<Client> getAll() {
		List<Client> list = new ArrayList<Client>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		String sql = "Select * From client";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultStatement = preparedStatement.executeQuery();
			while(resultStatement.next()) {
				list.add(new Client(resultStatement.getLong("id"),resultStatement.getString("nom"),resultStatement.getString("prenom"),resultStatement.getString("tel"),resultStatement.getString("email"),resultStatement.getString("adresse")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;

	}

	@Override
	public void update(Client obj) {
		PreparedStatement preparedStatement = null;
		String sql = "Update client set nom=?, prenom = ?,tel = ?,email=?, adresse=? Where id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, obj.getNom());
			preparedStatement.setString(2, obj.getPrenom());
			preparedStatement.setString(3, obj.getTel());
			preparedStatement.setString(4, obj.getEmail());
			preparedStatement.setString(5, obj.getAdresse());
			preparedStatement.setLong(6, obj.getId());
			preparedStatement.executeUpdate();
			System.out.println("Client updated ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Override
	public List<Client> findAll(String nom) {
		List<Client> list = new ArrayList<Client>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		String sql = "Select * From client Where nom like ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%"+nom +"%");
			resultStatement = preparedStatement.executeQuery();
			while(resultStatement.next()) {
				list.add(new Client(resultStatement.getLong("id"),resultStatement.getString("nom"),resultStatement.getString("prenom"),resultStatement.getString("tel"),resultStatement.getString("email"),resultStatement.getString("adresse")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	

}
