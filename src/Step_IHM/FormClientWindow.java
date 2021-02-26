package Step_IHM;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormClientWindow {
	ClientAddHandler handler = new ClientAddHandler(this);
	VBox root = new VBox();
	HBox buttonBox = new HBox(); 
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouveau client");

	Label clientNomLabel = new Label("Nom");
	TextField clientNomTF = new TextField();
	
	Label clientPrenomLabel = new Label("Prenom");
	TextField clientPrenomTF = new TextField();
	
	Label clientTelLabel = new Label("Telephone");
	TextField clientTelTF = new TextField();
	Label clientEmailLabel = new Label("Email");
	TextField clientEmailTF = new TextField();
	Label clientAdresseLabel = new Label("Adresse");
	TextField clientAdresseTF = new TextField();
	
	Button clientAddButton = new Button("Ajouter");
	Button clientAnnButton = new Button("Annuler");
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Nouveau Client");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(clientNomLabel,clientNomTF);
		root.getChildren().addAll(clientPrenomLabel,clientPrenomTF);
		root.getChildren().addAll(clientTelLabel,clientTelTF);
		root.getChildren().addAll(clientEmailLabel,clientEmailTF);
		root.getChildren().addAll(clientAdresseLabel,clientAdresseTF);
		
		buttonBox.getChildren().addAll(clientAddButton,clientAnnButton);
		root.getChildren().add(buttonBox);

	}
	private void addStylesToNodes() {
		scene.getStylesheets().add("css/style.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		clientNomLabel.getStyleClass().add("labelForm");
		clientPrenomLabel.getStyleClass().add("labelForm");
		clientTelLabel.getStyleClass().add("labelForm");
		clientAdresseLabel.getStyleClass().add("labelForm");
		clientEmailLabel.getStyleClass().add("labelForm");
		clientAddButton.getStyleClass().add("button");
		clientAnnButton.getStyleClass().add("button");
		root.setSpacing(15);
		buttonBox.setSpacing(10);
	}

	private void addEvents() {
		clientAnnButton.setOnAction(event->{
			window.close();
		});
		clientAddButton.setOnAction(event->{
			handler.addClick();
			window.close();
		});
		window.setOnCloseRequest(event->{
			event.consume();
		});
	}

	public FormClientWindow() {
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		addEvents();
		window.show();
		
	}
}
