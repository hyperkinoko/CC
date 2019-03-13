package section_7;

import java.util.Scanner;


public class Kadai_7 {
	Scanner scanner;

	public static void main(String[] args) {
		new Kadai_7().process();

	}

	private void process() {
		scanner = new Scanner(System.in);
		char input = askClass();

//		// FQCNでないと認識できない
		String className = this.getClass().getPackage().getName() + ".ReflectionClass" + input;
//		String className = "ReflectionClass" + input; //これはだめ

		try {
			Class<?> clazz = Class.forName(className);
			if(Executable.class.isAssignableFrom(clazz)) { // このif文は、clazzがExecutableインターフェースを実装しているか調べています
				Executable executableClass = (Executable)clazz.newInstance();
				executableClass.execute();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// 以下、他にもいろんな使い方があるよという例です
//		Class<ReflectionClassA> clazzA = ReflectionClassA.class;
//		Method[] methods = clazzA.getDeclaredMethods();
//
//		for(Method method : methods) {
//			System.out.println(method.getName());
//		}
//
//		java.lang.reflect.Field[] fields = clazzA.getDeclaredFields();
//		for(java.lang.reflect.Field field : fields) {
//			System.out.println(field.getName());
//		}
	}

	private char askClass() {
		while (true) {
		    System.out.print("実行するクラス名を入力してください。例）クラスAの場合は、`A`と入力>");
			String input = scanner.nextLine();
			try {
				char inputchar = input.charAt(0);
				if (inputchar == 'A' || inputchar == 'B') {
					return inputchar;
				} else {
					System.out.println("AかBで入力してください。");
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("AかBで入力してください。");
			}
		}
	}

}
