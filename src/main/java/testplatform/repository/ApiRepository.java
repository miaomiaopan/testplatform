package testplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import testplatform.entity.Api;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月3日
 */
public interface ApiRepository extends JpaRepository<Api, Long>,JpaSpecificationExecutor<Api>, ApiRepositoryCustom {
}
