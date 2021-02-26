package Step_IHM;

import java.util.List;

import Step_DB.Categorie;
import Step_DB.CategorieDAOImpl;
import Step_DB.ICategorieDAO;

public class CategorieHandler {
	CategorieHandler listWindow = null;
	
	public String[] getCategories() {
		ICategorieDAO catdao = new CategorieDAOImpl();
		List<Categorie> list = catdao.getAll();
		String[] tabItem = new String[5] ;
		int i=0;
		for(Categorie c:list) {
			tabItem[i] = c.getIntitule();
			i++;
		}
		return tabItem;
	}
	public Categorie getAll() {
		ICategorieDAO catdao = new CategorieDAOImpl();
		List<Categorie> list = catdao.getAll();
		
		for(Categorie c:list) {
			return c;
		}
		return null;
	}
	public long getIdCategorie(String inti) {
		ICategorieDAO catdao = new CategorieDAOImpl();
		Categorie c = catdao.getAll(inti);
		return c.getId();
	}
	
	public String getIntituleCategorie(long id) {
		ICategorieDAO catdao = new CategorieDAOImpl();
		Categorie c = catdao.getOne(id);
		return c.getIntitule();
	}
}
