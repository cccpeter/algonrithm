package communication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Map;

/**
 * Nio读的进程
 * @author User
 *
 */

public class ProcessRead {
	private static RandomAccessFile raf;
	public static void main(String[] args) throws IOException {
		raf=new RandomAccessFile("D://tmp/data.bat", "rw");
		FileChannel fc=raf.getChannel();
		MappedByteBuffer mbb=fc.map(MapMode.READ_WRITE, 0, 1024);
		int lastIndex=0;
		for(int i=0;i<27;i++){
			int flag=mbb.get(0);
			int index=mbb.get(1);
			if(flag!=2||index==lastIndex){
				i--;
				System.out.println("不行");
				continue;
			}
			lastIndex=index;
			System.out.println( System.currentTimeMillis() +  ":position:" + index +"read:" + (char)mbb.get(index));
			mbb.put(0,(byte)0);
			if(index==27){
				break;
			}
		}
		
	}
}
