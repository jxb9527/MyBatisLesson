package cn.et.lesson5.xml;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class JavaRedis {
	
	/**
	 * 序列化
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] objectToByteArray(Object obj) throws IOException{
		ByteOutputStream bos=new ByteOutputStream();
		ObjectOutputStream oos =new ObjectOutputStream(bos);
		oos.writeObject(obj);
		return bos.getBytes();
		
	}
	
	/**
	 * 反序列化字节数组为对象
	 * @param bt
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object byteArrayToObject(byte[] bt) throws IOException, ClassNotFoundException{
		
		ByteInputStream bis=new ByteInputStream(bt,0,bt.length);
		ObjectInputStream ois=new ObjectInputStream(bis);
		return ois.readObject();
	}
}
