package ch.rasc.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myController")
public class TestController {

	@RequestMapping("/doSomething")
	public Map<String, Object> doSomething() {
		Map<String, Object> response = new HashMap<String, Object>();

		String username = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
		}

		response.put("today", new Date());
		response.put("me", username);

		return response;

	}
}
