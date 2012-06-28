#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ${package}.config.JpaUserDetails;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

@Service
public class SecurityService {

	@ExtDirectMethod
	@PreAuthorize("isAuthenticated()")
	public String getLoggedOnUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof JpaUserDetails) {
			return ((JpaUserDetails) principal).getFullName();
		}
		return principal.toString();
	}

}
