// http://www.spoj.com/problems/HELPBOB/
//SPOJ HARD NP-COMPLETE
//BITMASKING 
//REDUCIBLE TO TSP

import java.io.*;
import java.util.*;
 
public class Main{
    
  public static int n,c_n[],c_i[][],c_a[][];     public static double p[],a[], res=2000000,dp[];
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        
        while ((n=Integer.parseInt(br.readLine()))!=0)
        {         res=20000000;
             p=new double[n];
             a=new double[n];
             c_n=new int[n];
             c_i=new int [n][n];
             c_a=new int [n][n];
             for(int i=0;i<n;i++)
               {StringTokenizer st=new StringTokenizer(br.readLine());
               
                   p[i]=Integer.parseInt(st.nextToken());
                   a[i]=Integer.parseInt(st.nextToken());
                   int k=Integer.parseInt(st.nextToken());
                   for(int j=0;j<k;j++)
                   {
                       int a=Integer.parseInt(st.nextToken())-1;
                       int b=Integer.parseInt(st.nextToken());
                       c_i[a][i]=1;
                       c_a[a][i]+=b;
                           
                       
                   }
               }
             if(n==1)
             {    res=((double)(p[0]/a[0]));
           
             }
             else{
           //  for(int i=0;i<n;i++)
          //   {for(int j=0;j<n;j++)
           //  System.out.print(c_a[i][j]+" ");
           //  System.out.println("");}
             dp=new double[(1<<n)];
             Arrays.fill(dp,-1);
             double area[]=new double[(1<<n)];
             for(int mask=1;mask<(1<<n);mask++)
             { int i;
                 for( i=0;i<n;i++)
                     if((mask & (1<<i))!=0)
                     break;
                 
                 area[mask]=area[mask ^ (1<<i)]+a[i];
                 
                 
                 
                 if((mask & (mask-1))==0)
                 {
                     double cost=p[i];
                     
                     res=Math.min(res,cost/a[i]);
                     
                     dp[mask]=cost;
                     
                 }
                 else
                 {
                     i=0;double cost=20000000;
                     while((1<<i)<= mask)
                     {
                        if(((1<<i) & mask)!=0)
                        {int j=0;double price=p[i];
                         while((1<<j)<=(mask^(1<<i)))
                         {
                             if((i!=j) && (((1<<j) & (mask^(1<<i))) !=0))
                             { 
                                 
                                  price*=(1-(double)c_a[i][j]/100);
                             }
                             j++;
                             
                         }
                        
                           cost=Math.min(cost,dp[mask^(1<<i)]+price);
                        }
                        i++;
                     }
                     
                     dp[mask]=cost;
                     res=Math.min(res,cost/area[mask]);
                 }
                 
             }
             
             }
             res=res*100000;
            // System.out.println("1-"+res);
             if((int)res%10>=5)
                 res+=10;
           //  System.out.println("2-"+res);
             res=(int)(res/10);
            // System.out.println("3-"+res);
             res=res/10000;
             String money = String.format("%.4f", res);
             System.out.println(money);
             
                  
 
    }}

   

}