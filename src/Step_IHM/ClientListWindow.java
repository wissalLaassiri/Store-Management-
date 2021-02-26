package Step_IHM;


import Step_DB.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ClientListWindow {
	ClientListHandler handler = new ClientListHandler(this);
	ClientDelHandler delhandler = new ClientDelHandler();
	ClientUpdHandler updhandler = new ClientUpdHandler(this);
	Stage window = new Stage();
	HBox screen = new HBox();
	VBox root = new VBox();
	VBox updRoot = new VBox();
	VBox actions = new VBox(); 
	HBox searchHB = new HBox();
	HBox buttonBox = new HBox();

	Scene scene = new Scene(screen);
	Label titleLabel = new Label("Liste des clients");
	
	Label searchClientLabel = new Label("chercher Client");
	TextField searchClientTF = new TextField();
	Button searchBtn = new Button("chercher");
	
	// Update fields
	Label updTitleLabel = new Label("Modification");
	Label clientNomLabel = new Label(" Nouveau Nom");
	TextField clientNomTF = new TextField();

	Label clientPrenomLabel = new Label(" Nouveau Prenom");
	TextField clientPrenomTF = new TextField();
	Label clientTelLabel = new Label(" Nouvelle numéro téléphone");
	TextField clientTelTF = new TextField();
	Label clientEmailLabel = new Label(" Nouveau email");
	TextField clientEmailTF = new TextField();
	Label clientAdresseLabel = new Label(" Nouvele adresse");
	TextField clientAdresseTF = new TextField();
	
	TableColumn<Client, Long> IdCol = new TableColumn<>("Id");
	TableColumn<Client, String> nomCol = new TableColumn<>("Nom");
	TableColumn<Client, String> prenomCol = new TableColumn<>("Prenom");
	TableColumn<Client, String> telCol = new TableColumn<>("Tel");
	TableColumn<Client, String> emailCol = new TableColumn<>("Email");
	TableColumn<Client, String> adresseCol = new TableColumn<>("Adresse");
    TableColumn<Client, Void> colBtn = new TableColumn<>("Action");
    Client selectedClient ;

	TableView<Client> ClientsTableView = new TableView<>();
	ObservableList<Client> clientObsList = FXCollections.observableArrayList();
	
	Button clientUpdButton = new Button("Mettre à jour");
	Button delBtn = new Button("Supprimer");
	Button clientAnnButton = new Button("Annuler");
	//BL VBOX
	Label actionsLabel = new Label("Actions");

	Button addBLButton = new Button("Ajouter BL");
	Button listeBLButton = new Button("Liste des BL");
	
	private void addColumnsToTableView() {
		ClientsTableView.getColumns().addAll(IdCol, nomCol, prenomCol, telCol,emailCol,adresseCol);
		ClientsTableView.setItems(clientObsList);

		ClientsTableView.setOnMouseClicked((MouseEvent event) -> {
		    if (event.getClickCount() > 1) {
		        onEdit();
		    }
		});	
	}
	
	public void onEdit() {
	    // check the table's selected item and get selected item
	    if (ClientsTableView.getSelectionModel().getSelectedItem() != null) {
	        selectedClient = ClientsTableView.getSelectionModel().getSelectedItem();
	        getDetails();
	        
	    }
	}
	private void getDetails() {
		clientNomTF.setText(selectedClient.getNom());
		clientPrenomTF.setText(selectedClient.getPrenom());
		clientTelTF.setText(selectedClient.getTel());
		clientEmailTF.setText(selectedClient.getEmail());
		clientAdresseTF.setText(selectedClient.getAdresse());
	}
	private void addNodesToPane() {
		searchHB.getChildren().addAll(searchClientLabel,searchClientTF, searchBtn);
		buttonBox.getChildren().addAll(clientUpdButton,delBtn,clientAnnButton);
		actions.getChildren().addAll(actionsLabel,addBLButton,listeBLButton);
		root.getChildren().addAll(titleLabel,searchHB, ClientsTableView);
		updRoot.getChildren().add(updTitleLabel);
		updRoot.getChildren().addAll(clientNomLabel,clientNomTF);
		updRoot.getChildren().addAll(clientPrenomLabel,clientPrenomTF);
		updRoot.getChildren().addAll(clientTelLabel,clientTelTF);
		updRoot.getChildren().addAll(clientEmailLabel,clientEmailTF);
		updRoot.getChildren().addAll(clientAdresseLabel,clientAdresseTF);
		updRoot.getChildren().add(buttonBox);

		screen.getChildren().addAll(actions,updRoot,root);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1300);
		window.setHeight(650);
		window.setTitle("Liste des Clients");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	
	

	@SuppressWarnings("static-access")
	private void addStylesToNodes() {
		scene.getStylesheets().add("css/style.css");
		titleLabel.getStyleClass().add("labelTitle");
		updTitleLabel.getStyleClass().add("labelTitle");
		actionsLabel.getStyleClass().add("labelTitle");
		ClientsTableView.getStyleClass().add("table-row-cell");
		ClientsTableView.setMinHeight(500);
		searchClientLabel.getStyleClass().add("searchForm");
		updRoot.getStyleClass().add("updateVBox");
		actions.getStyleClass().add("actionsVBox");
		clientUpdButton.getStyleClass().add("littleButton");
		clientAnnButton.getStyleClass().add("littleButton");
		delBtn.getStyleClass().add("littleButton");
		addBLButton.getStyleClass().add("actionsButton");
		listeBLButton.getStyleClass().add("actionsButton");
		searchHB.setSpacing(8);
		buttonBox.setSpacing(10);
		root.setSpacing(20);
		updRoot.setSpacing(8);
		actions.setSpacing(10);
		
		updRoot.setMargin(clientNomTF, new Insets(0, 15,0, 20));
		updRoot.setMargin(clientPrenomTF, new Insets(0, 15,0, 20));
		updRoot.setMargin(clientTelTF, new Insets(0, 15,0, 20));
		updRoot.setMargin(clientEmailTF, new Insets(0, 15,0, 20));
		updRoot.setMargin(clientAdresseTF, new Insets(0, 15,0, 20));
		
		actions.setPrefWidth(180);
		updRoot.setPrefWidth(500);
		root.setPrefWidth(600);
		screen.setSpacing(10);
		titleLabel.setPrefWidth(root.getPrefWidth());
		updTitleLabel.setPrefWidth(updRoot.getPrefWidth());
		actionsLabel.setPrefWidth(actions.getPrefWidth());
		
	}

	private void updateColumns() {
		IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		IdCol.setPrefWidth(100);
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		nomCol.setPrefWidth(250);
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		prenomCol.setPrefWidth(150);
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		telCol.setPrefWidth(150);
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailCol.setPrefWidth(150);
		adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		adresseCol.setPrefWidth(150);
		colBtn.setPrefWidth(140);
	}
	private void addEvents() {
		searchBtn.setOnAction(event->{
			handler.getSearchClient();
		});
		clientAnnButton.setOnAction(event->{
			updhandler.clearTextField();
		});
		delBtn.setOnAction(event->{
			delhandler.delClick(selectedClient);
			handler.updateListClientsWindow();
			updhandler.clearTextField();

		});
		clientUpdButton.setOnAction(event->{
			updhandler.updateClick(selectedClient);
			handler.updateListClientsWindow();
		});
		addBLButton.setOnAction(event ->{
			new BLWindow(selectedClient);
		});
		listeBLButton.setOnAction(event->{
			new BlListWindow(selectedClient);
		});
	}
	public ClientListWindow() {
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();		
		handler.updateListClientsWindow();
		addEvents();
		addNodesToPane();
		window.show();
	}

}
