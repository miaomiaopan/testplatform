package testplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import testplatform.security.User;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月3日
 */
public interface UserRepository extends JpaRepository<User, Long>{
	User getByUsername(String userName);
}
