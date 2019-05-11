package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import navigation.NavigationController;
import navigation.NavigationHandler;

public class HomeController implements Initializable, NavigationController {

	public static final String VIEW = "/view/HomeView.fxml";

	@FXML private Button 	closeBtn;
	@FXML private Button 	collapseMenuBtn;
	@FXML private VBox 		sideMenu;
	@FXML private Pane 		titleBar;
	@FXML private StackPane contentContainer;

	private NavigationHandler navigationHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		navigationHandler.setContainer(contentContainer);
		addEventHandlers();
	}

	private void addEventHandlers() {
		closeBtn.setOnAction(event -> ((Stage) ((Node) event.getSource()).getScene().getWindow()).close());
		collapseMenuBtn.setOnAction(event -> onCollapseButtonClicked(event));
	}

	private void onCollapseButtonClicked(ActionEvent event) {
		ObservableList<String> styleClasses = sideMenu.getStyleClass();
		ObservableList<String> collapsed = styleClasses.stream().filter(c -> c.equalsIgnoreCase("collapsed")).collect(Collectors.toCollection(FXCollections::observableArrayList));

		if(collapsed.isEmpty()) {
			collapseMenu(styleClasses);
		} else {
			expandMenu(styleClasses, collapsed);
		}
	}

	private void collapseMenu(ObservableList<String> menuClass) {
		menuClass.add("collapsed");

		AnchorPane.setLeftAnchor(contentContainer, 60.0);
		AnchorPane.setLeftAnchor(titleBar, 50.0);
	}

	private void expandMenu(ObservableList<String> menuClass, ObservableList<String> collapsed) {
		menuClass.removeAll(collapsed);
		AnchorPane.setLeftAnchor(contentContainer, 295.0);
		AnchorPane.setLeftAnchor(titleBar, 285.0);
	}

	@FXML
	private void menuButtonAction(ActionEvent event) {
		setActive(event);
		navigationHandler.showView(((Button) event.getSource()).getId());
	}

	private void setActive(ActionEvent event) {
		Button currentButton = (Button) event.getSource();
		Button previousButton = (Button) sideMenu.lookup(".menu-button.active");
		previousButton.getStyleClass().remove("active");
		currentButton.getStyleClass().add("active");
	}

	@Override
	public void setNavigationController(NavigationHandler navigationHandler) {
		this.navigationHandler = navigationHandler;
	}
}
