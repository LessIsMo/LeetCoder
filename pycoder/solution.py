class Solution(object):
  
  ''''
  1.两数之和
  tips: a.巧用列表函数 b.hash
  '''
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """

        i = 0
        lens = len(nums)
        for i in range(lens):
            numi = nums[i]
            temp = nums[:i]
            if target-numi in temp:
                j = nums.index(target-numi)
                return [i,j]
        return []
  
if __name__ == "__main__":
   nums = [2, 7, 11, 15]
   target = 9
   s = Solution()
   print(s.twoSum(nums, target))
