package com.ynhuang.protostuff;

import com.ynhuang.Student;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;

/**
 * 
 * @author ynhuang
 *
 */
public class ProtoStuffTest {

	public static void main(String[] args) {
		Student student = new Student();
		student.setMan(true);
		student.setStuId(1);
		student.setStuName("ynhuang");
		student.setStuAge(22);
		byte[] b = serializer(student);
		System.out.println(b);
		Student stu1 = deserializer(b, new Student().getClass());
		System.out.println(stu1.getStuName());
	}

	/**
	 * 序列化操作
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> byte[] serializer(T o) {
		@SuppressWarnings("rawtypes")
		RuntimeSchema schema = RuntimeSchema.createFrom(o.getClass());
		return ProtobufIOUtil.toByteArray(o, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
	}

	/**
	 * 反序列化
	 * @param bytes
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserializer(byte[] bytes, Class<T> clazz) {

		T obj = null;
		try {
			@SuppressWarnings("rawtypes")
			RuntimeSchema schema = RuntimeSchema.createFrom(clazz);
			obj = (T) schema.newMessage();
			ProtobufIOUtil.mergeFrom(bytes, obj, schema);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

}
