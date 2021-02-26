package Step_IHM;

import java.util.List;

import Step_DB.IProduitDAO;
import Step_DB.Produit;
import Step_DB.ProduitDAOImpl;

public class ProduitsListHandler {
	ProduitsListWindow listWindow = null;
	BLWindow blWindow = null;
	public ProduitsListHandler(ProduitsListWindow listWindow) {
		this.listWindow = listWindow;
	}
	public ProduitsListHandler(BLWindow blWindow) {
		this.blWindow = blWindow;
	}

	public void updateListProduitsWindow() {
		IProduitDAO pdao = new ProduitDAOImpl();
		List<Produit> list = pdao.getAll();
		listWindow.produitObsList.addAll(list);
		calculerTotal();
	}

	public void updateListProduitsBLWindow() {
		IProduitDAO pdao = new ProduitDAOImpl();
		List<Produit> list = pdao.getAll();
		blWindow.produitObsList.addAll(list);
	}
	
	private void calculerTotal() {
		double total = 0;
		for (Produit p : listWindow.produitObsList)
			total += p.getTotal();
		listWindow.totalValueLabel.setText(total + "");
	}
	public void getSearchProduct() {
		IProduitDAO pdao = new ProduitDAOImpl();
		listWindow.produitObsList.clear();
		List<Produit> list = pdao.findAll(listWindow.searchProduitTF.getText());
		listWindow.produitObsList.addAll(list);
	}
	public Produit getOneById(long id) {
		IProduitDAO pdao = new ProduitDAOImpl();
		Produit p = pdao.getOne(id);
		return p;
	}
}
