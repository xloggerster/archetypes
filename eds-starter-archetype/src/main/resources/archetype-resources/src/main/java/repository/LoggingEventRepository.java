#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import ${package}.entity.LoggingEvent;

public interface LoggingEventRepository extends JpaRepository<LoggingEvent, Long>,
		QueryDslPredicateExecutor<LoggingEvent> {

	List<LoggingEvent> findByLevelString(String level);
}
