import java.awt.List;
import java.util.ArrayList;

public class Nodes {
	
	public int subgraphNo;
	public int clusterNo;
	public int[] delay = new int[100];
	
	
	Nodes(){
		subgraphNo = 0;
		clusterNo = 0;
	}
	public void printInfo(){
		for(int i=0;i<100;i++){
			System.out.println(delay[i]);
		}
	}
	public void setPoints(int[] delays){
		for(int i=0;i<100;i++){
			delay[i] = delays[i];
		}
	}
	
	public int getnodeNo(){
		return this.clusterNo;
	}
	
	public void setCluster(int index){
		this.clusterNo = index;
	}

}
