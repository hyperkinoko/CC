package kadai_15;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		while(true) {
			try {
				int i = input();
				System.out.println("正しい数値データ" + i + "が入力されました。");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static int input() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("数値データを入力してください > ");
		try {
			String inputString = sc.nextLine();
			int input = Integer.parseInt(inputString);
			if(input < 0) {
				throw new Exception("入力されたデータが下限値を下回っています。");
			}
			if(input > 9) {
				throw new Exception("入力されたデータが上限値を上回っています。");

			}
			return input;
		} catch(NoSuchElementException e) {
			// eをそのまま投げたかったけどe.messageはnullだし、e.setMessage(str)はできない。
//			throw e;
			throw new Exception("半角数字1桁を入力してください。");
		}
	}
}
