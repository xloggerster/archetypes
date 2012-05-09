package ch.rasc.springjpa.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.jasypt.digest.StringDigester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ch.rasc.springjpa.config.DataConfig;
import ch.rasc.springjpa.repository.RoleRepository;
import ch.rasc.springjpa.repository.UserRepository;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(classes = DataConfig.class)
public class TestEntities {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StringDigester passwordDigester;

	@Test
	public void testUserDao() {
		assertEquals(2, userRepository.findAll().size());

		User adminUser = userRepository.findByUserName("admin");
		assertNotNull(adminUser);
		assertEquals("admin", adminUser.getUserName());
		assertEquals("test@test.ch", adminUser.getEmail());
		assertEquals("admin", adminUser.getFirstName());
		assertEquals("admin", adminUser.getName());
		assertTrue(passwordDigester.matches("admin", adminUser.getPasswordHash()));
		assertEquals(true, adminUser.isEnabled());
		assertEquals("en_US", adminUser.getLocale());
		assertNotNull(adminUser.getCreateDate());

		User normalUser = userRepository.findByUserName("user");
		assertNotNull(normalUser);
		assertEquals("user", normalUser.getUserName());
		assertEquals("user@test.ch", normalUser.getEmail());
		assertEquals("user", normalUser.getFirstName());
		assertEquals("user", normalUser.getName());
		assertTrue(passwordDigester.matches("user", normalUser.getPasswordHash()));
		assertEquals(true, normalUser.isEnabled());
		assertEquals("de_CH", normalUser.getLocale());
		assertNotNull(normalUser.getCreateDate());

	}

	@Test
	public void testUserCrud() {
		User newUser = new User();
		newUser.setUserName("new");
		newUser.setEmail("new@new.ch");
		newUser.setFirstName("newfirst");
		newUser.setName("new");
		newUser.setPasswordHash(passwordDigester.digest("new"));
		newUser.setEnabled(false);
		newUser.setLocale("new");
		newUser.setCreateDate(new Date());

		userRepository.save(newUser);
		userRepository.flush();
		assertEquals(3, userRepository.findAll().size());

		newUser = userRepository.findByUserName("new");
		assertNotNull(newUser);
		assertEquals("new", newUser.getUserName());
		assertEquals("new@new.ch", newUser.getEmail());
		assertEquals("newfirst", newUser.getFirstName());
		assertEquals("new", newUser.getName());
		assertTrue(passwordDigester.matches("new", newUser.getPasswordHash()));
		assertEquals(false, newUser.isEnabled());
		assertEquals("new", newUser.getLocale());
		assertNotNull(newUser.getCreateDate());

		userRepository.delete(newUser);
		assertEquals(2, userRepository.findAll().size());

		newUser = userRepository.findByUserName("new");
		assertEquals(null, newUser);
	}

	@Test
	public void testRoleDao() {
		assertEquals(2, roleRepository.findAll().size());

		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		assertNotNull(adminRole);
		assertEquals("ROLE_ADMIN", adminRole.getName());

		Role userRole = roleRepository.findByName("ROLE_USER");
		assertNotNull(userRole);
		assertEquals("ROLE_USER", userRole.getName());
	}

	@Test
	@Transactional
	public void testRoleCrud() {
		Role newRole = new Role();
		newRole.setName("ROLE_NEW");
		roleRepository.saveAndFlush(newRole);
		assertEquals(3, roleRepository.findAll().size());

		newRole = roleRepository.findByName("ROLE_NEW");
		assertNotNull(newRole);
		assertEquals("ROLE_NEW", newRole.getName());

		roleRepository.delete(newRole);
		assertEquals(2, roleRepository.findAll().size());

		newRole = roleRepository.findByName("ROLE_NEW");
		assertEquals(null, newRole);
	}

	@Test
	public void testFilter() {
		List<User> users = userRepository.filter("admin", null, null);
		assertEquals(1, users.size());

		users = userRepository.filter("admin", "admin", null);
		assertEquals(1, users.size());

		users = userRepository.filter("admin", "admin", "test@test.ch");
		assertEquals(1, users.size());

		users = userRepository.filter("xy", "admin", "test@test.ch");
		assertEquals(0, users.size());
	}

	@Test
	public void testSpecification() {
		BooleanExpression nameEqAdmin = QUser.user.name.eq("admin");
		BooleanExpression firstNameEqAdmin = QUser.user.firstName.eq("admin");
		BooleanExpression emailEqTest = QUser.user.email.eq("test@test.ch");

		Iterable<User> users = userRepository.findAll(nameEqAdmin);
		assertEquals(1, Lists.newArrayList(users).size());

		users = userRepository.findAll(nameEqAdmin.and(firstNameEqAdmin));
		assertEquals(1, Lists.newArrayList(users).size());

		users = userRepository.findAll(BooleanExpression.allOf(nameEqAdmin, firstNameEqAdmin, emailEqTest));
		assertEquals(1, Lists.newArrayList(users).size());

		users = userRepository
				.findAll(BooleanExpression.allOf(QUser.user.name.eq("xy"), firstNameEqAdmin, emailEqTest));
		assertEquals(0, Lists.newArrayList(users).size());
	}

}
