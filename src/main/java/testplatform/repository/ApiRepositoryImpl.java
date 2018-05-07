package testplatform.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import testplatform.entity.Api;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月4日
 */
public class ApiRepositoryImpl implements ApiRepositoryCustom {
	@Autowired
	private ApiRepository apiRepository;

	public Page<Api> search(Pageable pageable, final Api entity) {
		Page<Api> result = apiRepository.findAll(new Specification<Api>() {

			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<Api> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				// 名称
				String temp = entity.getName();
				if (null != entity && !StringUtils.isEmpty(temp)) {
					expressions.add(criteriaBuilder.like(root.<String>get("name"), temp));
				}

				// uri
				temp = entity.getUri();
				if (null != entity && !StringUtils.isEmpty(temp)) {
					expressions.add(criteriaBuilder.like(root.<String>get("uri"), temp));
				}

				return predicate;
			}

		}, PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize()));

		return result;
	}

}
