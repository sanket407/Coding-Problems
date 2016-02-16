
// http://codeforces.com/contest/500/problem/E
// Offline solution
 // for each query we find how much dist is covered if all dominoes are fallen
 //  we start from right most dynamo push it and continue leftwards
 //     after each push we update covered dist in range(p[i],p[i]+l[i])
 //  also if a query has left dynamo at i then we process that query
 
 // Note that we have made seg tree ranges in diff manner/
 //  child or l-r will be l-mid and mid-r instead of l-mid and mid+1-r-l since we need each subdist 
 //betwn two dominos
 // also base case is when we have two dominos and end of range in between them
 
 



import java.io.*;

import java.util.*;

public class Main {

	public static int covered[],p[],l[],lazy[];
	public static void main(String[] args)throws IOException {
	


 OutputWriter out=new OutputWriter(System.out);

 
 StringBuilder sb=new StringBuilder("");
 Reader r=new Reader();
 int n=r.nextInt();
  p=new int[n];
 l=new int[n];
 lazy=new int[4*n];
 covered=new int[4*n];
 for(int i=0;i<n;i++)
{
	p[i]=r.nextInt();
	l[i]=r.nextInt();
}
 
 int m=r.nextInt();
 int q[][]=new int[m][3];
 
 for(int i=0;i<m;i++)
 {
	 q[i][0]=r.nextInt()-1;
	 q[i][1]=r.nextInt()-1;
	 q[i][2]=i; //store index of query for offline processing
	 
 }
 Arrays.sort(q,new Comparator<int[]>(){
	 
	 public int compare(int a[],int b[])
	 
	 {return a[0]-b[0];}
	 
	 
 });
int curr_dom=m-1;int ans[]=new int[m];
 for(int i=n-2;i>=0;i--)
 {
	 ins(1,0,n-1,p[i],Math.min(p[i]+l[i],p[n-1]));   //update seg tree after pusing domino i
	 
   while(curr_dom>=0 && q[curr_dom][0]==i)     //cost = dist of range - covered of range
	 {
		 ans[q[curr_dom][2]]=p[q[curr_dom][1]]-p[q[curr_dom][0]]-find(1,0,n-1,q[curr_dom][0],q[curr_dom][1]);
	   curr_dom--;   
	   
	 }
	 
	 //System.out.println(i);
	// System.out.println(Arrays.toString(covered));
	 
	 
 }
for(int i=0;i<m;i++)
	sb.append(ans[i]+"\n");
	out.printLine(sb.toString());
	out.close();
	}	
	public static int find(int index,int l,int r,int x,int y)
	{
		
		if(x<=l && y>=r)
		{
			return covered[index];
			
		}
		
		if(lazy[index]==1)
			shift(index,l,r);
		
		int mid=(l+r)/2;
		
		if(y<=mid)
			return find(index*2,l,mid,x,y);
		else if(x>=mid)
			return find(index*2+1,mid,r,x,y);
		else
		{
			return find(index*2,l,mid,x,mid)+find(index*2+1,mid,r,mid ,y);
			
			
		}
		
	}
	
	public static void shift(int index,int l,int r)
	{
		int mid=(l+r)/2;
		lazy[index*2]=lazy[index*2+1]=1;
		covered[index*2]=p[mid]-p[l];
				covered[index*2+1]=p[r]-p[mid];
		
		
	}
public static int ins(int index,int l,int r,int x,int y)
{
	if(x<=p[l] && y>=p[r])              
	{
		lazy[index]=1;
		int change=p[r]-p[l]-covered[index];    //we pass changes in covered 
		covered[index]=p[r]-p[l];
		
		return change;
		
		
	}
	
	if(r-l==1)                          //only two nodes
	{                                  //see that left range of any query will coincide with a dominos
										//but right end may be between 2 dominos
		int change=y-p[l]-covered[index];  //change=right end - left point - old covered
		if(change>0)
		{                                     //this domino may cover less dist than some previous one
			covered[index]+=change;
			return change;
		}
		return 0;
	}
	
	if(lazy[index]==1)
		shift(index,l,r);
	
	int mid=(l+r)/2;
	
	if(y<=p[mid])
		{int change=ins(index*2,l,mid,x,y);
		 covered[index]+=change;
		// System.out.println("2"+change);
		 return change;
		}
	else if(x>=p[mid])
		{int change=ins(index*2+1,mid,r,x,y);
		covered[index]+=change;
		//System.out.println("3"+change);
		return change;
		}
	else
	{
	int change=	ins(index*2,l,mid,x,p[mid]);
		change+=ins(index*2+1,mid,r,p[mid],y);
		covered[index]+=change;
		//System.out.println("4"+change);
		return change;
		
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