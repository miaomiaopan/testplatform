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

import testplatform.entity.TestCase;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月4日
 */
public class TestCaseRepositoryImpl implements TestCaseRepositoryCustom {
	@Autowired
	private TestCaseRepository testCaseRepository;

	public Page<TestCase> search(Pageable pageable, final TestCase entity) {
		Page<TestCase> result = testCaseRepository.findAll(new Specification<TestCase>() {

			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<TestCase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				// 名称
				String temp = entity.getName();
				if (null != entity && !StringUtils.isEmpty(temp)) {
					expressions.add(criteriaBuilder.like(root.<String>get("name"), temp));
				}

//				// path
//				temp = entity.getPath();
//				if (null != entity && !StringUtils.isEmpty(temp)) {
//					expressions.add(criteriaBuilder.like(root.<String>get("uri"), temp));
//				}

				return predicate;
			}

		}, PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize()));

		return result;
	}

}
