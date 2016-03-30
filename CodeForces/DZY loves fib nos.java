import java.io.*;

import java.util.*;

public class Main {
	//public static Node node[];
	public static ArrayList<Integer> pending[];public static long sum[],ans,f[],prefix[];public static int a[];
	public static final long mod=1000000009 ;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub

// InputStreamReader isr=new InputStreamReader(System.in);
 OutputWriter out=new OutputWriter(System.out);
 //BufferedReader br=new BufferedReader(isr);
 
 StringBuilder sb=new StringBuilder("");
 Reader r=new Reader();
 int n=r.nextInt();
 int m=r.nextInt();
 
 a=new int[n];
 pending=new ArrayList[4*n];
 f=new long[n+10];
prefix=new long[n+10];
sum=new long[4*n];

 f[1]=1;f[2]=1;prefix[1]=1;prefix[2]=2;
 for(int i=3;i<=n;i++)
	 {f[i]=(f[i-1]+f[i-2]);
	 
      prefix[i]=(f[i]+prefix[i-1]);
      
      }
//System.out.println(Arrays.toString(prefix));
//System.out.println(Arrays.toString(f));
 for(int i=1;i<4*n;i++)
	 pending[i]=new ArrayList<Integer>();
  for(int i=0;i<n;i++)
  {
	  a[i]=r.nextInt();
	  
	  
  }
 build(1,0,n-1);
 
 //for(int i=1;i<300;i++)
	// System.out.println(pending[i].toString()+" "+sum[i]);
  for(int i=0;i<m;i++)
  {
	  
    if(r.nextInt()==1)  
	  {int x=r.nextInt()-1;
	   int y=r.nextInt()-1;
	  
	   add(1,0,n-1,x,y,1);
	 //(int ii=1;ii<=8;ii++)
		//	 System.out.println(pending[ii].toString()+" "+sum[ii]);
	   
	  }
    else
    {
    	int x=r.nextInt()-1;
    	int y=r.nextInt()-1;
    	
    	System.out.println(find(1,0,n-1,x,y));
    }
	  
  }
	}
  public static long find(int index,int l,int r,int x,int y)
  {
	  if(x<=l && y>=r)
	  {
		  return sum[index];
	  }
	  
	  if(!pending[index].isEmpty())
	  shift(index,l,r);
	  
	  int mid=(l+r)/2;
	  
	  if(y<=mid)
		  return find(index*2,l,mid,x,y);
	  else if(x>mid)
		  return find(index*2+1,mid+1,r,x,y);
	  else
		  return (find(index*2,l,mid,x,mid) + find(index*2+1,mid+1,r,mid+1,y))%mod;
	  
  }
	public static void build(int index,int l,int r)
	{//System.out.println(index+" "+l);
		if(l==r)
		{
			sum[index]=a[l];
			return;
		}
		
		int mid=(l+r)/2;
		
		build(index*2,l,mid);
		build(index*2+1,mid+1,r);
		sum[index]=(sum[index*2]+sum[index*2+1])%mod;
		sum[index]%=mod;
		
	}
	
	public static void shift(int index,int l,int r)
	{
		int mid=(l+r)/2;
		
		for(int i:pending[index])
		{
			pending[index*2].add(i);
			pending[index*2+1].add(i+mid-l+1);
		   sum[index*2]+=(prefix[i+mid-l]-prefix[i-1])%mod;
		   sum[index*2+1]+=(prefix[i+r-l]-prefix[i+mid-l])%mod;
			sum[index*2]%=mod;
			sum[index*2+1]%=mod;
		}
		pending[index].clear();
		
	}
	
  public static void add(int index,int l,int r,int x,int y,int start)
  {
	  
	  if(x<=l && y>=r)
	  {
		  pending[index].add(start);
		  //System.out.println((start+r-l)+" "+prefix[start+r-l]+"---"+(start-1)+" "+prefix[start-1]);
		  sum[index]+=(prefix[start+r-l]-prefix[start-1])%mod;
		  sum[index]%=mod;
		 // System.out.println(sum[index]+"***");
		  return;
	  }
	  
	  if(!pending[index].isEmpty())
	       shift(index,l,r);
	  
	  int mid=(l+r)/2;
	  
	  if(y<=mid)
		  add(index*2,l,mid,x,y,start);
	  else if(x>mid)
		  add(index*2+1,mid+1,r,x,y,start);
	  else
	  {
		  add(index*2,l,mid,x,mid,start);
		  add(index*2+1,mid+1,r,mid+1,y,start+mid-x+1);
		  
	  }
	  sum[index]=(sum[index*2]+sum[index*2+1])%mod;
	  sum[index]%=mod;
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