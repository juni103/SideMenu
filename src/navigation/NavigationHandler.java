package navigation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import animatefx.animation.FadeInRightBig;
import animatefx.animation.FadeOutLeftBig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NavigationHandler {

	@SuppressWarnings("unused")
	private Stage 		primaryStage;
	private String 		viewPath;
	private String		defaultViewPath;
	private Pane 		container;
	private Class<?> 	controller;
	private Map<String, NavigationViewModel> navigations = new HashMap<String, NavigationViewModel>();

	public NavigationHandler setController(String viewPath, Class<?> controller) {
		setViewPath(viewPath);
		setController(controller);
		return this;
	}

	public NavigationHandler setController(Class<?> controller) {
		this.controller = controller;
		return this;
	}

	public Class<?> getController() {
		return controller;
	}

	public NavigationHandler addNavigation(String actionId, String view, Class<?> navigateTo) {
		navigations.put(actionId, new NavigationViewModel(view, navigateTo));
		return this;
	}

	public void setContainer(Pane contentContainer) {
		this.container = contentContainer;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public NavigationHandler setDefaultView(String defaultViewPath) {
		this.defaultViewPath = defaultViewPath;
		return this;
	}

	public void showView(String viewId) {
		new FadeOutLeftBig(container).play();
		container.getChildren().clear();
		container.getChildren().add(navigations.get(viewId).getViewPane());
		new FadeInRightBig(container).play();
	}

	@SuppressWarnings("unchecked")
	public NavigationHandler startOnStage(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;

		try {
			FXMLLoader loader = new FXMLLoader(controller.getResource(viewPath));
			Constructor<NavigationController> constructor = (Constructor<NavigationController>) controller.getConstructor();
			NavigationController obj = constructor.newInstance();
			loader.setController(obj);

			obj.setNavigationController(this);

			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(controller.getResource("/application/application.css").toExternalForm());

			if(null != defaultViewPath) {
				showView(defaultViewPath);
			}

			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setMaximized(true);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return this;
	}
}
