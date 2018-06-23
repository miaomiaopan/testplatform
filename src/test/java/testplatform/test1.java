package testplatform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test1 {
	static int size;
	static int count;
	static char[] arrChar = new char[100];
	public static void main(String[] args) throws Exception {
		System.out.println("enter a word");
		String input = getString();
		size = input.length();
		count = 0;
		for(int j = 0; j < size; j ++){
			arrChar[j] = input.charAt(j);
		}
		
		doAngram(size);
	}

//	public static void quanweishu(String str) {
//		int length = str.length();
//		if(length == 1){
//			System.out.println(str.charAt(0));
//		}else{
//			String temp = "";
//			for(int i = 0;i < length; i ++){
//				temp = str.
//				quanweishu();
//			}
//		}
//	}
	
	public static void doAngram(int newSize){
		if(newSize == 1){
			return;
		} 
		
		for(int j =0; j < newSize; j ++){
			doAngram(newSize - 1);
			if(newSize==2){
				displayWord();
			rorate(newSize);	
			}
		}
	}
	
	public static void rorate(int newSize){
		int j ;
		int position = size-newSize;
		char temp = arrChar[position];
		for(j =position+1; j < size; j ++){
			arrChar[j-1] = arrChar[j]; 
		}
		arrChar[j-1] = temp; 
	}
	
	
	public static void displayWord(){
		if(count < 99){
			System.out.println(" ");
		}
		if(count < 9){
			System.out.println(" ");
		}
		
		System.out.print(++count+" ");
		
		for(int j = 0; j < size; j ++){
			System.out.print(arrChar[j]);
		}
		
		System.out.println(" ");
		System.out.flush();
		
		if(count%6 == 0){
			System.out.println("");
		}
	}
	
	public static String getString() throws Exception{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
}
