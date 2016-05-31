// Topcoder SRM 691 - MEDIUM
// Here for each connected component of graph we find no. of subsets which satisfy the problem.
//Then their product is the ans.
// For each component:
//      since no. of edges = no. of nodes it is guaranteed that each component will contain 1 cycle
//     so there are two types of components - with 1: 1 cycle 2: 1 cycle + tail nodes
//     Now if we choose any nodes from cycle as subset it is guaranteed that whole cycle will be connected
//     to new node N. So the cycle remains connected. But if we choose ONLY tail nodes in subset then they 
//    get disconnected from cycle nodes.
//    So no. of satisfying subsets for comp. with only 1 cycle = 2 ^(no. of cycle-nodes) - 1 ;
 //         and  for comp. with cycle and tail nodes = (2 ^(no. of cycle-nodes )- 1) * 2^(no. of tail nodes)
// We use dfs for finding all counts
//ALSO if the whole graph is only 1 connected component then we add +1 to ans. as the empty subset of whole graph
// will also be valid as all nodes of graph will be connected to N

import java.io.*;
import java.util.*;
 
 
public class Main {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream input = System.in;
		InputReader in = new InputReader(input);
		
   int a[] = {9,2,0,43,12,14,39,25,24,3,16,17,22,0,6,21,18,29,34,35,23,43,28,28,20,11,5,12,31,24,8,13,17,10,15,9,15,26,4,13,21,27,36,39};
   System.out.println(count(a));
		   
		 
	
	
		
	
	}	
		
	public static long count(int a[])
	{
		
		int n = a.length;
		int adj[][]=new int[n][n]; //adj[i][j] =1 if only 1 edge | adj[i][j] = 2 if 2 edges ie they both form cycle
		
		for(int i=0;i<n;i++)
		{adj[a[i]][i]++; adj[i][a[i]]++;}
		
		int components = 0;
		boolean visited[] = new boolean[n];
		
		long ans = 1;
		for(int i=0;i<n;i++)
			if(!visited[i])
			{ components++;
			ans *= dfs(i,adj,visited);
			}
//	System.out.println(components);
		if(components == 1 )
			return ans+1;
		else return ans;
		
	}
	
		

		
		
		
		
	
	
public static long dfs(int start,int adj[][],boolean visited[])
{
	int n = adj.length;
	Stack<Integer> s = new Stack<Integer>();
	
	
	
	s.push(start);
	
	int counter[]=new int[n]; //keep track of position of curr from start node
	counter[start] = 1;
	long total = 0;
	long cycle_length = 0;

	while(!s.empty())
	{
		int curr = s.pop();
	
		if(visited[curr])
			continue;
		total++;
	
	
	
	
	visited[curr] = true;
		
		for(int i=0;i<n;i++)
			{
			if(adj[curr][i]==2 && visited[i]) // if this node has double edge with other node
				cycle_length = 2;             //so these 2 nodes form cycle
			else if(adj[curr][i]==1 && visited[i]) 
			{                                   //adj with prev discovered node
				if(counter[curr]-counter[i]!=1)  // to avoid mistaking prev. node for cycle
				{
					cycle_length = counter[curr] - counter[i] + 1;}
			}
			else if(adj[curr][i]>=1 && !visited[i])
			{
				counter[i] = counter[curr]+1;
				s.push(i);
				
				
			}
			
			}
		
		
		
		
	}
	
	long tail_nodes = total - cycle_length;
	
	long a1 = (long)Math.pow(2,cycle_length);
	
	long a2 = (long)Math.pow(2, tail_nodes);
	
	
	
	return (a1-1)*a2 ;
}

	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
}
