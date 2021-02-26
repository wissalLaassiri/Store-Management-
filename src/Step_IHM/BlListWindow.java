package Step_IHM;

import java.time.LocalDate;

import Step_DB.BL;
import Step_DB.Client;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

public class BlListWindow {
	BlListHandler handler = new BlListHandler(this);
	BlDelHandler delhandler = new BlDelHandler();
	Client selectedClient;
	BL selectedBL;
	Stage window = new Stage();
	VBox root = new VBox();
	HBox searchHB = new HBox();
	HBox totalHB = new HBox();
	HBox actions = new HBox();

	Scene scene = new Scene(root);
	Label titleLabel = new Label("Liste des Bons de livraison");
	Label totalLabel = new Label("Total");
	Label totalValueLabel = new Label("0.0");
	Label searchProduitLabel = new Label("chercher bon de livraison");
	TextField searchProduitTF = new TextField();
	Button searchBtn = new Button("chercher");
	TableColumn<BL, Long> IdCol = new TableColumn<>("Id");
	TableColumn<BL, Double> totalCol = new TableColumn<>("Total");
	TableColumn<BL, LocalDate> dateCol = new TableColumn<>("Date");
	TableColumn<BL, String> ClientCol = new TableColumn<>("Client");

	Button delete = new Button("Delete");
	TableView<BL> bLTableView = new TableView<>();
	ObservableList<BL> bLObsList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		bLTableView.getColumns().addAll(IdCol, totalCol, dateCol, ClientCol);
		bLTableView.setItems(bLObsList);
		bLTableView.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() > 1) {
				if (bLTableView.getSelectionModel().getSelectedItem() != null) {
					selectedBL = bLTableView.getSelectionModel().getSelectedItem();
				}
			}
		});

	}

	private void addNodesToPane() {
		totalHB.getChildren().addAll(totalLabel, totalValueLabel);
		searchHB.getChildren().addAll(searchProduitLabel, searchProduitTF, searchBtn);
		actions.getChildren().addAll(searchHB, delete);
		root.getChildren().addAll(titleLabel, actions, bLTableView, totalHB);

	}

	private void addNodesToPane2() {
		totalHB.getChildren().addAll(totalLabel, totalValueLabel);
		root.getChildren().addAll(titleLabel, delete, bLTableView, totalHB);

	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Liste des Produits");
		window.getIcons().add(new Image("file:icon.png"));
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/style.css");
		titleLabel.getStyleClass().add("labelTitle");
		totalLabel.getStyleClass().add("labelTotal");
		totalValueLabel.getStyleClass().add("labelTotal");
		totalHB.getStyleClass().add("boxTotal");
		bLTableView.getStyleClass().add("table-row-cell");
		bLTableView.setMinHeight(300);
		titleLabel.setMinWidth(window.getWidth());
		totalHB.setSpacing(15);
		searchProduitLabel.getStyleClass().add("searchForm");
		searchHB.setSpacing(8);
		actions.setSpacing(15);
		root.setSpacing(20);

	}

	private void updateColumns() {
		IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		IdCol.setPrefWidth(100);
		totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
		totalCol.setPrefWidth(100);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateCol.setPrefWidth(150);
		ClientCol.setCellValueFactory(cellData -> {
			BL bl = cellData.getValue();
			Client client = handler.findById(bl.getIdclient());
			return new ReadOnlyStringWrapper(client.getNom() + " " + client.getPrenom());
		});
		ClientCol.setPrefWidth(150);
	}

	private void addEvents() {
		searchBtn.setOnAction(event -> {
			handler.getSearchBL();
		});
		delete.setOnAction(event -> {
			delhandler.delClick(selectedBL);
			handler.updateListBLWindow();
		});
	}

	public BlListWindow(Client cl) {
		this.selectedClient = cl;
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updateListBLWindow();
		addEvents();
		addNodesToPane2();
		window.show();
	}

	public BlListWindow() {
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updateListBLWindow();
		addEvents();
		addNodesToPane();
		window.show();
	}
}
