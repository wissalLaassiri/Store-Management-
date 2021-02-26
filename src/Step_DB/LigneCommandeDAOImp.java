package Step_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeDAOImp extends AbstractDAO implements ILigneCommandeDAO {

	@Override
	public void add(LigneCommande obj) {
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO lignecommande (qte, sousTotal, idproduit,idBL) VALUES (?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, obj.getQte());
			preparedStatement.setDouble(2, obj.getSousTotal());
			preparedStatement.setLong(3,obj.getIdproduit());
			preparedStatement.setLong(4,obj.getIdBl());
			preparedStatement.executeUpdate();
			System.out.println("Ligne de commande added ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public void update(LigneCommande obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(LigneCommande obj) {
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM lignecommande WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, obj.getId());
			preparedStatement.executeUpdate();
			System.out.println("Ligne deleted ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Override
	public void deleteAll(long idBl) {
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM lignecommande WHERE idBL = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, idBl);
			preparedStatement.executeUpdate();
			System.out.println("All Ligne deleted ..");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public LigneCommande getOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LigneCommande> getAll(long idBl) {
		List<LigneCommande> list = new ArrayList<LigneCommande>();
		PreparedStatement preparedStatement = null;
		ResultSet resultStatement;
		
		String sql = "select id,qte, sousTotal, idproduit,idBL,sum(sousTotal), idproduit,sum(qte) from lignecommande where idBL = ? GROUP BY idproduit";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, idBl);
			resultStatement = preparedStatement.executeQuery();
			while(resultStatement.next()) {
				list.add(new LigneCommande(resultStatement.getLong("id"),resultStatement.getInt("sum(qte)"),resultStatement.getDouble("sum(sousTotal)"),resultStatement.getLong("idproduit"),resultStatement.getLong("idBL")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;

	}
	@Override
	public List<LigneCommande> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
