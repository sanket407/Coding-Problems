
// http://codeforces.com/problemset/problem/486/C

import java.io.*;
 
import java.util.*;
 
public class Main {
	
	public static String n1, n2;public static long min;
	public static int mod = 1000000007;
	
	public static void main(String[] args)throws IOException {
		
OutputWriter out=new OutputWriter(System.out);
  Reader r=new Reader();
 BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
  
 
StringTokenizer st =new StringTokenizer(br.readLine());
		int n =Integer.parseInt(st.nextToken());
		int p =Integer.parseInt(st.nextToken())-1;      //since array is 0-based and p is 1-based
    if(p>=n/2) p = n-p-1;      //we calculate left/right moves only in one half of string ie left half
     
char s[]= br.readLine().toCharArray();
int c=0;int leftmost =n;int rightmost=-1;
char greater,smaller;
for(int i=0;i<n/2;i++)     // count cost req for updating each diff character
{
    if(s[i]> s[n-i-1])
    {
    	greater=s[i];smaller=s[n-i-1];
    	
    }
    else if(s[n-i-1] > s[i])
    {
    	greater=s[n-i-1];smaller=s[i];
    	
    }
    else continue;
    if(leftmost > i)                  //store leftmost index in first half of string which has non palindromic character
		leftmost=i;
    if(rightmost < i)                   //store rightmost index in first half of string which has non palindromic character
		rightmost = i; 

  c+=Math.min(Math.abs(s[i]-s[n-i-1]),('z'-greater)+(smaller-'a')+1);	
	

}

if(leftmost==n)
	System.out.println("0");           //leftmost index unchanged ie string already palindrome
else
{

if(p<=leftmost)
	c+=(rightmost-p);                          //we cover all positions between left and right index
else if(p>=rightmost)               
{
	c+=(p-leftmost);
}
else
{						//if p is between left and right we first move to nearest end and then go reverse
	c+=(Math.min(rightmost-p,p-leftmost)+rightmost-leftmost);
	
	
	
}
System.out.println(c);
}}


 
 
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
