//uVa
//bipartite matching
//set of points for owner,s path is one set 
//set of points for dogs interesting places in another set
//edge between point i on bob's path and j interesting place if and only if dog can
//go to j from i and come back to i+1 succesfully ie  dist(i,j)+dist(i+1,j) <= 2*dist(i,i+1)
//max matching will give no of interesting places (augmented path algo) 
//track those using matched[]
//https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=611
 

import java.io.*;
import java.util.*;
 
 class Main{
    
  public static int adj[][],matched[],n,total,m,nx[],ny[],mx[],my[],path[][];
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        
        int t=Integer.parseInt(br.readLine());
       while(t-->0)
       {
        br.readLine();
        StringTokenizer st=new StringTokenizer(br.readLine());
        
          n=Integer.parseInt(st.nextToken());
          m=Integer.parseInt(st.nextToken());
        
          nx=new int[n];
          ny=new int[n];
          mx=new int[m];
          my=new int[m];
         total=n+m;
          matched=new int[n+m];
          path=new int[n][n+m];
           adj=new int[220][220];
           Arrays.fill(matched,-1);
         st=new StringTokenizer(br.readLine());
         for(int i=0;i<n;i++)
         {
             nx[i]=Integer.parseInt(st.nextToken());
             ny[i]=Integer.parseInt(st.nextToken());
             
         }
    st=new StringTokenizer(br.readLine());
         for(int i=0;i<m;i++)
         {
             mx[i]=Integer.parseInt(st.nextToken());
             my[i]=Integer.parseInt(st.nextToken());
             
         }
         
           
          for(int i=0;i<n;i++)
           for(int j=0;j<m;j++)
               if(poss(i,j))
               {//System.out.println(i+" "+j);
                   adj[i][n+j]=1;adj[n+j][i]=1;}    
          
          int visited[];int c=0;
          for(int i=0;i<n;i++)
          { visited=new int[total];
             boolean a= find_aug_path(i,visited);
                 
          }           
           
           for(int i:matched)
               if(i!=-1) c++;
           System.out.println(c+n);
           for(int i=0;i<n-1;i++)
           {
               System.out.print(nx[i]+" "+ny[i]+" ");
              for(int j=n;j<n+m;j++)
                  if(matched[j]==i)
                  {System.out.print(mx[j-n]+" "+my[j-n]+" ");
                break;}
               
               
           }
           System.out.print(nx[n-1]+" "+ny[n-1]+" ");
           System.out.println("");
           if(t>0)
               System.out.println("");
       
       }
        
    }
public static boolean poss(int i,int j)
{
    if(i+1>=n)
        return false;
    double a=Math.sqrt((nx[i]-nx[i+1])*(nx[i]-nx[i+1])+(ny[i]-ny[i+1])*(ny[i]-ny[i+1]));
    
    double b=Math.sqrt((nx[i]-mx[j])*(nx[i]-mx[j])+(ny[i]-my[j])*(ny[i]-my[j]))+Math.sqrt((nx[i+1]-mx[j])*(nx[i+1]-mx[j])+(ny[i+1]-my[j])*(ny[i+1]-my[j]));
    
    if(b<=2.0*a)
        return true;
    else return false;
    
    
}

public static boolean find_aug_path(int curr,int visited[])
{
   if(visited[curr]==1)
       return false;
    visited[curr]=1;
   for(int i=n;i<n+m;i++)
   {   if(adj[curr][i]==1)
       if(matched[i]==-1 || find_aug_path(matched[i],visited))
               {
                   matched[i]=curr;
                   return true;
                   
               }
       
       
   }
    return false;
    
    
    
}





}
      

 
     
     
     
