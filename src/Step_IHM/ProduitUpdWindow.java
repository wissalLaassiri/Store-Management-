package Step_IHM;

import Step_DB.Produit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProduitUpdWindow {
	ProduitUpdHandler handler = new ProduitUpdHandler(this);
	VBox root = new VBox();
	HBox buttonBox = new HBox(); // pour les boutton dans la meme ligne horizental
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Mettre à jour produit");
	Produit p ;
	Label productDesignationLabel = new Label("Nouvelle Designation");
	TextField productDesignationTF = new TextField();
	
	Label productPriceLabel = new Label("Nouveau Prix");
	TextField productPriceTF = new TextField();
	
	Label productQuantityLabel = new Label(" Nouvelle Quantité");
	TextField productQuantityTF = new TextField();
	
	Button productUpdButton = new Button("Mettre à jour");
	Button productAnnButton = new Button("Annuler");
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Mettre à jour Produit");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(productDesignationLabel,productDesignationTF);
		root.getChildren().addAll(productPriceLabel,productPriceTF);
		root.getChildren().addAll(productQuantityLabel,productQuantityTF);
		
		buttonBox.getChildren().addAll(productUpdButton,productAnnButton);
		root.getChildren().add(buttonBox);

	}
	private void addStylesToNodes() {
		scene.getStylesheets().add("css/style.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		productDesignationLabel.getStyleClass().add("labelForm");
		productPriceLabel.getStyleClass().add("labelForm");
		productQuantityLabel.getStyleClass().add("labelForm");
		productUpdButton.getStyleClass().add("button");
		productAnnButton.getStyleClass().add("button");
		//ajouter un espacement entre les labels 
		root.setSpacing(15);
		buttonBox.setSpacing(10);
	}

	private void addEvents() {
		productAnnButton.setOnAction(event->{
			window.close();
		});
		productUpdButton.setOnAction(event->{
			handler.updateClick(p);
			window.close();
		});
		window.setOnCloseRequest(event->{
			event.consume();
		});
	}

	public ProduitUpdWindow(Produit obj) {
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		this.p = obj;
		addEvents();
		window.show();
		
	}

}
