package testplatform;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月29日
 */
public class test {
	public static void main(String[] args) {
		// System.out.println(get(1));
		// int[] arr = new int[100];
		// System.out.println(arr);
		// for (int i = 0; i < arr.length; i++) {
		// System.out.println(arr[i]);
		// }
		//
		// List<Integer> intArr = new LinkedList<Integer>();
		// intArr.size();
		int[] arr = { 8,7,6,5,4,3,2,1 };
		charu(arr);
		for (int i : arr) {
			System.out.println(i);
		}
	}

	public static String get(int i) {
		if (i == 1) {
			return "1";
		} else {
			return count(get(i - 1));
		}
	}

	public static String count(String str) {
		if (str.equals("1")) {
			return "11";
		}
		String temp = "";
		int num = 1;
		char tempC;
		char[] charArr = str.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			tempC = charArr[i];
			for (int j = i + 1; j < charArr.length; j++) {
				if (charArr[j] == tempC) {
					num++;
					i = j + 1;
				} else {
					i = j - 1;
					break;
				}
			}
			temp += num + "" + tempC;
			num = 1;
		}
		return temp;
	}

	// 交换 有序的在左边
	public static void jiaohuan(int[] arr) {
		int temp;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}

	// 冒泡 有序的在右边
	public static void maopao(int[] arr) {
		int count = 0;
		int temp;
		int length = arr.length;
		boolean bool = true;
		for (int i = length - 1; i > 0 && bool; i--) {
			bool = false;
			for (int j = 0; j < i; j++) {
				count++;
				if (arr[j] > arr[j + 1]) {
					temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
					bool = true;
				}
			}
		}
		System.out.println(count);
	}

	// 冒泡 有序的在右边
	public static void maopao1(int[] arr) {
		int count = 0;
		int temp;
		int length = arr.length;
		for (int i = length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				count++;
				if (arr[j] > arr[j + 1]) {
					temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(count);
	}

	// 插入
	public static void charu(int[] arr) {
		int length = arr.length;
		int in, out;
		for(out = 1; out < length; out ++){
			int temp = arr[out];
			in = out;
			while(in >0&&arr[in-1]>=temp){
				arr[in]=arr[in-1];
				--in;
			}
			
			arr[in]=temp;
		}
	}
}
