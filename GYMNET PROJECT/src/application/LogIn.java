/**
 * 
 */
package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author L Tami Class is to let the user login and check whether the inputed
 *         credentials match those that were used to register the profile
 *
 */
public class LogIn {
	@FXML
	private TextField username_input;
	@FXML
	private PasswordField password_input;
	@FXML
	private Label label_forUser;
	// everything that is on the fxml file has to be preceded by the @fxml

	private String email;
	private String password;
	public Profile newuserProfile;
	private  transient CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> network = new CopyOnWriteArrayList<>();

	/**
	 * @throws InterruptedException
	 * 
	 */
	private Profile readNewProfile(String fileName) {
		Profile newprofile = null;
		try {

			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);

			newprofile = (Profile) in.readObject();

		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return newprofile;
	}

	private ArrayList<Profile> createDummyProfiles() {
		ArrayList<Profile> profiles = new ArrayList<>();
		Profile lindo = new Profile("Lindo", "Tobela", "lTobela@gmail.com", "1234", 73, 152, 56);
		Profile lee = new Profile("lee", "whu", "leewhu@gmail.com", "7845", 173, 192, 45);
		Profile jason = new Profile("jason", "Meyer", "Meyer@gmail.com", "14", 273, 452, 47);
		Profile andrew = new Profile("andrew", "Gomez", "Gomez@gmail.com", "14", 273, 452, 48);
		Profile mali = new Profile("mali", "xulu", "xulu@gmail.com", "782146", 27, 802, 63);
		Profile khano = new Profile("khano", "zabel", "zabel@gmail.com", "782146", 27, 802, 50);
		Profile xia = new Profile("xia", "ghozou", "ghozou@gmail.com", "78!5", 173, 12, 51);
		Profile john = new Profile("john", "kim", "john@gmail.com", "L!23", 173, 12, 54);
		Profile andraw = new Profile("andraw", "johnson", "johnson@gmail.com", "Lov3", 173, 12, 77);
		profiles.add(andrew);
		profiles.add(lindo);
		profiles.add(lee);
		profiles.add(jason);
		profiles.add(mali);
		profiles.add(khano);
		profiles.add(xia);
		profiles.add(john);
		profiles.add(andraw);
		// adding the new profile to other profiles
		return profiles;
	}

	public void login(ActionEvent e) throws InterruptedException {

		System.out.println(" login ");

		// when the login button is pressed this returns the inputs of each text field
		if (username_input.getText().isEmpty() || password_input.getText().isEmpty()) {
			label_forUser.setTextFill(Color.RED);
			label_forUser.setText("Email or password not entered !");

		} else {
			email = username_input.getText().trim();
			password = password_input.getText().trim();

			System.out.println(" reading from data base");

			File file1 = new File("dataBase.txt");

			try {
				// this should be the while loop
				Scanner sc = new Scanner(file1);
				
				

					String line = sc.nextLine();

					String[] credentials = line.split(" ");

					if (credentials[0].equals(email) && credentials[1].equals(password)) {
						sc.close();

						System.out.println(" IN !!!!");

						// read from memory
						this.newuserProfile = readNewProfile("data.dat");

						CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> unConnectedlist = new CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>>();

						List<Profile> profiles = createDummyProfiles();
						// adding the recent profile created by the user 
						profiles.add(newuserProfile);
						
						
						for (Profile profile : profiles) {

							Entry<String, Profile> entry = new Entry<String, Profile>(profile.getName(), profile);
							@SuppressWarnings("unused")
							Graph.Vertex<Entry<String, Profile>> node = new Graph.Vertex<Entry<String, Profile>>(entry,
									(int) profile.getPhysicalAbility());
							unConnectedlist.add(node);
						}

						CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> connectedList = new CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>>();

						CopyOnWriteArrayList<Graph.Edge<Entry<String, Profile>>> pathList = new CopyOnWriteArrayList<Graph.Edge<Entry<String, Profile>>>();

						///////////////////////////////////////////////////////////////////////////////////////
						LoadProfileToGraph loader = new LoadProfileToGraph(pathList, unConnectedlist);
						loader.load();

						connectedList = loader.getVertexList();
						pathList = loader.getEdgeList();

						this.network = connectedList;
						FileOutputStream fout = new FileOutputStream("list.dat");
						ObjectOutputStream oOut = new ObjectOutputStream(fout);
						oOut.writeObject(this.network);
						oOut.close();
						fout.close();

						///////////////////////////////////////////////////////
						FXMLLoader fxml = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
						
						
						//////////////////////////////////////////////

						System.out.println("Creating connections between Nodes");

						Graph graph = new Graph(connectedList, pathList);

						System.out.println(graph.toString());

						Parent parent = fxml.load();
						Scene scene = new Scene(parent);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.show();

					} else {
						label_forUser.setTextFill(Color.RED);
						label_forUser.setText("Wrong password or email");
					}

				

				// let the user in the app
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void back(ActionEvent e) {

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));

			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
