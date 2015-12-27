import java.io.*;
import java.util.*;
 
public class Main{
    
  public static int adj[][],matched[],n,maxmatch[][];
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        
        
        StringTokenizer st=new StringTokenizer(br.readLine());
        
         n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
      
        int cx[]=new int[n];
        int cy[]=new int[n];
        int mx[]=new int[n];
        int my[]=new int[n];
        
        for(int i=0;i<n;i++)
        {
            st=new StringTokenizer(br.readLine());
            cx[i]=Integer.parseInt(st.nextToken());
            cy[i]=Integer.parseInt(st.nextToken());
        }
        
        for(int i=0;i<n;i++)
        {
            st=new StringTokenizer(br.readLine());
            mx[i]=Integer.parseInt(st.nextToken());
            my[i]=Integer.parseInt(st.nextToken());
        }
        
        double d[]=new double[n*n];
        double dist[][]=new double[n][n];
        
        int c=0;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
            {
                dist[i][j]=Math.sqrt((cx[i]-mx[j])*(cx[i]-mx[j])+(cy[i]-my[j])*(cy[i]-my[j]));
                d[c++]=dist[i][j];
            }
        
       Arrays.sort(d);
       
       int low=0,high=n*n-1,mid=(high+low)/2;
       
       while(true)
       {
          double limit =d[mid];
          
           adj=new int[n][n];
           
          
          for(int i=0;i<n;i++)
              for(int j=0;j<n;j++)
              {
                  if(dist[i][j]>limit)
                      adj[i][j]=1;
                  
              }
           
          matched=new int[n];
          maxmatch=new int [n][n];
          for(int i=0;i<n;i++)
          {int visited[]=new int[n];
              boolean a=find_aug_path(i,visited);
          }           
           
           
           
           
       }
       
        
        
    }


public static boolean find_aug_path(int curr,int visited[])
{
    for(int i=0;i<n;i++)
    {
        if(adj[curr][i]==1 && visited[i]!=0)
        {  visited[i]=1;
            int next=i;
            
            if(matched[next]==0 || find_aug_path(next,visited))
            {   
                matched[curr]=1;
                matched[next]=1;
                return true;
                
                
            }
        }
    }
    return false;
    
    
    
}





}
      

 
     
     
     
