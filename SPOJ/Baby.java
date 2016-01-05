
//SPOJ
// www.spoj.com/problems/BABY
// BITMASKING HARD
// HERE IF n NO. OF BITS OF MASK SET then QUEENS 1....N are set in positions denoted by set bits of mask
// Mask denotes both the index of queen set and position of that queen in solution
// eg 1000 denotes queen '1' is set at position '4' of soln 
// 1010 denotes queens 1 and 2 are set at positions 2 and 4 of soln (we dont care about wich is positioned where)
// So cost(q-1)=min [(dist(b-1 to s-1)+cost(q-2,q-3,q-4),dist(b-1 to s-2)+cost(q-2,q-3,q-4),
//                    dist(b-1 to s-3)+cost(q-2,q-3,q-4),dist(b-1 to s-4)+cost(q-2,q-3,q-4))]

//emulate and solve manually to understand


import java.io.*;
import java.util.*;

public class Main{

public static int s_r[],b_r[],dp[],n;
public static void main(String[] args) throws IOException{

    InputStreamReader isr=new InputStreamReader(System.in);
    BufferedReader br=new BufferedReader(isr);
    Scanner sc=new Scanner(System.in);

    while ((n=sc.nextInt())!=0)
    {                                          //b = baby's queens 
                                               //s = soln's queens
         b_r=new int[n];


        for(int i=0;i<n;i++)
        {b_r[i]=sc.nextInt()-1;
       }

         s_r=new int[n];

        for(int i=0;i<n;i++)
        {s_r[i]=sc.nextInt()-1;
       }

         dp=new int[(1<<n)-1];
         Arrays.fill(dp,-1);


          int ans=go(0,0);
          System.out.println(ans);
		  }}

public static int go(int mask,int c)
{
    if(mask==(1<<n)-1)
        return 0;
    if(dp[mask]!=-1)
        return dp[mask];
    
   
    int res=1000000;
    
    for(int i=0;i<n;i++)
        if((mask & (1<<i))==0)
     res=Math.min(res,Math.abs(b_r[c]-s_r[i])+Math.abs(c-i)+go(mask|(1<<i),c+1));
    
     dp[mask]=res;
    return dp[mask];
    
}
}