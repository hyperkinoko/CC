package kadai_15;

public class Sample {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			methodA();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("なにか出力");
	}

	public static void method() {
		int[] array = {1, 2, 3, 4};
		System.out.println("エラーを発生させます");
		System.out.println(array[4]);
	}

	public static void methodD() {
		System.out.println("メソッドAを開始");
		try {
			methodB();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("メソッドAを終了");
	}

	public static void methodB() throws Exception {
		System.out.println("メソッドBを開始");
		try {
			methodC(true);
		} catch (BakudanException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static void methodC(boolean throwsException) throws BakudanException {
		System.out.println("メソッドCを開始");
		if(throwsException) {
			System.out.println("爆弾Exceptionを投げます");
			throw new BakudanException("バーン！！！！");
		} else {
			System.out.println("メソッドCは例外を投げずに終了");
		}
	}

	public static void methodA() throws Exception {
		System.out.println("メソッドAを開始");
		System.out.println("Exceptionを投げます");
//		Exception e = new Exception();
//		throw e;
		throw new Exception("メッセージ");
	}

	public static void name() {

	}

}
