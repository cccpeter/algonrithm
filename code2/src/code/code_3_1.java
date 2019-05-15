package code;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class code_3_1 extends ClassLoader{
	/**
	 * �����������������
	 */
	private String name;
	private String path="D:\\";
	private final String fileType=".class";
	
	public code_3_1(String name){
		super();//�����������Ϊ�ü������ĸ�������
		this.name=name;
	}
	public code_3_1(ClassLoader parent,String name){
		super(parent);
		this.name=name;
	}
	/**
	 * ��ļ��ػ���
	 * ��ļ����������������ɵģ�
	 * ����������������������BootStrap������չ��������Extension����ϵͳ��������System�����û��Զ������������java.lang.ClassLoader�����ࣩ��
	 * ��Java 2��JDK 1.2����ʼ������ع�̲�ȡ�˸���ί�л��ƣ�PDM����PDM��õı�֤��Javaƽ̨�İ�ȫ�ԣ��ڸû����У�JVM�Դ��Bootstrap�Ǹ��������
	 * ����ļ����������ҽ���һ���������������ļ���������������������أ��������������Ϊ��ʱ������������������м��ء�
	 * JVM������Java�����ṩ��Bootstrap�����á������ǹ��ڼ������������˵����
		Bootstrap��һ���ñ��ش���ʵ�֣��������JVM�������⣨rt.jar����
		Extension����java.ext.dirsϵͳ������ָ����Ŀ¼�м�����⣬��ĸ���������Bootstrap��
		System���ֽ�Ӧ������������丸����Extension������Ӧ����㷺�����������
		��ӻ�������classpath����ϵͳ����java.class.path��ָ����Ŀ¼�м����࣬���û��Զ����������Ĭ�ϸ���������
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

        code_3_1 loader3=new code_3_1(null,"loader3");//nullָ��������������غ������

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
	 * ���ص������
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
		class�ļ���·���� 
		serverlib�ļ��£�Dog.class��Sample.class 
		clientlib�ļ�����û���ļ� 
		otherlib�ļ����£�Dog.class��Sample.class 
		syslib�ļ����£�MyClassLoader.class
	 */

}
