package testplatform.util;

import static io.restassured.RestAssured.given;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月7日
 */
public class HttpUtil {
	public static String post(String url,String bodyParams) {
		String response = given().body(bodyParams).post(url).asString();
		return response;
	}

	public static String get(String url) {
		String response = given().get(url).asString();
		return response;
	}
}
