package application;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


public class CustomThread implements Runnable {
	CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>>  vertexList;
	CopyOnWriteArrayList<Graph.Edge<Entry<String, Profile>>>  edgeList;
	
	

	public CustomThread(CopyOnWriteArrayList<Graph.Vertex<Entry<String, Profile>>> vertexList2,
			CopyOnWriteArrayList<Graph.Edge<Entry<String, Profile>>> edgeList2) {
		this.vertexList = vertexList2;
		this.edgeList= edgeList2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
	   
	    Graph.Vertex<Entry<String, Profile>> node;
	    
	    
		for(int i = 0; i <vertexList.size(); i++){
			
		 node = vertexList.get(i);
		
			
		   
			   
			   
			// another approach to take is to  get the first compatible partner and look at that partners group to see if there is anyone else which you are comaptible with.
			// this can lessen then the traversal or search time of going throught the entirre graph
		 for(int j = i+1; j <vertexList.size(); j++){
				
				// compare their weights since these are the their physical abilities
             
            	  
				if (Math.abs(vertexList.get(j).getWeight() - node.getWeight()) <30) {
					// create an edge and join the two nodes
					// the cost is the difference
					Graph.Edge<Entry<String, Profile>> edge = new Graph.Edge<>(Math.abs(vertexList.get(j).getWeight() - node.getWeight()), node,vertexList.get(j) );
					edgeList.add(edge);
					node.addEdge(edge);

					// now goes the opposite way
					Graph.Edge<Entry<String, Profile>> edge2 = new Graph.Edge<>(Math.abs(vertexList.get(j).getWeight() - node.getWeight()),vertexList.get(j), node);
					edgeList.add(edge2);
					vertexList.get(j).addEdge(edge2);

				}    }
			}
		}
	}



