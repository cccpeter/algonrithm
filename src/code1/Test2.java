package code1;

public class Test2 {
	public static int getMax(int[] arr,int L,int R){
		if (L == R){
			return arr[L];
		}
		int mid=L+(R-L)/2;//(L+R)/2此处可能溢出
		int maxLeft=getMax(arr, L, mid);
		int maxRight=getMax(arr, mid+1, R);
		return Math.max(maxLeft, maxRight);
	}
	public static void main(String[] args) {
		int[] arr={3,4,5,7};
		int max=getMax(arr, 0,arr.length-1);
		System.out.println(max);
	}
}
