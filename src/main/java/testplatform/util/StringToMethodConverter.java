package testplatform.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import testplatform.entity.Api.METHOD;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月4日
 */
@Configuration
public class StringToMethodConverter implements Converter<String, METHOD> {

	public METHOD convert(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		return METHOD.valueOf(value);
	}

}
