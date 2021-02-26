package Step_IHM;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormProduitWindow {
	ProduitAddHandler handler = new ProduitAddHandler(this);
	CategorieHandler catHandler = new CategorieHandler();
	VBox root = new VBox();
	final ObservableList<String> strings = FXCollections.observableArrayList();
	HBox buttonBox = new HBox(); // pour les boutton dans la meme ligne horizental
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouveau produit");

	Label productDesignationLabel = new Label("Designation");
	TextField productDesignationTF = new TextField();
	
	Label productPriceLabel = new Label("Price");
	TextField productPriceTF = new TextField();
	
	Label productQuantityLabel = new Label("Quantity");
	TextField productQuantityTF = new TextField();
	
	Label productCategoryLabel = new Label("Select Category");
	ChoiceBox<String> choiceCat = new ChoiceBox<String>();
    
	
	Label productDateLabel = new Label("Date ");
	DatePicker productDatePicker = new DatePicker();
	
	Button productAddButton = new Button("Ajouter");
	Button productAnnButton = new Button("Annuler");
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Nouveau Produit");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void fixChoiceBox() {
	    ObservableList<String> choiceList = choiceCat.getItems();
	    choiceList.addAll(catHandler.getCategories());
	    }
	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(productDesignationLabel,productDesignationTF);
		root.getChildren().addAll(productPriceLabel,productPriceTF);
		root.getChildren().addAll(productQuantityLabel,productQuantityTF);
		root.getChildren().addAll(productDateLabel,productDatePicker);
		fixChoiceBox();
		root.getChildren().addAll(productCategoryLabel,choiceCat);
		
		buttonBox.getChildren().addAll(productAddButton,productAnnButton);
		root.getChildren().add(buttonBox);
		

	}
	private void addStylesToNodes() {
		scene.getStylesheets().add("css/style.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		productDesignationLabel.getStyleClass().add("labelForm");
		productPriceLabel.getStyleClass().add("labelForm");
		productQuantityLabel.getStyleClass().add("labelForm");
		productDateLabel.getStyleClass().add("labelForm");
		productCategoryLabel.getStyleClass().add("labelForm");
		productAddButton.getStyleClass().add("button");
		productAnnButton.getStyleClass().add("button");
		//ajouter un espacement entre les labels 
		root.setSpacing(15);
		buttonBox.setSpacing(10);
	}

	private void addEvents() {
		productAnnButton.setOnAction(event->{
			window.close();
		});
		productAddButton.setOnAction(event->{
			System.out.println("selected item"+choiceCat.getSelectionModel().getSelectedItem());
			String item =choiceCat.getSelectionModel().getSelectedItem();
			long id = catHandler.getIdCategorie(item);
			System.out.println("\n"+id);
			handler.addClick(id);
			window.close();
		});
		window.setOnCloseRequest(event->{
			event.consume();
		});
	}

	public FormProduitWindow() {
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		addEvents();
		window.show();
		
	}

}
