package ch.rasc.eds.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class SimpleUserDb {

	private Map<String, User> users;

	@PostConstruct
	public void initData() {
		users = new ConcurrentHashMap<>();

		addUser("Cassidy", "Vaughn", "Nunc.laoreet@congueturpis.com");
		addUser("Jayme", "Frost", "egestas.a.dui@nisiaodio.org");
		addUser("Reece", "Ferrell", "placerat@lacusQuisqueimperdiet.org");
		addUser("Imelda", "Wong", "mauris.a.nunc@estac.ca");
		addUser("Carla", "Maynard", "Proin@nonummyultricies.ca");
		addUser("Whilemina", "Leach", "facilisis@nuncestmollis.org");
		addUser("Pearl", "Cline", "Sed@Maurisnulla.edu");
		addUser("Diana", "Clay", "massa.lobortis.ultrices@suscipit.org");
		addUser("Roanna", "Boyd", "mauris.ut@nonmassa.com");
		addUser("Carly", "Hester", "vulputate@volutpatornarefacilisis.org");
	}

	private void addUser(String firstName, String lastName, String email) {
		User u = new User(firstName, lastName, email);
		users.put(u.getId(), u);
	}

	public List<User> getAll() {
		return new ArrayList<>(users.values());
	}

	public User findUser(final String id) {
		return users.get(id);
	}

	public void deleteUser(User user) {
		users.remove(user.getId());
	}

}
