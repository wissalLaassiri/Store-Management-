package Step_IHM;


import Step_DB.IProduitDAO;
import Step_DB.Produit;
import Step_DB.ProduitDAOImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProduitUpdHandler {
	ProduitUpdWindow updWindow = null;

	public ProduitUpdHandler(ProduitUpdWindow updWindow) {
		this.updWindow = updWindow;
	}

	public void updateClick(Produit obj) {
		IProduitDAO pdao = new ProduitDAOImpl();
		Produit p = pdao.getOne(obj.getId());
		p.setDesignation(String.valueOf(updWindow.productDesignationTF.getText()));
		p.setPrix(Double.valueOf(updWindow.productPriceTF.getText()));
		p.setQte(Integer.valueOf(updWindow.productQuantityTF.getText()));
		pdao.update(p);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setContentText("Good, Product updated ");

		alert.showAndWait();

	}
}
