package ch.rasc.eds.starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@Component
public class SimpleUserDb {

	private Map<String, User> users;

	private final ImmutableList<String> departments;

	@Autowired
	private Resource randomdata;

	public SimpleUserDb() {
		departments = ImmutableList
				.<String> builder()
				.add("Accounting", "Advertising", "Asset Management", "Customer Relations", "Customer Service",
						"Finances", "Human Resources", "Legal Department", "Media Relations", "Payroll",
						"Public Relations", "Quality Assurance", "Sales and Marketing", "Research and Development",
						"Tech Support").build();
	}

	@PostConstruct
	public void initData() {
		users = new ConcurrentHashMap<>();

		try (InputStream is = randomdata.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

			br.readLine();

			String line;
			while ((line = br.readLine()) != null) {
				List<String> row = Lists.newArrayList(Splitter.on("|").split(line));

				User u = new User(row.get(0), row.get(1), row.get(2), row.get(3));
				users.put(u.getId(), u);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ImmutableList<User> getAll() {
		return ImmutableList.copyOf(users.values());
	}

	public ImmutableList<User> getAllFromDepartment(final String department) {

		ImmutableList.Builder<User> builder = ImmutableList.builder();

		builder.addAll(Collections2.filter(users.values(), new Predicate<User>() {
			@Override
			public boolean apply(User input) {
				return input.getDepartment().equals(department);
			}
		}));

		return builder.build();

	}

	public User findUser(final String id) {
		return users.get(id);
	}

	public void delete(User user) {
		users.remove(user.getId());
	}
	
	public void update(User user) {
		users.put(user.getId(), user);		
	}

	public ImmutableList<String> getDepartments() {
		return departments;
	}



}
