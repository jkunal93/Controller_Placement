import java.awt.List;
import java.util.*;

public class Subgraphs {
	
	private int id;
	private ArrayList<Nodes> nodes;
	private Nodes centroid;
	private int nodeNo;
	
	Subgraphs(){
		
		this.id=0;
		this.nodes=new ArrayList();
		this.centroid=null;
		this.nodeNo = 0;
	}
	public void setNodeNo(int nodeNo){
		this.nodeNo = nodeNo;
	}
	
	public void addNode(Nodes node){
		this.nodes.add(node);
	}
	
	public int getID(){
		return this.id;
	}
	
	public int getNodeNo(){
		return this.nodeNo;
	}
	
	public ArrayList<Nodes> getNodes(){
		return this.nodes;
	}
	public void setCentroid(Nodes node){
		this.centroid = node;
	}
	
	public Nodes getCentroid(){
		return this.centroid;
	}

}
