//CODECHEF
// https://www.codechef.com/AMR15MOS/problems/AMGIT
//TRIES + DPs
//dp iterates from leaf nodes to abv finding min (node is included,node not included)


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
            while(n-->0)
            {
                String s=br.readLine();
                if(s.charAt(0)=='s')
                {
                    root.addKey(s.substring(7),1);        
                }
                else root.addKey(s.substring(9),-1);
                
            }
            
              root.go();
            System.out.println(root.not_included);        //since original root of trie "/" will not be included in path
            
        }}

    
    


}

class Node{
    
    String name;
    int type;     //1-file 0-path
    int stage;   //0-dont care 1-stage 2-unstage
    int count;   //child count
    int included=-1;                                           //n.included denotes cost of subtree at  node n if n is included ie n is staged 
    int not_included=-1;                                      //n.not_included denotes cost of subtree at node n if n is not included i.e n is not staged
    ArrayList<Node> children;                                //note that we calculate below includ and not_incl only for peth nodes and not file nodes
    
    public Node()
            {
                this.name="/";
                this.type=0;
                this.stage=0;
                children=new ArrayList<Node>();
            }
    
    public Node(String key)
    {
        this.name=key;
        this.type=0;
        this.stage=0;
        children=new ArrayList<Node>();
        
        
    }
    
    public void addKey(String path,int stage)
    {int len=path.indexOf(('/'));                    //get index of first "/"
       
    Node n=null;
     
    if(len==-1) //this is last part of path i.e this is a file
     { n=new Node(path);
         this.children.add(n);
         n.stage=stage;
         n.type=1;
     }
    else                        //if this element is path
     {
    
      String key=path.substring(0,len);         //get the next path element
     
      for( int i=0;i<this.children.size();i++)     //search if it already exists
        { 
            if(this.children.get(i).name.equals(key))
            {
                n=this.children.get(i);
                break;
                
                
            }
            
        }
        
        path=path.substring(len+1);     //rem path after removing this prefix path element
        
      if(n==null)       //path found
            { n=new Node(key);
          this.children.add(n);
      }
      
        
      
          n.addKey(path,stage);            //continue on the path
      
    }}
    
    public void go()
    {this.included=1;  this.not_included=0;    
            for(int i=0;i<this.children.size();i++)                               //Note that if n is staged(included) cost will be 1+no. 
                                                                                 //of unstaged nodes in its subtree
    {                                                                            //if n is unstaged(not included) cost will be no. of staged
                Node n=this.children.get(i);                                     //leafnodes(ie file nodes)+ min(path child included,path  child not included)
                                                                                    
                if(n.stage==1)
                    this.not_included++;
                else if(n.stage==-1)
                    this.included++;
                else
                {    n.go();                                                     //find costs of subtree elements
                 this.included+=n.included;
                 this.not_included+=Math.min(n.included,n.not_included);
                }
                
                
                
            }
        
       // System.out.println(this.name+" "+this.included+" "+this.not_included);
    }
    
}