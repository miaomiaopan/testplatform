package testplatform;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月29日
 */
public class test {
	public static void main(String[] args) {
		System.out.println(get(1));
	}

	public static  String get(int i) {
		if (i == 1) {
			return "1";
		} else {
			return count(get(i-1));
		}
	}

	public static  String count(String str) {
		if(str.equals("1")){
			return "11";
		}
		String temp = "";
		int num = 1;
		char tempC;
		char[] charArr = str.toCharArray();
		for(int i = 0; i < charArr.length; i ++){
			tempC = charArr[i];
			for(int j = i + 1; j < charArr.length; j ++){
				if(charArr[j] == tempC){
					num ++;
					i = j+1;
				}else{
					i = j-1;
					break;
				}
			}
			temp += num+""+tempC;
			num = 1;
		}
		return temp;
	}
}
