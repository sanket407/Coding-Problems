//CODECHEF https://www.codechef.com/AMR15MOS/problems/AMCAM
//MEDIUM
//BIPARTITE MATCHING + BINARY SEARCH
//FIRST CALCULATE ALL POSSIBLE n*n distances and store them in sorted array DIST
//Now we choose each value of DIST as our ANS and find if it is possible to get k animals  if that value is ANSs
//Instead of linear searching we binary search on the array . 
//Suppose we start at mid = (low+high) /2
//If our chosen dist gives no. of animals less than k that means the limit should be decreased. so high=mid-1
//If our chosen dist gives no, of animals greater than k that means the limit should be increased. so low=mid+1

//To find no. of animals who satisfy  a particular limit :
//Consider cats and mice as a bipartite graph with 2 sets
//We draw an edge between a cat and mouse only if dist. betwn them is GREATER than limit
//Since  the animals at edge end pts  dont satisfy limit we remove any one of them
//Thus after creating the graph with edges , the max no of animals which can be removed = Max Independent Set of the graph
//MIS= no of vertices - Min Vertex Cover =2*n -Min Vertex Cover
//But in bipartite graph min vertex cover == Maximum bipartite matching (by konigs theorem)
//Find max bipartite matching using augmented path algo ref: http://www.slideshare.net/KuoE0/acmicpc-bipartite-matching





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
       
       while(low<=high)
       {mid=(high+low)/2;
          
       double limit =d[mid];
          
           adj=new int[2*n][2*n];
          
          
          for(int i=0;i<n;i++)
              for(int j=0;j<n;j++)
              {
                  if(dist[i][j]>limit)
                  {adj[i][n+j]=1;adj[n+j][i]=1;}
                  
              }
          
          
          matched=new int[2*n];
          maxmatch=new int [n][n];
          for(int i=0;i<n;i++)
          {int visited[]=new int[2*n];
              boolean a=find_aug_path(i,visited);
          }           
            c=0;
          for(int i=0;i<n;i++)
              if(matched[i]==1)
                  c++;
        
          if((2*n-c)<k)
          {
             low=mid+1;
          }
          else if((2*n-c)>k)
          {
              high=mid-1;
              
          }
          else break;
           
           
       }
        System.out.println(d[mid]);
        
        
    }


public static boolean find_aug_path(int curr,int visited[])
{
    for(int i=0;i<n;i++)
    {
        if(adj[curr][n+i]==1 && visited[n+i]==0)
        {  visited[n+i]=1;visited[curr]=1;
            int next=n+i;
            
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
      

 
     
     
     
