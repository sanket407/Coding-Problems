// http://www.spoj.com/problems/DQUERY/
// online solution : 
//     store distinct elements in each node in sorted order and while merging only add distinct ones
//offline solution :
//     sort queries in increasing order of right_range
//     while traversing array in increasing order 
//       add elements to seg tree
//      if element already discovered before remove old copy and add at new pos
//   idea is to store only one rightmost copy of element in tree





import java.io.*;

import java.util.*;

public class Main {
	//public static Node node[];
	public static int a[],c[],q[][];
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub

// InputStreamReader isr=new InputStreamReader(System.in);
 OutputWriter out=new OutputWriter(System.out);
 //BufferedReader br=new BufferedReader(isr);
 
 Reader r=new Reader();
 int n=r.nextInt();
 
 a=new int[n];
 for(int i=0;i<n;i++)
	 a[i]=r.nextInt();
 
 int m=r.nextInt();
 int ans[]=new int[m];
 c=new int[4*n];
 q=new int[m][3];
 
 for(int i=0;i<m;i++)
 {
	 q[i][0]=r.nextInt()-1;
	 q[i][1]=r.nextInt()-1;
	 q[i][2]=i;
	 
	 
 }
 
 Arrays.sort(q,new Comparator<int[]>(){
	 
	 public int compare(int a1[],int a2[])
	 {
		 return a1[1]-a2[1];
		 
		 
	 }
	 
	 
 });

 
 
 //StringTokenizer st=new StringTokenizer(br.readLine());
 



build(1,0,n-1);     // 1 based indexing for seg tree array
StringBuilder sb=new StringBuilder("");

int lastpos[]=new int[1000001];
//int ans[]=new int[m];
//System.out.println(Arrays.toString(c));
for(int i=0,qno=0;i<n;i++)
{//System.out.println(i);
	if(lastpos[a[i]]-1 != -1 )        //last pos is 1-based as we chk if (lastpos == 0) for uninitialized
	{
		modify(1,0,n-1,lastpos[a[i]]-1);
	}
	
	lastpos[a[i]]=i+1;
	//System.out.println(Arrays.toString(c));
	while(qno < m && q[qno][1]==i)
	{
		ans[q[qno][2]]=find(1,0,n-1,q[qno][0],i);
	
		qno++;
		
	}
	
	
}

for(int i=0;i<m;i++)
	{sb.append(ans[i]+"\n");
	}
out.printLine((sb.toString()));
out.close();
	}
	
	public static void modify(int index,int l,int r,int pos)
{ //System.out.println(l+" "+r+" "+index+" "+pos);
		c[index]--;
		if(l==r)
		{
			
			
			return;
		}
		
		
		int mid=(l+r)/2;
		
		
		if(pos <=mid)
			modify(index<<1,l,mid,pos);
		else 
			modify((index<<1)+1,mid+1,r,pos);
	
}
public static void build(int index,int l,int r)
{ 
	if(l==r)
	{
		c[index]=1;
		return;
	}
	
	int mid=(l+r)/2;
	build(index<<1,l,mid);
	build((index<<1)+1,mid+1,r);
		
	c[index]=(r-l+1);
	
	

	
}



public static int find(int index,int l,int r,int x,int y)
{ //System.out.println(l+" "+r+" "+x+" "+y+" "+index);
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