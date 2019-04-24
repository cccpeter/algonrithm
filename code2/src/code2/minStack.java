package code2;

class minStack {

    /** initialize your data structure here. */
    static int[] numStack=new int[10];
    static int nums;
    public minStack() {
        numStack[0]=0;
        nums=1;
    }
    
    public void push(int x) {
        if(numStack.length/2>nums){
            numStack=addLengthArray(numStack);
        }
        if(x<numStack[0]){
            numStack[0]=x;
        }
        numStack[nums]=x;
        nums++;
    }
    
    public void pop() {
        
        if(nums==numStack[nums]){
            for(int i=1;i<nums-1;i++){
                if(numStack[i]<numStack[0]){
                    numStack[0]=numStack[i];
                }
            }
        }
//        System.out.println(nums+"去掉栈顶获得栈顶的数组下标1");
        minStack.nums=nums-1;
//        System.out.println(nums+"去掉栈顶获得栈顶的数组下标2");
        //删除要确认最小元素是否发生改变
    }
    
    public int top() {
//    	System.out.println(nums+"获得栈顶的数组下标");
//    	System.out.println(numStack[2]);
//    	System.out.println(numStack[1]);
        return numStack[nums];
    }
    
    public int getMin() {
        return numStack[0];
    }
    public static int[] addLengthArray(int[] array){
		int[] newArray = new int [array.length*2];
                //将array数组从0位置至array.length位置，复制到newArray数组0位置到array.length位置。
                System.arraycopy(array,0,newArray,0,array.length);
		return newArray;
	}
    public static void main(String[] args) {
    	 minStack obj = new minStack();
    	 obj.push(-3);
    	 obj.push(-2);
    	 obj.push(0);
    	 obj.push(-5);
    	 obj.pop();
    	 int param_3 = obj.top();//为-2才对
    	 int param_4 = obj.getMin();
    	 System.out.println(param_3);
    	 System.out.println(param_4);
    	 System.out.println(numStack[nums-1]);
    	 
	}
}