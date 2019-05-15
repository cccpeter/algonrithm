package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class code_hash_1_2 {
	/**
	 * 
	 * @param x
	 * @param y
	 * @param bound
	 * @return
	 */
	public static List<Integer> powerfulIntegers(int x, int y, int bound) {
		Set<Integer> set=new HashSet<>();
		if(bound<=1){
			return new LinkedList<Integer>();
		}
		int powI=0,powJ=0;
		for(int i=0;;i++){
			powI=(int)Math.pow(x, i);
			if(powI>=bound){
				break;
			}
			for(int j=0;;j++){
				powJ=(int)Math.pow(y, j);
				int val=powI+powJ;
				if(val>bound){
					break;
				}
				set.add(val);
				if(y==1){
					break;
				}
			}
			if(x==1){
				break;
			}
		}
		
		return new ArrayList<Integer>(set);
    }
	/**
	 * ��ѯ�����ظ��ַ�
	 * @param A
	 * @return
	 */
	public List<String> commonChars(String[] A) {
		List<String> list=new LinkedList<>();
		if(A==null){
			return list;
		}
		for(int i=0;i<A[0].length();i++){
			boolean flag=true;
			String value=String.valueOf(A[0].charAt(i));
			for(int j=1;j<A.length;j++){
				if(A[j].contains(value)){//A[j]������A[i]���ַ�
					A[j]=A[j].replaceFirst(value, "");
				}else{
					flag=false;
					break;
				}
			}
			if(flag){
				list.add(value);
			}
		}
		return list;
    }
	/**
	 * �Ҳ�ͬ���ַ�
	 * �������ַ� s �� t������ֻ��Сд��ĸ���ַ� t ���ַ� s ������ţ�Ȼ�������λ�����һ����ĸ��
	 * ���ҳ��� t �б���ӵ���ĸ��
	 * ��2���ַ�char��ascii��ֵ�����תchar��Ϊ��
	 * @param args
	 */
	public char findTheDifference(String s, String t) {
        char[] s1=s.toCharArray();
        char[] t1=t.toCharArray();
        int sum1=0;
        int sum2=0;
        for(int i=0;i<s1.length;i++){
        	sum1+=s1[i];
        }
        for(int i=0;i<t1.length;i++){
        	sum2+=t1[i];
        }
        return (char) (sum2-sum1);
    }
	/**
	 * �ڽ�hashmap
	 * hashcode�������±꼴��
	 * @param args
	 */
	int[] h=new int[1000001];
	public void init(){
		Arrays.fill(h,-1);//������h�������ֵ-1��ȥ
	}
	public void put(int key, int value) {
        
    }
	public int hash(Object object){
		return object.hashCode();
	}
	/**
	 * �ҳ��ж��ٿ鱦ʯ
	 * �ַ�Ƚ�
	 * @param J
	 * @param S
	 * @return
	 */
	public int numJewelsInStones(String J, String S) {
		Set<Character> sj=new HashSet();
		int nums=0;
		char[] j1=J.toCharArray();
		char[] s1=S.toCharArray();
		for(int i=0;i<j1.length;i++){
			sj.add(j1[i]);
		}
		for(int i=0;i<s1.length;i++){
			if(sj.contains(s1[i])){
				nums++;
			}
		}
        return nums;
    }
	/**
	 * �ҳ��ַ��е�������ĸ��λ��
	 * ������ϻ��߻�������
	 * s������ĳ���
	 * p�ǻ����Ĵ����ص�
	 * @param args
	 */
	public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans=new ArrayList<Integer>();
        if(s.length()==0||p.length()==0||p.length()>s.length()){
        	return ans;
        }
        int[] indexS=new int[26];
        int[] indexP=new int[26];
        int lenP=p.length();
        for(int i=0;i<lenP;i++){
        	indexP[p.charAt(i)-'a']++;
        	indexP[p.charAt(i)-'a']=indexP[p.charAt(i)-'a']+1;
        	System.out.println();
        }
        for(int i=0;i<s.length();i++){
        	indexS[s.charAt(i)-'a']++;
        	if(i==lenP-1){
        		if(Arrays.equals(indexS, indexP)){
        			ans.add(i+1-lenP);
        		}
        	}else if(i>=lenP){
                indexS[s.charAt(i-lenP)-'a']--;
                if(Arrays.equals(indexS, indexP)){
                    ans.add(i+1-lenP);
                }
            }
        }
		return ans;
    }
	/**
	 * �ҳ����Ӣ�ĵ��ʣ��ӵ���Ӣ�ĵ��ʿ�ʼ
	 * ���һ���ַ�����words��ɵ�һ��Ӣ��ʵ䡣�����ҳ����һ�����ʣ�
	 * �õ�������words�ʵ�������������һ����ĸ��ɡ��������ж�����еĴ𰸣�
	 * �򷵻ش����ֵ�����С�ĵ��ʡ�
	 * ���޴𰸣��򷵻ؿ��ַ�
	 * @param words
	 * @return
	 * ִ����ʱ : 27 ms, ��Longest Word in Dictionary��Java�ύ�л�����72.00% ���û�
		�ڴ���� : 37.1 MB, ��Longest Word in Dictionary��Java�ύ�л�����97.69% ���û�
	 */
	public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set=new HashSet<>();
        String res="";
        for(String word:words){
            int len=word.length();
            if(len==1){
                set.add(word);
                res=len>res.length()?word:res;//�ж���֮ǰ�Ǹ����������ڵĳ��������
            }else if(set.contains(word.substring(0,len-1))){
                set.add(word);
                res=len>res.length()?word:res;
            }
        }
        return res;
    }
	/**
	 * ��������Ľ���
	 * @param nums1
	 * @param nums2
	 * @return
	 * ִ����ʱ : 7 ms, ��Intersection of Two Arrays��Java�ύ�л�����69.07% ���û�
	 * �ڴ���� : 36.6 MB, ��Intersection of Two Arrays��Java�ύ�л�����52.30% ���û�
	 * 3��set�ȽϺĿռ�
	 */
	static public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set=new HashSet<>();
        HashSet<Integer> setRe=new HashSet<>();
		HashSet<Integer> numsSet=new HashSet<>();
		int re=0;
		for(int i=0;i<nums1.length;i++){
			numsSet.add(nums1[i]);
		}
		for(int i=0;i<nums2.length;i++){
			if(numsSet.contains(nums2[i])&&!setRe.contains(nums2[i])){
				setRe.add(nums2[i]);
				++re;
			}
		}
		int[] re1=new int[setRe.size()];
        int i=0;
        for(Integer n:setRe){
        	re1[i]=n;
        	i++;
        }
		return re1;
    }
	/**
	 * Ѱ�Ҳ�����ĵ���
	 * �������ַ�����һ��Ȼ��ָ����������
	 * ���������� A �� B �� ��������һ���ɿո�ָ��ĵ��ʡ�ÿ�����ʽ���Сд��ĸ��ɡ���
	 * ���һ������������һ��������ֻ����һ�Σ�����һ��������ȴû�г���
	 * ����ô������ʾ��ǲ�����ġ�
	 * �������в����õ��ʵ��б?
	 * ����԰��κ�˳�򷵻��б?
	 * @param A
	 * @param B
	 * @return
	 * ִ����ʱ : 6 ms, ��Uncommon Words from Two Sentences��Java�ύ�л�����92.26% ���û�
	 * �ڴ���� : 35.1 MB, ��Uncommon Words from Two Sentences��Java�ύ�л�����94.94% ���û�
	 */
	/**
	 * ���ǹ�
	 * ����һλ�ǳ�2
	 * @param candies
	 * @return
	 */
	public int distributeCandies(int[] candies) {
		int len=candies.length>>1;
		
		return 0;
        
    }
	public static void main(String[] args) {
		int[] n1=new int[3];
		n1[0]=4;
		n1[1]=9;
		n1[2]=5;
		int[] n2=new int[5];
		n2[0]=9;
		n2[1]=4;
		n2[2]=9;
		n2[3]=8;
		n2[4]=4;
		int[] re=new int[5];
		re=intersection(n1,n2);
		for(int i=0;i<re.length;i++){
			System.out.println(re[i]);
		}
//		Integer a=129,b=129;
//		int c=129;
//		System.out.println(a==b);
//		System.out.println(a==c);
//		String s1 = "Programming";
//        String s2 = new String("Programming");
//        String s3 = "Program" + "ming";
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println(s1 == s1.intern());
	}
}
