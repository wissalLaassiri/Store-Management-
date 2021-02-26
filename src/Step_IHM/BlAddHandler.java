package Step_IHM;

import java.time.LocalDate;

import Step_DB.BL;
import Step_DB.BLDAOImpl;
import Step_DB.IBLDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BlAddHandler {
	BLWindow blWindow;
	public BlAddHandler(BLWindow blWindow) {
		this.blWindow = blWindow;
	}
	
	public void addClick(double total) {
		IBLDAO bdao = new BLDAOImpl();
		try {
			LocalDate date = blWindow.dateBlTF.getValue();

			BL b = new BL(0, date,total,blWindow.client.getId());
			bdao.add(b);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setContentText("Good, Bon de livraison added ");
			alert.showAndWait();
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Exception");
			alert.setContentText("No, you should add the date ");
			alert.showAndWait();
		}
		
		
	}
	public long getLastId() {
		IBLDAO bdao = new BLDAOImpl();
		long id;
		id = bdao.getLastid();
		return id;
	}
	
}
