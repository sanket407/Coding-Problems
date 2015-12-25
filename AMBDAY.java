import java.io.*;
import java.util.*;
 $
public class AMBDAY{
    
  public static int n,c_n[],c_i[][],c_a[][];     public static double p[],a[], res=2000000,dp[];
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        
        int t=Integer.parseInt(br.readLine());
        while (t-->0)
        {   
            StringTokenizer st=new StringTokenizer(br.readLine());
            
            int f=Integer.parseInt(st.nextToken());
            int h=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            
            int min=200000;
            int p[]=new int[f+1];
            st=new StringTokenizer(br.readLine());
            
            for(int i=1;i<=f;i++)
            {
              p[i]=Integer.parseInt(st.nextToken());
            }
            Arrays.sort(p);
            for(int i=1;i<=f;i++)
                min=Math.min(min,p[i]-p[i-1]);
            
            min=Math.min(min,w-p[f]);
            System.out.println(min*h);
            
        }
}}