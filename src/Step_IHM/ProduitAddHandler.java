package Step_IHM;

import java.time.LocalDate;

import Step_DB.IProduitDAO;
import Step_DB.Produit;
import Step_DB.ProduitDAOImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProduitAddHandler {

	FormProduitWindow addWindow = null;
	public ProduitAddHandler(FormProduitWindow addWin) {
		this.addWindow = addWin;
	}
	public void addClick(long id) {
		IProduitDAO pdao = new ProduitDAOImpl();

		LocalDate date = addWindow.productDatePicker.getValue();
		Produit p = new Produit(0, addWindow.productDesignationTF.getText(), Double.valueOf(addWindow.productPriceTF.getText()), Integer.valueOf(addWindow.productQuantityTF.getText()), date,id);
		pdao.add(p);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setContentText("Good, Product added ");

		alert.showAndWait();
		
	}
	
	
	
}
