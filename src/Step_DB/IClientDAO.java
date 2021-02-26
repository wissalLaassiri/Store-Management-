package Step_DB;

import java.util.List;


public interface IClientDAO extends IDAO<Client>{
	public List<Client> findAll(String nom);

}
