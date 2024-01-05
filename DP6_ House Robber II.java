//https://www.codingninjas.com/studio/problems/house-robber-ii_839733?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
	public static long houseRobber(int[] valueInHouse) {
		// Write your code here.	
		int len = valueInHouse.length;
		if(len == 1)
			return valueInHouse[0];
		List<Integer> temp1 = new ArrayList<>();
		List<Integer> temp2 = new ArrayList<>();
		for(int i = 0; i < len; i++)
		{
			if(i != 0)
				temp1.add(valueInHouse[i]);
			if(i != len - 1)
				temp2.add(valueInHouse[i]);
		}
		len = temp1.size();
		return Math.max(maxProfit(len - 1, temp1),maxProfit(len - 1, temp2));	
	}	
	private static int maxProfit(int idx, List<Integer> temp)
	{
		if(idx < 0)
			return 0;
		if(idx == 0)
			return temp.get(idx);
		int pick = temp.get(idx) + maxProfit(idx - 2, temp);
		int notPick = maxProfit(idx - 1, temp);
		return Math.max(pick, notPick);
	}
}
//2.space optimization
//https://leetcode.com/problems/house-robber-ii/description/
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
		if(len == 1)
			return nums[0];
		List<Integer> temp1 = new ArrayList<>();
		List<Integer> temp2 = new ArrayList<>();
		for(int i = 0; i < len; i++)
		{
			if(i != 0)
				temp1.add(nums[i]);
			if(i != len - 1)
				temp2.add(nums[i]);
		}
		return Math.max(maxProfit(len - 2, temp1),maxProfit(len - 2, temp2));	
    }
    private int maxProfit(int len, List<Integer> temp)
    {
        int prev = temp.get(0);
        int prev1 = 0;

        for(int i = 1; i <= len; i++)
        {
            int pick = temp.get(i) + prev1;
            int notPick = prev;
            int cur = Math.max(pick, notPick);
            prev1 = prev;
            prev = cur;
        }
        return prev;
    }
}
