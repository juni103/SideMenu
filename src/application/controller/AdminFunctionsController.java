package application.controller;

public class AdminFunctionsController implements NavigationViewController {

	public static final String REF_ID 	= "adminFunction";
	public static final String VIEW 	= "/view/AdminFunctionsView.fxml";
	public static final String VIEW_TITLE = "Admin Functions";

	@Override
	public String getViewTitle() {
		return VIEW_TITLE;
	}

}
