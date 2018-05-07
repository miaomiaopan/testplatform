package testplatform;

import testplatform.entity.Api.PROTOCOL;

/**
 * @author panmiaomiao
 *
 * @date  2018年5月4日
 */
public class test {
public static void main(String[] args) {
	PROTOCOL p = PROTOCOL.valueOf("HTTP");
	System.out.println(p.name());
}
}
