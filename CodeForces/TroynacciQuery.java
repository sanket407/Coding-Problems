// PARTIAL SUM TO EXECUTE QUERIES
// SINCE SUBTRACTIONS AND MODULUS IS USED IN SAME OPERATION 
//WE long(1) to maintain corectness of subtractions
// http://codeforces.com/gym/100571/problem/B

import java.io.*; 
import java.util.*;

public class Main {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub

 InputStreamReader isr=new InputStreamReader(System.in);
 BufferedReader br=new BufferedReader(isr);
 
 StringTokenizer st=new StringTokenizer(br.readLine());

 
int n=Integer.parseInt(st.nextToken());
int q=Integer.parseInt(st.nextToken());

st=new StringTokenizer(br.readLine());
long f1=Long.parseLong(st.nextToken());
long f2=Long.parseLong(st.nextToken());


st=new StringTokenizer(br.readLine());
long a=Long.parseLong(st.nextToken());
long b=Long.parseLong(st.nextToken());

long mod=1000000007;

long f[]=new long[n+1];

f[1]=f1;
f[2]=f2;

for(int i=3;i<=n;i++)
	f[i]=(a*f[i-2]+b*f[i-1])%mod;
//System.out.println(Arrays.toString(f));

long parsum[]=new long[n+1];

long s[]=new long[n+1];

st=new StringTokenizer(br.readLine());

for(int i=1;i<=n;i++)
	s[i]=Long.parseLong(st.nextToken());

for(int i=1;i<=q;i++)
{

st=new StringTokenizer(br.readLine());
int l=Integer.parseInt(st.nextToken());
int r=Integer.parseInt(st.nextToken());

if(l<r)
{
parsum[l]=(parsum[l]+f[1])%mod;
parsum[l+1]=(parsum[l+1]+f[2])%mod;
parsum[l+1]=((long)(1)*parsum[l+1]+mod-(long)(1)*(((long)(1)*f[1]*b)%mod))%mod;


if(r<n)
	parsum[r+1]=((long)(1)*parsum[r+1]+mod-(long)(1)*(((long)(1)*f[r-l+2])%mod))%mod;

if(r<n-1)
	parsum[r+2]=((long)(1)*parsum[r+2]+mod-(long)(1)*(((long)(1)*a*f[r-l+1])%mod))%mod;
//System.out.println(Arrays.toString(parsum));
}
else
{
	parsum[l]=(parsum[l]+f[1])%mod;
	
	if(r<n) parsum[r+1]=((long)(1)*parsum[r+1]+mod-(long)(1)*(((long)(1)*b*f[1])%mod))%mod;
	if(r<n-1) parsum[r+2]=((long)(1)*parsum[r+2]+mod-(long)(1)*(((long)(1)*a*f[1])%mod))%mod;
	
}
}

for(int i=2;i<=n;i++)
	parsum[i]=(parsum[i]+a*parsum[i-2]+b*parsum[i-1])%mod;

//System.out.println(Arrays.toString(parsum));

for(int i=1;i<n;i++)
	System.out.print(((s[i]+parsum[i])%mod)+" ");
    System.out.println((s[n]+parsum[n])%mod);


}}


