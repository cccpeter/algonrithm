package code2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class code_4_1 {
	/**
	 * ���������ʼ���
	 * ���ֵ���������
	 * ִ����ʱ : 40 ms, ��Subdomain Visit Count��Java�ύ�л�����32.29% ���û�
		�ڴ����� : 48 MB, ��Subdomain Visit Count��Java�ύ�л�����75.96% ���û�
	 * @param cpdomains
	 * @return
	 */
	static public List<String> subdomainVisits(String[] cpdomains) {
		HashMap<String, Integer> map=new HashMap<>();
        for(int i=0;i<cpdomains.length;i++){
        	String[] cpd0main=cpdomains[i].split(" ");
        	String[] cpd0=cpd0main[1].split("\\.");//��Ҫת�壬����Ҫע��
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
