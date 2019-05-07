package code2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class code_4_1 {
	/**
	 * 子域名访问计数
	 * 用字典来做即可
	 * 执行用时 : 40 ms, 在Subdomain Visit Count的Java提交中击败了32.29% 的用户
		内存消耗 : 48 MB, 在Subdomain Visit Count的Java提交中击败了75.96% 的用户
	 * @param cpdomains
	 * @return
	 */
	static public List<String> subdomainVisits(String[] cpdomains) {
		HashMap<String, Integer> map=new HashMap<>();
        for(int i=0;i<cpdomains.length;i++){
        	String[] cpd0main=cpdomains[i].split(" ");
        	String[] cpd0=cpd0main[1].split("\\.");//需要转义，尤其要注意
        	String domain="";
        	for(int j=cpd0.length-1;j>=0;j--){
        		if(j==cpd0.length-1){
        			domain+=cpd0[j];
        		}else{
        			domain=cpd0[j]+"."+domain;
        		}
        		
        		if(map.get(domain)==null){
        			map.put(domain, Integer.parseInt(cpd0main[0]));
        		}else{
        			map.put(domain, map.get(domain)+Integer.parseInt(cpd0main[0]));
        		}
        	}
        }
        List<String> re=new ArrayList<>();
        for (String key : map.keySet()) {
        	re.add(map.get(key)+" "+key);
        }
        return re;
    }
	//字母大小的全排列
	/**
	 * 
	 * 用递归或者用栈即可
	 * @param S
	 * @return
	 */
	static public List<String> letterCasePermutation(String S) {
		List<String> list=new ArrayList<>();
		char []arr=S.toCharArray();
		function(list,arr,0);
		return list;
        
    }
	static public void function(List<String> list,char[] arr,int i){
		if(i>=arr.length){
			list.add(new String(arr));
			return;
		}
		function(list, arr, i+1);
		if(!Character.isDigit(arr[i])){
			if(Character.isUpperCase(arr[i])){
				arr[i]=Character.toLowerCase(arr[i]);
				function(list, arr, i+1);
				arr[i]=Character.toLowerCase(arr[i]);
			}else{
				arr[i]=Character.toUpperCase(arr[i]);
				function(list, arr, i+i);
				arr[i]=Character.toLowerCase(arr[i]);
			}
		}
	}
	/**
	 * 动态规划的递归
	 */
	static int[] c = new int[100];     //将划分到的每个数字存储到c数组里面
	static int num = 0;                //存储待划分的数字
	static int nums=0;
    static void dfs(int start,int n)
    {
//    	System.out.println(nums+"执行的次数");
    	nums++;
    	if(n == 0)
    	{
    		for(int i = 1;i <= start-2;i++)
    		{
    			System.out.print(c[i]+"+");
    		}
    		return;//递归的出口
    	}
    	for(int i = 1;i <= n;i++)
    	{	
    		if(i >= c[start-1])
    		{
//    			System.out.println(c[start-1]+"start的值");
    		   c[start] = i;
    		   dfs(start+1,n-i);//递归的条件
    		}
    	}
    }
    /**
     * 分割回文串
     * 回溯算法
     * @param args
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backTrack(s.toCharArray(), 0, new ArrayList<String>(), res);
        return res;
    }
    public static void backTrack(char[] s, int idx, List<String> cur, List<List<String>> res){
        if(idx == s.length){
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = idx; i < s.length; i++){
            if(isPalind(s, idx, i)){
                    cur.add(new String(s, idx, i-idx+1));
                backTrack(s, i + 1, cur, res);
                cur.remove(cur.size()-1);
                }
        }
    }
    private static boolean isPalind(char[] s, int i, int j){
        while(i < j){
            if(s[i++] != s[j--])
                return false;
        }
        return true;
    }
	public static void main(String[] args) {
//		String[] re=new String[1];
//		re[0]="9001 discuss.leetcode.com";
//		List<String> result=subdomainVisits(re);
//		for (String string : result) {
//			System.out.println(string);
//		}
		
//		Scanner cin = new Scanner(System.in);
//	    num = cin.nextInt();
//		dfs(1,num);
		
		List<String> result=letterCasePermutation("a1b2");
		for (String string : result) {
			System.out.println(string);
		}
	}
}
