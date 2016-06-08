import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class FFalgorithm
{
	//Define the global variables
	private int nodes;
    private int capacity[][];
    private int flow[][];
    private boolean visited[];
    private int pre[];
    private int maxFlow;
    //Queue<Integer> path = new LinkedList<Integer>();
    
    //Constructor 
    public FFalgorithm(){
    	
    }
    
    
    
    public FFalgorithm( int[][] capacity, int nodes )
    {
        this.capacity = capacity;
        this.nodes = nodes;
        this.flow = new int[nodes][nodes];
        this.pre = new int[nodes];
        this.visited = new boolean[nodes];
    }
    
    
    
    
    //BFS algorithm
    private boolean BFS( int src, int des )
    {	
    	//use a queue to store the visited nodes
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add( src );
        
        visited[src] = true;
        //standard BFS loop
        while( !queue.isEmpty() )
        {
            int node = queue.poll();
            
            for( int i = 0; i < nodes; i++ )
            {
                if( !visited[i] && (capacity[node][i] - flow[node][i] > 0) )  // calculate and store the argument path
                {
                    queue.add( i );
                    
                    visited[i] = true;
                    pre[i] = node;
                }
            }
        }

        return visited[des];
    }
    
    
  //Recursive DFS algorithm
    private void DFS(int src, int des){
        visited[src] = true;
        for(int i = 0; i < nodes; i++){
            if(!visited[i] && ( capacity[src][i] - flow[src][i] > 0) ){
                pre[i] = src;
                visited[i] = true;
                DFS(i,des);     //use recursive to implement this algorithm
            }
        }
    }
    
    
    
    //No recursive DFS method
    private boolean DFSNorecursive(int src, int des){
    	Stack<Integer> mystack = new Stack<Integer>();
    	visited[src] = true;
    	mystack.push(src);
    	
    	while(!mystack.empty()){
    		int tmp = mystack.pop();
    		for( int i = 0; i < nodes; i++ )
            {
                if( !visited[i] && (capacity[tmp][i] - flow[tmp][i] > 0) )   // calculate and store the argument path
                {
                    mystack.push( i );
                    visited[i] = true;
                    pre[i] = tmp;
                }
            }
    	}
    	return visited[des];
    }
   
    
    
    
   //Intialize the parameters
    public  void initilize(int src,int des){
    	for( int i = 0; i < nodes; i++ )
            for( int j = 0; j < nodes; j++ )
                flow[i][j] = 0;
    }
    

    public void CalMaxFlow(int src, int des){

        int increment = Integer.MAX_VALUE;
        //find the min flow of the path
        for( int i = des; pre[i] >= src; i = pre[i] )
        {
            
            increment = Math.min( increment, capacity[pre[i]][i]
                    - flow[pre[i]][i] );
        }
        
        //update the flow
        for( int i = des; pre[i] >= src; i = pre[i] )
        {
            flow[pre[i]][i] += increment;
            flow[i][pre[i]] -= increment;
        }
        
        maxFlow += increment;
    }
    
    
    
    public void maxFlowDFS( int src, int des )
    {
        maxFlow = 0;
        initilize(src,des);
        
        //Traverse the graph until there is no argument path
        while( true )
        {
            for( int i = 0; i < nodes; i++ )
            {
                visited[i] = false;
            }
            pre[src] = -1;
            
            //If we can find a argument path from src to des
            DFS(src,des);
            if(!visited[des])
                break;
            showPath(src,des);
            CalMaxFlow(src,des);
        }
        
        System.out.println("Use DFS method to calculate the MaxFlow is "+ maxFlow);
        
    }
    
    
    
    public void maxFlowBFS( int src, int des )
    {
        maxFlow = 0;
        initilize(src,des);
      
        //Traverse the graph until there is no argument path
        while( true )
        {
            for( int i = 0; i < nodes; i++ )
            {
                visited[i] = false;
            }
            pre[src] = -1;
            
          //If we can find a argument path from src to des
            if(!BFS( src, des )){
                break;
            }
            showPath(src,des);
            CalMaxFlow(src,des);
            //System.out.println("Use BFS method to calculate the MaxFlow is "+ maxFlow);
        }
        System.out.println("Use BFS method to calculate the MaxFlow is "+ maxFlow);
        
    }
    
    public void maxFlowDFS2( int src, int des )
    {
        maxFlow = 0;
        initilize(src,des);
      //Traverse the graph until there is no argument path
        while( true )
        {
            for( int i = 0; i < nodes; i++ )
            {
                visited[i] = false;
            }
            pre[src] = -1;
            
            //If we can find a argument path from src to des
            if(!DFSNorecursive( src, des )){
                break;
            }
           showPath(src,des);
         CalMaxFlow(src,des);
            
        }
        System.out.println("Use No Recursive DFS method to calculate the MaxFlow is "+ maxFlow);
    }
    
    
    //Show the current argument path
   public void showPath(int src, int des){
	   Stack<Integer> mystack = new Stack<Integer>();
	   for( int i = des; pre[i] >= src; i = pre[i] )
       {	
          mystack.push(i);
          
       }
	   System.out.println("A argument path is:");
	   while(!mystack.empty()){
		   System.out.print(mystack.pop()+" ");
	   }
	   System.out.println();
   }

    

    public static void main(String[] args) {
        int nodes = 7;
        int[][] capacity = new int[nodes][nodes];
        capacity[0][1] = 7;
        capacity[0][2] = 6;
        capacity[0][3] = 5;
        capacity[1][4] = 2;
        capacity[4][6] = 6;
        capacity[3][6] = 3;
        capacity[2][3] = 3;
        capacity[2][5] = 9;
        capacity[5][6] = 8;
        capacity[1][3] = 1;
        capacity[3][4] = 5;
        
        FFalgorithm ff = new FFalgorithm( capacity, nodes );
        
        //BFS method
        System.out.println("Use BFS method:");
        long startTime = System.nanoTime();
        ff.maxFlowBFS( 0, nodes - 1 );
        long endTime = System.nanoTime();
        
        System.out.println("The running time for BFS is: "+(endTime - startTime)+"ns");
        System.out.println("------------------------------------------------");
        
        //DFS method
        System.out.println("Use DFS method:");
        startTime = System.nanoTime();
        ff.maxFlowDFS( 0, nodes - 1 );
        endTime = System.nanoTime();
        System.out.println("The running time for BFS is: "+(endTime - startTime)+"ns");
        System.out.println("------------------------------------------------");
        
        
        //No recursive DFS
        System.out.println("Use DFS(no recursive) method:");
        startTime = System.nanoTime();
        ff.maxFlowDFS2(0,nodes-1);
        endTime = System.nanoTime();
        System.out.println("The running time for Dijkstra is :" + (endTime - startTime)+"ns");
        
    }

}

