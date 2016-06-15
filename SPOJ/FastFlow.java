import java.io.*;
import java.util.*;
 
 
public class Main {
 

	public static int mod = 1000000007;
	
	public static int n,nedges,source,sink,m;
	public static int flow[],cap[],level[],from[],first[],next[],to[],temp[];
	

	

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		InputStream input = System.in;
		InputReader in = new InputReader(input);
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int _n = in.nextInt();
		int _m = in.nextInt();
		

		init(0,_n-1,_n,_m);
		
		for(int i=0;i<m;i++)
		{
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			int c = in.nextInt();
			
			if(a!=b)
			{
			add_edge(a,b,c);
			}
					
					
		}
	
		long ans = dinics();
		
		System.out.println(ans);
	}
	
	public static void init(int src,int snk,int _n,int _m)
	{
		source = src; sink = snk; n = _n; m = _m;
		to = new int[2*m];
		flow = new int [2*m];
		next = new int[2*m];
		cap = new int[2*m];
		first = new int[n];
		temp = new int[n];
		nedges = 0;
		Arrays.fill(first, -1);
	}
	
	public static void add_edge(int u,int v,int c)
	{
		to[nedges] = v; flow[nedges] = 0; cap[nedges] = c;next[nedges] =first[u]; first[u] = nedges++;
		to[nedges] = u; flow[nedges] = 0; cap[nedges] = c;next[nedges] = first[v];first[v] = nedges++;
	
	}

	
	public static long dinics()
	{
		long ans = 0;
		
		int df=0;
		
		while(bfs())
		{System.arraycopy(first, 0, temp, 0, n);
			while(true)
			{
				
				df = dfs(source,Integer.MAX_VALUE);
				if(df!=0)
					ans += df;
				else break;
				
				
			}
			
			
			
		}
		return ans;
		
	}
	
	public static boolean bfs()
	{
		int Q[] = new int[n];
		int size =0, curr=0;
		level = new int[n];
		
		Q[size++] = source;
		level[source] = 1;
		while(curr < size)
		{
			int u = Q[curr++];
			//System.out.println(u);
			for(int i=first[u];i>=0;level[u]=i=next[i])
			{	int v = to[i];
			//System.out.println(i);
				if(cap[i]-flow[i]>0 && level[v]==0)
				{
					level[v] =level[u]+1;
					Q[size++] = v;
				}
				
			}
			
		}
		
		if(level[sink]==0)
			return false;
		else return true;
		
		
		
	}
	
	public static int dfs(int u,int f)
	{
		if(u == sink)
			{return f;}
		int df = 0;
		for(int i=temp[u];i>=0;i=next[i])
		{
			int v = to[i];
			if(cap[i] - flow[i] >0 && level[v] == level[u]+1)
			{
				df = dfs(v,Math.min(f, cap[i] - flow[i]));
				
				if(df>0)
				{
					flow[i] += df;
					flow[i^1] -= df;
					return df;
					
					
				}
				
				
				
			}
			
			
		}
		
		return 0;
		
	}
	
	
	
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while  (tokenizer == null || !tokenizer.hasMoreTokens()) {
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