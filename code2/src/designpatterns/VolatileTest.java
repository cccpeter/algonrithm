package designpatterns;

/**
 * volatile是可见性，但本身不具备原子性，可用synchronized来进行原子操作（效率会低点）
 * 下述代码结果每次不一样，但是都是小于10000
 * 
 * 假如某个时刻变量inc的值为10，线程1对变量进行自增操作，线程1先读取了变量inc的原始值，
 * 然后线程1被阻塞了；然后线程2对变量进行自增操作，线程2也去读取变量inc的原始值，
 * 由于线程1只是对变量inc进行读取操作，而没有对变量进行修改操作，
 * 所以不会导致线程2的工作内存中缓存变量inc的缓存行无效，所以线程2会直接去主存读取inc的值，
 * 发现inc的值时10，然后进行加1操作，并把11写入工作内存，最后写入主存。
 * 然后线程1接着进行加1操作，由于已经读取了inc的值，
 * 注意此时在线程1的工作内存中inc的值仍然为10，所以线程1对inc进行加1操作后inc的值为11，
 * 然后将11写入工作内存，最后写入主存。那么两个线程分别进行了一次自增操作后，inc只增加了1。 
 * 自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的。
 * @author apple
 *
 */
public class VolatileTest {
    public volatile int inc = 0;  
    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
         //保证前面的线程都执行完
        while(Thread.activeCount()>1)  
            Thread.yield();
        System.out.println(test.inc);
    }
}