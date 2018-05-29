package testplatform.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import io.restassured.path.json.JsonPath;
import testplatform.entity.Api;
import testplatform.entity.CaseResult;
import testplatform.entity.Step;
import testplatform.entity.StepHeader;
import testplatform.entity.StepResult;
import testplatform.entity.TestCase;

public class TestCaseExcutor {
	public static TestCase excutor(TestCase testCase) throws Exception {
		List<Step> stepArr = testCase.getSteps();
		Map<String, String> queryMap = new HashMap<String, String>();
		CaseResult caseResult = new CaseResult();
		for (Step step : stepArr) {
			Api api = step.getApi();
			String queryParams = step.getQueryParams();
			if (StringUtils.isNotEmpty(queryParams)) {
				queryMap = stringConvertToMap(step.getQueryParams());
				Set<String> keySet = queryMap.keySet();
				String value = null;
				for (String key : keySet) {
					value = queryMap.get(key);
					if (isVariable(value)) {
						queryMap.put(key, getParams(value, stepArr));
					}
				}
				// 替换后的query参数重新赋值
				api.setQueryParams(mapConvertToString(queryMap));
			}

			List<StepHeader> stepHeader = step.getHeaders();
			for (StepHeader header : stepHeader) {
				if (isVariable(header.getKey())) {
					header.setValue(getParams(header.getValue(), stepArr));
				}
			}

			api.setBodyParams(step.getBodyParams());
			api.setValidateStr(step.getValidateStr());
			api.setHeaders(StepApiConverter.transToHeader(step.getHeaders()));
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

	public static String getParams(String route, List<Step> stepArr) throws Exception {
		String tempVar = "";
		ResolveEntity resolveEntity = stringConvertToResolveEntity(route);
		// 从step的response中获取需要的值，替换value
		Long stepId = resolveEntity.getStepId();
		TYPE type = resolveEntity.getType();
		// 从body中获取参数
		if (type.equals(TYPE.BODY)) {
			String path = resolveEntity.getPath();
			for (Step tempStep : stepArr) {
				if (tempStep.getId() == stepId) {
					List<StepResult> stepResultArr = tempStep.getStepResultArr();
					StepResult stepResult = stepResultArr.get(stepResultArr.size() - 1);
					String tmepResponse = stepResult.getResponse();
					tempVar = JsonPath.from(tmepResponse).getString(path);
					break;
				}
			}
		}

		// 从header中获取参数
		if (type.equals(TYPE.HEADER)) {
			String path = resolveEntity.getPath();
			for (Step tempStep : stepArr) {
				if (tempStep.getId() == stepId) {
					List<StepHeader> headers = tempStep.getHeaders();
					for (StepHeader header : headers) {
						if (header.getKey().equals(path)) {
							tempVar = header.getValue();
							break;
						}
					}
				}
			}
		}

		return tempVar;
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
		resolveEntity.setStepId(Long.valueOf(tempArr[0]));
		resolveEntity.setType(TYPE.getByName(tempArr[1]));
		String path = "";
		int length = tempArr.length;
		for (int i = 2; i < length; i++) {
			path += tempArr[i] + ".";
		}
		resolveEntity.setPath(path.substring(0, path.length() - 1));

		return resolveEntity;
	}

	public static boolean isVariable(String param) {
		if (param.startsWith("{{") && param.endsWith("}}")) {
			return true;
		}

		return false;
	}
}
