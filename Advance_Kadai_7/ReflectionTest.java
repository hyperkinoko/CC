package section_7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
	int count;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Class<ReflectionTest> clazz = ReflectionTest.class;
		Method[] methods = clazz.getDeclaredMethods();

//		Arrays.stream(methods).map(m -> m.getName()).forEach(System.out::println);

		for(Method method : methods) {
			if(method.getName().startsWith("hoge") || method.getName().startsWith("fuga")) {
				ReflectionTest instance = new ReflectionTest();
				try {
					method.invoke(instance);
				} catch (IllegalAccessException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

			}
		}

	}

	public void hogeMethod1() {
		System.out.println("hogeMethod1が実行されました");
	}

	public void hogeMethod2() {
		System.out.println("hogeMethod2が実行されました");
	}

	public void fugaMethod1() {
		System.out.println("fugaMethod1が実行されました");
	}

	public void fugaMethod2() {
		System.out.println("fugaMethod2が実行されました");
	}
}
