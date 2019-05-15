package code;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
public class code2_3 {

	/**
	 * N �����������������е� 2N ����λ�ϣ���Ҫǣ���Է����֡� �������ٽ�����λ�Ĵ����Ա�ÿ�����¿��Բ�������һ�� һ�ν�����ѡ���������ˣ�������վ����������λ��

�˺���λ�� 0 �� 2N-1 �������ʾ�������ǰ�˳���ţ���һ���� (0, 1)���ڶ����� (2, 3)���Դ����ƣ����һ���� (2N-2, 2N-1)��

��Щ���µĳ�ʼ��λ  row[i] �������ʼ���ڵ� i ����λ�ϵ��˾����ġ�

ʾ�� 1:

����: row = [0, 2, 1, 3]
���: 1
����: ����ֻ��Ҫ����row[1]��row[2]��λ�ü��ɡ�
ʾ�� 2:

����: row = [3, 2, 0, 1]
���: 0
����: ���轻����λ�����е����¶��Ѿ�������ǣ���ˡ�
˵��:

len(row) ��ż������ֵ�� [4, 60]��Χ�ڡ�
���Ա�֤row ������ 0...len(row)-1 ��һ��ȫ���С�
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
    public int couple(int a) {//��������λǰ�����λ
        return (a % 2 == 0) ? a + 1 : a - 1;
    }
    
    /**
     * 
     */
    public int maxProduct(String[] words) {
        //�ж����������Ƿ��й�����ĸ
        int wordslen=words.length;
        int max=0;
        for(int i=0;i<wordslen;i++){
            for(int j=i+1;j<wordslen;j++){
                //�ж��Ƿ��й����ַ�
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
                	System.out.println("������ͬ�ַ�---"+word1+"++++"+word2+"****"+word1list+"*****"+word2list[j]);
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
     * ���·��
     * @param args
     */
    HashMap<Integer, Integer[]> vews = new HashMap<Integer, Integer[]>();//���ж���
    HashMap<Integer, Integer> allvews = new HashMap<Integer, Integer>();//���нڵ�
    HashMap<Integer, Integer> nowvews = new HashMap<Integer, Integer>();//��ǰ���ж���ĵ���·��
    int[][] times;
    public int networkDelayTime(int[][] times, int N, int K) {
    	this.times=times;
        int edgnum=times.length;//�ߵ�����
        
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
                allvews.put(times[i][0], i);//���붥��
                nowvews.put(times[i][0], 9999);//�������нڵ�ľ����ʼ��
                if(allvews.get(times[i][1])==null){
                	allvews.put(times[i][1],i);//����ָ��Ľڵ�
                	nowvews.put(times[i][1], 9999);
                }
            }
        }
        /**
         * ˼·
         * �Ѽ���·��һ�飬ѡ��̵�·������Ľڵ㣬ͬʱ��Ҫ���Ѿ����ڵĽڵ����Աȡ�
         */
        if(allvews.get(K) != null){
        	//���붥��Ȼ��������·�����¶���
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
    	//���붥��Ȼ��������·�����¶���
    	if (allvews.size()==0) {
		    Integer[] index=vews.get(K);//��ȡ�ö����м���·
		    int[] minarray=new int[2];
		    minarray[0]=9999;
		    for(int i=0;i<index.length;i++){
		    	int[] time_index=times[index[i]];//����������������ֵ
		    	//���ڵ�ľ���
		    	if(minarray[0]>time_index[2]){
		    		minarray[0]=time_index[2];
		    		minarray[1]=time_index[1];//����̵Ľڵ�¼��
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
