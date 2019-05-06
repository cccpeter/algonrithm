package code2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class code_3_1 extends ClassLoader{
	/**
	 * 定义类加载器的名字
	 */
	private String name;
	private String path="D:\\";
	private final String fileType=".class";
	
	public code_3_1(String name){
		super();//让类加载器成为该加载器的父加载器
		this.name=name;
	}
	public code_3_1(ClassLoader parent,String name){
		super(parent);
		this.name=name;
	}
	/**
	 * 类的加载机制
	 * 类的加载是由类加载器完成的，
	 * 类加载器包括：根加载器（BootStrap）、扩展加载器（Extension）、系统加载器（System）和用户自定义类加载器（java.lang.ClassLoader的子类）。
	 * 从Java 2（JDK 1.2）开始，类加载过程采取了父亲委托机制（PDM）。PDM更好的保证了Java平台的安全性，在该机制中，JVM自带的Bootstrap是根加载器，
	 * 其他的加载器都有且仅有一个父类加载器。类的加载首先请求父类加载器加载，父类加载器无能为力时才由其子类加载器自行加载。
	 * JVM不会向Java程序提供对Bootstrap的引用。下面是关于几个类加载器的说明：
		Bootstrap：一般用本地代码实现，负责加载JVM基础核心类库（rt.jar）；
		Extension：从java.ext.dirs系统属性所指定的目录中加载类库，它的父加载器是Bootstrap；
		System：又叫应用类加载器，其父类是Extension。它是应用最广泛的类加载器。
		它从环境变量classpath或者系统属性java.class.path所指定的目录中记载类，是用户自定义加载器的默认父加载器。
	 * @param args
	 */
	@Override
    public String toString() {
          return this.name;
    }
    public String getPath() {
          return path;
    }
    public void setPath(String path) {
          this.path = path;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
          // TODO Auto-generated method stub
          byte[] data=loadClassData(name);
          return this.defineClass(name, data, 0,data.length);
    }
    private byte[] loadClassData(String name){
          InputStream is=null;
          byte[] data =null;
          ByteArrayOutputStream baos=null;
          this.name=this.name.replace(".", "\\");
          try {
                is=new FileInputStream(new File(path+name+fileType));
                baos=new ByteArrayOutputStream();
                int ch=0;
                while ((ch=is.read())!=-1) {
                      baos.write(ch);
                }
                data=baos.toByteArray();
          } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
          }finally {
                try {
                      is.close();
                      baos.close();
                } catch (IOException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                }
          }
          return data;
    }
	public static void main(String[] args) {
		code_3_1 loader1=new code_3_1("loader1");

        loader1.setPath("d:\\myapp\\serverlib\\");

        code_3_1 loader2=new code_3_1(loader1,"loader2");

        loader2.setPath("d:\\myapp\\clientlib\\");

        code_3_1 loader3=new code_3_1(null,"loader3");//null指根类加载器，加载核心类库

        loader3.setPath("d:\\myapp\\otherlib\\");

        try {
			test(loader2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			test(loader3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test(ClassLoader loader) throws Exception{
        Class clazz=loader.loadClass("com.jvm.classloader.Sample");

        Object object=clazz.newInstance();
  }
	/**
	 * 加载的类代码
	 * public class Sample {
	      public int v1=1;
	
	      public Sample(){
	            System.err.println("Sample is loaded by : "+ this.getClass().getClassLoader());
	          new Dog();
	      }
		}
		public class Dog {
		      public Dog(){
		            System.err.println("Dog is loaded by : "+ this.getClass().getClassLoader());
		      }
		}
		class文件的路径： 
		serverlib文件下：Dog.class，Sample.class 
		clientlib文件夹下没有文件 
		otherlib文件夹下：Dog.class，Sample.class 
		syslib文件夹下：MyClassLoader.class
	 */

}
