package application;

import application.controller.AdminFunctionsController;
import application.controller.AttendanceController;
import application.controller.DashboardController;
import application.controller.HomeController;
import application.controller.LockersController;
import application.controller.MembersController;
import application.controller.MessagingController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import navigation.NavigationHandler;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			new NavigationHandler()
				.setController(HomeController.VIEW, HomeController.class)
				.addNavigation(MembersController.REF_ID, 		MembersController.VIEW, 		MembersController.class)
				.addNavigation(LockersController.REF_ID, 		LockersController.VIEW, 		LockersController.class)
				.addNavigation(AttendanceController.REF_ID, 	AttendanceController.VIEW, 		AttendanceController.class)
				.addNavigation(MessagingController.REF_ID, 		MessagingController.VIEW, 		MessagingController.class)
				.addNavigation(AdminFunctionsController.REF_ID, AdminFunctionsController.VIEW, 	AdminFunctionsController.class)
				.addNavigation(DashboardController.REF_ID, 		DashboardController.VIEW, 		DashboardController.class)
				.setDefaultView(DashboardController.REF_ID)
				.startOnStage(primaryStage, getStyleSheets());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ObservableList<String> getStyleSheets() {
		return FXCollections.observableArrayList(
				Main.class.getResource("/style.css").toExternalForm()
		);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
