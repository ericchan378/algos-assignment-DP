
/**
 * Rod cutting problem described in Chapter 15 of textbook
 Author: Eric Chan
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
	  //array of max revenue for a certain length rod for lookup
	  int[] maxRev = new int[rodLength + 1];
	  for(int i = 0; i < maxRev.length; i++)
	  {
		  //initialize to -1 indicating not yet stored
		  maxRev[i] = -1;
	  }
	  return rodCuttingAux(rodLength, lengthPrices, maxRev);
  }
  
  private int rodCuttingAux(int rodLength, int[] lengthPrices, int[] maxRev) {
	  //greater than or equal to 0 would mean a maximum revenue was stored
	  if (maxRev[rodLength] >= 0)
	  {
		  return maxRev[rodLength];
	  }
	  //for comparison not expecting anything else negative
	  int max = -1;
	  //no rod, no money
	  if(rodLength == 0)
	  {
		  max = 0;
	  }
	  //recursive step to find max of that length
	  else
	  {
		for(int i = 0; i < rodLength; i++)
		{
			max = Math.max(max, lengthPrices[i] + rodCuttingAux(rodLength - i - 1, lengthPrices, maxRev));
		}
	  }
	  //memo
	  maxRev[rodLength] = max;
	  return max;
  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
	  //array of best/max values for some length rod
	  int maxTable[] = new int[rodLength + 1];
	  //rod length 0
	  maxTable[0] = 0;
	  //fill rest of array
	  for (int i = 1; i <= rodLength; i++)
	  {
		  int max = -1;
		  for (int j = 1; j <= i; j++)
			  max = Math.max(max, lengthPrices[j - 1] + maxTable[i-j]);
		  maxTable[i] = max;
	  }
	  
    return maxTable[rodLength];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
