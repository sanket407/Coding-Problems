
//SPOJ
//BITMASKING AND FLOYD WARSHALLS 
//HARD 
// http://www.spoj.com/problems/COURIER/

import java.io.*;
import java.util.*;
 
public class Main{
    
 public static int cost[][],path[][],d_s[],d_d[];
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
 
        
        for(int o=1;o<=t;o++)
 {
     int n=sc.nextInt();
     int r=sc.nextInt();
    int h=sc.nextInt();
     cost=new int[n+1][n+1];
     d_s=new int[12];
     d_d=new int[12];
     for(int i=0;i<=n;i++)
         Arrays.fill(cost[i],20000);
     for(int i=1;i<=r;i++)
     {
         int a=sc.nextInt();
         int b=sc.nextInt();
         int w=sc.nextInt();
         cost[b][a]=cost[a][b]=Math.min(cost[a][b],w);
     }
     
     int z=sc.nextInt();
     int c=0;
     for(int i=0;i<z;i++)
     {
         int s=sc.nextInt();
         int d=sc.nextInt();
         int w=sc.nextInt();
         
         while(w-->0)
         {
             d_s[c]=s;
             d_d[c++]=d;
         }
     }
    // for(int i=0;i<c;i++)
      //   System.out.println(d_s[i]+" "+d_d[i]);
     path=new int[n+1][n+1];
    
     floyd_warshal(n);
     
     int mask=1;
     
     int dp[][]=new int[5000][13];
     
     for(mask=1;mask<(1<<c);mask++)
     { //System.out.println("mask="+mask);
         if((mask & (mask-1))==0)
         {
             int i=0;
             while(((1<<i)&mask)==0) i++;
             dp[mask][i]=path[h][d_s[i]]+path[d_s[i]][d_d[i]];
            // System.out.println("dp["+mask+"]["+i+"]="+dp[mask][i]);
             
             
         }
         else
         {
             int i=0;
             while((1<<i)<=mask)
             {  
                 if(((1<<i)&mask)!=0)
                 {
                     int res=-1;
                     int j=0;
                     while((1<<j)<=(mask^(1<<i)))
                     {
                         if(((1<<j)&mask)!=0 && i!=j)
                         {
                             if(res==-1)
                                 res=dp[mask^(1<<i)][j]+path[d_d[j]][d_s[i]]+path[d_s[i]][d_d[i]];
                             else 
                                 res=Math.min(res,dp[mask^(1<<i)][j]+path[d_d[j]][d_s[i]]+path[d_s[i]][d_d[i]]);
                             
                             
                         }
                         j++;
                     }//System.out.println("dp["+mask+"]["+i+"]="+res);
                      dp[mask][i]=res;
                 }
                 i++;
             }
         }
     }  
     
     int res=dp[mask-1][0]+path[d_d[0]][h];
     for(int i=1;i<c;i++)
         res=Math.min(res,dp[mask-1][i]+path[d_d[i]][h]);
     
     System.out.println(res);
     
 }
    }

public static void floyd_warshal(int n)
{
    for(int i=1;i<=n;i++)
    for(int j=1;j<=n;j++)
        path[i][j]=i==j?0:cost[i][j];
    
    
    
    
    for(int k=1;k<=n;k++)
        for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++)
            {
                path[i][j]=Math.min(path[i][j],path[i][k]+path[k][j]);
            }
                
                
                
            
    
    
}

}