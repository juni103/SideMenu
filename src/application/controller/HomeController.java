package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import navigation.NavigationController;
import navigation.NavigationHandler;
import navigation.NavigationViewModel;

public class HomeController implements Initializable, NavigationController {

	public static final String VIEW = "/view/HomeView.fxml";

	@FXML private Button 	closeBtn;
	@FXML private Button 	collapseMenuBtn;
	@FXML private VBox 		sideMenu;
	@FXML private Pane 		titleBar;
	@FXML private Text		viewTitleLabel;
	@FXML private StackPane contentContainer;
	@FXML private Label		companyLogo;

	private NavigationHandler navigationHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		navigationHandler.setContainer(contentContainer);
		addEventHandlers();
		addPropertyListeners();
	}

	private void addEventHandlers() {
		closeBtn.setOnAction(event -> ((Stage) ((Node) event.getSource()).getScene().getWindow()).close());
		collapseMenuBtn.setOnAction(event -> onCollapseButtonClicked(event));
	}

	private void addPropertyListeners() {
		sideMenu.prefWidthProperty().addListener((observable, oldValue, newValue) -> expandOrCollapseMenu(oldValue, newValue));
	}

	private void onCollapseButtonClicked(ActionEvent event) {
		ObservableList<String> styleClasses = sideMenu.getStyleClass();

		if(styleClasses.remove("collapsed")) {
			animateSideMenuWidth(sideMenu.prefWidthProperty(), 285, 300);
		} else {
			styleClasses.add("collapsed");
			animateSideMenuWidth(sideMenu.prefWidthProperty(), 50, 300);
		}
	}

	private void expandOrCollapseMenu(Number oldValue, Number newValue) {
		sideMenu.lookupAll(".menu-button").forEach(node -> ((Button) node).setPrefWidth(newValue.doubleValue()));
		companyLogo.setPrefWidth(newValue.doubleValue());
		AnchorPane.setLeftAnchor(contentContainer, newValue.doubleValue() + 10);
		AnchorPane.setLeftAnchor(titleBar, newValue.doubleValue());
	}

	@FXML
	private void menuButtonAction(ActionEvent event) {
		setMenuButtonActive(event);
		String actionId = ((Button) event.getSource()).getId();
		navigationHandler.showView(actionId);
		setViewTitle(actionId);
	}

	private void setViewTitle(String viewId) {
		NavigationViewModel viewModel = navigationHandler.getViewModel(viewId);
		Object viewController = viewModel.getViewController();
		if(viewController instanceof NavigationViewController) {
			viewTitleLabel.setText(((NavigationViewController) viewController).getViewTitle());
		}
	}

	private void setMenuButtonActive(ActionEvent event) {
		((Button) sideMenu.lookup(".menu-button.active")).getStyleClass().remove("active");
		((Button) event.getSource()).getStyleClass().add("active");
	}

	public void animateSideMenuWidth(DoubleProperty paneWidthProperty, double width, double durationInMilis) {
		final Timeline timeline = new Timeline();
		final KeyValue kv = new KeyValue(paneWidthProperty, width);
		final KeyFrame kf = new KeyFrame(Duration.millis(durationInMilis), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	@Override
	public void setNavigationController(NavigationHandler navigationHandler) {
		this.navigationHandler = navigationHandler;
	}

}
