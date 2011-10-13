package ch.ralscha.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.ralscha.webapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
