/**
 * Glass Falling
 Author: Eric Chan
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {

	  
    // If 0 floors, no testing required and if 1 floor, only 1 test required.
   if (floors == 0 || floors == 1)
    {
      return floors;
    }

    //Worst case with only 1 sheet is to go up each floor one by one.
    if (sheets == 1)
    {
      return floors;
    }
    //initialize to some high number for comparison, never going to be more than number of floors. 
    int min = floors + 1;
    int temp;

    for (int i = 1; i <= floors; i++)
    {
      //max for worst case
      temp = Math.max(glassFallingRecur(i - 1, sheets - 1), glassFallingRecur(floors - i, sheets));
      //minimum worst case
      min = Math.min(temp, min);
    }
    //plus 1 for initial drop
    return min + 1;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets) {
	  
	  int[][] table = new int[floors][sheets + 1];
	  for(int i = 0; i <= sheets; i++)
	  {
		  for(int j = 0; j < floors; j++)
		  {
			  table[j][i] = floors + 1;
		  }
	  }
	  return memoAux(floors, sheets, table);
  }
  private int memoAux(int floors, int sheets, int[][] table) {
	  //worst case for 1 sheet is to go up every floor
	  if (sheets == 1)
	  {
		  return floors;
	  }
	  //0 floors 1 test, 1 floor 1 test, trivial
	  if(floors == 1 || floors == 0)
	  {
		  return floors;
	  }
	  //for comparison, not going to exceed number of floors
	  int min = floors + 1;
	  
	  for(int i = 1; i < floors; i++)
	  {
		  int temp1 = table[i - 1][sheets - 1];
		  int temp2 = table[floors - i][sheets];
		  //if value at table unchanged
		  if(temp1 > floors)
		  {
			  temp1 = memoAux(floors - 1, sheets - 1, table);
		  }
		  if(temp2 > floors)
		  {
			  temp2 = memoAux(floors - i, sheets, table);
		  }
		  
		  min = Math.min(min, Math.max(temp1, temp2));  
	  }
	  //update table
	  if(floors < table.length)
	  {
		  table[floors][sheets] = min + 1;
	  }
		
	  return min + 1;
  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
	  	//lookup table [floors][sheets]
		int[][] lookup = new int[floors + 1][sheets + 1];
		
		// If 0 floors, no testing required and if 1 floor, only 1 test required.
		//Fill 2d array for 0 and 1 floors no matter how many sheets
		for (int i = 1; i <= sheets; i++) 
		{
			//[0 or 1 floors][sheets = i]
			lookup[0][i] = 0;
			lookup[1][i] = 1;
		}
		
		//Worst case with only 1 sheet is to go up each floor one by one.
		//Fill 2d array for 1 sheet no matter how many floors
		for(int j = 1; j <= floors; j++)
		{
			//[floor = j][1 sheet]
			lookup[j][1] = j;
		}
		
		//fill rest of table
		for (int i = 2; i <= sheets; i++)
		{
			for (int j = 2; j <= floors; j++)
			{
				//j floors//i sheets
				//initialize to some high number for comparison, never going to be more than number of floors.
				lookup[j][i] = floors + 1;
				int temp;
				//iterate 1 through j floors 
				for (int k = 1; k <= j; k++)
				{
					//plus 1 for the initial drop
					temp = Math.max(lookup[k-1][i-1], lookup[j-k][i]) + 1;
					lookup[j][i] = Math.min(temp, lookup[j][i]);
				}
			}
		}

		return lookup[floors][sheets];
  }
  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}
