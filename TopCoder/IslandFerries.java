import java.util.StringTokenizer;
import java.util.*;
public class IslandFerries {

	public static int ferrycost[][];
	
	public int[] costs(String[] legs, String[] prices) {
		
		int F = legs.length;
		int I = prices.length;
		int min_dist[] = new int[I];
		Arrays.fill(min_dist,Integer.MAX_VALUE);
		boolean service[][][] = new boolean[F][I][I];
		 ferrycost  = new int[I][F];
		
		for(int i=0;i<I;i++)
		{
			StringTokenizer st2 = new StringTokenizer(prices[i], " ");
			
			for(int ii=0;ii<F;ii++)
				ferrycost[i][ii] = Integer.parseInt(st2.nextToken());
			
			
			
		}
		
		for(int i=0;i<F;i++)
		{
			StringTokenizer st = new StringTokenizer(legs[i]," ");
			
			while(st.hasMoreTokens())
			{
				StringTokenizer st1 = new StringTokenizer(st.nextToken(),"-");
				int s = Integer.parseInt(st1.nextToken());
				int d = Integer.parseInt(st1.nextToken());
				
				service[i][s][d] = true;
				
				
				
			}
			
			
		}
		
		PriorityQueue<State> pq = new PriorityQueue<State>(10,new Comparator<State>(){
			
			public int compare(State s1,State s2)
			{
				
				return s1.cost-s2.cost;
			}
			
		});
		
		pq.add(new State(0,0,0));
		int visited_count=0;
		boolean visited[][] = new boolean[I][1<<F+1];
		while( !pq.isEmpty())
		{
			State currstate = pq.remove();
			System.out.println("out"+currstate.des+" "+currstate.cost+" "+currstate.tickets);
			int curr = currstate.des;
			
			
			
			if(!visited[curr][currstate.tickets])
			{visited_count++;
			visited[curr][currstate.tickets]=true;
			if(min_dist[curr]>currstate.cost)
			{	//System.out.println(curr+" -"+currstate.cost);
				min_dist[curr] = currstate.cost;
				
				
			}
		//	System.out.println(curr+" --"+currstate.cost);
			}
			else
				{
				if(min_dist[curr]>currstate.cost)
				{	System.out.println(curr+" -"+currstate.cost);
					min_dist[curr] = currstate.cost;
					
					
				}else continue;
				
				
				
				}
			int curr_tickets = currstate.tickets;
			for(int i=0;i<I;i++)
				for(int j=0;j<F;j++)
				{
					if(service[j][curr][i])
					{ 
						for(int t=0;t<(1<<F);t++)
						{int cost = getCost(t,curr_tickets,j,i,curr);
							if(cost!=-1)
							{
								pq.add(new State(i,currstate.cost+cost,t^(1<<j)));
								System.out.println("in"+i+" "+(currstate.cost+cost)+" "+t);
								
							}
							
							
							
							
							
						}
										
							
						
						
						
						
					}
				}
			
			
			
			
		}
		
		
		System.out.println(Arrays.toString(min_dist));
		
		int ans[] = new int[I-1];
		
		for(int i=0;i<I-1;i++)
		{
			if(min_dist[i+1]==Integer.MAX_VALUE)
				ans[i]=-1;
			else ans[i]=min_dist[i+1];
			
		}
	//	System.out.println(Arrays.toString(ans));
		
		return ans;
		
		
	}

	public static int getCost(int next_tickets,int curr_tickets,int j,int next_des,int curr_des)
	{// System.out.println(next_tickets+" "+j);
		if((next_tickets & 1<<j)==0)
			return -1;
		int temp = next_tickets;
		int nc=0;
		while(temp>0)
		{
			if((temp&1)!=0)
			nc++;
			if(nc>3)
				return -1;
			temp>>=1;
			
		}
			
		temp = curr_tickets;
		
		for(int i=0;(1<<i)<=temp;i++)
		{
			if(((1<<i) & curr_tickets)!=0)
			   if(((1<<i) & next_tickets)==0)
				   return -1;
			
		}
		int cost = 0;
		for(int i=0;(1<<i)<=next_tickets;i++)
		{
			if(((1<<i) & next_tickets)!=0)
			   if(((1<<i) & curr_tickets)==0)
				   cost+= ferrycost[curr_des][i];
			
		}
		
		
		
		return cost;
		
		
	}
	
	
}

class State{
	
	int des ;
	int cost;
	int tickets;
	State(int des,int cost,int tickets){
		this.des = des;
		this.cost=cost;
		this.tickets=tickets;
	}
	
	
}