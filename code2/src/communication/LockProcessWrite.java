package communication;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

public class LockProcessWrite {
	 private static RandomAccessFile raf;
	    public static void main(String[] args) throws Exception {
	        //获取随机存取文件对象，建立文件和内存的映射，即时双向同步
	        raf = new RandomAccessFile("D:/tmp/data.dat", "rw");
	        FileChannel fc = raf.getChannel();      //获取文件通道
	        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, 1024);  //获取共享内存缓冲区
	        FileLock flock=null; 

	        for(int i=65;i<91;i++){
	            //阻塞独占锁，当文件锁不可用时，当前进程会被挂起      
	            flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + (char)i);
	            mbb.put(i-65,(byte)i);  //从文件第一个字节位置开始写入数据    
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	        }

	    }
}
