public class {
  // 03. 数组中重复的数字
  public int findRepeatNumber(int[] nums) {
        // o(n) o(1) 原地交换
        for(int i=0; i<nums.length; ){
            if(nums[i] == i){
                ++i;
                continue;
            }
            if(nums[nums[i]] == nums[i]){
                return nums[i];
            }
            int temp = nums[nums[i]];
            nums[nums[i]] = nums[i];
            nums[i] = temp;
        }
        // // o(n) o(n)
        // Set<Integer> num = new HashSet<>();
        // for(int i=0 ;i<nums.length; i++){
        //     if(num.contains(nums[i])){
        //         return nums[i];
        //     }
        //     num.add(nums[i]);
        // }
        return -1;
    }
  
  // 04. 二维数组中的查找
  public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length==0)
            return false;
        int n = matrix.length;
        int m = matrix[0].length;
        for(int k=0; k<n; ++k){
            if(target > matrix[k][m-1]){
                continue;
            } else if(target < matrix[k][0]){
                return false;
            }
            int i=0, j=m-1;
            while(i<=j){
                if(target > matrix[k][(i+j)/2]){
                    i = (i+j)/2+1;
                } else if(target < matrix[k][(i+j)/2]) {
                    j = (i+j)/2-1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
  
  // 06. 从尾到头打印链表
  int[] ans;
  int len;
  public int[] reversePrint(ListNode head) {
      // 递归
      if(head == null){
          ans = new int[len];
      } else {
          ++len;
          reversePrint(head.next);
          ans[ans.length - len] = head.val;
          --len;
      }
      return ans;
      // List<Integer> list = new ArrayList<>();
      // ListNode node = head;
      // while(node != null){
      //     list.add(0,node.val);
      //     node = node.next;
      // }
      // return list.stream().mapToInt(Integer::valueOf).toArray();
  }

  // 07. 重建二叉树
  HashMap<Integer,Integer> map = new HashMap<>();
  int[] preorderCopy;
  public TreeNode buildTree(int[] preorder, int[] inorder) {
      preorderCopy = preorder;
      for(int i=0; i<inorder.length; i++){
          map.put(inorder[i],i);
      }
      return generateTree(0,0,inorder.length-1);
  }

  public TreeNode generateTree(int cur, int left, int right){
      if(right < left){
          return null;
      }
      TreeNode node = new TreeNode(preorderCopy[cur]);
      int inIndex = map.get(preorderCopy[cur]);
      node.left = generateTree(cur+1, left, inIndex-1);
      //右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
      node.right = generateTree(cur+(inIndex-left)+1,inIndex+1,right);
      return node;
  }
  
  // 09. 用两个栈实现队列
  Deque<Integer> st1;
    Deque<Integer> st2;

    public CQueue() {
        st1 = new LinkedList<>();
        st2 = new LinkedList<>();
    }
    
    public void appendTail(int value) {
        st1.addLast(value);
    }
    
    public int deleteHead() {
        if(!st2.isEmpty()){
            return st2.removeLast();
        }
        while(!st1.isEmpty()){
            st2.addLast(st1.removeLast());
        }
        if(!st2.isEmpty()){
            return st2.removeLast();
        }
        return -1;
    }
  
  // 10- I. 斐波那契数列
  public int fib(int n) {
        if(n==0 || n==1){
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        return dp[n];
    }
  
  // 10- II. 青蛙跳台阶问题
  public int numWays(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        return dp[n];
    }
  
}
