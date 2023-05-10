package application;
	


import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	

		

	
	
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException, IOException {
		launch(args);
		
		
		
		
	   
		
		
		
		
		
	
		
		
		
		
		
	
		 ///////////////////////////////////////////////
		//going to need a data class to store  the connectedList
				
				
				
					
				
				
				
		
		
		
		
		
		
		
		
		
		
		
	}
}
