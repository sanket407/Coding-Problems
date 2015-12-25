
//http://codeforces.com/contest/602/problem/B
//MATH LOGIC

import java.io.*;
import java.util.*;

public class Main
{ static double a;static int fall[];
 public static void main(String args[]) throws IOException
{
  InputStreamReader isr=new InputStreamReader(System.in);
  BufferedReader br=new BufferedReader(isr);

          
     int n=Integer.parseInt(br.readLine());
     StringTokenizer st= new StringTokenizer(br.readLine()); 
     int a[]=new int[n];
     for(int i=0;i<n;i++)
         
     
     
     a[i]=Integer.parseInt(st.nextToken());
       
   int min=a[0];int max=a[0];int currlen=1;int maxlen=-1;
   int r=0,l=0;
  
      int maxi=0,mini=0;
   
       while(r<n-1)
       {       
           
      
        
           if(a[r+1]!=max && a[r+1]!=min)
           {
               if(a[r+1]==min-1)
               {   
                      if(min!=max)
                   l=maxi+1;
                   max=min;
                   min=a[r+1];
                   
                   
                  
                   maxi=mini;
                   mini=r+1;
                   r++;
                    if(maxlen<currlen)
                       maxlen=currlen;
                   currlen=r-l+1; 
                   
               }
               else if(a[r+1]==max+1)
               {   if(min!=max)
                   l=mini+1;
                   min=max;
                  max=a[r+1];
                  
                   mini=maxi;
                  maxi=r+1;
                   r++;
                   if(maxlen<currlen)
                       maxlen=currlen;
                   currlen=r-l+1;; 
               }
                else
               {   if(maxlen<currlen)
                       maxlen=currlen;
                   min=a[r+1];max=a[r+1];
                   l=r=mini=maxi=r+1;
                   currlen=1;
                   
               }
           } 
               else if(a[r+1]==min)
               { mini=r+1;r++;currlen++; }
               else if(a[r+1]==max)
               {
                   maxi=r+1;r++;currlen++;
               }
             
               
           
               
    //  System.out.println(l+" "+r+" "+mini+" "+min+" "+maxi+" "+max+" "+currlen);
           
           
       }
       if(maxlen<r-l+1)
           maxlen=r-l+1;
          
           
       
       
       
       
   
     
     System.out.println(maxlen);
     
    
}}
 
 