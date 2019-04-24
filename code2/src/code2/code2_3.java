package code2;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
public class code2_3 {

	/**
	 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。

人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。

这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。

示例 1:

输入: row = [0, 2, 1, 3]
输出: 1
解释: 我们只需要交换row[1]和row[2]的位置即可。
示例 2:

输入: row = [3, 2, 0, 1]
输出: 0
解释: 无需交换座位，所有的情侣都已经可以手牵手了。
说明:

len(row) 是偶数且数值在 [4, 60]范围内。
可以保证row 是序列 0...len(row)-1 的一个全排列。
	 */
	public int minSwapsCouples(int[] row) {
        int res=0;
        for(int i=0;i<row.length;i+=2){
            if(couple(row[i])!=row[i+1]){
                for(int j=i+2;j<row.length;j++){
                    if(row[j]==couple(row[i])){
                        swap(row,j,i+1);
                        res++;
                        break;
                        
                    }
                }
            }
        }
        return res;
    }
    public void swap(int[] row, int i, int j) {
        int temp = row[i];
        row[i] = row[j];
        row[j] = temp;
    }
    public int couple(int a) {//返回他座位前后的两位
        return (a % 2 == 0) ? a + 1 : a - 1;
    }
    
    /**
     * 
     */
    public int maxProduct(String[] words) {
        //判断俩个单词是否有公共字母
        int wordslen=words.length;
        int max=0;
        for(int i=0;i<wordslen;i++){
            for(int j=i+1;j<wordslen;j++){
                //判断是否有公共字符
                if(sameWord(words[i],words[j])){
                    char[] word1list=words[i].toCharArray();
                    char[] word2list=words[j].toCharArray();
                    if(word1list.length*word2list.length>max){
                        max=word1list.length*word2list.length;
                    }
                }
            }
        }
        return max;
    }
    public boolean sameWord(String word1,String word2){
        char[] word1list=word1.toCharArray();
        char[] word2list=word2.toCharArray();
        for(int i=0;i<word1list.length;i++){
            for(int j=0;j<word2list.length;j++){
            	if(word1list[i]==word2list[j]){
                	System.out.println("存在相同字符串---"+word1+"++++"+word2+"****"+word1list+"*****"+word2list[j]);
                    return false;
                }
            }        
        }
        return true;
    }
    public List<Integer> addToArrayForm(int[] A, int K) {
        String num=new String("");
        for(int i=0;i<A.length;i++){
            num+=A[i];
        }
        int nums=Integer.parseInt(num);
        nums=nums+K;
        System.out.println(nums);
        char[] numchar=String.valueOf(nums).toCharArray();
        System.out.println(numchar);
        List<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<numchar.length;i++){
            list.add(Character.getNumericValue(numchar[i]));
        }
        return list;
    }
    /**
     * 最短路径
     * @param args
     */
    HashMap<Integer, Integer[]> vews = new HashMap<Integer, Integer[]>();//所有顶点
    HashMap<Integer, Integer> allvews = new HashMap<Integer, Integer>();//所有节点
    HashMap<Integer, Integer> nowvews = new HashMap<Integer, Integer>();//当前所有顶点的到达路径
    int[][] times;
    public int networkDelayTime(int[][] times, int N, int K) {
    	this.times=times;
        int edgnum=times.length;//边的数量
        
        int re=-1;//result
        for(int i=0;i<edgnum;i++){
            if(vews.get(times[i][0]) != null){
                Integer[] num=new Integer[1];
                num[0]=i;
                vews.put(times[i][0],num);
            }else{
                Integer[] nums=vews.get(times[i][0]);
                Integer[] num=new Integer[nums.length+1];
                for(int j=0;j<nums.length;j++){
                	num[j]=nums[j];
                }
                num[nums.length+1]=i;
                vews.put(times[i][0], num);
                allvews.put(times[i][0], i);//加入顶点
                nowvews.put(times[i][0], 9999);//定义所有节点的距离初始化
                if(allvews.get(times[i][1])==null){
                	allvews.put(times[i][1],i);//加入指向的节点
                	nowvews.put(times[i][1], 9999);
                }
            }
        }
        /**
         * 思路
         * 把几条路走一遍，选最短的路径和他的节点，同时还要和已经存在的节点做对比。
         */
        if(allvews.get(K) != null){
        	//输入顶点然后输出最短路径和新顶点
        	getlist(K);
        	for(int value : nowvews.values()){
        		if(value==9999){
        			re=-1;
        			return re;
        		}else{
        			re+=value;
        		}
        	}
        }else{
        	re=-1;
        }
		return re;
        
    }
    public void getlist(int K) {
    	//输入顶点然后输出最短路径和新顶点
    	if (allvews.size()==0) {
		    Integer[] index=vews.get(K);//获取该顶点有几条路
		    int[] minarray=new int[2];
		    minarray[0]=9999;
		    for(int i=0;i<index.length;i++){
		    	int[] time_index=times[index[i]];//单个的情况，三个数值
		    	//到节点的距离
		    	if(minarray[0]>time_index[2]){
		    		minarray[0]=time_index[2];
		    		minarray[1]=time_index[1];//将最短的节点录入
		    	}
		    	int nowtime=nowvews.get(time_index[1]);
		    	if(nowtime>time_index[2]){
		    		nowvews.put(time_index[i],time_index[2]);
		    	}
		    }
		    getlist(minarray[1]);
		    allvews.remove(K);
		}
	}

    
    
    public static void main(String[] args) {
		code2_3 code=new code2_3();
		int[] s1=new int[8];
		s1[0]=9;
		s1[1]=9;
		s1[2]=9;
		s1[3]=9;
		s1[4]=9;
		s1[5]=9;
		s1[6]=9;
		s1[7]=9;
		int k=1;
		System.out.println(code.addToArrayForm(s1, k));
		
	}
}
