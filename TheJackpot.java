import java.io.*;
import java.util.*;
public class TheJackpot {
	
	static int[] mem;
	static boolean[] comp;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		
		Scanner lector=new Scanner(System.in);
		BufferedWriter esc=new BufferedWriter(new OutputStreamWriter(System.out));
		while(true){
			
			int n=lector.nextInt();
			if(n==0)
			break;
			arr=new int[n];
			comp=new boolean[n];
			mem=new int[n];
			for (int i = 0; i <n; i++) {
				mem[i]=Integer.MIN_VALUE;
			}
			for (int i = 0; i <n; i++) {
				arr[i]=lector.nextInt();
			}
			int max=Integer.MIN_VALUE;
			
			for (int i = 0; i < n; i++) {
				int act=maxSum(i);
				if(max<act)
					max=act;
			}
			if(max>0)
				esc.write("The maximum winning streak is "+max+".\n");
			else
				esc.write("Losing streak.\n");
		}
		esc.flush();
		}
    
	static int maxSum(int i){
		
		if(i==0){
			return arr[i];
		}
		
		if(comp[i]){
			return mem[i];
		}
		int res=Math.max(arr[i],arr[i]+ maxSum(i-1));
	
		comp[i]=true;
		mem[i]=res;
		
		return res;
	}
}
