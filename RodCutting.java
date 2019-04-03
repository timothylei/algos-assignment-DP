/**
 * RodCutting
 * Author: Timothy Lei
 */

public class RodCutting {

	private int rodCuttingRecur(int rodLength, int[] lengthPrices) {
		int[] memo = new int[rodLength + 1];
		return rodCuttingRecurMemo(rodLength, lengthPrices, memo);
	}

	// Do not change the parameters!
	public int rodCuttingRecurMemo(int rodLength, int[] lengthPrices, int[] memo) {

		int max = Integer.MIN_VALUE;

		if (memo[rodLength] != 0)
			return memo[rodLength];
		if (rodLength <= 0)
			return 0;

		else {
			for (int i = 0; i < rodLength; i++) {
				max = Math.max(max, lengthPrices[i] + rodCuttingRecurMemo(rodLength - i -1, lengthPrices, memo));
			}
		}
		memo[rodLength] = max;
		return max;
	}

	// Do not change the parameters!
	public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {

		int[] profit = new int[rodLength + 1];
		profit[0] = 0;
		for (int i = 1; i <= rodLength; i++) {
			int max = -1;
			for (int j = 1; j < i; j++) {
				max = Math.max(max, lengthPrices[j] + profit[i - (j + 1)]);
				profit[i] = max;
			}
		}
		return profit[rodLength];
	}

	public static void main(String args[]) {
		RodCutting rc = new RodCutting();

		// In your turned in copy, do not touch the below lines of code.
		// Make sure below is your only output.
		int length1 = 7;
		int[] prices1 = { 1, 4, 7, 3, 19, 5, 12 };

		int length2 = 14;
		int[] prices2 = { 2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26 };
		int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
		int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
		int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
		int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
		System.out.println(maxSell1Recur + " " + maxSell1Bottom);
		System.out.println(maxSell2Recur + " " + maxSell2Bottom);
	}
}


