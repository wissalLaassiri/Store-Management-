package Step_DB;

import java.util.List;

public interface ILigneCommandeDAO extends IDAO<LigneCommande>{

	List<LigneCommande> getAll(long numBL);
	void deleteAll(long numBL);

}
