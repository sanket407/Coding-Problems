
// http://www.spoj.com/problems/MULTQ3/
// keep track of no's in each range div[0]=div by 3 , div[1]=gives mod 1 on div by 3 ,
 //  div[2]=gives mod 2 on div by 3 

import java.io.*;
 
import java.util.*;
 
public class Main {
	
	public static int lazy[],div[][];
	public static void main(String[] args)throws IOException {
		
 
 InputStreamReader isr=new InputStreamReader(System.in);
 
 
 Reader r=new Reader();
 int n=r.nextInt();

 div=new int[4*n][3];
lazy=new int[4*n];
 
 
 
 
 
build(1,0,n-1);     // 1 based indexing for seg tree array
 
int m=r.nextInt();
int c,x,y;
StringBuilder sb=new StringBuilder("");
while(m-->0)
{ 
	
	c=r.nextInt(); 
	x=r.nextInt();
	 y=r.nextInt();
	

	if(c==1)
		{sb.append(find(1,0,n-1,x,y));
	    sb.append('\n');
		}
	else
	{
		
		modify(1,0,n-1,x,y);
	}
	
 
}
System.out.println(sb.toString());
	}
	
	public static void upd(int index,int l,int r,int x)
	{ lazy[index]+=x;
	
	x=x%3;
	if (x==0)
		return;
	if(x==1)
	{ int t=div[index][0];
		div[index][0]=div[index][2];
		div[index][2]=div[index][1];
		div[index][1]=t;
	}
	else	
	if(x==2)
	{
		int t=div[index][0];
		div[index][0]=div[index][1];
		
		div[index][1]=div[index][2];
		div[index][2]=t;
		
	}
	}
	public static void shift(int index,int l,int r)
	{
		int mid=(l+r)/2;
		
		upd(index*2,l,mid,lazy[index]);
		upd(index*2+1,mid+1,r,lazy[index]);
		
		lazy[index]=0;
	}
	
public static void modify(int index,int l,int r,int x,int y)
{ 
	if(x<=l && y >=r)
	{
		upd(index,l,r,1);
		return;
		
	}
	
	shift(index,l,r);
	int mid=(l+r)/2;
	
	
	if(y<=mid)
	   modify(2*index,l,mid,x,y);
	else
	if(x>mid)
		modify(2*index+1,mid+1,r,x,y);
	else
	{ modify(2*index,l,mid,x,mid);
	modify(2*index+1,mid+1,r,mid+1,y);
	}
	
	div[index][0]=div[index*2][0]+div[index*2+1][0];
	div[index][1]=div[index*2][1]+div[index*2+1][1];
	div[index][2]=div[index*2][2]+div[index*2+1][2];
	
}
public static void build(int index,int l,int r)
{ 
	if(l==r)
	{
		div[index][0]=1;
		return;
	}
	
	int mid=(l+r)/2;
	build(2*index,l,mid);
	build(2*index+1,mid+1,r);
		
	div[index][0]=(r-l+1);
	
	
 
	
}
 

 
public static int find(int index,int l,int r,int x,int y)
{ //System.out.println(l+" "+r+" "+x+" "+y+" "+index);
	 if(x<=l && y >=r)
		 return div[index][0];
	
	shift(index,l,r);
	 int mid=(l+r)/2;
	 
	 if(y<=mid)
		 return find(index*2,l,mid,x,y);
	 if(x>mid)
		 return find(index*2+1,mid+1,r,x,y);
	 
	 
	  return  find(index*2,l,mid,x,mid)  + find(index*2+1,mid+1,r,mid+1,y);      
	
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