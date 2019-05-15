package code;

import java.util.HashSet;
import java.util.Set;

public class code2_1 {
	public int lengthOfLongestSubstring(String s) {
	     int l=s.length();
	     int arr=0;
	     for (int i = 0; i < l; i++) {
			for(int j=i+1;j<= l;j++){//��ʼ�ַ�Ϊi+1ֱ���յ�
				int num=getsonstring(s, i, j);
				arr=(arr<num)?num:arr;
			}
		}
	     return arr;
	    }
		public int getsonstring(String s,int i,int j) {
			int num=0;
			Set<Character> set=new HashSet<>();
			for(int n=i;n<j;n++){
				Character ch=s.charAt(n);
				if(set.contains(ch))return 0;
				set.add(ch);
			}
			return j-i;
		}
}
