package Step_IHM;

import java.util.List;

import Step_DB.ILigneCommandeDAO;
import Step_DB.IProduitDAO;
import Step_DB.LigneCommande;
import Step_DB.LigneCommandeDAOImp;
import Step_DB.Produit;
import Step_DB.ProduitDAOImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LigneCommandeAddUpdateHandler {
	BLWindow blWindow;
	public LigneCommandeAddUpdateHandler(BLWindow blWindow) {
		this.blWindow = blWindow;
	}
	
	public void addClick() {
		ILigneCommandeDAO bdao = new LigneCommandeDAOImp();
		double sousTotal = Integer.valueOf(blWindow.qteTF.getText()) * blWindow.selectedProduit.getPrix();
		LigneCommande l = new LigneCommande(0, Integer.valueOf(blWindow.qteTF.getText()), sousTotal,blWindow.selectedProduit.getId(),blWindow.numBL);
		bdao.add(l);
		blWindow.total += l.getSousTotal();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setContentText("Good, ligne added ");
		alert.showAndWait();
		
	}
	
	public void updateListLigneCommande() {
		blWindow.ligneCObsList.clear();
		
		ILigneCommandeDAO ldao = new LigneCommandeDAOImp();
		List<LigneCommande> list = ldao.getAll(blWindow.numBL);
		blWindow.total =0;
		for(LigneCommande l :list) {
			blWindow.total += l.getSousTotal();	
		}
		blWindow.totalValue.setText(String.valueOf(blWindow.total));

		blWindow.ligneCObsList.addAll(list);
	}

	

	public Produit findById(long id) {
		IProduitDAO pdao = new ProduitDAOImpl();
		Produit p = pdao.getOne(id);
		return p;
	}
}
