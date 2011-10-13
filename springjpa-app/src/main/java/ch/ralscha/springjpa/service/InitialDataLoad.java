package ch.ralscha.springjpa.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ch.ralscha.springjpa.entity.Role;
import ch.ralscha.springjpa.entity.User;
import ch.ralscha.springjpa.repository.RoleRepository;
import ch.ralscha.springjpa.repository.UserRepository;

import com.google.common.collect.Sets;

@Service
public class InitialDataLoad {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@PostConstruct
	public void postConstruct() {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
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
			adminUser.setPasswordHash(DigestUtils.shaHex("admin"));
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
			normalUser.setPasswordHash(DigestUtils.shaHex("user"));
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
