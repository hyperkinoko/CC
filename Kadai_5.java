package sampleProject;

public class Kadai_5 {

	public static void main(String[] args) {
		// 配列データの宣言・初期化
		int[] arrays1 = { 96, 45, 31, 29, 84, 77 };

		//並び替え前の配列内を表示
		display(arrays1, false);

		// 昇順で並べ替え→表示
		arraysSort(arrays1, true);
		display(arrays1, true);

		//降順で並べ替え→表示
		arraysSort(arrays1, false);
		display(arrays1, true);
	}

	/** 配列内を表示するためのstaticメソッド
	 * @param array ：表示する配列
	 * @param isSorted ：ソート前かソート後か（ソート後→true）
	 */
	public static void display(int[] array, boolean isSorted) {
		if(isSorted) {
			System.out.println("***並び替え後***");
		} else {
			System.out.println("***並び替え前***");
		}

		for(int i = 0; i < array.length+ 1; i++) {
			System.out.print(array[i]);
			if(i == array.length -1) {
				System.out.println("");
				continue;
			}
			System.out.print(",");
		}
	}

	/** 配列を降順/昇順で並び替え
	 * @param array ：並び替える配列
	 * @param orderType ：並び替えの順番（昇順→true、降順→false）
	 */
	public static void arraysSort(int[] array, boolean orderType) {
		// バブルソートという手法で並び替え
		for(int i = 0; i < array.length; i++) {
			for(int j = i + 1; j < array.length; j++) {
				if(array[i] > array[j]) {
					if(orderType) {
						change(array, i, j);
					}
				} else {
					if(!orderType) {
						change(array, i, j);
					}
				}
			}
		}
	}

	/** 配列のi番目とj番目の要素を入れ替えるメソッド
	 * @param array ：要素を入れ替える配列
	 * @param i, j ：入れ替えるindex
	 */
	public static void change(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
