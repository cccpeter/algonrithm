package code;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
public class code_hash_1_1 {
    /**
     * ����׳���n! = n * (n-1) * ... * 2 * 1
     * @param n
     * @return
     */
    private static long factorial(int n) {
        return (n > 1) ? n * factorial(n - 1) : 1;
    }

    /**
     * ����������A(n, m) = n!/(n-m)!
     * @param n
     * @param m
     * @return
     */
    public static long arrangement(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) : 0;
    }

    /**
     * ���������C(n, m) = n!/((n-m)! * m!)
     * @param n
     * @param m
     * @return
     */
    public static long combination(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;
    }




    /**
     * ���ѡ�񣨴��б���ѡ��n����ϣ�
     * @param dataList ��ѡ�б�
     * @param n ѡ�����
     */
    public static void combinationSelect(int[] dataList, int n) {
        System.out.println(String.format("C(%d, %d) = %d", dataList.length, n, combination(dataList.length, n)));
        combinationSelect(dataList, 0, new int[n], 0);
    }

    /**
     * ���ѡ��
     * @param dataList ��ѡ�б�
     * @param dataIndex ��ѡ��ʼ����
     * @param resultList ǰ�棨resultIndex-1��������Ͻ��
     * @param resultIndex ѡ�������0��ʼ
     */
    static private List<List<Integer>> list=new ArrayList<List<Integer>>();
    static HashMap<String, String> map=new HashMap<>();
    static int target=5;
//    private static void combinationSelect(int[] dataList, int dataIndex, int[] resultList, int resultIndex) {
//        int resultLen = resultList.length;
//        int resultCount = resultIndex + 1;
//        if (resultCount > resultLen) { // ȫ��ѡ����ʱ�������Ͻ��
//            String mString="";
//            int muns=0;
//            List<Integer> list1 = new ArrayList<>();//С�б�
//            for(int i=0;i<resultLen;i++){
//            	System.out.println(resultList[i]);
//            	mString+=resultList[i];
//            	muns+=resultList[i];
//            	list1.add(resultList[i]);
//            }
//            if(map.get(mString)==null&&muns==target){//���ظ�
//            	list.add(list1);
//            	map.put(mString, "1");
//            }
//            
////            System.out.println("------------���-------------");
//            return;
//        }

        // �ݹ�ѡ����һ��
//        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
//            resultList[resultIndex] = dataList[i];
//            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
//        }
//    }
    private static void combinationSelect(int[] dataList, int dataIndex, int[] resultList, int resultIndex) {  
        int resultLen = resultList.length;  
        int resultCount = resultIndex + 1;  
        if (resultCount > resultLen) { // ȫ��ѡ����ʱ�������Ͻ��  
            System.out.println(Arrays.asList(resultList));  
            for(int i=0;i<resultLen;i++){
            	System.out.print(resultList[i]+",");
            }
            return;  
        }  
      
        // �ݹ�ѡ����һ��  
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {  
            resultList[resultIndex] = dataList[i];  
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);  
        }  
    }  
/**
 * ��������־�����
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
     * �������������ɸѡ��(����ɸѡ������)
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
     * ͬ���ַ�
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
