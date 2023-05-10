package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import application.Graph.Edge;
import application.Graph.Vertex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Dashboard implements Initializable {
	@FXML private DatePicker calender;
	@FXML private ListView<String> listView;
	@FXML private ListView<String> fullNetwork;
	@FXML private TextField search;
	@FXML private Label label;
	private  CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> connectedList ;
	private Profile profile;
	/**
	 * This function reads the newly created profile from disk
	 * @param fileName
	 * @return
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
	/**
	 * Shows the your schedule to 
	 * @param e
	 */
	
	public void scheduleSession(ActionEvent e) {
		
		label.setText("Session you will be available at "+ calender.getValue());
		
	}
	public void addFriend(ActionEvent e) {
		String name = null;
		if(search.getText().isEmpty()) {
			name =" ";
			label.setText("Enter name");
		}else {
			name = search.getText();
		}
	listView.getItems().add(name);
		
		
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		// reading the data that was written to disk upon signing in
		try {
			FileInputStream fin = new FileInputStream("list.dat");
			ObjectInputStream ObOut = new ObjectInputStream(fin);
			connectedList = (CopyOnWriteArrayList<Vertex<Entry<String, Profile>>>) ObOut.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
			 
		  List<Edge<Entry<String, Profile>>>  friends=    connectedList.get(connectedList.size()-1).getEdges();
		  for (Graph.Vertex<Entry<String, Profile>> vertex : connectedList) {
			  fullNetwork.getItems().add(vertex.getValue().getKey());
			  if(connectedList.get(connectedList.size()-1).pathTo(vertex)) {
				  listView.getItems().add(vertex.getValue().getKey());
				  
			  }
			
		}
		
			 
		/*
		 * for (Edge<Entry<String, Profile>> edge :
		 * friends) { System.out.println(edge.getToVertex().getValue().getKey());
		 * listView.getItems().add(edge.getToVertex().getValue().getKey());
		 */
				
			}
			  
		  
		 // }
		 // listView.getSelectionModel().getSelectedItem();
		  
		  
		 
		  
		  
		  
		  
		
		  
		  
		  
		  
		  //}
		 public void setProfile(Profile profile) {
			this.profile = profile;
		}
		 public void setConnectedList(CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> connectedList) {
			this.connectedList = connectedList;
		}
		
	

}
