package testplatform.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.restassured.path.json.JsonPath;
import testplatform.entity.Api;
import testplatform.entity.CaseResult;
import testplatform.entity.Step;
import testplatform.entity.StepResult;
import testplatform.entity.TestCase;

public class TestCaseExcutor {
	public static TestCase excutor(TestCase testCase) throws Exception {
		List<Step> stepArr = testCase.getSteps();
		Map<String, String> queryMap = new HashMap<String, String>();
		CaseResult caseResult = new CaseResult();
		for (Step step : stepArr) {
			Api api = step.getApi();
			queryMap = stringConvertToMap(step.getParams());
			Set<String> keySet = queryMap.keySet();
			String value = null;
			for (String key : keySet) {
				value = queryMap.get(key);
				ResolveEntity resolveEntity = null;
				if (value.startsWith("{{") && value.endsWith("}}")) {
					resolveEntity = stringConvertToResolveEntity(value);
					// 从step的response中获取需要的值，替换value
					Long apiId = resolveEntity.getApiId();
					TYPE type = resolveEntity.getType();
					if (type.equals(TYPE.BODY)) {
						String path = resolveEntity.getPath();
						for (Step tempStep : stepArr) {
							Api tempApi = tempStep.getApi();
							if (tempApi.getId() == apiId) {
								List<StepResult> stepResultArr = tempStep.getStepResultArr();
								StepResult stepResult = stepResultArr.get(stepResultArr.size() - 1);
								String tmepResponse = stepResult.getResponse();
								String tempVar = JsonPath.from(tmepResponse).getString(path);
								// 替换变量值
								queryMap.put(key, tempVar);
								break;
							}
						}
					}
				}
			}
			api.setBodyParams(step.getBodyParams());
			api.setValidateStr(step.getValidateStr());
			// 替换后的query参数重新赋值
			api.setParams(mapConvertToString(queryMap));
			StepResult stepResult = ApiExcutor.excutor(api);
			step.getStepResultArr().add(stepResult);
			if (stepResult.getStatus().equals(STATUS.FAIL)) {
				caseResult.setStatus(STATUS.FAIL);
				break;
			}
		}

		if (caseResult.getStatus() == null) {
			caseResult.setStatus(STATUS.SUCESS);
		}

		testCase.getCaseResult().add(caseResult);
		return testCase;
	}

	public static Map<String, String> stringConvertToMap(String queryParams) {
		String[] strArr = queryParams.split("&");
		String[] temp = null;
		Map<String, String> map = new HashMap<String, String>();
		for (String str : strArr) {
			temp = str.split("=");
			map.put(temp[0], temp[1]);
		}

		return map;
	}

	public static String mapConvertToString(Map<String, String> queryMap) {
		String queryParams = "";
		Set<String> keySet = queryMap.keySet();
		for (String key : keySet) {
			queryParams += (key + "=" + queryMap.get(key) + "&");
		}

		queryParams = queryParams.substring(0, queryParams.length() - 1);
		return queryParams;
	}

	public static ResolveEntity stringConvertToResolveEntity(String param) {
		ResolveEntity resolveEntity = new ResolveEntity();
		param = param.substring(2, param.length() - 2);
		String[] tempArr = param.split("\\.");
		resolveEntity.setApiId(Long.valueOf(tempArr[0]));
		resolveEntity.setType(TYPE.getByName(tempArr[1]));
		String path = "";
		int length = tempArr.length;
		for (int i = 2; i < length; i++) {
			path += tempArr[i] + ".";
		}
		resolveEntity.setPath(path.substring(0, path.length() - 1));

		return resolveEntity;
	}
}
