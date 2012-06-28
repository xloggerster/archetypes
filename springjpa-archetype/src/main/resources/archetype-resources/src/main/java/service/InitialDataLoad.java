#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.jasypt.digest.StringDigester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ${package}.entity.Role;
import ${package}.entity.User;
import ${package}.repository.RoleRepository;
import ${package}.repository.UserRepository;

import com.google.common.collect.Sets;

@Service
public class InitialDataLoad {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Autowired
	private StringDigester passwordDigester;

	@PostConstruct
	public void postConstruct() {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(final TransactionStatus status) {
				init();
			}
		});
	}

	void init() {
		Role adminRole = null;
		Role userRole = null;

		if (roleRepository.count() == 0) {
			adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);

			userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);
		}

		if (userRepository.count() == 0) {
			//admin user
			User adminUser = new User();
			adminUser.setUserName("admin");
			adminUser.setEmail("test@test.ch");
			adminUser.setFirstName("admin");
			adminUser.setName("admin");
			adminUser.setPasswordHash(passwordDigester.digest("admin"));
			adminUser.setEnabled(true);
			adminUser.setLocale("en_US");
			adminUser.setCreateDate(new Date());

			if (adminRole == null) {
				adminRole = roleRepository.findByName("ROLE_ADMIN");
			}

			if (adminRole != null) {
				adminUser.setRoles(Sets.newHashSet(adminRole));
			}

			userRepository.save(adminUser);

			//normal user
			User normalUser = new User();
			normalUser.setUserName("user");
			normalUser.setEmail("user@test.ch");
			normalUser.setFirstName("user");
			normalUser.setName("user");
			normalUser.setPasswordHash(passwordDigester.digest("user"));
			normalUser.setEnabled(true);
			normalUser.setLocale("de_CH");
			normalUser.setCreateDate(new Date());

			if (userRole == null) {
				userRole = roleRepository.findByName("ROLE_USER");
			}

			if (userRole != null) {
				normalUser.setRoles(Sets.newHashSet(userRole));
			}

			userRepository.save(normalUser);
		}

	}

}
