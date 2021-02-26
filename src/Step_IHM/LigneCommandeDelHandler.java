package Step_IHM;

import Step_DB.ILigneCommandeDAO;
import Step_DB.LigneCommande;
import Step_DB.LigneCommandeDAOImp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LigneCommandeDelHandler {
	BLWindow blWindow;
	public LigneCommandeDelHandler(BLWindow blWindow) {
		this.blWindow = blWindow;
	}
	public void delClick(LigneCommande obj) {
		ILigneCommandeDAO ldao = new LigneCommandeDAOImp();
			ldao.delete(obj);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Delete Ligne de commande");
			alert.setContentText("Done, Ligne deleted ");

			alert.showAndWait();
		}
	public void delAll(long idBl) {
		ILigneCommandeDAO ldao = new LigneCommandeDAOImp();
			ldao.deleteAll(blWindow.numBL);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Delete Ligne de commande");
			alert.setContentText("Done, Lignes deleted ");

			alert.showAndWait();	
	}
}
