package ch.ralscha.springjpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.util.StringUtils;

import ch.ralscha.springjpa.entity.QUser;
import ch.ralscha.springjpa.entity.User;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> filter(String name, String firstName, String email) {

		JPQLQuery query = new JPAQuery(entityManager);
		query.from(QUser.user);

		if (StringUtils.hasText(name)) {
			query.where(QUser.user.name.eq(name));
		}

		if (StringUtils.hasText(firstName)) {
			query.where(QUser.user.firstName.eq(firstName));
		}

		if (StringUtils.hasText(email)) {
			query.where(QUser.user.email.eq(email));
		}

		return query.list(QUser.user);

	}

}
