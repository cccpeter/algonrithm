package code2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

import javax.xml.crypto.Data;

public class SocketReceiver {
	public static String host = "127.0.0.1";
	public static void main(String[] args) {
 
		System.out.println("welcome to SocketReceiver");
 
		try {
			InetAddress address = InetAddress.getByName(host);
			int port = 15014;
 
			// 创建用于接收信息的socket
			DatagramSocket socketRecv = new DatagramSocket(port, address);
			byte buf[] = new byte[1024];
			DatagramPacket recvPacket = new DatagramPacket(buf, buf.length);
			while(true){
			socketRecv.receive(recvPacket);
			String recvMsg = new String(buf, 0, recvPacket.getLength());
			System.out.println("in the SocketRecviver.java----the recvMsg is : " + recvMsg);
			System.out.println("转化为字符串");
			System.out.println(SocketReceiver.toStringHex2(recvMsg));
			
			InetAddress clientAddress = recvPacket.getAddress();
			 int clientPort = recvPacket.getPort();
			System.out.println("对方的IP和port是： " + clientAddress + "   " + clientPort);  
			// 向发送方反馈信息
			 
			SocketAddress socketAddress = recvPacket.getSocketAddress();
			String returnMsg = "Hello, I have recvived the msg";
			byte[] returnBuf = returnMsg.getBytes();
			DatagramPacket returnPacket = new DatagramPacket(returnBuf, returnBuf.length, socketAddress);
			socketRecv.send(returnPacket);
			}
		} catch (Exception e) {
		}
		
	}
	// 转化十六进制编码为字符串
	public static String toStringHex2(String s) {
	   byte[] baKeyword = new byte[s.length() / 2];
	   for (int i = 0; i < baKeyword.length; i++) {
	    try {
	     baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
	       i * 2, i * 2 + 2), 16));
	    } catch (Exception e) {
	     e.printStackTrace();
	    }
	   }
	   try {
	    s = new String(baKeyword, "utf-8");// UTF-16le:Not
	   } catch (Exception e1) {
	    e1.printStackTrace();
	   }
	   return s;
	}
}
