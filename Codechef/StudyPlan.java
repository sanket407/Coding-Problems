// https://www.codechef.com/problems/SUBSEG2a
//Activity selection problem using greedy
// But here multiplequery ranges are given
// so normal greedy will take O(n) for each query
// Hence we use jump pointers to get values n O(logn)

import java.io.*;
import java.util.*;
 
 
public class Main {
 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream input = System.in;
		InputReader in = new InputReader(input);
		
	int n = in.nextInt();
	int m = in.nextInt();
	
	Course c[] = new Course[n];
	
	for(int i=0;i<n;i++)
	{
		
		int x = in.nextInt();
		int y = in.nextInt();
		
		c[i] = new Course(x,y);
	}
		Arrays.sort(c,new Comparator<Course>(){
			
			public int compare(Course c1,Course c2){
				
				
				if(c1.etime==c2.etime)
					return c2.stime-c1.stime;
				else return c1.etime-c2.etime;
				
				
			}
			
			
			
		});
		
		
		ArrayList<Course> relevant_Courses = new ArrayList<Course>();
		
		relevant_Courses.add(c[0]);
		int S=c[0].stime,E=c[0].etime;
		for(int i=1;i<n;i++)
		{
			if(S<c[i].stime && E<c[i].etime)
			{
				relevant_Courses.add(c[i]);
				S=c[i].stime;E=c[i].etime;
				
			}
		}
		
		
		 n =relevant_Courses.size();
		int next[] = new int[n];
		int jump[][] = new int[n+1][19];
	
		//for(int i=0;i<n;i++)
			//System.out.println(relevant_Courses.get(i).stime+" "+relevant_Courses.get(i).etime);
	
		
		//System.out.println(n);
		for(int i=0;i<n;i++)
		{
			
				int s = binSearch(relevant_Courses, relevant_Courses.get(i).etime+1);
				
			
			next[i]=s;jump[i][0]=s;
			
		}
		//System.out.println(Arrays.toString(next));
		int log = (int)(Math.log(n)/Math.log(2));
		for(int i=0;i<=n;i++)
			for(int j=1;j<18;j++)
				jump[i][j]=n;
		jump[n][0]=n;
		for(int i=1;i<=log;i++)
		{
			for(int j=n-1;j>=0;j--)
				{jump[j][i]=jump[jump[j][i-1]][i-1];
				
			//System.out.println(j+" "+i+" "+jump[j][i]);
				}
			
		}
		
		relevant_Courses.add(new Course(Integer.MAX_VALUE,Integer.MAX_VALUE));
		for(int i=0;i<m;i++)
		{
			int qs=in.nextInt();
			int qe = in.nextInt();
			//System.out.println(qs+" q "+qe);
			int start = binSearch(relevant_Courses, qs);
			//System.out.println(start);
			if(relevant_Courses.get(start).etime>qe)
				System.out.println("0");
			else{
				
				int ans=1;
				//System.out.println(log+"log");
				for(int j=log;j>=0;j--)
				{
					if(relevant_Courses.get(jump[start][j]).etime<=qe)
					{//System.out.println(j+"j");
						ans+=1<<j;
						start = jump[start][j];
					}
					
					
					
					
				}
				System.out.println(ans);
				
				
			}
			
			
		}
		
 
		
	}
	
	public static int binSearch(ArrayList<Course> courses,int key)
	{//System.out.println(key+" k");
		int low = 0;
		int high = courses.size()-1;
		
		int mid=(low+high)/2;
		int prev=0;//System.out.println(low+" "+high);
		while(low<=high)
		{ mid=(low+high)/2;
		
			if(courses.get(mid).stime==key)
				return mid;
			else if(courses.get(mid).stime>key)
				{high=mid-1;prev=-1;}
			else {low=mid+1;prev=1;}
		//	System.out.println(low+" "+high);
	}
		
		return low;
		
		
	}
	
 
	static class Course{
		
		int stime, etime;
		
		Course(int stime,int etime)
		{
			this.stime=stime;
			this.etime = etime;
		}
		
		
	}
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while  (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
} 