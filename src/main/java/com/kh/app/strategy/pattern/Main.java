package com.kh.app.strategy.pattern;

/**
 * 
 * 철수는 강아지를 키운다.
 * 영희는 고양이를 키운다.
 * 
 * -> Person클래스를 설계
 * -> 철수 Person has a Dog
 * -> 영희 Person has a Cat
 *
 * 전략패턴 Strategy Pattern
 * - GoF의 디자인패턴에 소개된 행위패턴 중의 하나
 * 
 * - Context : Strategy를 이용하는 클래스 - 필요에 따라 구체적인 전략(Dog, Cat)을 바꿔서 사용할 수 있어야함 (Person)
 * - Strategy : 인터페이스/추상클래스 - Context가 의존하는 타입으로 자식클래스에 대해 동일한 규격 제공 (Pet)
 * - Concrete Strategy : Strategy클래스의 구현체 - Strategy를 수행하는 주체 (Dog, Cat)
 */
public class Main {

	public static void main(String[] args) {
		Person p1 = new Person("철수", new Dog("구리구리"));	// 다형성 적용
		Person p2 = new Person("영희", new Cat("두리두리"));
		
		System.out.println(p1);
		System.out.println(p2);
		
		// Person이 가질 수 있는 반려동물 class 이후에 추가될 수 있다.
		Person p3 = new Person("광수", new Snake("쉬리릭"));
		System.out.println(p3);
	}

}
