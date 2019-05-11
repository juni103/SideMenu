package navigation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public  class NavigationViewModel {
	private String 		viewPath;
	private Pane 		viewPane;
	private Class<?> 	controllerClass;
	private Object		viewController;

	public NavigationViewModel(String viewPath, Class<?> controllerClass) {
		setViewController(controllerClass);
		setViewPane(viewPath);
	}

	private void setViewController(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
		try {
			this.viewController = this.controllerClass.getConstructor().newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Object getViewController() {
		return viewController;
	}

	private void setViewPane(String viewPath) {
		this.viewPath = viewPath;
		try {
			FXMLLoader loader = new FXMLLoader(controllerClass.getResource(viewPath));
			setLoaderController(loader);
			this.viewPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setLoaderController(FXMLLoader loader) {
		if(null == loader.getController()) {
			loader.setController(viewController);
		}
	}

	public String getViewPath() {
		return viewPath;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Pane getViewPane() {
		return viewPane;
	}
}
