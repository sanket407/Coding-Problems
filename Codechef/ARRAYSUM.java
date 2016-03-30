//  https://www.codechef.com/problems/ARRAYSUM/
// See the editorial for recurrence 
//Note that we use coordinate compression and seg trees to skip recurring addition part of recurrence

import java.io.*;
 
import java.util.*;
 
public class Main {
	
	public static int seg[];
	public static int val[];
	public static int mod = 1000000007;
	
	public static void main(String[] args)throws IOException {
		
OutputWriter out=new OutputWriter(System.out);
  Reader r=new Reader();
 
  
 
	
		int n =r.nextInt();
		int m =r.nextInt();
		
		int a[]=new int[n+1];
		int b[]=new int[m+1];
		
		for(int i=1;i<=n;i++)
			a[i]=r.nextInt();
		for(int i=1;i<=m;i++)
			b[i]=r.nextInt();
		
		
		
		int dp[][]=new int[m+1][n+1];  //dp[i][j] = no. of req. subseq of length i ending at index j
		
		
		for(int i=1;i<=n;i++)
			dp[1][i]=1;
		
		
	for(int len = 2; len<=m; len++)
	{
	   
	   
	   SortedSet <Integer> ss = new TreeSet<Integer>();
	   
	   for(int j=1;j<=n;j++)
	   {
        ss.add(b[len-1] + a[j]);      //add current length subseq last element and length-1 subseq last elements
        ss.add(b[len] + a[j]);		   
	   }
	   
	   int val [] =new int[ss.size()];
	   int N =ss.size();
	
	   Iterator ir =ss.iterator();
	   int k=0;
	   while(ir.hasNext())
		   val[k++]=(Integer)ir.next();
	                                          //get unique values to be used for seg tree
	 
	   seg = new int[4*N];
	   
	   for(int j=1;j<=n;j++)
	   {
		   /*Note that we are storing dp values of curr_len-1 in dp(using upd()) to calc dp values of curr_len */
		 
		   int pos = Arrays.binarySearch(val, a[j] + b[len]);
		/*Find count of subseq of length 'len-1'  and ending with index '1....j-1' with sum value of last element 
		                less than a[j]+b[len] */
		   dp[len][j] = find(1, 0, N-1, 0, pos);      
		  
		   /*add count of subseq of length 'len-1' and ending at index 'j' to seg tree */
		   pos = Arrays.binarySearch(val , a[j] + b[len-1]);
		   upd(1,0,N-1,pos,dp[len-1][j]);
		   
		   /*Note that when we find dp[len][j] , as per recurrence dp[len-1][1]....dp[len-1][j-1] has already been added
		         to seg tree */
		   
	   }
	   
		
		
		
		
		
		
		
	}
				
		
				
				
	long sum=0;
	for(int i=1;i<=n;i++)
		sum=(long)((sum+dp[m][i])%mod);
	
	System.out.println(sum);
			
	
		
		
	}
   	
	
 
	
	
	
	public static int find(int index,int l,int r,int x,int y)
	{
		//System.out.println("len-"+len+" "+index+" "+l+" "+r+" "+x+" "+y);
		if(x<=l && y>=r)
		{
			return seg[index];
			
			
		}
		int mid=(l+r)/2;
		//System.out.println(val[mid]);
		if(y <= mid)
			return find(index*2,l,mid,x,y);
		else if(x > mid)
			return find(index*2+1,mid+1,r,x,y);
		else
		{
			int a = find(index*2,l,mid,x,mid) + find(index*2+1,mid+1,r,mid+1,y);
			
			return a%mod;
			
	}
	}	
		public static void upd(int index,int l,int r,int pos,int add_value)
		{
			//System.out.println(index+" "+l+" "+r+" "+val_at_pos);
         if(l==r )
         {
        	 
        	 seg[index]=(seg[index]+add_value)%mod;
			return;
			}
         
         int mid=(l+r)/2;
         
         if(pos<=mid)
        	 upd(index*2,l,mid,pos,add_value);
         else
        	 upd(index*2+1,mid+1,r,pos,add_value);
         
         seg[index] = (seg[index*2]+seg[index*2+1])%mod;
         
         }
	
	
}
 
class Reader {
    final private int BUFFER_SIZE = 1 << 16;private DataInputStream din;private byte[] buffer;private int bufferPointer, bytesRead;
    public Reader(){din=new DataInputStream(System.in);buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
    }public Reader(String file_name) throws IOException{din=new DataInputStream(new FileInputStream(file_name));buffer=new byte[BUFFER_SIZE];bufferPointer=bytesRead=0;
    }public String readLine() throws IOException{byte[] buf=new byte[64];int cnt=0,c;while((c=read())!=-1){if(c=='\n')break;buf[cnt++]=(byte)c;}return new String(buf,0,cnt);
    }public int nextInt() throws IOException{int ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
    }public long nextLong() throws IOException{long ret=0;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c=read();do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(neg)return -ret;return ret;
    }public double nextDouble() throws IOException{double ret=0,div=1;byte c=read();while(c<=' ')c=read();boolean neg=(c=='-');if(neg)c = read();do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');if(c=='.')while((c=read())>='0'&&c<='9')ret+=(c-'0')/(div*=10);if(neg)return -ret;return ret;
    }private void fillBuffer() throws IOException{bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);if(bytesRead==-1)buffer[0]=-1;
    }private byte read() throws IOException{if(bufferPointer==bytesRead)fillBuffer();return buffer[bufferPointer++];
    }public void close() throws IOException{if(din==null) return;din.close();}
}
 
class OutputWriter {
	private final PrintWriter writer;
 
	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(outputStream);
	}
 
	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}
 
	public void print(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}
 
	public void printLine(Object... objects) {
		print(objects);
		writer.println();
	}
 
	public void close() {
		writer.close();
	}
}   