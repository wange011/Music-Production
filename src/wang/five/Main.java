/*
Wang, Ethan
APCS-A
Period 5
Spring Project
*/

package wang.five;

//all the necessary imports for the class, includes JavaFX objects, java.io, and model classes
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import wang.five.model.Piece;
import wang.five.view.NoteSelectionController;
import wang.five.view.OrnamentSelectionController;
import wang.five.view.ProjectOverviewController;
import wang.five.view.RootLayoutController;
import wang.five.view.SurpriseController;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	//stores the object of type Piece that is currently being used
	private ObservableList<Piece> projectList = FXCollections.observableArrayList();
	//main stage of the application
	private Stage primaryStage;
	//another stage to display multiple windows
	private Stage secondaryStage;
	//field for the RootLayout fxml
	private BorderPane rootLayout;
	//file that is currently being modified in the program
	private File file;

	@Override
	//method that sets up the application window
	public void start(Stage primaryStage) {

		try {

			this.primaryStage = primaryStage;

			//changes the title of the stage and adds an icon
			this.primaryStage.setTitle("Music Composer");
			this.primaryStage.getIcons().add(new Image("file:Resources/icon.png"));

			//calls the method that adds the view to the stage and creates the controller
			initRootLayout();

		}

		//catches errors in code, helps with debugging
		catch(Exception e) {

			//prints the exception
			e.printStackTrace();

		}

	}

	//necessary method to create the application
	public static void main(String[] args) {
		launch(args);
	}


	//getters and setters for the fields of the class
	public ObservableList<Piece> getProjectList() {
		return projectList;
	}

	public void setProjectList(ObservableList<Piece> projectList) {
		this.projectList = projectList;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Stage getSecondaryStage() {
		return secondaryStage;
	}

	public void setSecondaryStage(Stage secondaryStage) {
		this.secondaryStage = secondaryStage;
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


	//method that sets the stage to the fxml RootLayout file
	public void initRootLayout() {

		try {

			//instantiates the FXMLLoader class by calling default constructor
			//creates an FXMLLoader called loader
			FXMLLoader loader = new FXMLLoader();

			//finds the location of the FXML file to load
			loader.setLocation(Main.class.getResource("/wang/five/view/RootLayout.FXML"));

			//sets the BorderPane in the FXML file to rootLayout
			//so that the BorderPane is set to the display of the app
			rootLayout = (BorderPane) loader.load();

			//Sets the scene to this rootLayout
			//creates the controller for the scene
			Scene scene = new Scene(rootLayout);
			RootLayoutController controller = loader.getController();
	        controller.setMainApp(this);

			//Sets the primaryScene Scene object to scene
			primaryStage.setScene(scene);

			//Shows the primaryStage object
			primaryStage.show();

		}

		//catches errors in code, helps with debugging
		catch(Exception e) {

			//prints the exception
			e.printStackTrace();

		}

	}

	//method that shows a second view within the app
	public void showProjectOverview(Piece piece) {

		try {

			//ensures that the right file is being edited by removing previous files
			projectList.clear();
			projectList.add(piece);

			// Load the fxml file by setting loader location.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("/wang/five/view/ProjectOverview.fxml"));
	        ScrollPane page = (ScrollPane) loader.load();

	        //places the scene within the root stage
	        rootLayout.setCenter(page);

	        //creates the controller that is linked to the mainApp and controls the view
	        ProjectOverviewController controller = new ProjectOverviewController();
	        controller = loader.getController();
	        controller.setMainApp(this);
	        controller.setMeasures();

	    }

		//catches errors in code, helps with debugging
		catch (IOException e) {

			//prints the exception
			e.printStackTrace();

		}

	}

	//method that shows the NoteSelection fxml view
	//has a rootController and a function passed in as parameters
	public void initNoteSelection(int function, ProjectOverviewController rootController) {

		try {

			//instantiates the FXMLLoader class by calling default constructor
			//creates an FXMLLoader called loader
			FXMLLoader loader = new FXMLLoader();

			//finds the location of the FXML file to load
			loader.setLocation(Main.class.getResource("/wang/five/view/NoteSelection.FXML"));

			//instantiates the secondaryStage to display the view
			secondaryStage = new Stage();
			secondaryStage.setTitle("Note Selection");

			//loads the view
			ScrollPane scrollPane = (ScrollPane) loader.load();
			Scene scene = new Scene(scrollPane);

			//instantiates the controller for the view and passes in the necessary fields
			NoteSelectionController controller = loader.getController();
			controller.setFunction(function);
			controller.setController(rootController);
			controller.setMainApp(this);

			//sets the scene into the stage and shows it
			secondaryStage.setScene(scene);
			secondaryStage.show();

		}

		//catches errors in code, helps with debugging
		catch(Exception e) {

			//prints the exception
			e.printStackTrace();

		}

	}

	//method to display the OrnamentSelection view to the stage
	//has the rootController as a parameter
	public void initOrnamentSelection(ProjectOverviewController rootController) {

		try {

			//instantiates the FXMLLoader class by calling default constructor
			//creates an FXMLLoader called loader
			FXMLLoader loader = new FXMLLoader();

			//finds the location of the FXML file to load
			loader.setLocation(Main.class.getResource("/wang/five/view/OrnamentSelection.FXML"));

			//instantiates the secondaryStage to display the view
			secondaryStage = new Stage();
			secondaryStage.setTitle("Ornament Selection");

			//loads the view
			ScrollPane scrollPane = (ScrollPane) loader.load();
			Scene scene = new Scene(scrollPane);

			//creates the controller and passes in the fields
			OrnamentSelectionController controller = loader.getController();
			controller.setRootController(rootController);
			controller.setMainApp(this);

			//sets the scene and displays it
			secondaryStage.setScene(scene);
			secondaryStage.show();

		}

		//catches errors in code, helps with debugging
		catch(Exception e) {

			//prints the exception
			e.printStackTrace();

		}

	}

	//method that displays a surprise view in the about window
	public void initSurprise() {

		try {

			//instantiates the FXMLLoader class by calling default constructor
			//creates an FXMLLoader called loader
			FXMLLoader loader = new FXMLLoader();

			//finds the location of the FXML file to load
			loader.setLocation(Main.class.getResource("/wang/five/view/Suprise.FXML"));

			//instantiates the secondaryStage to display the scene
			secondaryStage = new Stage();
			secondaryStage.setTitle("About");

			//loads the view
			AnchorPane borderPane = (AnchorPane) loader.load();
			Scene scene = new Scene(borderPane);

			//creates the controller for the view
			SurpriseController controller = loader.getController();
			controller.setMainApp(this);

			//sets the scene and displays the view
			secondaryStage.setScene(scene);
			secondaryStage.show();

		}

		//catches errors in code, helps with debugging
		catch(Exception e) {

			//prints the exception
			e.printStackTrace();

		}

	}

	//method that closes a stage (passed in as parameter)
	public void close(Stage stage) {

		//closes the selected stage
		stage.close();

	}

}
