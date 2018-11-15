package code1;

public class mergesort {
	public static void mergeSort(int[] arr){//入口函数
		if(arr==null||arr.length<2){
			return;
		}
		sortProcess(arr,0,arr.length-1);
	}
	public static void sortProcess(int[] arr,int L,int R){
		if(L==R){
			return;
		}
		int mid=L+((R-L)>>1);//L和R中点的位置,位运算：（R-L）/2就相当于（R-L）右移，乘2就左移一位。位运算肯定比算数快
		sortProcess(arr, L, mid);//排左边
		sortProcess(arr,mid+1,R);//排右边
		merge(arr,L,mid,R);//合并俩数组
	}
	public static void merge(int[] arr,int l,int m,int r){
		int[] help=new int[r-l+1];
		int i=0;
		int p1=l;
		int p2=m+1;
		while(p1<=m&&p2<=r){//将左右子数组合并到help
			help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
		}
		while (p2<=r) {//左边提前边越界，右边还没有遍历完
			help[i++]=arr[p2++];
		}
		while (p1<=m) {//右边提前越界，左边还没有遍历完
			help[i++]=arr[p1++];
		}
		for(i=0;i<help.length;i++){
			arr[l+i]=help[i];
		}
		for(int x=0;x<arr.length;x++){
			System.out.println(arr[x]);
		}
		}
	public static void main(String[] args) {
		int[] arr={2,4,32,63,632,2};
		mergeSort(arr);
	}
}
