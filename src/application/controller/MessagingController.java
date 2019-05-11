package application.controller;

public class MessagingController implements NavigationViewController {

	public static final String REF_ID 	= "messaging";
	public static final String VIEW 	= "/view/MessaginView.fxml";
	public static final String VIEW_TITLE = "Messaging";

	@Override
	public String getViewTitle() {
		return VIEW_TITLE;
	}

}
