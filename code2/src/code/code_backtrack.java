package code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class code_backtrack {
	public static List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>>arraylist=new ArrayList<>();
    	return recursion(arraylist, nums, 0, nums.length-1);
    	
    }
    	//track
	    public static List<List<Integer>> recursion(List<List<Integer>> al,int[]nums,int begin,int end){
	    	List<Integer>al2=new ArrayList<>();
	        if(begin==end){
	        	for(int i=0;i<=end;i++){
	        		al2.add(nums[i]);
	        	}
	        	al.add(al2);
	        	return al;
	        }else{
	        	for(int j=begin;j<=end;j++){
	        		exch(nums,begin,j);
	            	recursion(al,nums, begin+1, end);
	            	exch(nums,begin,j);
	        	} 
	        	return al;
	        }
	    }
    	//����
		private static void exch(int[] nums, int i, int j) {
			int temp=nums[i];
			nums[i]=nums[j];
			nums[j]=temp;
		}
}
