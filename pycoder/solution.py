class Solution(object):
  
    ''''
    1.两数之和 two sum
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
      
    ''''
    2. add two nums
    tips: a.remember add the last carry
    '''
    # Definition for singly-linked list.
    # class ListNode(object):
    #     def __init__(self, val=0, next=None):
    #         self.val = val
    #         self.next = next
    def addTwoNumbers(self, l1, l2):
      """
      :type l1: ListNode
      :type l2: ListNode
      :rtype: ListNode
      """

      resultHead = ListNode()
      resultHead.val = 0
      resultHead.next = None
      i = l1
      j = l2
      now = resultHead
      flag = 0
      while i or j:
          # if((temp / 10) != 0):
          #     next.val += (temp / 10)
          if i is None:
              temp = j.val
          elif j is None:
              temp = i.val
          else:
              temp = i.val + j.val
          temp += flag/10
          flag = temp
          now.val += temp % 10
          if i :
              i = i.next
          if j :
              j = j.next
          if i or j:
              next = ListNode()
              now.next = next
              now = next

      if flag >= 10:
          next = ListNode()
          next.val = flag/10
          now.next = next

      return resultHead
  
if __name__ == "__main__":
   nums = [2, 7, 11, 15]
   target = 9
   s = Solution()
   print(s.twoSum(nums, target))
