package application;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



/**
 * @author L Tami 
 * This class is for loading the new profile to the graph and ensuring that it is connected to the appropriate nodes that are compatible to it
 *
 */
public class LoadProfileToGraph {
	
	private CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> vertexList ;
	
	private CopyOnWriteArrayList<Graph.Edge<Entry<String, Profile>>> edgeList ;
	
   
   
   
	 
	
	
	public LoadProfileToGraph(CopyOnWriteArrayList<Graph.Edge<Entry<String, Profile>>> edgeList2,CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> vertexList2) {
		this.vertexList = vertexList2;
		this.edgeList = edgeList2;
		
		}
	
	
	
	@SuppressWarnings("static-access")
	public void load() throws InterruptedException {
		
		
	
		CustomThread thread = new CustomThread( vertexList, edgeList);
		new Thread(thread).start();
		
	}

	public CopyOnWriteArrayList<Graph.Edge<Entry<String, Profile>>> getEdgeList() {
		return edgeList;
	}public CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> getVertexList() {
		return vertexList;
	}

	
	
	
}
	
