package communication;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

public class LockProcessRead {
	private static RandomAccessFile raf;

    public static void main(String[] args) throws Exception {

        raf = new RandomAccessFile("D:/tmp/data.dat", "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, 1024);
        FileLock flock=null;

        for(int i=0;i<26;i++){
            flock=fc.lock();    //ÉÏËø  
            System.out.println( System.currentTimeMillis() +  ":read:" + (char)mbb.get(i));
            flock.release();    //ÊÍ·ÅËø  
            Thread.sleep(1000);
        }
    }
}
