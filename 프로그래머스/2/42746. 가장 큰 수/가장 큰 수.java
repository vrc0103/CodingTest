import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        
        for(int i = 0; i < nums.length; i++) {
            nums[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(nums, (o1, o2) -> {
            String num1 = o1 + o2;
            String num2 = o2 + o1;
            
            return num2.compareTo(num1);
        });
        
        if (nums[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        
        return sb.toString();
    }
}