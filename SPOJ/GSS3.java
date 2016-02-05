
// http://www.spoj.com/problems/GSS3/

import java.io.*;

import java.util.*;

public class Main {
	public static Node node[];public static int a[];
	public static void main(String[] args)throws IOException {
		

 InputStreamReader isr=new InputStreamReader(System.in);
  Reader r=new Reader();
  
 int n=r.nextInt();
node=new Node[4*n];
a=new int [n];
 
 

for(int i=0;i<n;i++)
	a[i]=r.nextInt();

build(1,0,n-1);     // 1 based indexing for seg tree array

int m=r.nextInt();
int c,x,y;
StringBuilder sb=new StringBuilder("");
while(m-->0)
{
	
	c=r.nextInt(); 
	x=r.nextInt()-1;
	 y=r.nextInt()-1;
	
	
	
	if(c==1)
		{sb.append(find(1,0,n-1,x,y).max);
	    sb.append('\n');
		}
	else
	{
		int k=y+1;
		modify(1,0,n-1,x,k);
	}
	

}
System.out.println(sb.toString());
	}
	
public static void modify(int index,int l,int r,int pos,int k)
{
	if(l==r)
	{
		node[index].total=node[index].max=node[index].max_l=node[index].max_r=k;
		return;
	}
	
	int mid=(l+r)/2;
	
	
	if(pos<=mid)
	   modify(2*index,l,mid,pos,k);
	else
		modify(2*index+1,mid+1,r,pos,k);
	
	node[index]=merge(node[2*index],node[2*index+1]);  //reconstruct the values of nodes after modifying
	
	
	
}
public static Node build(int index,int l,int r)
{  node[index]=new Node();                    //new node corr. to this range

	if(l==r)                                   //single element
	{
		node[index].total=node[index].max=node[index].max_l=node[index].max_r=a[l];
		return node[index];
	}
	
	int mid=(l+r)/2;
	
	
	node[index]=merge(build(2*index,l,mid)	, build(2*index+1,mid+1,r));    //find the values in each half
																			// and combine the values
	return node[index];

	
}

public static Node merge(Node n_left,Node n_right)
{
	Node n=new Node();
	n.total=n_left.total+n_right.total;
	n.max_l=Math.max(n_left.total + n_right.max_l,n_left.max_l);
	n.max_r=Math.max(n_right.total + n_left.max_r,n_right.max_r);
	n.max=Math.max(n_left.max_r + n_right.max_l, Math.max(n_left.max, n_right.max));
	
	return n;
	
	
}

public static Node find(int index,int l,int r,int x,int y)
{ //System.out.println(l+" "+r+" "+x+" "+y+" "+index);
	 if(x<=l && y >=r)
		 return node[index];                    // this node completely inside req. range 
	
	
	 int mid=(l+r)/2;
	 
	 if(y<=mid) 
		 return find(index*2,l,mid,x,y);               //req range completely on left of mid
	 if(x>mid)
		 return find(index*2+1,mid+1,r,x,y);            //req range completely on right of mid
	 
	 
	  return merge( find(index*2,l,mid,x,mid)  , find(index*2+1,mid+1,r,mid+1,y));  
	  //note that since range is divided in half range end pts. x and y are also changed
	
}



}

class Node{
	
 	int max;      //max subarray in this range
 	int max_l;    //max subarray starting at leftmost element of this range
	int max_r;    //max subarray starting at rightmost element of this range
	int total;     //total of all elements in this range
	
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