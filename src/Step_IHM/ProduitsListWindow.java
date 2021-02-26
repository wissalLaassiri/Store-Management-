package Step_IHM;

import java.time.LocalDate;

import Step_DB.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ProduitsListWindow {
	ProduitsListHandler handler = new ProduitsListHandler(this);
	ProduitDelHandler delhandler = new ProduitDelHandler();
	CategorieHandler catHandler = new CategorieHandler();
	Stage window = new Stage();
	VBox root = new VBox();
	HBox searchHB = new HBox();
	HBox totalHB = new HBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Liste des produits");
	Label totalLabel = new Label("Total");
	Label totalValueLabel = new Label("0.0");
	Label searchProduitLabel = new Label("chercher Produit");
	TextField searchProduitTF = new TextField();
	Button searchBtn = new Button("chercher");
	TableColumn<Produit, Long> IdCol = new TableColumn<>("Id");
	TableColumn<Produit, String> designationCol = new TableColumn<>("Designation");
	TableColumn<Produit, Double> prixCol = new TableColumn<>("Prix");
	TableColumn<Produit, Integer> qteCol = new TableColumn<>("Qte");
	TableColumn<Produit, Double> totalCol = new TableColumn<>("Total");
	TableColumn<Produit, LocalDate> dateCol = new TableColumn<>("Date");
    TableColumn<Produit, Void> colBtn = new TableColumn("Action");

	TableView<Produit> produitsTableView = new TableView<>();
	ObservableList<Produit> produitObsList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		produitsTableView.getColumns().addAll(IdCol, designationCol, prixCol, qteCol, totalCol, dateCol);
		produitsTableView.setItems(produitObsList);
		addButtonToTable();
	}

	private void addNodesToPane() {
		totalHB.getChildren().addAll(totalLabel, totalValueLabel);
		searchHB.getChildren().addAll(searchProduitLabel,searchProduitTF, searchBtn);
		root.getChildren().addAll(titleLabel,searchHB, produitsTableView, totalHB);

	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Liste des Produits");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addButtonToTable() {
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
           @Override
        	public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button delBtn = new Button("del");
                    private final Button updBtn = new Button("upd");
                    HBox pane = new HBox(delBtn, updBtn);


                    {
                    	delBtn.setOnAction((ActionEvent event) -> {
                        	Produit data = getTableView().getItems().get(getIndex());
                			delhandler.delClick(data);
                			window.close();
                            System.out.println("selectedData: " + data);
                        });
                    	updBtn.setOnAction((ActionEvent event) -> {
                        	Produit data = getTableView().getItems().get(getIndex());
                    		new ProduitUpdWindow(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                        	setGraphic(pane);
                    		pane.getStyleClass().add("littleButton");

                        }
                    }
                };
                return cell;
            
        }
        
        };
        colBtn.setCellFactory(cellFactory);
        produitsTableView.getColumns().add(colBtn);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/style.css");
		titleLabel.getStyleClass().add("labelTitle");
		totalLabel.getStyleClass().add("labelTotal");
		totalValueLabel.getStyleClass().add("labelTotal");
		totalHB.getStyleClass().add("boxTotal");
		produitsTableView.getStyleClass().add("table-row-cell");
		produitsTableView.setMinHeight(500);
		titleLabel.setMinWidth(window.getWidth());
		totalHB.setSpacing(15);
		searchProduitLabel.getStyleClass().add("searchForm");
		searchHB.setSpacing(8);
		root.setSpacing(20);

	}

	private void updateColumns() {
		IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		IdCol.setPrefWidth(100);
		designationCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
		designationCol.setPrefWidth(260);
		prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
		prixCol.setPrefWidth(150);
		qteCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
		qteCol.setPrefWidth(60);
		totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
		totalCol.setPrefWidth(100);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateCol.setPrefWidth(150);
		colBtn.setPrefWidth(140);

		
		/*
		catCol.setCellValueFactory(new PropertyValueFactory<Produit, Long>("idCategorie"));
		catCol.setPrefWidth(150);
		 */
	}

	private void addEvents() {
		searchBtn.setOnAction(event->{
			handler.getSearchProduct();
		});
	}
	public ProduitsListWindow() {
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updateListProduitsWindow();
		addEvents();
		addNodesToPane();
		window.show();
	}
}
