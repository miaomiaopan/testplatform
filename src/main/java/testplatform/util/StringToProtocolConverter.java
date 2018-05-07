package testplatform.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import testplatform.entity.Api.PROTOCOL;

/**
 * @author panmiaomiao
 *
 * @date  2018年5月4日
 */
@Configuration 
public class StringToProtocolConverter implements Converter<String,PROTOCOL>{

	public PROTOCOL convert(String source) {
		  String value = source.trim();  
	        if ("".equals(value)) {  
	            return null;  
	        }  
	        return PROTOCOL.valueOf(value);
	}

}
