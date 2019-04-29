package code2;

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
	 * 查询常用重复字符
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
				if(A[j].contains(value)){//A[j]包括了A[i]的字符
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
	 * 找不同的字符
	 * 给定两个字符串 s 和 t，它们只包含小写字母。字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
	 * 请找出在 t 中被添加的字母。
	 * 用2个字符串char的ascii的值做差，再转char即为答案
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
	 * 内建hashmap
	 * hashcode用数组下标即可
	 * @param args
	 */
	int[] h=new int[1000001];
	public void init(){
		Arrays.fill(h,-1);//将数组h填充所有值-1进去
	}
	public void put(int key, int value) {
        
    }
	public int hash(Object object){
		return object.hashCode();
	}
	/**
	 * 找出有多少块宝石
	 * 字符比较
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
	 * 找出字符串中的所有字母异位词
	 * 排列组合或者滑动窗口
	 * s是输入的长条
	 * p是滑动的窗口重点
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
	 * 找出最长的英文单词，从单个英文单词开始
	 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，
	 * 该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，
	 * 则返回答案中字典序最小的单词。
	 * 若无答案，则返回空字符串。
	 * @param words
	 * @return
	 * 执行用时 : 27 ms, 在Longest Word in Dictionary的Java提交中击败了72.00% 的用户
		内存消耗 : 37.1 MB, 在Longest Word in Dictionary的Java提交中击败了97.69% 的用户
	 */
	public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set=new HashSet<>();
        String res="";
        for(String word:words){
            int len=word.length();
            if(len==1){
                set.add(word);
                res=len>res.length()?word:res;//判断是之前那个长还是现在的长，会出现
            }else if(set.contains(word.substring(0,len-1))){
                set.add(word);
                res=len>res.length()?word:res;
            }
        }
        return res;
    }
	/**
	 * 两个数组的交集
	 * @param nums1
	 * @param nums2
	 * @return
	 * 执行用时 : 7 ms, 在Intersection of Two Arrays的Java提交中击败了69.07% 的用户
	 * 内存消耗 : 36.6 MB, 在Intersection of Two Arrays的Java提交中击败了52.30% 的用户
	 * 3个set比较耗空间
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
	 * 寻找不常见的单词
	 * 将两个字符串连在一起然后分割做计数就行
	 * 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
	 * 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现
	 * ，那么这个单词就是不常见的。
	 * 返回所有不常用单词的列表。
	 * 您可以按任何顺序返回列表。
	 * @param A
	 * @param B
	 * @return
	 * 执行用时 : 6 ms, 在Uncommon Words from Two Sentences的Java提交中击败了92.26% 的用户
	 * 内存消耗 : 35.1 MB, 在Uncommon Words from Two Sentences的Java提交中击败了94.94% 的用户
	 */
	/**
	 * 分糖果
	 * 右移一位是除2
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
