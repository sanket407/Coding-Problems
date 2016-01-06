//SPOJ
// http://www.spoj.com/problems/PHONELST/
// #TRIES #EASY


import java.io.*;
import java.util.*;
 
public  class Main{
    
  private static Node root;            
       
    public static void main(String[] args) throws IOException{
        
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
      
        int t=Integer.parseInt(br.readLine());
        while(t-->0)
        {
            int n=Integer.parseInt(br.readLine());
            root=new Node();
            root.value=0;
            boolean flag=false;
            while(n-->0)
            {
                String s=br.readLine();
                
                if(flag==false)                //even though we got ans midway , we read remaining redundant inputs to avoid RUNTIME ERROR
                if(root.findNode(s))
                {flag=true;}
                
                
            }
            if(flag==true)
                System.out.println("NO");
            else System.out.println("YES");
            
        }
        
        
    }}

class Node{
    
   int value;
    Node children[];
    
    public Node()
    {
        value=0;
        children=new Node[10];
        
    }
 
    
    public boolean findNode(String no)
    {
        int key=no.charAt(0)-'0';
        
        if(no.length()==1)                        //if current no to be inserted has only 1 digit left
        { if(this.children[key]==null)
              {this.children[key]=new Node();this.children[key].value=1;return false;}     //create that last digit node
           else
             return true;}                      //since last digit is already present ie this no is already a prefix of some other
       
        else                                     //if current no is larger than 1
        {if(this.children[key]==null)
            this.children[key]=new Node();          
        
        
            if(this.children[key].value==1)         //if next digit is already last digit of previously inserted number
            return true;
            
        
        
            return this.children[key].findNode(no.substring(1));            
         
        }
       
         
         
    }
}