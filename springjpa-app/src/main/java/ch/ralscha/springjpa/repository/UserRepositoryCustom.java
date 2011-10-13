package ch.ralscha.springjpa.repository;

import java.util.List;

import ch.ralscha.springjpa.entity.User;

public interface UserRepositoryCustom {
	List<User> filter(String name, String firstName, String email);
}
