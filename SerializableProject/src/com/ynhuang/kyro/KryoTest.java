package com.ynhuang.kyro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.ynhuang.Student;

/**
 * 
 * @author ynhuang
 *
 */
public class KryoTest {

	private static Kryo kryo = new Kryo();

	public static void main(String[] args) throws Exception {
		Student student = new Student();
		student.setMan(true);
		student.setStuId(1);
		student.setStuName("ynhuang");
		student.setStuAge(22);

		byte[] b = setSerializableObject1(student);
		Student stu = getSerializableObject1(b, new Student());
		System.out.println(stu.getStuAge());

	}

	/**
	 * 序列化操作
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static <T> byte[] setSerializableObject1(T object) {

		kryo.setReferences(false);
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
		kryo.register(object.getClass());
		try {
			// Output output = new Output(new FileOutputStream("1.txt"));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			Output output = new Output(byteArrayOutputStream);
			kryo.writeObject(output, object);
			output.flush();
			output.close();
			// 流转换为byte数组
			byte[] b = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream.flush();
			byteArrayOutputStream.close();
			return b;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 反序列化
	 * 
	 * @param b
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSerializableObject1(byte[] b, T object) {

		kryo.setReferences(false);
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
		Input input;
		try {
			//input = new Input(new FileInputStream("1.txt"));
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			input = new Input(bais);
			object = (T) kryo.readObject(input, Student.class);
			input.close();
			bais.close();

		} catch (KryoException | IOException e) {

		}
		return object;
	}
}
