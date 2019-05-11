package application.controller;

public class MembersController implements NavigationViewController {

	public static final String REF_ID 	= "members";
	public static final String VIEW 	= "/view/MembersView.fxml";
	public static final String VIEW_TITLE = "Members";

	@Override
	public String getViewTitle() {
		return VIEW_TITLE;
	}

}
