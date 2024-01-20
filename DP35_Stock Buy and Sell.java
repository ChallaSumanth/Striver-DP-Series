//https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock_893405?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
import java.util.* ;
import java.io.*; 
import java.util.ArrayList;

public class Solution{
    public static int maximumProfit(ArrayList<Integer> prices){
        // Write your code here.
        int len = prices.size();
        int maxP = Integer.MIN_VALUE;
        int buy = prices.get(0);
        for(int i = 1; i < len; i++)
        {
            buy = Math.min(buy, prices.get(i));
            maxP = Math.max(maxP, prices.get(i) - buy);
        }

        return maxP;
    }
}