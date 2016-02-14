
// http://www.spoj.com/problems/POSTERS/
//We don't need all elements in the interval [1,10^7]. The only thing we need is the set s1,s2,...,sk 
//where for each i, si is either L or  R in one of the queries.

//We can use interval 1....k instead of that (each query is running in this interval, in code, 
//we use 0-based). For the i-th query, we will paint all the interval [l,r](l==sx,r==sy) with 
//color i (1-based).

//For each interval, if all it's interval is from the same color, I will keep that color for it and 
//update the nodes using lazy propagation.

//So,we will have a value lazy for each node and there is no any build function (if lazy[i] ≠ 0 then 
//all the interval of node i is from the same color (color lazy[i]) and we haven't yet shifted the updates
 //to its children. Every member of lazy is 0 at first).




import java.io.*;

import java.util.*;

public class Main {
	//public static Node node[];
	public static int lazy[];public static Set<Integer> ans;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub


 
 Reader r=new Reader();
 int t=r.nextInt();
 for(int ii=1;ii<=t;ii++)
 {
 int n=r.nextInt();
SortedSet<Integer> set=new TreeSet<Integer>(); //store sorted set of distinct range end points
 int q[][]=new int[n][2];

 for(int i=0;i<n;i++)
 {
	 
	 q[i][0]=r.nextInt();
	 q[i][1]=r.nextInt();
	 set.add(q[i][0]);
	 set.add(q[i][1]);
	 
	 
	 
	 
 
 }
 int pos[]=new int[10000010];     //pos of range end pts in seg tree array
 int p=set.size();            //seg tree of range 0 to p-1
 lazy=new int[4*p];     
 Iterator<Integer> ir=set.iterator();
 int i=0;
 while(ir.hasNext())
 {
	 
	
	 pos[ir.next()]=i++;
	 
	 
 }
 
 for( i=0;i<n;i++)
 {
	 
	 modify(1,0,p-1,pos[q[i][0]],pos[q[i][1]],i+1);
	 
	 
	 
 }
 
 ans=new TreeSet<Integer>(); //storing the distinct colors in the tree

 count(1,0,p-1);
 
 System.out.println(ans.size());
 
 

	}

	}
	
	
	public static void count(int index,int l,int r)
	{
		if(lazy[index]!=0)            //this range is of same color . So add to ans
		{
			ans.add(lazy[index]);return;
		}
		
		int mid=(l+r)/2;
		count(index*2,l,mid);
		count(index*2+1,mid+1,r);
		
		
	}
	public static void shift(int index,int l,int r)
	{
		
		int mid=(l+r)/2;
		
		lazy[index*2]=lazy[index];
		lazy[index*2+1]=lazy[index];
		
		lazy[index]=0;
		
		
	}
	public static void modify(int index,int l,int r,int x,int y,int poster)
{ //System.out.println(l+" "+r+" "+index+" "+pos);
		if(x<=l && y>=r)
		{
			lazy[index]=poster;
			return;
			
			
		}
		
		if(lazy[index]!=0)           //lazy pending
			shift(index,l,r);
		
		int mid=(l+r)/2;
		
		if(y<=mid)
			modify(index*2,l,mid,x,y,poster);
		else if(x>mid)
			modify(index*2+1,mid+1,r,x,y,poster);
		else
		{
			modify(index*2,l,mid,x,mid,poster);
			modify(index*2+1,mid+1,r,mid+1,y,poster);
		}
		
	
}

	
	

	








}

//class Node{
	
	
	
//}
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