
//CODECHEF 
//SOLVED BY MECHANICALLY WORKING EXAMPLES TILL n=4



import java.io.*;
import java.util.*;
 
public class Main{
    
  
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        
        int t=Integer.parseInt(br.readLine());
        
            
            long ans[]=new long[5000+1];
            
            ans[1]=26;
              ans[2]=ans[1]+26+26+26*26;
              
              long mul=26,add=2;
              for(int i=3;i<=5000;i++)
              {  mul=(mul*(26-i+2))%1000000007;
                 add+=2;
                 
                  ans[i]=(ans[i-1]+(26*add)+mul+(26*26))%1000000007;
                  
              }
              while(t-->0)
              {
                  int n=Integer.parseInt(br.readLine());
                  System.out.println(ans[n]);
                  
                  
                  
              }
           
}}