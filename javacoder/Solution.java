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
    
    // 2. three sum（hash / double pointer）
    // hash
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums); // 先给数组排序，方便之后查重
        for(int i=0; i<nums.length-2; i++){
            Map<Integer,Integer> map = new HashMap<>();
            for(int j=i+1; j<nums.length; j++){
                int temp = 0 - nums[i] - nums[j];
                if(map.containsKey(temp)){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(temp);
                    list.add(nums[j]);
                    if(!result.contains(list))
                        result.add(list);
                }
                map.put(nums[j],j);
            }
        }
        return result;
    }
    
    // double pointer
    public List<List<Integer>> threeSum_double(int[] nums){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i=0; i<len-2; i++){
            if(nums[i] > 0)
                return result;
            if(i>0 && nums[i] == nums[i-1])
                continue;
            for (int j=i+1; j<len-1; j++){
                if(j>i+1 && nums[j] == nums[j-1])
                    continue;
                for (int k = len-1; k>j; k--) {
                    if(nums[i] + nums[j] + nums[k] < 0)
                        break;
                    if ((k == len - 1 || nums[k] != nums[k + 1]) && nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        Two_Sum ts = new Two_Sum();
        System.out.println(ts.twoSum(new int[]{2,7,11,5}, 9));
    }
}

