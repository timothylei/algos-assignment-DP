/**
 * Glass Falling
 * Author: Timothy Lei
 */
public class GlassFalling {
	
	
	public int glassFallingRecur(int floors, int sheets) {
				
		if (sheets == 1) return floors;

		// when there is only one or zero floor return floor

		if (floors == 0 || floors == 1) return floors;

		int min = Integer.MAX_VALUE;
		int solution;

		for (int i = 1; i <= floors; i++) {
			solution = Math.max(glassFallingRecur(i - 1, sheets - 1),
					glassFallingRecur(floors - i, sheets));
			if (solution < min)
				min = solution;
		}
		
		return min + 1;

	}

	
	
	
	// Optional:
	// Pick whatever parameters you want to, just make sure to return an int.

	// Do not change the parameters!
	public int glassFallingMemoized(int floors, int sheets, int[][] memo) {
		// check whether answer exist in memo
		if (memo[floors][sheets] != 0)
			return memo[floors][sheets];

		// case when there is one sheets, try every floors until break
		if (sheets == 1) {
			memo[floors][sheets] = floors;
			return floors;
		}

		// when there is only one or zero floor return floor

		if (floors == 0 || floors == 1) {
			memo[floors][sheets] = floors;
			return floors;
		}

		int min = Integer.MAX_VALUE;
		int solution;

		for (int i = 1; i <= floors; i++) {
			solution = Math.max(glassFallingMemoized(i - 1, sheets - 1, memo),
					glassFallingMemoized(floors - i, sheets, memo));
			if (solution < min)
				min = solution;
		}
		memo[floors][sheets] = min + 1;
		return min + 1;

	}

	


	// Do not change the parameters!
	public int glassFallingBottomUp(int floors, int sheets) {
		int glassFall[][] = new int[sheets + 1][floors + 1];

		// base case 1: floor 0, 1
		for (int i = 1; i < sheets; i++) {
			glassFall[i][1] = 1;
			glassFall[i][0] = 0;
		}

		// base case 2:
		// if only one egg is there then try every floor

		for (int j = 1; j < floors; j++) {
			glassFall[1][j] = j;
		}

		for (int i = 2; i <= sheets; i++) {
			for (int j = 2; j <= floors; j++) {
				glassFall[i][j] = Integer.MAX_VALUE;

				int temp;
				for (int z = 1; z < j; z++) {
					temp = 1 + Math.max(glassFall[i - 1][z - 1], glassFall[i][j - z]);
					glassFall[i][j] = Math.min(temp, glassFall[i][j]);
				}
			}
		}
		return glassFall[sheets][floors];
	}

	public static void main(String args[]) {
		GlassFalling gf = new GlassFalling();
		int[][] memo = new int[100 + 1][3 + 1];
		
		

		// Do not touch the below lines of code, and make sure
		// in your final turned-in copy, these are the only things printed
		int minTrials1Recur = gf.glassFallingRecur(27, 2);
		int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
		int minTrials2Memo = gf.glassFallingMemoized(100, 3,memo);
		int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
		System.out.println(minTrials1Recur + " " + minTrials1Bottom);
		System.out.println(minTrials2Memo + " " + minTrials2Bottom);
	}
