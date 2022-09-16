import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target){
        for(int i=0; i < nums.length-1; i++){
            for(int j=i+1; j < nums.length; j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j}; // 使用花括号
                }
            }
        }
        return null;
    }

  // 1. two sum (hash)
    public int[] twoSum_hash(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i < nums.length; i++){
            int temp = target-nums[i];
            if(map.containsKey(temp)){ // 把要判断是否存在的值作为key
                return new int[]{i,map.get(temp)};
            }
            map.put(nums[i],i); // 当两个嵌套循环+外层i+内层初始i+1(在当前值往后判断) 可以使用单层循环+在每层循环的最后加入(在当前值往前判断)
        }
        return null;
    }

    public static void main(String[] args){
        Two_Sum ts = new Two_Sum();
        System.out.println(ts.twoSum(new int[]{2,7,11,5}, 9));
    }
}

