package Step_IHM;


import java.time.LocalDate;
import Step_DB.Client;
import Step_DB.LigneCommande;
import Step_DB.Produit;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BLWindow {
	ProduitsListHandler handler = new ProduitsListHandler(this);
	LigneCommandeAddUpdateHandler handler2 = new LigneCommandeAddUpdateHandler(this);
	BlAddHandler handler3 = new BlAddHandler(this);
	LigneCommandeDelHandler handler4 = new LigneCommandeDelHandler(this);

	Client client;
	Produit selectedProduit;
	LigneCommande selectedLC;
	long numBL = handler3.getLastId() + 1;
	double total = 0;
	Stage window = new Stage();
	VBox s = new VBox();
	HBox screen = new HBox();
	HBox buttons = new HBox();
	VBox root = new VBox();
	VBox root2 = new VBox();
	VBox products = new VBox();
	HBox detailsBL = new HBox();
	VBox detailCommande = new VBox();
	HBox detailsCommande = new HBox();
	HBox topButtons = new HBox();
	HBox buttonBox = new HBox();
	HBox reglement = new HBox();
	HBox details = new HBox();
	Scene scene = new Scene(s);
	Label TitleBLLabel = new Label("Détail de BL");
	Label TitleRegLabel = new Label("Réglement de BL");
	Label TitleCommLabel = new Label("Lignes de commande");
	// buttons on top
	Button enregistrer = new Button("enregistrer");
	Button modifier = new Button("Modifier Ligne");
	Button supprimer = new Button("Supprimer");
	Button Reglement = new Button("Réglement");
	Button quitter = new Button("Quitter");

	// details fields
	Label numBlLabel = new Label("N°BL");
	TextField numBlTF = new TextField();
	Label clientLabel = new Label("Client");
	TextField clientNomTF = new TextField();
	Label dateBlLabel = new Label("Date");
	DatePicker dateBlTF = new DatePicker();
	// ligne de comande fields
	Label codeLabel = new Label(" Code.B");
	TextField codeTF = new TextField();
	Label designBLabel = new Label(" Design");
	TextField desigTF = new TextField();
	Label serieLabel = new Label("N° série");
	TextField serieTF = new TextField();
	Label qteLabel = new Label(" Qte");
	TextField qteTF = new TextField("1");
	Label prixLabel = new Label(" Prix");
	TextField prixTF = new TextField();
	// Reglement fields
	Label totalLabel = new Label("Total");
	TextField totalValue = new TextField();
	
	TableColumn<Produit, Long> IdCol = new TableColumn<>("Id");
	TableColumn<Produit, String> designationCol = new TableColumn<>("Designation");
	TableColumn<Produit, Double> prixCol = new TableColumn<>("Prix");
	TableColumn<Produit, Integer> qteCol = new TableColumn<>("Qte");
	TableColumn<Produit, Double> totalCol = new TableColumn<>("Total");
	TableColumn<Produit, LocalDate> dateCol = new TableColumn<>("Date");

	TableColumn<LigneCommande, String> referenceCol = new TableColumn<>("Référence");
	TableColumn<LigneCommande, String> designationLcCol = new TableColumn<>("Designation");
	TableColumn<LigneCommande, String> prixLcCol = new TableColumn<>("Prix");
	TableColumn<LigneCommande, Integer> qteLcCol = new TableColumn<>("Qte");
	TableColumn<LigneCommande, Double> sousTotalCol = new TableColumn<>("Sous Total");

	TableView<Produit> produitsTableView = new TableView<>();
	TableView<LigneCommande> ligneCTableView = new TableView<>();
	ObservableList<Produit> produitObsList = FXCollections.observableArrayList();
	ObservableList<LigneCommande> ligneCObsList = FXCollections.observableArrayList();
	Button addLigneButton = new Button("+");
	Button delLigneBtn = new Button("-");

	private void addColumnsToTableView() {
		produitsTableView.getColumns().addAll(IdCol, designationCol, prixCol, qteCol, totalCol, dateCol);
		produitsTableView.setItems(produitObsList);
		produitsTableView.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() > 1) {
				onEdit();
			}
		});
		ligneCTableView.getColumns().addAll(referenceCol, designationLcCol,prixLcCol, qteLcCol, sousTotalCol);
		ligneCTableView.setItems(ligneCObsList);

		ligneCTableView.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() > 1) {
				onEdit();
			}
		});
	}

	public void onEdit() {
		// check the table's selected item and get selected item
		if (produitsTableView.getSelectionModel().getSelectedItem() != null) {
			selectedProduit = produitsTableView.getSelectionModel().getSelectedItem();
			getDetails();
		}
		if (ligneCTableView.getSelectionModel().getSelectedItem() != null) {
			selectedLC = ligneCTableView.getSelectionModel().getSelectedItem();
			getDetails();
		}
	}

	private void getDetails() {
		desigTF.setText(selectedProduit.getDesignation());
		prixTF.setText(String.valueOf(selectedProduit.getPrix()));
	}

	private void addNodesToPane() {
		
		VBox labels1 = new VBox();
		VBox fields1 = new VBox();
		clientNomTF.setText(this.client.getNom() + " " + this.client.getPrenom());
		clientNomTF.setDisable(true);
		numBlTF.setText(String.valueOf(this.numBL));
		numBlTF.setDisable(true);
		labels1.getChildren().addAll(numBlLabel, clientLabel, dateBlLabel);
		fields1.getChildren().addAll(numBlTF, clientNomTF, dateBlTF);
		labels1.setSpacing(9);
		detailsBL.getChildren().addAll(labels1, fields1);
		VBox labels = new VBox();
		VBox fields = new VBox();
		labels.getChildren().addAll(codeLabel, designBLabel, serieLabel, prixLabel, qteLabel);
		fields.getChildren().addAll(codeTF, desigTF, serieTF, prixTF, qteTF);
		labels.setSpacing(9);
		detailsCommande.getChildren().addAll(labels, fields, addLigneButton, delLigneBtn);
		topButtons.getChildren().addAll(enregistrer, modifier, supprimer, Reglement, quitter);
		products.getChildren().add(produitsTableView);
		root.getChildren().addAll(TitleBLLabel, detailsBL, detailsCommande, products);
		reglement.getChildren().addAll(totalLabel, totalValue);
		root2.getChildren().addAll(TitleRegLabel, reglement, TitleCommLabel, ligneCTableView);
		screen.getChildren().addAll(root, root2);
		s.getChildren().addAll(topButtons, screen);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1300);
		window.setHeight(650);
		window.setTitle("Gestion des bons de livraison");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/style.css");
		TitleBLLabel.getStyleClass().add("labelTitle");
		TitleCommLabel.getStyleClass().add("labelTitle");
		TitleRegLabel.getStyleClass().add("labelTitle");
		produitsTableView.setPrefHeight(200);
		root.getStyleClass().add("updateVBox");
		detailsBL.getStyleClass().add("vbox_style");
		addLigneButton.getStyleClass().add("longButton");
		delLigneBtn.getStyleClass().add("longButton");
		topButtons.setSpacing(5);
		detailsCommande.setSpacing(10);
		detailsBL.setSpacing(10);
		root.setSpacing(8);
		buttonBox.setSpacing(10);
		root.setSpacing(20);
		root2.setSpacing(20);
		root.setPrefWidth(600);
		root2.setPrefWidth(600);
		screen.setSpacing(10);
		TitleBLLabel.setPrefWidth(root.getPrefWidth());
		TitleRegLabel.setPrefWidth(root2.getPrefWidth());
		TitleCommLabel.setPrefWidth(root2.getPrefWidth());
	}

	private void updateColumns() {
		IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		IdCol.setPrefWidth(100);
		designationCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
		designationCol.setPrefWidth(250);
		prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
		prixCol.setPrefWidth(50);
		qteCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
		qteCol.setPrefWidth(50);
		totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
		totalCol.setPrefWidth(100);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateCol.setPrefWidth(60);
	}

	private void updateLCColumns() {
		referenceCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		referenceCol.setPrefWidth(50);
		designationLcCol.setCellValueFactory(cellData -> {
			LigneCommande l = cellData.getValue();
			Produit p = handler2.findById(l.getIdproduit());
			return new ReadOnlyStringWrapper(p.getDesignation());
		});
		
		designationLcCol.setPrefWidth(100);
		prixLcCol.setCellValueFactory(cellData -> {
			LigneCommande l = cellData.getValue();
			Produit p = handler2.findById(l.getIdproduit());
			return new ReadOnlyStringWrapper(String.valueOf(p.getPrix()));
		});
		
		qteLcCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
		qteLcCol.setPrefWidth(50);
		sousTotalCol.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));
		sousTotalCol.setPrefWidth(50);
	}


	private void addEvents() {
		addLigneButton.setOnAction(event -> {
			handler2.addClick();
			totalValue.setText(String.valueOf(total));
			handler2.updateListLigneCommande();
		});
		delLigneBtn.setOnAction(event -> {
			handler4.delClick(selectedLC);
			handler2.updateListLigneCommande();
		});
		enregistrer.setOnAction(event -> {
			handler3.addClick(this.total);
		});
		quitter.setOnAction(event -> {
			window.close();
		});
		supprimer.setOnAction(event ->{
			handler4.delAll(numBL);
			handler2.updateListLigneCommande();
		});
	}

	public BLWindow(Client selectedClient) {
		this.client = selectedClient;
		initWindow();
		addStylesToNodes();
		updateColumns();
		updateLCColumns();
		addColumnsToTableView();
		
		handler.updateListProduitsBLWindow();
		handler2.updateListLigneCommande();

		addEvents();
		addNodesToPane();
		window.show();
	}

}
