/**
 * 
 */
package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author L Tami 219005173 This class is to facilitate the creation of a
 *         profile and it also write the profile object created to file so that
 *         another class can get it and use it on the network(Graph)
 *
 */
public class Register {

	@FXML
	private TextField name_input;
	@FXML
	private TextField surname_input;
	@FXML
	private TextField email_input;

	@FXML
	private PasswordField create_password_input;
	@FXML
	private PasswordField confirm_password_input;
	@FXML
	private TextField weight_input;
	@FXML
	private TextField height_input;
	@FXML
	private ImageView imageViewer;
	@FXML
	private TextField age_input;
	

	@FXML
	private TextField gender_input;
	@FXML
	private TextField sitUp_input;
	@FXML
	private TextField pushUp_input;

	@FXML
	private TextField cooperTest_input;
	@FXML
	private TextField gymFrequency_input;
	@FXML
	private Label password_Label;
	@FXML
	private CheckBox showPassword_checkBox;
	@FXML
	private Label notifyUser;

	private int num_pushups, cooperTest, gymFrequency, plank, age;
	public double average = 0;
	private double bmi = 0;

	private static Entry<String, Profile> newEntry;
// this entry will be returned everytime a profile is created

	private String name, surname, email, bio, password, gender;
	private int height, weight;

	/**
	 * Thus function is for getting the inputs from the user interface
	 * 
	 * @param e
	 */
	public void createProfile(ActionEvent e) {
		// to do
		// try and validat evalues that are coming from the stream.
		if (name_input.getText().isEmpty() == true) {
			// this is to ensure that we get the users name
			notifyUser.setTextFill(Color.RED);
			notifyUser.setText("Please enter your name");
			// throw new IllegalArgumentException("please emter your name");

		} else {
			name = name_input.getText().trim();
		}

		surname = surname_input.getText().trim();

		// email is essential to this application so we need t get it by all means
		if (email_input.getText().isEmpty() == true || email_input.getText().contains("@gmail.com")
				|| email_input.getText().contains("@yahoo.com")) {
			notifyUser.setTextFill(Color.RED);
			notifyUser.setText("Please enter valid email. eg me@gmail.com");
		}

		email = email_input.getText().trim();

		// check if the passwords match???

		if (create_password_input.getText().isEmpty() == false && confirm_password_input.getText().isEmpty() == false) {
			if (create_password_input.getText().equals(confirm_password_input.getText())) {
				// if the passwords do match then store the password with profile
				password = confirm_password_input.getText().trim();
			} else {
				// tell client that the passwords do not match

				password_Label.setTextFill(Color.RED);
				password_Label.setText("Passwords do not match !!");
			}
		}

		if (sitUp_input.getText().isEmpty() == true || pushUp_input.getText().isEmpty() == true
				|| cooperTest_input.getText().isEmpty() == true || weight_input.getText().isEmpty() == true
				|| height_input.getText().isEmpty() == true || gymFrequency_input.getText().isEmpty() == true) {
			notifyUser.setTextFill(Color.RED);
			notifyUser.setText("Inavalid inputs");

		} else {

			age = Integer.parseInt(age_input.getText().trim());
			gender = gender_input.getText().trim();
			num_pushups = Integer.parseInt(pushUp_input.getText().trim());

			cooperTest = Integer.parseInt(cooperTest_input.getText().trim());
			weight = Integer.parseInt(weight_input.getText().trim());
			height = Integer.parseInt(height_input.getText().trim());
			gymFrequency = Integer.parseInt(gymFrequency_input.getText().trim());
			plank = Integer.parseInt(sitUp_input.getText().trim());
	
			
			bmi = weight / (height * height);
            
			average = (bmi * (0.3) + cooperTest * (0.25) + plank * (0.05) + gymFrequency * (0.2) + num_pushups * (0.2));
			
			
            System.out.println("gama in Register "+name);
		}
		// save the email and password to a text file that we use to signin
		File file = new File("dataBase.txt");
		try {
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(email + " " + password);
			pw.close();
			fw.close();

		} catch (IOException e1) {

			e1.printStackTrace();
		}

		
		
		
		

		Profile profile = new Profile(name, surname, email, password, weight, height, average);
		try {
			FileOutputStream file2 = new FileOutputStream("data.dat");
			ObjectOutputStream out = new ObjectOutputStream(file2);
			out.writeObject(profile);
			out.close();
			file2.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

		
		
		
		
		try {

			// now we let the user sign into the application
			Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));

			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void uploadImage(ActionEvent e) {
		FileChooser fc = new FileChooser();

		fc.setTitle("Pick An Image");

		File fileimage = fc.showOpenDialog(null);
		Image image = new Image(fileimage.getAbsolutePath());

		// fix this function ////4

		imageViewer.setImage(image);

	}

	public String getBio() {
		return bio;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSurname() {
		return surname;
	}


}
