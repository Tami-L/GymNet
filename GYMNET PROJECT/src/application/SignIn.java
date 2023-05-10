/**
 * 
 */
package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author L Tami
 *
 */

public class SignIn {
	@FXML

	public void signIn(ActionEvent e) {
		// this is to go to scene of the sign in

		try {
			Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void register(ActionEvent e) {

		System.out.println("registering");
		// this is to switch the scene to the registration portal so that the client may
		// register for GymNet
		try {

			Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
