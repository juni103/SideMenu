package application.controller;

public class LockersController implements NavigationViewController {

	public static final String REF_ID 	= "lockers";
	public static final String VIEW 	= "/view/LockersView.fxml";
	public static final String VIEW_TITLE = "Lockers";

	@Override
	public String getViewTitle() {
		return VIEW_TITLE;
	}

}
