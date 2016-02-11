// https://www.hackerearth.com/problem/algorithm/comrades-iii/
// First of all, construct a tree of the hierarchy given in the question.
//Next, we convert the tree into an array, arr, by performing a DFS traversal of this tree 
//and noting down the DFS number for each node.
 //The DFS number tells us about the order in which the nodes were encountered during DFS
 //The position of a node in array will be equal to its DFS number. For each node, record 
 //the smallest and largest DFS number in its subtree. If the smallest DFS number in a subtree 
 //is a and the largest DFS number is b, it means that the subtree is represented by the subarray [a, b]in arr.
 //For a soldier x, arr [x.DFSnumber] ==1 if the soldier is awake and 0 if he is asleep. As all soldiers are 
//initially awake, set all elements in arr to 1.//The problem is now converted into a Range Update, Range Query
// problem. Now, build a segment tree on arr. 

import java.io.*;
import java.util.*;
 
public class Main {
public static int n,child_index=-1,     //used for calculating the indices of subtree in seg tree array
					visited[],          //used for dfs traversal for calculating seg tree array
					range[],             //stores the sol. nos. in resp pos in seg tree array
					l_range[],          //left end of range of subtree of a soldier in seg tree
					r_range[],          //right end of range of subtree of a soldier in seg tree 
					awake[],            //stores no awake soldiers in range 
                    total[],             //stores no of total soldiers in range
					lazy[];              //lazy propogation -1 if sleep pending / 1 if wake pending / 0 dont care
					
public static ArrayList<Integer> child[]; //used for storing initial rooted superiority tree
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
 
InputStreamReader isr=new InputStreamReader(System.in);
 
 BufferedReader br=new BufferedReader(isr);
 
 
 n=Integer.parseInt(br.readLine());
 visited=new int[n+1];
child=new ArrayList[n+1];
range=new int[n];
awake=new int[4*n];
total=new int[4*n];
lazy=new int[4*n];
l_range=new int[n+1];
r_range=new int[n+1];

for(int i=0;i<=n;i++)
	child[i]=new ArrayList<Integer>();

StringTokenizer st=new StringTokenizer(br.readLine());
for(int i=1;i<=n;i++)
 child[Integer.parseInt(st.nextToken())].add(i);   //add i to junior (child) 

 
calc(child[0].get(0));                             //calculate the seg tree array from root

build(1,0,n-1);                                   //build seg tree using array obtained above

int m=Integer.parseInt(br.readLine());

for(int i=1;i<=m;i++)
{
	st=new StringTokenizer(br.readLine());
	int c=Integer.parseInt(st.nextToken());
	int a=Integer.parseInt(st.nextToken());
	
	if(c==3)
	{	
	 System.out.println(find(1,0,n-1,l_range[a],r_range[a]));  //find no of soldier awake who are juniors of a 
	}                                              //note that l_range ->r_range describes the subtree of a
	
	else
	if(c==1)
		update(1,0,n-1,l_range[a],r_range[a],1);             //wake up
	else if(c==2)
		update(1,0,n-1,l_range[a],r_range[a],-1);        //sleep
	
	
}
	}
	
	public static void upd(int index,int l,int r,int x)
	{ if(x==-1)
		awake[index]=0;                 //sleep
	else awake[index]=total[index];    //awake
		lazy[index]=x;
		
		
	}
	public static void shift(int index,int l,int r)
	{ 
		int mid=(l+r)/2;
		
		upd(index*2,l,mid,lazy[index]);
		upd(index*2+1,mid+1,r,lazy[index]);
		
		lazy[index]=0;
		
		
	}
	
	public static void update(int index,int l,int r,int x,int y,int query)
	{ 
		if(x>y) return;        //only if no child exists
		if(x<=l && y>=r)
		{
			upd(index,l,r,query);  //query = -1 if sleep 1 if wake
			
			return;
			
		}
		if(lazy[index]!=0)          //lazy pending
		shift(index,l,r);
		int mid=(l+r)/2;
		
		if(y<=mid)
		update(index*2,l,mid,x,y,query);
		else if(x>mid)
			update(index*2+1,mid+1,r,x,y,query);
		else
		{update(index*2,l,mid,x,mid,query);
		update(index*2+1,mid+1,r,mid+1,y,query);
		}
		awake[index]=awake[2*index]+awake[2*index+1];
	}
	public static int find(int index,int l,int r,int x,int y)
	{
		if(x>y) return 0;       //no child exists
		if(x<=l && y>=r)
		{
			
			
			return awake[index];
			
		}
		if(lazy[index]!=0)       //lazy pending
		shift(index,l,r);
		int mid=(l+r)/2;
		
		if(y<=mid)
		return find(index*2,l,mid,x,y);
		else if(x>mid)
		return	find(index*2+1,mid+1,r,x,y);
		else
		{return find(index*2,l,mid,x,mid)+ find(index*2+1,mid+1,r,mid+1,y);
		}
		
	}
	public static void build(int index,int l,int r)
	{
		if(l==r)
		{
			total[index]=awake[index]=1;
			return;
			
		}
		
		int mid=(l+r)/2;
		
		build(index*2,l,mid);
		build(index*2+1,mid+1,r);
		
		awake[index]=awake[index*2]+awake[2*index+1];
		total[index]=total[index*2]+total[index*2+1];
		
		
	}
public static void calc(int i)    //iterative dfs since recursive one causes stackoverflow
{ LinkedList<Integer> dfs=new LinkedList<Integer>();
   dfs.add(i);             //add root
   while(!dfs.isEmpty())
   { i=dfs.getFirst();
	   if(visited[i]==1)           //previously visited i.e backtrack now in recursive approach 
	   {r_range[i]=child_index;     //set right range ie last visited child 
	    dfs.removeFirst();          //remove this node
	    continue;
	   }
		   range[++child_index]=i;     //set this node in seg tree array
	   l_range[i]=child_index+1;      //set left range ie first visited child
	   visited[i]=1;
	   for(int k=child[i].size()-1;k>=0;k--)
		   dfs.addFirst(child[i].get(k));       //PREPEND the child list to the dfs list
	   
   }
   

		
	
}
 

}
 
