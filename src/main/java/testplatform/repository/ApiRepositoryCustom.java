package testplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import testplatform.entity.Api;

/**
 * @author panmiaomiao
 *
 * @date  2018年5月4日
 */
public interface ApiRepositoryCustom{
	Page<Api> search(Pageable pageable, final Api entity);
}
