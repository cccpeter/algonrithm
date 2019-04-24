package code2;

import java.util.HashSet;
import java.util.Set;

public class code2_2 {
	/**
	 * 输入一个数，统计相加各位的数如果为单位数就返回
	 * @param num
	 * @return
	 */
	public int getnum1(int num){
		return (num-1)%9+1;
	}
	public int getnum2(int num){
		while(num<10){
			num=num/10+num%10;
		}
		return num;
	}
	/**
	 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
		示例 :
		
		输入: [1,2,1,3,2,5]
		输出: [3,5]
	 * @param nums
	 * @return
	 */
    public int[] singleNumber(int[] nums) {
        Set<Integer> ll=new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(ll.contains(nums[i]))
                ll.remove(nums[i]);
            else
                ll.add(nums[i]);
        }
        if(ll.size()==0){
            return new int[2];
        }
        int[] end=new int[ll.size()];
        if(ll.size()==1){
            end=new int[2];
        }
        int w=0;
        for(Integer i: ll){
            end[w]=i;
            w++;
        }
        return end;
    }

}
