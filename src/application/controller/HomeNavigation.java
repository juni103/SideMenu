package application.controller;

import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import navigation.NavigationController;
import navigation.NavigationHandler;

public class HomeNavigation implements NavigationController {

	@FXML private Button 	collapseMenuBtn;
	@FXML public StackPane contentContainer;

	public NavigationHandler navigationHandler;

	public void handleNavigation(Pane sideMenu, Pane titleBar) {
		collapseMenuBtn.setOnAction(event -> onCollapseButtonClicked(event, sideMenu, titleBar));
	}

	private void onCollapseButtonClicked(ActionEvent event, Pane sideMenu, Pane titleBar) {
		ObservableList<String> styleClasses = sideMenu.getStyleClass();
		ObservableList<String> collapsed = styleClasses.stream().filter(c -> c.equalsIgnoreCase("collapsed")).collect(Collectors.toCollection(FXCollections::observableArrayList));

		if(collapsed.isEmpty()) {
			collapseMenu(styleClasses, titleBar);
		} else {
			expandMenu(styleClasses, collapsed, titleBar);
		}
	}

	private void collapseMenu(ObservableList<String> menuClass, Pane titleBar) {
		menuClass.add("collapsed");
		AnchorPane.setLeftAnchor(contentContainer, 60.0);
		AnchorPane.setLeftAnchor(titleBar, 50.0);
	}

	private void expandMenu(ObservableList<String> menuClass, ObservableList<String> collapsed, Pane titleBar) {
		menuClass.removeAll(collapsed);
		AnchorPane.setLeftAnchor(contentContainer, 295.0);
		AnchorPane.setLeftAnchor(titleBar, 285.0);
	}

	public void setActive(ActionEvent event, Pane sideMenu) {
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
