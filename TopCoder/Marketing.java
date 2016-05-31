// https://community.topcoder.com/tc?module=ProblemDetail&rd=4472&pm=1524
// It is a bipartite verifications problem 
// We verify bipartiteness using DFS by assigning alternating colors to adj vertices making sure they dont 
// conflict with other neighbours


import java.io.*;
import java.util.*;
 
 
public class Main {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream input = System.in;
		InputReader in = new InputReader(input);
		
   
		String s[] ={"1 2 3 4", "", "", "", "", "1 7", "2 8", "3", "4", "10", "11", "12", "13", "14", "9"};

		//sortedAreas(s);
		System.out.println(howMany(s));
	}
	
	public static long howMany(String[] compete)
	{
    	
		
		boolean adj [][]=new boolean[compete.length][compete.length];
		
		for(int i=0;i<compete.length;i++)
		{
			
			
			
			StringTokenizer st = new StringTokenizer(compete[i]," ");
			
			while(st.hasMoreTokens())
			{
				int k = Integer.parseInt(st.nextToken());
			
				adj[i][k]=true;adj[k][i]=true;
				
			}
		}
			
			
	
			
			
			long ans = 1;
			boolean visited[] = new boolean[compete.length];
			
			for(int i=0;i<compete.length;i++)
				if(!visited[i])
				{
					ans *= dfs(i,adj,visited);
					
					
					
			
		}if(ans==0)
			 ans = -1;  // here if any disjoint part of graph is not bipartite it returns 0. So product ans. will be zero
			return ans;
		
	}
		
		
		
		
		
    	
    
		
	public static int dfs(int start,boolean adj[][],boolean visited[])
	{
		boolean flag = false;  // will be true if bipartiteness fails
		
		
		Stack<Integer> s = new Stack<Integer>();   // for running dfs
		int n = adj.length;
		
		 int color [] = new int[n];  		//assign colors
			
		s.push(start);
		color[start]=0;
	
		while(!s.empty())          //here this dfs will fully discover one connected component
		{ 
		
			int curr = s.pop();
		
			if(visited[curr])
				continue;
			
			visited[curr]=true;
			
			for(int i=0;i<n;i++)
				if(adj[curr][i] &&  visited[i] && color[i] == color[curr] ) //fails bipartite for neighbours which have already been assigned colors
					{flag = true; }
			
			if(flag==true )
				continue;
			
			
				
				for(int i=0;i<n;i++)
					if(adj[curr][i] &&  !visited[i]  )
						{
						
						s.push(i);
					color[i] = color[curr]^1;  //set colors for neighbouts and add them to stack
						}
				
		
			
			
			
			
		
		}
		if(flag==false)
		
		return 2;
		else return 0;
		
		
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