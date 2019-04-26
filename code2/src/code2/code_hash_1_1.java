package code2;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
public class code_hash_1_1 {
    /**
     * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
     * @param n
     * @return
     */
    private static long factorial(int n) {
        return (n > 1) ? n * factorial(n - 1) : 1;
    }

    /**
     * 计算排列数，即A(n, m) = n!/(n-m)!
     * @param n
     * @param m
     * @return
     */
    public static long arrangement(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) : 0;
    }

    /**
     * 计算组合数，即C(n, m) = n!/((n-m)! * m!)
     * @param n
     * @param m
     * @return
     */
    public static long combination(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;
    }




    /**
     * 组合选择（从列表中选择n个组合）
     * @param dataList 待选列表
     * @param n 选择个数
     */
    public static void combinationSelect(int[] dataList, int n) {
        System.out.println(String.format("C(%d, %d) = %d", dataList.length, n, combination(dataList.length, n)));
        combinationSelect(dataList, 0, new int[n], 0);
    }

    /**
     * 组合选择
     * @param dataList 待选列表
     * @param dataIndex 待选开始索引
     * @param resultList 前面（resultIndex-1）个的组合结果
     * @param resultIndex 选择索引，从0开始
     */
    static private List<List<Integer>> list=new ArrayList<List<Integer>>();
    static HashMap<String, String> map=new HashMap<>();
    static int target=5;
//    private static void combinationSelect(int[] dataList, int dataIndex, int[] resultList, int resultIndex) {
//        int resultLen = resultList.length;
//        int resultCount = resultIndex + 1;
//        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
//            String mString="";
//            int muns=0;
//            List<Integer> list1 = new ArrayList<>();//小列表
//            for(int i=0;i<resultLen;i++){
//            	System.out.println(resultList[i]);
//            	mString+=resultList[i];
//            	muns+=resultList[i];
//            	list1.add(resultList[i]);
//            }
//            if(map.get(mString)==null&&muns==target){//不重复
//            	list.add(list1);
//            	map.put(mString, "1");
//            }
//            
////            System.out.println("------------输出-------------");
//            return;
//        }

        // 递归选择下一个
//        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
//            resultList[resultIndex] = dataList[i];
//            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
//        }
//    }
    private static void combinationSelect(int[] dataList, int dataIndex, int[] resultList, int resultIndex) {  
        int resultLen = resultList.length;  
        int resultCount = resultIndex + 1;  
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果  
            System.out.println(Arrays.asList(resultList));  
            for(int i=0;i<resultLen;i++){
            	System.out.print(resultList[i]+",");
            }
            return;  
        }  
      
        // 递归选择下一个  
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {  
            resultList[resultIndex] = dataList[i];  
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);  
        }  
    }  
/**
 * 快乐数，快乐就是了
 * @param args
 */
    static public boolean isHappy(int n) {
        int sum=n;
        HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
        while(true){
            if(map.get(sum)!=null){
                return false;
            }
            if(sum==1){
                return true;
            }
            System.out.println(sum);
            map.put(sum,1);
            char[] charnum=String.valueOf(sum).toCharArray();
            for(int i=0;i<charnum.length;i++){
                int s=Integer.parseInt(String.valueOf(charnum[i]));
                sum+=s*s;
            }
        }
    }
    /**
     * 找质数，厄拉多塞筛选法(大网筛选法……)
     * @param args
     */
    static public int getela(int n){
    	int[] nums=new int[n];
    	for(int i=2;i<n;i++){
    		nums[i]=i;
    	}
    	for(int j=2;j<n;j++){
    		if(nums[j]!=0){
    			for(int k=2;k*j<n;k++){
    				nums[k*j]=0;
    			}
    		}
    	}
    	int re=0;
    	for(int i=0;i<n;i++){
    		if(nums[i]!=0){
    			re++;
    		}
    	}
    	return re;
    }
    /**
     * 同构字符串
     * @param s
     * @param t
     * @return
     */
    
    public boolean isIsomorphic(String s, String t) {
    	HashMap<Character, Integer> h1=new HashMap<>();
    	HashMap<Character, Integer> h2=new HashMap<>();
    	char[] c1=s.toCharArray();
    	char[] c2=t.toCharArray();
    	if(c1.length!=c2.length){
    		return false;
    	}
    	for(int i=0;i<c1.length;++i){
    		if(h1.get(c1[i])!=h2.get(c2[i]))return false;
    		h1.put(c1[i], i+1);
    		h1.put(c1[i], i+1);
    	}
        return true;
    }
    public static void main(String[] args) {
//        arrangementSelect(new String[] {
//                "1", "2", "3", "4"
//        }, 2);
//       combinationSelect(new int[] {
//                1, 2, 3, 4, 5
//        }, 4);
       isHappy(324);
//        System.out.println(list.size());
    }
}
