package communication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.RandomAccess;

/**
 * Nio�����ڴ�
 * @author User
 *����Ϊд����
 */
public class ProcessWrite {
/**
 * ����MappedByteBuffer�ڴ�ӳ���ļ������ڴ湲�������ڴ�ӳ�䣬�ڴ����������һ�£�
 * MappedByteBuffer����ȡд����ļ���Ч�ʼ���
 */
	//Nioд�Ľ���
	private static RandomAccessFile raf;
	public static void main(String[] args) throws IOException, InterruptedException {
		//�����ļ����ڴ�ӳ�䣬��ʱͬ��
		raf=new RandomAccessFile("D://tmp/data.bat", "rw");
		FileChannel fc=raf.getChannel();
		MappedByteBuffer mbb=fc.map(MapMode.READ_WRITE, 0,1024);
		//����ļ����� ���� MappedByteBuffer �Ĳ������Ƕ��ļ��Ĳ���
		for(int i=0;i<1024;i++){
			mbb.put(i,(byte)0);
		}
		for(int i=65;i<91;i++){//д��A-Z
			int index=i-63;
			int flag=mbb.get(0);//��0��Ϊ��־λ����ȡ��־λΪ0�Ϳ���д
			if(flag!=0){//������д������������Ҫ��һ�������дһ������������
				i--;
				System.out.println("д����");
				continue;
			}
			//д������
			mbb.put(0, (byte)1);//��0��λ�ã���־λ��1�����ñ�־λΪ1������ʼд����
			mbb.put(1, (byte)(index));//��1��д�뵱ǰ�������ڼ���index��
			System.out.println(System.currentTimeMillis() +  ":position:" + index +"write:" + (char)i);
			
			mbb.put(index, (byte)i);//д����ĸ
			
			mbb.put(0, (byte)2);//����־λ����Ϊ2������ɶ�
//			Thread.sleep(1000);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

