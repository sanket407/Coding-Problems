
//EASY
//LOGIC

import java.io.*;
import java.util.*;
 
public class Main{
    
  
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        
        int t=Integer.parseInt(br.readLine());
        while (t-->0)
        {   
            
            
            int n=Integer.parseInt(br.readLine());
            int aa=0,ab=0,ba=0,bb=0;
           for(int i=0;i<n;i++)
           {
               String s=br.readLine();
               if(s.equals("aa"))
                   aa++;
               else if(s.equals("ab"))
                   ab++;
               else if(s.equals("ba"))
                   ba++;
               else bb++;
               
           }
           int a,b,c,d;
           String l="",r="";
           a=aa/2;
           for(int i=1;i<=a;i++)
           {   l=l+"aa";
             r=r+"aa";
           }
           
           b=Math.min(ab,ba);
           for(int i=1;i<=b;i++)
           {
               l=l+"ab";
               r="ba"+r;
           }
           
           c=bb/2;
           for(int i=1;i<=c;i++)
           {
               l=l+"bb";
               r="bb"+r;
           }
           
           if(aa%2==1)
               l=l+"aa";
           else if(bb%2==1)
               r="bb"+r;
           
            System.out.println(l+r);
               
               
               
               
        }
           
}}