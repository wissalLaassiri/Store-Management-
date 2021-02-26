package Step_DB;

import java.util.List;

public interface IProduitDAO extends IDAO<Produit>{
	public List<Produit> findAll(String des);

}
