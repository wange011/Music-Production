/*
Wang, Ethan
APCS-A
Period 5
Spring Project
*/

package wang.five.view;

//imports necessary classes, including JavaFX objects and Java.io objects
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
//imports necessary classes
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import wang.five.Main;

public class SurpriseController {

	//fields of the controller class, the fields are decorated with @FXML to
	//show that they are within the FXML document

	//AnchorPane to display the view
	@FXML
	private AnchorPane myAnchorPane;

	//field that allows controller to connect to the mainApp
	private Main mainApp;

	//double to track the position of
	double position;

	//empty constructor that is necessary for the loader to run
	public SurpriseController() {

	}

	//necessary method for the loader
	@FXML
	public void initialize() {

		//displays the image and adds it to the AnchorPane
		ImageView myImgVw = new ImageView("file:Resources/suprise.png");
		AnchorPane.setLeftAnchor(myImgVw, 50.0);
		AnchorPane.setBottomAnchor(myImgVw, 40.0);
		myAnchorPane.getChildren().add(myImgVw);

	}

	//method that accepts a main class to link controller with
	public void setMainApp(Main mainApp) {

		this.mainApp = mainApp;

	}

	//method that handles a Mouse Event (set off when the AnchorPane is clicked)
	@FXML
	private void display() {

		//displays an image in the AnchorPane
		ImageView view = new ImageView("file:Resources/suprise2.png");
		AnchorPane.setRightAnchor(view, 235.0);
		AnchorPane.setTopAnchor(view, 103.0);
		myAnchorPane.getChildren().add(view);

		//creates a timer object
		Timer timer = new Timer();

		//schedules tasks for the timer to run after a delay
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {

					String musicFile = "supriseAudio.mp3";
					//plays the audio file with the MediaPlayer class
					Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();

				});
			}
		}, 0);

		//schedules tasks for the timer to run after a delay
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {

					//removes the image
					myAnchorPane.getChildren().remove(view);

				});
			}
		}, 30000);

		//schedules tasks for the timer to run after a delay
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {

					String musicFile = "supriseAudio2.mp3";     // For example
					//plays the audio file with the MediaPlayer class
					Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();

				});
			}
		}, 40000);

	}

}
