// http://codeforces.com/problemset/problem/292/E
// Here we are supposed to copy a subarray from A to a subarray in B.
// We store the subarray B in seg tree
// while doing copying we query the start index of subarray A which is to be copied
// when we get the range we store start index in lazy of that range// if range is divided then for 
// right range the start index changes accorinding to mid and l and pos value
// Note that lazy is used for updating
// For finding element value we find topmost range with lazy value
// Then we find new required value from the start value(stored in lazy)  wrt to the range left and position values



import java.io.*;

import java.util.*;

public class Main {
	//public static Node node[];
	public static int a[],b[],lazy[];public static long ans;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub

// InputStreamReader isr=new InputStreamReader(System.in);
 OutputWriter out=new OutputWriter(System.out);
 //BufferedReader br=new BufferedReader(isr);
 
 StringBuilder sb=new StringBuilder("");
 Reader r=new Reader();
 int n=r.nextInt();
 lazy=new int[4*n];
 a=new int[n];
 b=new int[n];
 
 int m=r.nextInt();
 
 for(int i=0;i<n;i++)
	 a[i]=r.nextInt();
 for(int i=0;i<n;i++)
	 b[i]=r.nextInt();
 
 for(int i=0;i<m;i++)
 {  
	 if(r.nextInt()==1)
	 {
		 int x=r.nextInt()-1;int y=r.nextInt()-1;int k=r.nextInt();
		 
		 insert(1,0,n-1,y,y+k-1,x+1);      //note that lazy is 1-indexed
		//set start value = x+1 for range (y,y+k-1) in B
		 
	 }
	 
	 else
	 {  ans=0;
	 int pos=r.nextInt()-1;
		find(1,0,n-1,pos);
		 out.printLine(ans);
		 
	 }
 }
out.close();	 
 }
 
 public static void shift(int index,int l,int r)
 {
	 int mid=(l+r)/2;
	 
	 
	lazy[index*2]=lazy[index];
	  
	lazy[index*2+1]=lazy[index]+mid-l+1;    //start value will change for right child
		
	  lazy[index]=0;
	 
	 
 }
 
 public static void find(int index,int l,int r,int pos)
 {//System.out.println("find"+pos+" "+l+" "+r+" "+index);
	 
	 if(lazy[index]!=0)
		 {ans=a[lazy[index]-1+pos-l];return;}    //if curr range has lazy 
		 
	 
	 if(l==r)
	 {
		 if(lazy[index]==0) 
		 ans=b[l];                    //if this is leaf node and no lazy found 
		 return ;                  //which means no copying took place for this pos
	 }
	 
	
	 
	 int mid=(l+r)/2;
	 
	 if(pos<=mid)
		 find(index*2,l,mid,pos);
	 else
		 find(index*2+1,mid+1,r,pos);
	 
	 
 }
 public static void insert(int index,int l,int r,int x,int y,int k)
 {//System.out.println(x+" "+y+" "+l+" "+r+" "+index);
	 if(x<=l && y>=r)
	 { //System.out.println(k);
		 lazy[index]=k;
		 
		 return;
		 
	 }
	 
	 if(lazy[index]!=0)
		 shift(index,l,r);
	 
	 int mid=(l+r)/2;
	 
	 if(y<=mid)
		 insert(index*2,l,mid,x,y,k);
	 else if(x>mid)
		 insert(index*2+1,mid+1,r,x,y,k);
	 else
	 {
		 insert(index*2,l,mid,x,mid,k);
		 insert(index*2+1,mid+1,r,mid+1,y,k+mid-x+1);  //note that start value for right range changes
		 
	 }
	 
	 
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