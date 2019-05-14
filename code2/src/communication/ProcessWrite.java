package communication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.RandomAccess;

/**
 * Nio共享内存
 * @author User
 *本块为写进程
 */
public class ProcessWrite {
/**
 * 采用MappedByteBuffer内存映射文件来做内存共享（磁盘内存映射，内存与磁盘内容一致）
 * MappedByteBuffer来读取写入大文件的效率极高
 */
	//Nio写的进程
	private static RandomAccessFile raf;
	public static void main(String[] args) throws IOException, InterruptedException {
		//建立文件和内存映射，即时同步
		raf=new RandomAccessFile("D://tmp/data.bat", "rw");
		FileChannel fc=raf.getChannel();
		MappedByteBuffer mbb=fc.map(MapMode.READ_WRITE, 0,1024);
		//清除文件内容 ，对 MappedByteBuffer 的操作就是对文件的操作
		for(int i=0;i<1024;i++){
			mbb.put(i,(byte)0);
		}
		for(int i=65;i<91;i++){//写入A-Z
			int index=i-63;
			int flag=mbb.get(0);//第0个为标志位，获取标志位为0就可以写
			if(flag!=0){//不可以写，（读进程需要第一个你才能写一个，类似锁）
				i--;
				System.out.println("写不行");
				continue;
			}
			//写数据了
			mbb.put(0, (byte)1);//第0个位置，标志位置1，设置标志位为1，代表开始写数据
			mbb.put(1, (byte)(index));//第1个写入当前操作到第几个index了
			System.out.println(System.currentTimeMillis() +  ":position:" + index +"write:" + (char)i);
			
			mbb.put(index, (byte)i);//写入字母
			
			mbb.put(0, (byte)2);//将标志位设置为2，代表可读
//			Thread.sleep(1000);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

