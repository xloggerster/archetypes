#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.schedule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ${package}.entity.AccessLog;
import ${package}.entity.QAccessLog;

import com.mysema.query.jpa.impl.JPAQuery;

@Component
public class AccessLogCleanup {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Scheduled(cron = "0 0 5 * * *")
	public void doCleanup() {
		// Delete all access logs that are older than 6 months
		DateTime sixMonthAgo = DateTime.now().minusMonths(6);

		for (AccessLog al : new JPAQuery(entityManager).from(QAccessLog.accessLog)
				.where(QAccessLog.accessLog.logIn.loe(sixMonthAgo)).list(QAccessLog.accessLog)) {
			entityManager.remove(al);
		}

	}

}
