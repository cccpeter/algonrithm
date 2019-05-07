package code2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	public static void main(String[] args) {
		String[] re=new String[1];
		re[0]="9001 discuss.leetcode.com";
		List<String> result=subdomainVisits(re);
		for (String string : result) {
			System.out.println(string);
		}
	}
}
