package code;

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
//        System.out.println(nums+"ȥ��ջ�����ջ���������±�1");
        minStack.nums=nums-1;
//        System.out.println(nums+"ȥ��ջ�����ջ���������±�2");
        //ɾ��Ҫȷ����СԪ���Ƿ���ı�
    }
    
    public int top() {
//    	System.out.println(nums+"���ջ���������±�");
//    	System.out.println(numStack[2]);
//    	System.out.println(numStack[1]);
        return numStack[nums];
    }
    
    public int getMin() {
        return numStack[0];
    }
    public static int[] addLengthArray(int[] array){
		int[] newArray = new int [array.length*2];
                //��array�����0λ����array.lengthλ�ã����Ƶ�newArray����0λ�õ�array.lengthλ�á�
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
    	 int param_3 = obj.top();//Ϊ-2�Ŷ�
    	 int param_4 = obj.getMin();
    	 System.out.println(param_3);
    	 System.out.println(param_4);
    	 System.out.println(numStack[nums-1]);
    	 
	}
}