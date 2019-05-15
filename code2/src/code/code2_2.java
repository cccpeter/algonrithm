package code;

import java.util.HashSet;
import java.util.Set;

public class code2_2 {
	/**
	 * ����һ����ͳ����Ӹ�λ�������Ϊ��λ��ͷ���
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
	 * ��һ���������� nums������ǡ��������Ԫ��ֻ����һ�Σ���������Ԫ�ؾ�������Ρ� �ҳ�ֻ����һ�ε�������Ԫ�ء�
		ʾ�� :
		
		����: [1,2,1,3,2,5]
		���: [3,5]
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
