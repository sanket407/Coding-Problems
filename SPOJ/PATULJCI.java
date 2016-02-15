// http://www.spoj.com/problems/PATULJCI/
// array of length n  will have  more than n/2 same colors only if atleast one half(length m) of it has 
// more than m/2 of that color
// we use above prop in seg trees
// also we merge and store all elements in sorted order to  get count by binary search

import java.io.*;

import java.util.*;

public class Main {
	//public static Node node[];
	public static  Node node[];public static int cap[];
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub

// InputStreamReader isr=new InputStreamReader(System.in);
 OutputWriter out=new OutputWriter(System.out);
 //BufferedReader br=new BufferedReader(isr);
 
 
 Reader r=new Reader();
 int n=r.nextInt();
 int c=r.nextInt();
 
  cap=new int[n];
 for(int i=0;i<n;i++)
	 cap[i]=r.nextInt();
 
 node=new Node[4*n];
 build(1,0,n-1);
 
int m=r.nextInt();
StringBuilder sb=new StringBuilder("");
for(int i=0;i<m;i++)
	
{
	int a=r.nextInt()-1;
	int b=r.nextInt()-1;
   Node nn=find(1,0,n-1,a,b);
	sb.append(nn.poss==0?"no\n":"yes "+nn.color+'\n');
}
out.printLine(sb.toString());
out.close();
	}
	
	public static Node build(int index,int l,int r)
	{
		//System.out.println(l+" "+r+" "+index);
		
		if(l==r)
		{ node[index]=new Node(1);
			node[index].list[0]=cap[l];
			node[index].color=cap[l];
			node[index].poss=1;
			return node[index];
		}
		
		int mid=(l+r)/2;
		node[index]=merge(build((index<<1),l,mid)  , build((index<<1)+1,mid+1,r));
		
		return node[index];
		
		
	}
	
	public static Node merge(Node n1,Node n2)
	{  
		
	    int s1=n1.list.length;
	    int s2=n2.list.length;
	    Node n=new Node(s1+s2);
	    int i=0,j=0,k=0;
	    while(i<s1 && j<s2)
	    {
	    	if(n1.list[i]<n2.list[j])
	    		n.list[k++]=n1.list[i++];
	    	else
	    		n.list[k++]=n2.list[j++];
	    }
	    
	    while(i<s1)
	    	n.list[k++]=n1.list[i++];
	    while(j<s2)
	    	n.list[k++]=n2.list[j++];
	  // System.out.println(Arrays.toString(n.list));
	    
		if(n1.poss==1 && n2.poss==1 && n1.color==n2.color)  //both halves have more than half of same color
		{n.poss=1;n.color=n1.color;return n;}
		
		if(n1.poss==0 && n2.poss==0)       
		{
			n.poss=0;return n;
		}
		
		if(n1.poss==1)
		{
			i=Arrays.binarySearch(n.list, n1.color-1);
			
			if(i<0)
				i=-1*(i+1);    
			else
				i++;
			
			j=Arrays.binarySearch(n.list,n1.color+1);
			
			if(j<0)
				j=-1*(j+1);
			j--;
			
		
			if(j-i+1 > (s1+s2)/2)
			{
				n.poss=1;
				n.color=n1.color;
				//System.out.println(i+" "+j+" 1");
				return n;
			}
			
		}
		
		if(n2.poss==1)
		{

			i=Arrays.binarySearch(n.list, n2.color-1);
			
			if(i<0)
				i=-1*(i+1);    
			else
				i++;
			
			j=Arrays.binarySearch(n.list,n2.color+1);
			
			if(j<0)
				j=-1*(j+1);
			j--;
		
			if(j-i+1 > (s1+s2)/2)
			{
				n.poss=1;
				n.color=n2.color;
				//System.out.println("3");
				return n;
			}
			
			
		}
		n.poss=0;return n;
		
		
	}
	public static Node find(int index,int l,int r,int x,int y)
	{
		if(l>=x && r<=y)
		{
			return node[index];
		}

		int mid=(l+r)/2;
		
		if(y<=mid)
			return find((index<<1),l,mid,x,y);
		else if(x>mid)
			return find((index<<1)+1,mid+1,r,x,y);
		else
			return merge(find((index<<1),l,mid,x,mid) , find((index<<1)+1,mid+1,r,mid+1,y));
		
	}
	
	}
class Node{
	int poss;
	int color;
	int list[];
	
	Node(int n)
	{
		list=new int[n];
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