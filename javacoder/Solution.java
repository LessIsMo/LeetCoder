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
    
    /**********************************************   hash  **********************************************/
    
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
    
    // 15. three sum（hash / double pointer）
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
            if(i>0 && nums[i] == nums[i-1])
                continue;
            for (int j=i+1; j<len-1; j++){
                if(j>i+1 && nums[j] == nums[j-1])
                    continue;
                int k=len-1;
                while(k>j && nums[j]+nums[k]+nums[i]>0){ // 循环里面的语句要尽可能少，取代下面循环中操作过多的for
                    k--;
                }
                if(k==j) break;
                if (nums[j] + nums[k] + nums[i] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                }
//                for (int k = len-1; k>j; k--) {
//                    if(nums[i] + nums[j] + nums[k] < 0)
//                        break;
//                    if ((k == len - 1 || nums[k] != nums[k + 1]) && nums[i] + nums[j] + nums[k] == 0) {
//                        List<Integer> list = new ArrayList<>();
//                        list.add(nums[i]);
//                        list.add(nums[j]);
//                        list.add(nums[k]);
//                        result.add(list);
//                    }
//                }
            }
        }
        return result;
    }
    
    // 13. Roman to Integer
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int result = 0;
        int pre = 9999;
        for(int i=0; i<s.length(); i++){
            int temp = map.get(s.charAt(i));
            if(pre < temp)
                result = result + temp - 2*pre;
            else
                result += temp;
            pre = temp;
        }
        return result;
    }
    
    
    /**********************************************   Iteration / Recursion  **********************************************/
    // 20. Merge Two Sorted Lists( Iteration / Recursion)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) { // Iteration
        ListNode prehead = new ListNode(0,null);
        ListNode result = prehead;
        while(list1!=null && list2!=null){
            if(list1.val < list2.val){
                result.next = list1;
                list1 = list1.next;
            } else {
                result.next = list2;
                list2 = list2.next;
            }
            result = result.next;
        }
        if(list1 != null)
            result.next = list1;
        else
            result.next = list2;
        return prehead.next;
    }

    public ListNode mergeTwoListsRecursion(ListNode list1, ListNode list2) { // Recursion 只关注这一层要干什么,返回什么,至于下一层(规模减1),不管
        if(list1 == null)
            return list2;
        else if(list2 == null)
            return list1;
        else if(list1.val < list2.val) {
            list1.next = mergeTwoListsRecursion(list1.next, list2);
            return list1;
        }
        else {
            list2.next = mergeTwoListsRecursion(list1, list2.next);
            return list2;
        }
    }
    
    // 17. Letter Combinations Phone Number
    public List<String> letterCombinations(String digits) {
        if(digits.equals("")){
            return result;
        }
        Map<Character, String> map = new HashMap<Character,String>() {{
            put('2',"abc");
            put('3',"def");
            put('4',"ghi");
            put('5',"jkl");
            put('6',"mno");
            put('7',"pqrs");
            put('8',"tuv");
            put('9',"wxyz");
        }};
        int len = digits.length();
        List<String> letters = new ArrayList<String>();
        for(int i=0; i<len; i++){
            letters.add(map.get(digits.charAt(i)));
        }
        combine(letters, len, 0, new String(""), result);
        return result;
    }

    public void combine(List<String> letters,int len, int nd, String combination, List<String> result){
        if(nd >= len) {
            result.add(combination);
            return;
        }
        for(int i=0; i<letters.get(nd).length(); i++){
            combine(letters,len,nd+1,combination+letters.get(nd).charAt(i),result);
        }
    }
    
    /**********************************************   DP  **********************************************/
    // 62. Unique Paths( Dynamic Programming / Recursion)
    public int uniquePaths(int m, int n) { // DP f(m,n) = f(m-1,n) + f(m,n-1)
        int[][] result = new int[m][n];
//        result[0][1] = 1;
//        result[1][0] = 1;
//        for (int i=1; i<m; i++){
//            for (int j=1; j<n; j++){
//                result[i][j] = result[i-1][j] + result[i][j-1];
//            }
//        }
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if(i==0 || j==0)
                    result[i][j] = 1;
                else
                    result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }
        return result[m-1][n-1];
    }

    public int uniquePaths_Recursion(int m, int n){ //Recursion
        if(m==1 || n==1)
            return 1;
        return uniquePaths_Recursion(m-1,n) + uniquePaths_Recursion(m,n-1);
    }
    
    
    // 70. Climbing Stairs ( Dynamic Programming )
    public int climbStairs(int n) { //DP f(n) = f(n-1) + f(n-2)
        int prepre = 1;
        if(n<=1)
            return prepre;
        int pre = 2;
        int result = 2;
        for(int i=2; i<n; i++){
            result = pre + prepre;
            prepre = pre;
            pre = result;
        }
        return result;
    }
    
    
    /**********************************************     **********************************************/
    // 14. Longest Common Prefix ( 首先找到最短的字符串长度 )
    public String longestCommonPrefix(String[] strs) {
        String result = new String("");
        int len = strs.length;
        int shortestLen = strs[0].length();
        boolean flag = true;
        for (int i=1; i<len; i++){
            if(shortestLen > strs[i].length())
                shortestLen = strs[i].length();
        }
        for(int i=0; i<shortestLen; i++){
            char temp = strs[0].charAt(i);
            for(int j=1; j<len; j++){
                if(temp != strs[j].charAt(i))
                    flag = false;
            }
            if(!flag)
                break;
            result = result + String.valueOf(temp);
        }
        return result;
    }
   
    /**********************************************    Stack  **********************************************/
    // 20. valid parentheses (stack)
    public boolean ValidParentheses(String s) {
        int len = s.length();
        char[] charArr = s.toCharArray();
        Stack<Character> st = new Stack<Character>();
        for (int i=0; i<len; i++){
            if(charArr[i] == '(') {
                st.push(')');
            } else if(charArr[i] == '[') {
                st.push(']');
            } else if(charArr[i] == '{') {
                st.push('}');
            } else if(st.isEmpty() || charArr[i] != st.pop()){ //st.isEmpty() || !st.isEmpty() && charArr[i] != st.pop()
                return false;
            }
        }
        if(st.empty())
            return true;
        return false;
    }
    
    // 1047. Remove All Adjacent Duplicates In String
    public String removeDuplicates(String s) { // 或者用StringBuilder模拟栈操作
        StringBuilder result = new StringBuilder();
        Stack<Character> st = new Stack<Character>();
        int len = s.length();
        st.push(s.charAt(0));
        for(int i=1; i < len; i++){
            char temp = s.charAt(i);
            if(!st.isEmpty() && st.peek() == temp){
                st.pop();
            } else {
                st.push(temp);
            }
        }
        while(!st.isEmpty()){
            result.append(st.pop());
        }
        return result.reverse().toString();
    }
    
    //739. Daily Temperatures
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        Stack<Integer> st = new Stack<>(); // 存储没有计算出天数的天在temperatures中的位置
        int[] result = new int[len];
        for(int i=0; i < len; i++){
            while(!st.isEmpty() && temperatures[i] > temperatures[st.peek()]){
                result[st.peek()] = i - st.pop();
            }
            st.push(i);
        }
        return result;
    }
    
    //678. Valid Parenthesis String
    public boolean checkValidString(String s) {
        char[] cs = s.toCharArray();
//        Stack<Character> st = new Stack<Character>(); // 可以不用存储右括号
//        Stack<Character> star = new Stack<Character>(); // 存储 *
        // 可以不用存储）, 存储 *
        // 栈不存储char，存储位置编号，为了防止*与（配对，（在*后面的情况
        Stack<Integer> st = new Stack<Integer>(); // 可以不用存储右括号
        Stack<Integer> star = new Stack<Integer>(); // 存储 *

        for(int i=0; i<s.length(); i++){
            if(cs[i] == '(')
                st.push(i);
            else if(cs[i] == '*')
                star.push(i);
            else{
                if(!st.isEmpty())
                    st.pop();
                else if(!star.isEmpty())
                    star.pop();
                else
                    return false;
            }
        }
//        if(st.isEmpty())
//            result = true;
        // 不能只判断st是否为空，因为star可以和里面的左括号配对
        if(st.size() > star.size())
            return false;
        else{
            while(!st.isEmpty()){
                if(st.pop() > star.pop())
                    return false;
            }
            return true;
        }
    }

    /**********************************************  Hash Map / Hash Set  **********************************************/
    // 1160. Find Words That Can Be Formed by Characters
    public int countCharacters(String[] words, String chars) {
        // 也可以用数组代替hashmap存储字母个数
        int result = 0;
        // hash记录的是chars中各字母出现的次数
        HashMap<Character,Integer> letters = new HashMap<>();
        for(int i=0; i<chars.length(); i++){
            letters.put(chars.charAt(i),letters.getOrDefault(chars.charAt(i),0)+1);
        }
        for(String word : words){
            char[] cword = word.toCharArray();
            HashMap<Character,Integer> wl = new HashMap<>();
            for(int i=0; i<cword.length; i++){
                wl.put(cword[i],wl.getOrDefault(cword[i],0)+1);
            }
            boolean flag = true;
            for(char key : wl.keySet()){
                if(!letters.containsKey(key) || letters.get(key)<wl.get(key)){
                    flag = false;
                    break;
                }
            }
            if(flag)
                result += cword.length;
        }
        return result;
    }
    
    // 451. Sort Characters By Frequency
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] letters = s.toCharArray();
        for(char c : letters){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        List<Character> ans = new ArrayList<>(map.keySet());
        // ans.sort((a,b) -> map.get(b)-map.get(a));
        ans.sort((a,b) -> map.get(b) - map.get(a));
        StringBuffer result = new StringBuffer();
        for(Character c:ans){
            for(int j=0; j<map.get(c); j++)
                result.append(c);
        }
        return result.toString();
    }
    
    // 12. Integer to Roman
    public String intToRoman(int num) {
//        HashMap<Integer,String> map = new HashMap<>(){{
//            put(1,"I");
//            put(4,"IV");
//            put(5,"V");
//            put(9,"IX");
//            put(10,"X");
//            put(40,"XL");
//            put(50,"L");
//            put(90,"XC");
//            put(100,"C");
//            put(400,"CD");
//            put(500,"D");
//            put(900,"CM");
//            put(1000,"M");
//        }};
//        StringBuffer ans = new StringBuffer();
//        int[] nums = new int[]{1,4,5,9,10,40,50,90,100,400,500,900,1000};
//        int pos = nums.length-1;
//        while(num>0){
//            for(; nums[pos] > num; pos--){}
//            num -= nums[pos];
//            ans.append(map.get(nums[pos]));
//        }
//        return ans.toString();
        String[] symbols = new String[]{"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        int[] nums = new int[]{1,4,5,9,10,40,50,90,100,400,500,900,1000};
        StringBuffer ans = new StringBuffer();
        for(int pos = nums.length-1; num > 0;){
            for(; nums[pos] > num; pos--){}
            num -= nums[pos];
            ans.append(symbols[pos]);
        }
        return ans.toString();
    }
    
    // 697. Degree of Array
    public int findShortestSubArray(int[] nums) {
        // Hash value也可以存储数组
        // 数组用来存储 出现次数，起始坐标，终止坐标
        HashMap<Integer,int[]> map = new HashMap<>();
        int max = 0;
        int ans = 50000;
        for(int i=0; i<nums.length; ++i){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], new int[]{1,i,i});
            } else {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            }
            max = Math.max(max,map.get(nums[i])[0]);
        }
        for(Map.Entry<Integer, int[]> entry : map.entrySet()){
            if( entry.getValue()[0] == max){
                ans = Math.min(ans, entry.getValue()[2]-entry.getValue()[1]+1);
            }
        }
        return ans;
    }
    
    // 219. Contains Duplicate II
    // 滑动窗口
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++){
            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);
            if(set.size() > k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
    

    /**********************************************  List  **********************************************/
    // 3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        List<Character> sub_str = new ArrayList<>();
        int ans = 0;
        for(int i=0; i<s.length(); i++){
            if(!sub_str.contains(s.charAt(i))) {
                sub_str.add(s.charAt(i));
                ans = Math.max(ans,sub_str.size());
            }else{
                ans = Math.max(ans,sub_str.size());
//                for (; sub_str.get(j) != s.charAt(i); j++) {
//                    sub_str.remove(j);
//                } 每次的删除操作会影响字母的下标，因此这样行不通，会报超出界限的错误
                while(sub_str.get(0) != s.charAt(i)) {
                    sub_str.remove(0);
                }
                sub_str.remove(0);
                sub_str.add(s.charAt(i)); // 不要忘记这句
            }
        }

        // 方法二 Hash
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }

        return ans;
    }
    
    // 692. Top K Frequent Words
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer> map = new HashMap<>();
        int max = 1;
        for(String word : words){
            map.put(word, map.getOrDefault(word,0)+1);
        }
        List<String> ans = new ArrayList<>(map.keySet());
        ans.sort((a,b)-> map.get(a) > map.get(b) ? -1 : map.get(a).equals(map.get(b)) ? a.compareTo(b) : 1);
        return ans.subList(0, k);
    }

    public static void main(String[] args){
        Top_K_Frequent_Words_692 t = new Top_K_Frequent_Words_692();
        t.topKFrequent(new String[]{"the","day","is","sunny","the","the","the","sunny","is","is"},4);
    }
    
    // 763. Partition Labels
    public List<Integer> partitionLabels(String s) {
//        List<Integer> ans = new ArrayList<>();
//        List<Character> letters = new ArrayList<>();
//        boolean flag_contain = false;
//        int len = 1;
//        ArrayList<Character> sc = Arrays.asList(s.toCharArray());
//        letters.add(sc[0]);
//        for(int i=1; i<sc.length; i++){
//            for(int j=0; j<letters.size(); j++){
//                if(s.contains(letters.get(j).toString())){
//                    flag_contain = true;
//                    break;
//                }
//            }
//            if(flag_contain){
//                len++;
//                if(!letters.contains(s.charAt(i))){
//                    letters.add(s.charAt(i));
//                }
//            } else {
//                ans.add(len);
//                letters.clear();
//                letters.add(s.charAt(i));
//                len = 1;
//            }
//            flag_contain = false;
//        }
        List<Integer> ans = new ArrayList<>();
        int[] pos = new int[26]; // 存储每个字母在s中出现的最后的位置
        for(int i=0; i<s.length(); i++){
            pos[s.charAt(i)-'a'] = i;
        }
        int len = 0;
        int letter_pos = 0;
        for(int i=0; i<s.length(); i++){
            len++;
            letter_pos = Math.max(letter_pos, pos[s.charAt(i) - 'a']);
            if(letter_pos == i) {
                ans.add(len);
                len = 0;
            }
//            if(letter_pos > i) {
//                letter_pos = Math.max(letter_pos, pos[s.charAt(i) - 'a']);
//            } else {
//                ans.add(len);
//                len = 0;
//                letter_pos = pos[s.charAt(i) - 'a'];
//            }
        }
        return ans;
    }
    
    /**********************************************  Linked List  **********************************************/
    private class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    
    // 206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        ListNode node = head;
        ListNode pre = null;
        while( node != null){
            ListNode temp = node.next;
            node.next = pre;
            pre = node;
            node = temp;
        }
        return pre;
    }
    
    // 2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode node = result;
        int carry = 0;
        while(l1!=null || l2!=null){
            int temp=0;
//                if(l1 != null && l2 != null){
//                    temp = l1.val + l2.val + flag;
//                } else {
//                    temp = l1 == null ? l2.val + flag : l1.val + flag;
//                }
            int m,n;
            m = l1 == null ? 0 : l1.val;
            n = l2 == null ? 0 : l2.val;
            temp = m + n + carry;
            carry = 0;

            if(temp>=10){
                carry = 1;
                temp %= 10;
            }

            ListNode tempNode = new ListNode(temp);
            node.next = tempNode;
            node = tempNode;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if(carry==1){ // 判断最后有无进位
            ListNode tempNode = new ListNode(1);
            node.next = tempNode;
            node = tempNode;
        }
        return result.next; // 返回result.next
    }
    
    // 445. Add Two Numbers reverse
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // deque 可用作 queue 或 stack
        Deque<Integer> st1 = new LinkedList<>();
        Deque<Integer> st2 = new LinkedList<>();
        while(l1!=null){
            st1.push(l1.val);
            l1 = l1.next;
        }
        while(l2!=null){
            st2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while(!st1.isEmpty() || !st2.isEmpty()){
            int m = st1.isEmpty() ? 0 : st1.pop();
            int n = st2.isEmpty() ? 0 : st2.pop();
            int sum = m + n + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode node = new ListNode(sum,ans);
            ans = node;
        }
        if(carry > 0){
            ListNode node = new ListNode(carry,ans);
            ans = node;
        }
        return ans;
    }
    
    // 160. Intersection of Two Linked Lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        HashSet<ListNode> aset = new HashSet<>();
        while( a!=null ){
            aset.add(a);
            a = a.next;
        }
        while( b!=null ){
            if( aset.contains(b) ){
                return b;
            } else {
                b = b.next;
            }
        }
        return null;
    }
    
    // 237. Delete Node in a Linked List
    public void deleteNode(ListNode node) {
        // node = node.next;
        node.val = node.next.val;
        node.next = node.next.next;
    }
    
    // 19. Remove Nth Node From End
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        int count = 0;
        while(node != null){
            node = node.next;
            count++;
        }
        node = head;
        if(count-n == 0) // 判断特殊情况
            return head.next;
        else{
            for(int j=1; count-j > n; ++j){
                node = node.next;
            }
            node.next = node.next.next;
        }
        return head;
    }
    
    // 234. Palindrome Linked List
    public boolean isPalindrome(ListNode head) {

        // 快慢指针
        if (head == null) {
            return true;
        }
        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;

//        // 双指针
//        List<Integer> vals = new ArrayList<>();
//        while(head != null){
//            vals.add(head.val);
//            head = head.next;
//        }
//        int back = vals.size()-1;
//        for(int start = 0; start < back; ++start, --back){
//            if(vals.get(start) != vals.get(back))
//                return false;
//        }
//        return true;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
    
    // 142. Linked List Cycle II
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        HashSet<ListNode> set = new HashSet<>();
        ListNode node = head;
        while(node != null){
            if(set.contains(node))
                return node;
            set.add(node);
            node = node.next;
        }
        return null;
    }
    
    // 138. Copy List with Random Pointer
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        HashMap<Node,Node> map = new HashMap<>();
        Node temp = head;
        // map.put(null,null);
        while(temp != null){
            map.put(temp,new Node(temp.val));
            temp = temp.next;
        }
        temp = head;
        while(temp != null){
            Node node = map.get(temp);
            node.next = map.get(temp.next);
            node.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }
    
    // 876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        if(fast.next == null)
            return slow;
        return slow.next;
    }
    
    // 61. Rotate List
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return head;
        }
        ListNode temp = head;
        int cnt = 0;
        while(temp.next != null){
            cnt++;
            temp = temp.next;
        }
        temp.next = head;
        cnt++;
        for(int i=0; i<(cnt-(k%cnt)); i++){
            temp = head;
            head = head.next;
        }
        temp.next = null;
        return head;
    }
    
    // 328. Odd Even Linked List
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) // 别忘记这一步的判断
            return head;
        ListNode head_even = head.next;
        ListNode odd = head, even = head_even;
        while(even!=null && even.next!=null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = head_even;
        return head;
    }
    
    // 24. Swap Nodes in Pairs
    public ListNode swapPairs(ListNode head) {
        // // 转变思路，创造一个pre
        // ListNode pre = new ListNode(0);
        // pre.next = head;
        // ListNode node = pre;
        // while(node.next != null && node.next.next != null){
        //     ListNode start = node.next;
        //     ListNode end = node.next.next;
        //     node.next = end;
        //     start.next = end.next;
        //     end.next = start;
        //     node = start;
        // }
        // return pre.next;

        // 递归
        if(head == null || head.next == null){
            return head;
        }
        ListNode start = head;
        ListNode end = head.next;
        start.next = swapPairs(end.next);
        end.next = start;
        return end;
    }
    
    // 203. Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;
        ListNode pre = head, node = head;
        while(node != null){
            if(node.val == val){
                pre.next = node.next;
            } else {
                pre = node;
            }
            node = node.next;
        }
        if(head.val == val)
            return head.next;
        return head;
    }
    
    // 1019. Next Greater Node In Linked List
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while(node != null){
            list.add(node.val);
            node = node.next;
        }
        int[] ans = new int[list.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=0; i<list.size(); i++){
            if(stack.isEmpty() || list.get(stack.peek()) >= list.get(i)){
                stack.push(i);
            } else {
                while(!stack.isEmpty() && list.get(stack.peek()) < list.get(i)){
                    ans[ stack.pop() ] =  list.get(i);
                }
                stack.push(i);
            }
        }
        return ans;   
    }
    
    // 82. Remove Duplicates from Sorted List II
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode pre_head = new ListNode(0,head);
        ListNode pre = pre_head, cur, next;
        while(pre!=null && pre.next!=null && pre.next.next!=null){
            cur = pre.next;
            next = cur.next;
            if(cur.val == next.val){
                while(cur != null && next != null && cur.val == next.val){
                    cur = next;
                    next = cur.next;
                }
                pre.next = next;
            } else{
                pre = pre.next;
            }
        }
        return pre_head.next;
    }
    
    // 1171. Remove Zero Sum Consecutive Nodes from Linked List
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode pre_head = new ListNode(0,head);
        HashMap<Integer,ListNode> map = new HashMap<>();
        int sum = 0;
        for(ListNode node=pre_head; node!=null; node=node.next){
            sum += node.val;
            if(map.containsKey(sum)){
                ListNode temp = map.get(sum);
                ListNode del_node = temp.next;
                int temp_num = sum;
                temp.next = node.next;
                while(del_node != node){ // 把中间节点的历史记录都删掉
                    temp_num += del_node.val;
                    map.remove(temp_num);
                    del_node = del_node.next;
                }
                // break;
            } else {
                map.put(sum,node);
            }
        }
        return pre_head.next;
    }
    
    // 92. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head.next == null)
            return head;
        ListNode ans = new ListNode(0,head);
        ListNode pre = ans;
        Deque<ListNode> stack = new ArrayDeque<>();
        for(int i=1; i<left; i++){
            pre = pre.next;
        }
        ListNode node = pre.next;
        for(int i=1; i<=(right-left+1); i++){
            stack.push(node);
            node = node.next;
        }
        ListNode temp = node;
        while(!stack.isEmpty()){
            pre.next = stack.pop();
            pre = pre.next;
        }
        pre.next = temp;
        return ans.next;
    }
    
    // 86. Partition List
    public ListNode partition(ListNode head, int x) {
        Deque<ListNode> greater = new ArrayDeque<>();
        ListNode ans = new ListNode(0,head);
        ListNode pre = ans;
        ListNode node = head;
        while(node != null){
            if(node.val < x){
                pre.next = node;
                pre = pre.next;
            } else{
                greater.add(node);
            }
            node = node.next;
        }
        while(!greater.isEmpty()){
            pre.next = greater.remove();
            pre = pre.next;
        }
        pre.next = null;
        return ans.next;
    }

    /**********************************************  Tree  **********************************************/
    // 101. Symmetric Tree
    public boolean isSymmetric(TreeNode root) {
        TreeNode l = root.left;
        TreeNode r = root.right;
        return isDuiCheng(l,r);
    }

    public boolean isDuiCheng(TreeNode l,TreeNode r){
        if(l==null && r==null){
            return true;
        } else if(l==null || r==null){
            return false;
        } else {
            if(l.val == r.val && isDuiCheng(l.left,r.right) && isDuiCheng(l.right,r.left))
                return true;
        }
        return false;
    }
    
    public static void main(String[] args){
        Two_Sum ts = new Two_Sum();
        System.out.println(ts.twoSum(new int[]{2,7,11,5}, 9));
    }
    
}

