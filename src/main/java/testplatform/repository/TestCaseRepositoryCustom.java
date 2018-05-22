package testplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import testplatform.entity.TestCase;

/**
 * @author panmiaomiao
 *
 * @date  2018年5月4日
 */
public interface TestCaseRepositoryCustom{
	Page<TestCase> search(Pageable pageable, final TestCase entity);
}
