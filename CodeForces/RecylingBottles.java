import java.io.*;
 
import java.util.*;
 
// http://www.codeforces.com/contest/671/problem/A

/* here only 1st point to be chosen will affect final dist as for all other points
dist will be from bin to point and point to bin ie 2*dist(bin,point)
 So we initially add all dists ie 2*dist(bin,point) for all points
 Then we see which 1st point choice gives min(dist(person,point)-dist(point,bin)) for both persons
 Note that we had already added 2*dist(bin,point) for that point. But since it is to be chosen as 1st pt.
 we remove one dist(bin,point)
 So we do this for all points to find min. for both the persons
 Also same point may give min for both of them . So we keep track of best 2 min. of each person
 At end we choose according to mins.
 
 */
public class Main {
	
	
	public static int mod = 1000000007;
	
	public static void main(String[] args)throws IOException {
		
OutputWriter out=new OutputWriter(System.out);
  Reader r=new Reader();
 BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
  

int ax=r.nextInt(); 
int ay=r.nextInt();
int bx=r.nextInt();
int by=r.nextInt();
int rx=r.nextInt();
int ry=r.nextInt();

int n = r.nextInt();
int px[]=new int[n];
int py[]=new int[n];
double sum=0;
for(int i=0;i<n;i++)
{
	px[i]=r.nextInt();
	py[i]=r.nextInt();
	sum+=2*dist(px[i],py[i],rx,ry);  //add all dist (bin,point) for all points
	
}

double b1c= Double.MAX_VALUE;int b1i=-1;     //1st best for person b
double a1c= Double.MAX_VALUE;int a1i=-1;     //1st best for person a
double b2c= Double.MAX_VALUE;int b2i=-1;     //2nd best for person b
double a2c= Double.MAX_VALUE;int a2i=-1;      //22nd best for person b

for(int i=0;i<n;i++)
{
	double d1 = dist(ax,ay,px[i],py[i])-dist(rx,ry,px[i],py[i]);   
	
	if(d1<a2c)
	{
		a2c=d1;a2i=i;
		
	}                     //cal. dist and compare with prev best values
	if(a2c<a1c)
	{
		double t=a2c;
		a2c=a1c;
		a1c=t;
		int tt =a2i;
		a2i=a1i;
		a1i=tt;
		
	}
double d2 = dist(bx,by,px[i],py[i])-dist(rx,ry,px[i],py[i]);

	if(d2<b2c)
	{
		b2c=d2;b2i=i;
		
	}
	if(b2c<b1c)
	{
		double t=b2c;
		b2c=b1c;
		b1c=t;
		int tt =b2i;
		b2i=b1i;
		b1i=tt;
		
	}
	
	
}


if(n==1)         //if only 1 pt.
{
	System.out.println(Math.min(sum+a1c,sum+b1c));
	
	
}
else
{
double min;
if(a1i!=b1i)
{
	min=a1c+b1c;         //if both 1st best points differ then they can be chosen for resp person
	
}
else
{
	min=Math.min(a1c+b2c, b1c+a2c);   //if not then we take min (1st best of a + 2nd best of b , 1st best of b + 2nd best of a)
}

min=Math.min(min,a1c);         //also there may be case that b doesnt pick any pt. All are done by person A
min=Math.min(min,b1c);          // all are done by person B
//System.out.println(Double.MAX_VALUE);
System.out.println(sum+min);
}}
public static double dist(long a1,long a2,long b1,long b2)
{
	
	return Math.sqrt((a1-b1)*(a1-b1)+(a2-b2)*(a2-b2));
	
	
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
