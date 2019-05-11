package application.controller;

public class DashboardController implements NavigationViewController {

	public static final String REF_ID 	= "dashboard";
	public static final String VIEW 	= "/view/DashboardView.fxml";
	public static final String VIEW_TITLE = "Dashbaord";

	@Override
	public String getViewTitle() {
		return VIEW_TITLE;
	}

}
