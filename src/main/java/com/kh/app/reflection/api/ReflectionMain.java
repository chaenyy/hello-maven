package com.kh.app.reflection.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * reflect 투영/반사하다
 * 
 * - 클래스객체를 통해서 클래스 정보를 열람. 객체 생성, 메소드 호출, 필드값제어 처리
 *
 */
public class ReflectionMain {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		ReflectionMain main = new ReflectionMain();
		//main.test1();
		//main.test2();
		main.test3();
	}

	/**
	 * 필드제어
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchFieldException 
	 */
	private void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		Class<Sample> clz = (Class<Sample>) Class.forName("com.kh.app.reflection.api.Sample");
		Sample sample = clz.getDeclaredConstructor(int.class, String.class).newInstance(123, "Wow");
		Field num = clz.getDeclaredField("num");	// private에 접근가능!
		System.out.println(num);
		
		num.setAccessible(true);	// private 필드도 접근 가능하도록 설정
		
		Object value = num.get(sample);	// private필드이므로 직접 접근하려고 하니 IllegalAccessException 발생 (setAccessible을 true로 설정해줘야함)
		System.out.println(value);
		
		System.out.println(sample);
	}

	/**
	 * 메소드 제어
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void test2() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<Sample> clz = Sample.class;
		Method[] methods = clz.getDeclaredMethods();
		for(Method method : methods) {
			System.out.println(method);
		}
		
		// 하나의 메소드 가져오기
		Sample sample = clz.getDeclaredConstructor(null).newInstance(null);
		Method setNum = clz.getDeclaredMethod("setNum", int.class);	// 오버로딩으로 인한 메소드 구별을 위해 매개인자 타입을 지정
		Object returnValue = setNum.invoke(sample, 123);			// 대상객체, 전달할 인자 (sample객체의 setNum에 123을 전달하여 호출!)
		System.out.println(returnValue);							// void타입의 returnValue는 null!
		
		// getNum 메소드 호출
		Method getNum = clz.getDeclaredMethod("getNum", null);		// 전달할 인자가 없으므로 null
		Object returnValue2 = getNum.invoke(sample);				// invoke -> 호출!
		System.out.println(returnValue2);

		System.out.println(sample);
	}

	/**
	 * 객체생성
	 * 
	 * 클래스객체
	 * - 클래스 당 하나씩 만들어지는 객체로 클래스의 모든 정보를 가지고 있다.
	 * - new 연산자 호출 시에도 이 클래스 객체를 베이스로 객체 생성
	 * 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void test1() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Sample s1 = new Sample();
		
		Class clz1 = Sample.class;
		Class clz2 = s1.getClass();
		Class clz3 = Class.forName("com.kh.app.reflection.api.Sample");
		
		System.out.println(clz1 == clz2); // true
		System.out.println(clz1 == clz3); // true
		
		// 기본생성자
		Constructor<Sample> const1 = clz1.getDeclaredConstructor(null);
		Sample s2 = const1.newInstance(null); // 객체 생성
		System.out.println(s2); // Sample [num=0, str=null]
		
		// 파라미터 있는 생성자
		Class[] parameterTypes = {int.class, String.class};
		Constructor<Sample> const2 = clz1.getDeclaredConstructor(parameterTypes); // int와 String 받는 생성자 의미
		Object[] initArgs = {100, "Hello world"};
		Sample s3 = const2.newInstance(initArgs);
		System.out.println(s3); // Sample [num=100, str=Hello world]
	}
}
