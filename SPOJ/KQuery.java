// http://www.spoj.com/problems/KQUERY/

// online solution : keep sorted array in each node and while merging do bin search to find no. of elements.
// greater than k
//offline solution : sort array and queries in desc. order of elements
//  then process queries as follows:
//     if(array element <= next query element)
//	         search the query in seg tree
//	insert array element 
//  ie at time of exec of query it is guranted that only elements greater than k are inserted in tree		 


import java.io.*;

import java.util.*;

public class Main {
	
	public static int a[][],c[],q[][];
	public static void main(String[] args)throws IOException {
		


OutputWriter out=new OutputWriter(System.out);
 
 Reader r=new Reader();
 int n=r.nextInt();
 a=new int[n][2];          //a[index][0]= array element ,a[index][1]=original index before sorting
 for(int i=0;i<n;i++)
	 {a[i][0]=r.nextInt();
     a[i][1]=i;}
 int m=r.nextInt();
 
 c=new int[4*n];
 q=new int[m][4];
 int ans[]=new int[m];     //ans[ i] will store ans of query[i]   
 for(int i=0;i<m;i++)
 {
	 q[i][0]=r.nextInt()-1; //left end of range
	 q[i][1]=r.nextInt()-1; //right end of range
	 q[i][2]=r.nextInt();  //k
	 q[i][3]=i;            //original index before sorting
	                       
	 
 }
 
 Arrays.sort(q,new Comparator<int[]>(){
	 
	 public int compare(int a1[],int a2[])
	 {
		 return a2[2]-a1[2];        //sort queries in desc order of k
		 
		 
	 }
	 
	 
 });

Arrays.sort(a,new Comparator<int[]>(){
	 
	 public int compare(int a1[],int a2[])
	 {
		 return a2[0]-a1[0];        //sort array in desc order of values
		 
		 
	 }
	 
	 
 });

 
 
 //StringTokenizer st=new StringTokenizer(br.readLine());
 
int next_query=0;

for(int i=0;i<n;i++)
{
	while(next_query<m && a[i][0]<=q[next_query][2])
	{ 
		ans[q[next_query][3]]=find(1,0,n-1,q[next_query][0],q[next_query][1]);
    next_query++;
		
	}
	insert(1,0,n-1,a[i][1]);
	
	
}
for(int i=next_query;i<m;i++)
{
	ans[q[i][3]]=find(1,0,n-1,q[i][0],q[i][1]);
	
	
}

StringBuilder sb=new StringBuilder("");


//int ans[]=new int[m];
//System.out.println(Arrays.toString(c));



for(int i=0;i<m;i++)
	{sb.append(ans[i]+"\n");
	}
out.printLine((sb.toString()));
out.close();
	}
	
	public static void insert(int index,int l,int r,int pos)
{ //System.out.println(l+" "+r+" "+index+" "+pos);
		
		if(l==r)
			{c[index]=1;
			return;
			}
		
		int mid=(l+r)/2;
		
		if(pos<=mid)
	 insert(index<<1,l,mid,pos);
		else 
			insert((index<<1)+1,mid+1,r,pos);
		
		c[index]=c[index<<1]+c[(index<<1)+1];
		
		
		
	
}




public static int find(int index,int l,int r,int x,int y)
{/// System.out.println(l+" "+r+" "+x+" "+y+" "+index);
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