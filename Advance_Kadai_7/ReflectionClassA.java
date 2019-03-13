package section_7;

public class ReflectionClassA implements Executable {

	@Override
	public void execute() {
		System.out.println("ReflectionClassAのexecute()メソッドが実行されました。");
	}

	public void methodHoge() {
		System.out.println("hoge");
	}


	public void methodFuga() {
		System.out.println("fuga");
	}

}
