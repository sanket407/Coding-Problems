// TOPCODER SRM 150 DIV 1 1000pt
// Check editorial for detailed explanation and diagram
// Note : Each state stores the node location and direction
//         Since there are 6 directions, a state = 6*node_index + direction;
//         vice versa node_index = state/6; dir = state%6;
//    Here we use dist[state] to store each state current min. dist
//   And we add state to priority queue(heap) only if curr. edge to that state gives takes cost than any 
//    previously discovered path

import java.lang.reflect.Array;

import java.util.*;


public class RoboCourier {

	public static int nodecount;       //counts the no of nodes visited by scout
	public static Node node[];              //array of all nodes 
	public static int  dx[] = {0,1,1,0,-1,-1};   //all 6 directions stored
	public static int  dy[] = {1,1,0,-1,-1,0};
	public int timeToDeliver(String[] path) {
	
		
		
		node = new Node[501];
		nodecount=0;
		
		int currx=0,curry=0,currd=0;
		
		for(int i=0;i<path.length;i++)
		{
			for(int j=0;j<path[i].length();j++)
			{
				if(path[i].charAt(j)=='L')
				{
					currd = (currd+5)%6;    
				}
				else if(path[i].charAt(j)=='R')
				{
					currd = (currd+1)%6;
				}
				else
				{
					int src = findNode(currx,curry);
					currx += dx[currd];
					curry += dy[currd];
					
					int des = findNode(currx, curry);
					
					node[src].edge[currd]=des;
					node[des].edge[(currd+3)%6]=src;
					
				}
			}
		}
		
		int source = findNode(0,0);
		int destination = findNode(currx,curry);
		
		return dijikstras(0,destination);
		
		
		
	}
	
	public static int dijikstras(int source,int destination)
	{
		int dist[] = new int[5001];      //Dist for each state 
		Arrays.fill(dist,Integer.MAX_VALUE);
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(50,new Comparator<Integer>(){
			
			public int compare(Integer state1,Integer state2)    //compare state wise distances
			{
				int n1 = state1;
				int n2 = state2;
				
				return dist[n1]-dist[n2];
				
				
			}
			
		});
		
		boolean visited[] = new boolean[5001];    //visited for states
		dist[0]=0;
		pq.add(source);
		
		while(!pq.isEmpty())
		{
			int next_state = pq.remove();
			
			int next = next_state/6;       //node index of next state
			
		
			
			int curr_dir = next_state%6;       
			
			if(visited[next_state])
				continue;
			
			visited[next_state] = true;
			
			if(next == destination) 
				return dist[next_state];
			
			int left_state = next*6 + (curr_dir+5)%6;
			if(dist[next_state]+3<dist[left_state])
			{ 
				dist[left_state] = dist[next_state]+3;
			
				pq.add(left_state);
				
				
			}
			
			int right_state = next*6 + (curr_dir+1)%6;
			if(dist[next_state]+3<dist[right_state])
			{
				dist[right_state] = dist[next_state]+3;
				
				pq.add(right_state);
			}
			
			int steps = 0,time=4;
			
			while(node[next].edge[curr_dir]>-1)
				
			{steps++;
			
				next = node[next].edge[curr_dir];
				if(steps>1) time = 8 + (steps-2)*2;  //due to acceleration for consecutive forward moves
				
				if(dist[next*6+curr_dir]>dist[next_state]+time)
				{
					dist[next*6 + curr_dir] = dist[next_state]+time;
					
					pq.add(next*6+curr_dir);
				}
				
				
				
			}
			
		}
		
		return -1;
		
		
	}
	
	public static int findNode(int x,int y)
	{
		for(int i=0;i<nodecount;i++)
		{
			if(node[i].x==x && node[i].y==y)
				return i;
			
			
			
		}
		
		node[nodecount] = new Node(x,y);
		
		return nodecount++;
		
		
	}
	

}
class Node{
	int x;int y;
	int edge[];
	
	Node(int x,int y){
		 edge = new int[6];
		for(int i=0;i<6;i++)
			this.edge[i]=-1;
		this.x=x;this.y=y;
		
		 
	}
}