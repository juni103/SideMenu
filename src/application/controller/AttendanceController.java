package application.controller;

public class AttendanceController implements NavigationViewController {

	public static final String REF_ID 	= "attendance";
	public static final String VIEW 	= "/view/AttendanceView.fxml";
	public static final String VIEW_TITLE = "Attendance";

	@Override
	public String getViewTitle() {
		return VIEW_TITLE;
	}

}
