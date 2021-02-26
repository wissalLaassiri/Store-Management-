package Step_IHM;

import Step_DB.IProduitDAO;
import Step_DB.Produit;
import Step_DB.ProduitDAOImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProduitDelHandler {

	// DeleteProduitWindow delWindow = null;

	public void delClick(Produit obj) {
		IProduitDAO pdao = new ProduitDAOImpl();
		

		pdao.delete(obj);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setContentText("Good, Product deleted ");

		alert.showAndWait();
	}

}
