package testplatform.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import testplatform.entity.Api;
import testplatform.entity.Api.METHOD;
import testplatform.entity.Api.PROTOCOL;
import testplatform.entity.Header;
import testplatform.entity.StepResult;

public class ApiExcutor {
	public static StepResult excutor(Api api) throws Exception {
		String url = null;
		PROTOCOL protocol = api.getProtocol();
		if (protocol.equals(PROTOCOL.HTTP)) {
			url = "http";
		} else if (protocol.equals(PROTOCOL.HTTPS)) {
			url = "https";
		}

		url += "://" + api.getDomain() + api.getPath();
		String params = api.getQueryParams();
		if (StringUtils.isNotEmpty(params)) {
			url += "?" + api.getQueryParams();
		}

		METHOD method = api.getMethod();
		String response = null;
		List<Header> headers = api.getHeaders();

		if (method.equals(METHOD.GET)) {
			response = HttpUtil.get(url, transHeaders(headers));
		} else if (method.equals(METHOD.POST)) {
			response = HttpUtil.post(url, api.getBodyParams(), transHeaders(headers));
		}

		// TODO 添加验证 先实现=验证
		String validateStr = api.getValidateStr();
		StepResult stepResult = new StepResult();
		stepResult.setResponse(response);
		if (StringUtils.isNotEmpty(validateStr)) {
			String[] constArr = validateStr.split("=");
			String path = constArr[0];
			String expectValue = constArr[1];

			JsonPath jsonPath = JsonPath.from(response);
			String value = "";
			if (expectValue.startsWith("\"")) {
				value = jsonPath.getString(path);
			} else {
				value = String.valueOf(jsonPath.getInt(path));
			}

			if (!String.valueOf(value).equals(expectValue)) {
				stepResult.setReason(validateStr + "：校验不通过");
				stepResult.setStatus(STATUS.FAIL);
			} else {
				stepResult.setStatus(STATUS.SUCESS);
			}
		} else {
			stepResult.setStatus(STATUS.SUCESS);
		}

		return stepResult;
	}

	public static Headers transHeaders(List<Header> headers) {
		List<io.restassured.http.Header> temp = new ArrayList<io.restassured.http.Header>();
		for (Header header : headers) {
			temp.add(new io.restassured.http.Header(header.getKey(), header.getValue()));
		}

		return new Headers(temp);
	}
}
