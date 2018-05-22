package testplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import testplatform.entity.TestCase;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月3日
 */
public interface TestCaseRepository extends JpaRepository<TestCase, Long>,JpaSpecificationExecutor<TestCase>, TestCaseRepositoryCustom {
}
