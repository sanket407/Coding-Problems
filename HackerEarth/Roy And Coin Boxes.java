// https://www.hackerearth.com/problem/algorithm/roy-and-coin-boxes-1/
// We do lazy propogation for the updates
//After all updates we do a final propogation to pass all values to leaf nodes 
// at leaf nodes we store count of each
//sort them and later do binary Searh
//Note that no need to build tree as initially all values will be 0

import java.io.*;
 
import java.util.*;
 
public class Main {
	
	public static int c[],lazy[]; 
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
 

 OutputWriter out=new OutputWriter(System.out);
 
 
 Reader r=new Reader();
 int n=r.nextInt();

 lazy=new int[4*n];
 
 int m=r.nextInt();
 
 c=new int[n];            // c will store the final count at leaf nodes ie in each box

 
 int a,b;
 for(int i=0;i<m;i++)
 {
	 a=r.nextInt()-1;
	 b=r.nextInt()-1;
	 
	 modify(1,0,n-1,a,b);
 }
 
pass(1,0,n-1);
 
 
 
 
 
 Arrays.sort(c);
 
int q=r.nextInt();

while(q-->0)
{ int x=r.nextInt();
	int i=Arrays.binarySearch(c, x);
	
	if(i>0)
		{while(i>=0 && c[i]==x)
			i--;
		i++;
		}
	else
	if(i<0)
		i=-1*(i+1);
	if(i>=n)
	System.out.println("0");
	else System.out.println(n-i);
		


	}}
	public static void pass(int index,int l,int r)
	{
		if(l==r)
			{return;}
		if(lazy[index]!=0) shift(index,l,r);
		int mid=(l+r)/2;
		pass(index<<1,l,mid);
		pass((index<<1)+1,mid+1,r);
		
		
		
	}
	public static void upd(int index,int l,int r,int x)
	{
		if(l==r)
			c[l]+=x;
		else
		lazy[index] +=x;
		
		
	}
	public static void shift(int index,int l,int r)
	{
		int mid=(l+r)/2;
		
		upd(index<<1,l,mid,lazy[index]);
		upd((index<<1)+1,mid+1,r,lazy[index]);
		
		lazy[index]=0;
		
	}
	
	public static void modify(int index,int l,int r,int x,int y)
{ 
		
		if(x<=l && y>=r)
		{
			upd(index,l,r,1);
			return;
		}
	
		shift(index,l,r);
		int mid=(l+r)/2;
		if(y<=mid)
			modify(index<<1,l,mid,x,y);
		else if(x>mid)
			modify((index<<1)+1,mid+1,r,x,y);
		else
		{modify(index<<1,l,mid,x,mid);
		modify((index<<1)+1,mid+1,r,mid+1,y);
		}
}

 
 
 
public static int find(int index,int l,int r,int x,int y)
{
	 if(x<=l && y >=r)
		 return c[index];
	
		 int mid=(l+r)/2;
	 
	 if(y<=mid)
		 return find(index<<1,l,mid,x,y);
	 if(x>mid)
		 return find((index<<1)+1,mid+1,r,x,y);
	 
	 
	  return  find(index<<1,l,mid,x,mid)  + find((index<<1)+1,mid+1,r,mid+1,y);      //note that since range is divided in half 
	                                                                                    //range end pts. x and y are also changed
	
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