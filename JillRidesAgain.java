import java.io.*;
import java.util.*;

public class JillRidesAgain {

	static boolean[] calc;
	static int[] memo;
	static int[] arr;
	static pair[] way;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner lector = new Scanner(System.in);
		BufferedWriter esc = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = lector.nextInt();

		for (int i = 0; i < n; i++) {
			int nActual = lector.nextInt() - 1;

			calc = new boolean[nActual];
			memo = new int[nActual];
			Arrays.fill(memo, -1000000);
			arr = new int[nActual];
			way = new pair[nActual];
			for (int j = 0; j < nActual; j++) {
				arr[j] = lector.nextInt();
				way[j] = new pair(j + 1, j + 2);
			}

			int resp = -1000001;
			int pos = 0;
			for (int j = 0; j < nActual; j++) {

				int calc = maxSum(j);
				if (calc > resp) {

					pos = j;
					resp = calc;
				} else if (calc == resp) {

					int r1 = way[j].end - way[j].start;
					int r2 = way[pos].end - way[pos].start;
					if (r1 > r2)
						pos = j;
					else if (r1 == r2) {
						if (way[j].start <= way[pos].start)
							pos = j;

					}

				}

			}
			if (resp > 0)
				esc.write("The nicest part of route " + (i + 1) + " is between stops " + way[pos].start + " and "
						+ way[pos].end + "\n");
			else
				esc.write("Route " + (i + 1) + " has no nice parts\n");
		}
		esc.flush();
	}

	static int maxSum(int i) {

		if (i == 0)
			return arr[0];

		if (calc[i])
			return memo[i];

		int max = 0;
		int opc = arr[i] + maxSum(i - 1);
		if (arr[i] > opc) {
			max = arr[i];

		} else {
			max = opc;
			way[i].start = way[i - 1].start;

		}
		memo[i] = max;
		calc[i] = true;
		return max;
	}

}

class pair {

	int start;
	int end;

	public pair(int start, int end) {
		this.start = start;
		this.end = end;

	}

}
