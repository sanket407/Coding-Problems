//CODECHEF
// DP 
//www.codechef.com/PROBLEMS/AMJMP

import java.io.*;
import java.util.*;
 
public class Main{
    
  
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        
        int n=Integer.parseInt(br.readLine());
        int h[]=new int[n];
      
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++)
        {
            h[i]=Integer.parseInt(st.nextToken());
        }
          
        
      int max=-1;
        int dp[][]=new int[n][n];    //dp[i][j]=no of jumps possible from i to anywhere between [i,j)
        
        for(int i=1;i<n;i++)
            for(int j=0;j<n;j++)
            { 
                if(j-i>=0 && h[j-i]<h[j])
                {dp[j][j-i]=1+dp[j-i][j-1];   }   //left side 
                
                  
                
                if(j+i<n && h[j+i]<h[j])             //right side
                {  dp[j][j+i]=1+dp[j+i][j+1];
                
              
                }
                
                
            }
        
        
        for(int i=0;i<n;i++)
        { max=-1;
            for(int j=0;j<n;j++)
             max=Math.max(max,dp[i][j]);
            System.out.print(max+" ");}
        
        System.out.println("");
}}


 
     
     
     
