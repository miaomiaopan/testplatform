package testplatform.util;

import static io.restassured.RestAssured.given;

import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月7日
 */
public class HttpUtil {
	public static String post(String url, String bodyParams, Headers headers) {
		RequestSpecification requestSpecification = given();
		if (headers != null) {
			requestSpecification = requestSpecification.headers(headers);
		}

		String response = requestSpecification.body(bodyParams).post(url).asString();

		return response;
	}

	public static String get(String url, Headers headers) {
		RequestSpecification requestSpecification = given();
		if (headers != null) {
			requestSpecification = requestSpecification.headers(headers);
		}
		String response = requestSpecification.get(url).asString();
		return response;
	}
}
