package designpatterns;

public class Test {
	static int i=0;
	public static void main(String[] args) {
		for(int j=0;j<1000;j++){
			new Thread(){
				@Override
	            public void run() {
	                for(int j=0;j<10;j++){
	                    Singleton.getInstance();
		                ++i;
//		                System.out.println("+++++"+i);
	                }
	            };
	        }.start();
		}
	}
}
