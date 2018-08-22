import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class KMeans {

	
	int noOfNodes=100;
	//int noOfClusters;
	Nodes[] nlist = new Nodes[noOfNodes];
	int Delay [][]=null;
	ArrayList<Subgraphs> subGraphList=null;
	List cList;
	//Delay d = new Delay();
	
	KMeans() throws FileNotFoundException{
		subGraphList = new ArrayList<>();
		
	//	System.out.println(n);
		this.readData();
	}
	
	// main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
	    KMeans kmeans= new KMeans();
	    int next=0;
		   ArrayList<Integer> templist = new ArrayList();
		//System.out.println(n);
		int n = kmeans.Clusters();	
				 
		kmeans.init();
		kmeans.assignCluster(0);
		kmeans.calculateCentroids();
		next = kmeans.generateinitCenter();
		int current = kmeans.subGraphList.get(0).getNodeNo();
		templist.add(current);
		templist.add(next);
		kmeans.printInfo(templist);
		//templist.clear();
	   int counter = 0;
	   while(counter < n-1 ){
		  //if(counter!=0)
		kmeans.subGraphList.clear();
		kmeans.init(templist);
		kmeans.assignCluster(kmeans.subGraphList.size());
		kmeans.calculateCentroids();
		next = kmeans.generateinitCenter();
		  
		//System.out.println(kmeans.subGraphList.size());
		templist.clear();
		for(Subgraphs subs:kmeans.subGraphList){
			templist.add(subs.getNodeNo());
		}
		templist.add(next);
		kmeans.printInfo(templist);
		//  }
		counter++;
		//templist.clear();
	   }    

	}
	
	public int Clusters(){
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter number of clusters: ");
		int n = reader.nextInt();
		//System.out.println(n);
	
		return n;
	
	}
	
	public void printInfo(ArrayList<Integer>templist){
		
		
			System.out.println("Iteration------------------------");
		
		for(int i =0;i<subGraphList.size();i++){
		//	System.out.println(subGraphList.size());
		//	cList.add(subGraphList.get(i).getNodeNo());
			System.out.println(subGraphList.get(i).getNodeNo());
		}
		//System.out.println("temp list size is: "+templist.size());
		
	}
	
	// initiailize 
	public void init(){
		//init starts here
	    //for (int i = 0; i < noOfClusters; i++) {
    		Subgraphs Subs = new Subgraphs();
    		int initNode;
    		Random rand = new Random();
    		int random = rand.nextInt(noOfNodes-1);
    		System.out.println("First random initial center: "+random);
    		Nodes nd = nlist[random];
    		Subs.setCentroid(nd);
    		Subs.setNodeNo(random);
    		subGraphList.add(Subs);
    		
	}
	
	public void init(ArrayList<Integer> temp){
		//init starts here
		Subgraphs Subs = null;
		//System.out.println("temp sixe in init is : " + temp.size());
	    for (int i = 0; i < temp.size(); i++) {
	    	//System.out.println("something");
    		 Subs = new Subgraphs();
    		//int initNode;
    		Nodes centroid = nlist[temp.get(i)];
    		Subs.setCentroid(centroid);
    		Subs.setNodeNo(temp.get(i));
    		subGraphList.add(Subs);
	    }	
	}
	
	// reads data for delay from the file
	public void readData() throws FileNotFoundException{
		FileReader filename=new FileReader("output.txt");
		Scanner scan = new Scanner(filename);
		
		String line;
	    int u = 0;
	    int v = 0;
	    
	    // read matrix from the file
	    while (scan.hasNext()) {
	    	line = scan.nextLine();
	        String[] vals = line.trim().split(",");
	 
	        if (Delay == null) {
	            v = vals.length;
	            Delay = new int[v][v];
	    //        System.out.println(v);
	      //      System.out.println(line);
	            
	        
	        }
	        for(int i =0;i<vals.length;i++){
	//            System.out.println(vals[i]);
	            }
	  //      System.out.println(u);
	 
	        for (int col = 0; col < noOfNodes; col++) {
	        	//System.out.println(u + "and "+col);
	            Delay[u][col] = Integer.parseInt(vals[col]);
	            
	        }
	        nlist[u] = new Nodes();
	        nlist[u].setPoints(Delay[u]);
	        u++;
	    }
	    
		
	}
	
	// assign nodes to the clusters
	private void assignCluster(int noOfClusters) {
        //double max = 100;
        int min = Integer.MAX_VALUE; 
        int cluster = 0;
        int nodeNo = 0;
        int clusterID = 0;
        // distance = 0.0; 
        int delay = 0;
        //System.out.println("value of noofcluster is : "+noOfClusters);
        for(int j=0;j<nlist.length;j++){
        	
            for(int i = 0; i < noOfClusters; i++) {
            	//System.out.println("Hello" +i);
            	Subgraphs c = subGraphList.get(i);
            	cluster = c.getID();
                nodeNo = c.getNodeNo();
                
                	if(j!=nodeNo){
                		delay = nlist[j].delay[nodeNo];
                		if(delay< min){
                			min = delay;
                			clusterID = i;
                		}
                	}
                }
                
            nlist[j].setCluster(cluster);
            subGraphList.get(cluster).addNode(nlist[j]);
        }
    }
	
	 public  List getCentroids() {
	    	ArrayList<Nodes> centroids = new ArrayList();
	    	for(Subgraphs cluster : subGraphList) {
	    		Nodes aux = cluster.getCentroid();
	    		Nodes node = new Nodes();
	    		node.delay = aux.delay;
	    		node.clusterNo = aux.clusterNo;
	    		node.subgraphNo = aux.subgraphNo;
	    		centroids.add(node);
	    	}
	    	return centroids;
	    }
	 
	 // generate new initial center // graph is further divided
	 public int generateinitCenter(){
		 int max = Integer.MIN_VALUE;
		 int delay = 0;
		 int nodeNo=0;
		 for(Subgraphs subs : subGraphList){
			 
			 for(int i = 0;i<nlist[subs.getNodeNo()].delay.length;i++){
				 if(i!=subs.getNodeNo()){
					 delay = nlist[subs.getNodeNo()].delay[i];
					 if(delay>max){
						 max = delay;
						nodeNo = i; 
					 }
				 }
			 }
		 }
		 return nodeNo;
	 }
	 
	 // calculates new centroids of each subgraph
	 public void calculateCentroids(){
		 //System.out.println("In calculate centroids"+subGraphList.size());
		 ArrayList<Subgraphs> cent= new ArrayList();
		 //int index = 0;
		 ArrayList<Integer> centroids= new ArrayList<>();
		 for(Subgraphs s : subGraphList){
			 cent.add(s); 
		 }
		 List<Integer> centlist = new ArrayList<Integer>();
		 int j=0;
		// System.out.println(subGraphList.get(i).getNodeNo());
		 for(Subgraphs subs : cent) {
	           // double sumX = 0;
	            //double sumY = 0;
			 	int sum=0;
			 	int nodeNumber=0;
			 	int min = Integer.MAX_VALUE;
			 	//System.out.println("SUBS "+subs.getNodeNo());
	            ArrayList<Nodes> list = subs.getNodes(); 
	            //int listSize = list.size();
	            
	           // System.out.println("In calculate Centroids: "+ nlist.length);
	           // System.out.println(" centroid: "+ subs.getNodeNo());
	           for(int n =0;n<nlist.length;n++) {
	        	  // System.out.println(n);
	        	  // for(int i =0;i<centroids.size();i++){
	        		  // System.out.println(n);
	        		
	        	   Integer x = new Integer(n);
	        	   if(centroids.contains(x)){
	        			   
	        			   //	System.out.println("Centroids: "+subs.getNodeNo());
	        			  // 	System.out.println("N is "+n+" "+j);
			            	// j++;
	        		   }
	        		  else{
	        			   for(int k = 0;k<nlist[n].delay.length;k++){
			            		sum += nlist[n].delay[k];
			            	}
			            	//System.out.println("Sum of delay for node "+n+" is "+sum);
			            	if(min>sum){
			            		min = sum;
			            		nodeNumber = n;
			            	}
			            	//System.out.println("Minimum value is "+nodeNumber);
			            	sum=0;
			           }
	           }
	           centroids.add(nodeNumber);
	         //  for(int i=0;i<centroids.size();i++){
	        //   System.out.println("Minimum value is "+centroids.get(i));
	        //   }
	          // System.out.println("Node added: "+nlist[nodeNumber]);
	            subs.setCentroid(nlist[nodeNumber]);
	            subs.setNodeNo(nodeNumber);
	           // min=Integer.MAX_VALUE;
	       }
	 }
}
