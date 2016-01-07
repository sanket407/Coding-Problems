//CODECHEF
// #geom #medium 
//  https://www.codechef.com/ACM15AMR/problems/AMCAS
//Here ans=(sum of areas covered in all possible squares)/(no. of possible squares)
//        =[for all squares(no. of quadrants covered in a square * area of each quadrant)]/(no. of possible squares)
//        =[for all quadrants(no. of squares satisfied by that quadrant * area of each quadrant)]/(no. of possible squares)

// TO find no. of squares covered by  quadrants of a circle:
    // Let x be centre of corr. circle and a be length of req sqr
    // we find limits within which the req, squares can exist
    //left limit = max(0,x-a) right limit = min(n,x+a) uper limit = min(m,x+a) lower limit = max(0,x-a)
    //no. of squares convered by s.e. quad = left sqr * down sqr = (x-left limit) * (x-lower limit)
   // similarly  n_w_quad=(x-left limit)*(upper limit-x);
	 //       n_e_quad_sqr=(right limit-x)*(upper limit-x);
	//       s_e_quad_sqr=(right limit-x)*(x-lower limit);

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub

 InputStreamReader isr=new InputStreamReader(System.in);
 BufferedReader br=new BufferedReader(isr);
 
 StringTokenizer st=new StringTokenizer(br.readLine());
 
 int n=Integer.parseInt(st.nextToken());
 int m=Integer.parseInt(st.nextToken());
 int a=Integer.parseInt(st.nextToken());
 int c=Integer.parseInt(st.nextToken());

 double sum=0,quad=0.7853981634;
 
 for(int i=0;i<c;i++)
 {  int sqr=0;
	 st=new StringTokenizer(br.readLine());
	 int x=Integer.parseInt(st.nextToken());
	
	 if(x==0 || (x==n && x==m))                           //since the quadrant is at extreme edge only 1 square can satisfy it
		 {sum+=quad;System.out.println(x+" "+1);}
	 else
	 {  double left,right,up,down;
	        left=Math.max(0,x-a);
	        right=Math.min(n,x+a);
	        up=Math.min(m, x+a);
	       down=Math.max(0,x-a);
	
	       
	     double  s_w_quad_sqr=(x-left)*(x-down);
	     double  n_w_quad_sqr=(x-left)*(up-x);
	     double   n_e_quad_sqr=(right-x)*(up-x);
	     double  s_e_quad_sqr=(right-x)*(x-down);
	
	 
	     
		 sum+=(s_w_quad_sqr+s_e_quad_sqr+n_e_quad_sqr+n_w_quad_sqr)*quad;
	 }
     
 }
 double sqr=(n-a+1)*(m-a+1);  // all possible sqr count

 System.out.println(sum/sqr);
		
	}

}
