package designpatterns;
/**
 * 1、饿汉模式：一开始就创建一个类，然后直接返回单例（消耗资源快）
 * 2、懒汉非线程安全模式：先声明类，然后判断为空不，然后再返回单例（线程不安全）
 * 3、懒汉安全模式：声明类，然后用synchronize修饰判断为空的方法（线程安全，但是耗费类同步锁的资源）
 * 4、双重检查模式：下面这种模式，用Volatile修饰声明的单例类，用锁住代码块synchronized (Singleton.class)
 * 再进行判断单例是否为空，为空再将其初始化并返回（初次反应慢，避免资源消耗和多余同步Synchronized），但是某些情况
 * 会出现失效，最好使用静态内部类单例模式。
 * 5、静态内部类的单例模式（C#也存在这种规则写法）：第一次加载Singleton类时并不会初始化sInstance，
 * 只有第一次调用getInstance方法时虚拟机加载SingletonHolder 并初始化sInstance ，
 * 这样不仅能确保线程安全也能保证Singleton类的唯一性，所以推荐使用静态内部类单例模式。
 */
/**
 * 双重检查模式（DCL）
 * 
 * @author apple
 *
 */
/**
 * 原子操作
 * java.util.concurrent.atomic包中有很多类使用了很高效的机器级指令（而不是使用锁）来保证其他操作的原子性。
 * 例如AtomicInteger类提供了方法incrementAndGet和decrementAndGet，
 * 它们分别以原子方式将一个整数自增和自减。
 * 可以安全地使用AtomicInteger类作为共享计数器而无需同步。 
 * @author apple
 *
 */
public class Singleton {
	//1、保证了不同线程对这个变量进行操作时的可见性，
	//即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
	//2、禁止进行指令重排序。
	//但是volatile（[ˈvɑːlətl] va 乐 错）也无法保证对变量的任何操作都是原子性的，所以需要配合synchronized（[ˈsɪŋkrənaɪzd] 辛亏耐）。
	private volatile static Singleton instance;
	private static int count;
	//声明构造器
	private Singleton(){
		System.out.println("创建"+count+"次");
	}
	public static Singleton getInstance() {
		if(instance==null){
			synchronized (Singleton.class) {
				//
				if(instance==null){
					instance=new Singleton();
				}
			}
		}
		return instance;
	}
	//静态内部类单例模式
	public static Singleton getInstance1(){
		return SingletonHolder.sInstance;
	}
	private static class SingletonHolder{
		private static final Singleton sInstance=new Singleton();
	}
}

