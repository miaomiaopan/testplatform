/**
 * 
 */
package testplatform.util;

import java.util.ArrayList;
import java.util.List;

import testplatform.entity.Api;
import testplatform.entity.Header;
import testplatform.entity.Step;
import testplatform.entity.StepHeader;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月22日
 */
public class StepApiConverter {
	public static Step ApiConvertToStep(Api api) {
		Step step = new Step();
		step.setApi(api);
		step.setQueryParams(api.getQueryParams());
		step.setBodyParams(api.getBodyParams());
		List<StepHeader> stepHeaders = new ArrayList<StepHeader>();
		for (Header header : api.getHeaders()) {
			stepHeaders.add(new StepHeader(header));
		}

		step.setHeaders(stepHeaders);
		step.setSleep(api.getSleep());
		return step;
	}
	
	public static List<StepHeader> transToStepHeader(List<Header> headers){
		List<StepHeader> stepHeaders = new ArrayList<StepHeader>();
		for (Header header : headers) {
			stepHeaders.add(new StepHeader(header));
		}
		
		return stepHeaders;
	}
	
	public static List<Header> transToHeader(List<StepHeader> stepHeaders){
		List<Header> headers = new ArrayList<Header>();
		for (StepHeader header : stepHeaders) {
			headers.add(new Header(header));
		}
		
		return headers;
	}
}
