package communication;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

public class LockProcessWrite {
	 private static RandomAccessFile raf;
	    public static void main(String[] args) throws Exception {
	        //��ȡ�����ȡ�ļ����󣬽����ļ����ڴ��ӳ�䣬��ʱ˫��ͬ��
	        raf = new RandomAccessFile("D:/tmp/data.dat", "rw");
	        FileChannel fc = raf.getChannel();      //��ȡ�ļ�ͨ��
	        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, 1024);  //��ȡ�����ڴ滺����
	        FileLock flock=null; 

	        for(int i=65;i<91;i++){
	            //������ռ�������ļ���������ʱ����ǰ���̻ᱻ����      
	            flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + (char)i);
	            mbb.put(i-65,(byte)i);  //���ļ���һ���ֽ�λ�ÿ�ʼд������    
	            flock.release();        //�ͷ���  
	            Thread.sleep(1000);
	        }

	    }
}
