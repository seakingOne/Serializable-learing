package com.ynhuang.old;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ynhuang.Student;

public class SerializationUtil {
	
	public static void main(String[] args) throws Exception {
		Student student = new Student();
		student.setMan(true);
		student.setStuId(1);
		student.setStuName("ynhuang");
		student.setStuAge(22);
		//serialize(student,"test.txt");
		//System.out.println(str);
	 	Student student1 =  (Student) deserialize("test.txt");
	 	System.out.println(student.getStuAge());

	}

	/**
	 * 反序列化
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Object deserialize(String fileName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}

	/**
	 * 序列化
	 * 
	 * @param obj
	 * @param fileName
	 * @throws Exception
	 */
	public static void serialize(Object obj, String fileName) throws Exception {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		fos.close();
	}
}
