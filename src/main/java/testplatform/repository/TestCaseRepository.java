package testplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import testplatform.entity.TestCase;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月3日
 */
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
}
