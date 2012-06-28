package ch.rasc.eds.starter.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_MODIFY;
import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_READ;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;
import ch.ralscha.extdirectspring.filter.StringFilter;
import ch.rasc.eds.starter.SimpleUserDb;
import ch.rasc.eds.starter.User;
import ch.rasc.eds.starter.util.PropertyOrderingFactory;

import com.google.common.collect.Ordering;

@Service
public class StoreService {

	@Autowired
	private SimpleUserDb db;

	@ExtDirectMethod(STORE_READ)
	public ExtDirectStoreResponse<User> read(final ExtDirectStoreReadRequest storeRequest) {

		String filterValue = null;
		if (!storeRequest.getFilters().isEmpty()) {
			StringFilter filter = (StringFilter) storeRequest.getFilters().iterator().next();
			filterValue = filter.getValue();
		}

		List<User> users = db.find(filterValue);
		int totalSize = users.size();

		Ordering<User> ordering = PropertyOrderingFactory.INSTANCE.createOrderingFromSorters(storeRequest.getSorters());
		if (ordering != null) {
			users = ordering.sortedCopy(users);
		}

		if (storeRequest.getStart() != null && storeRequest.getLimit() != null) {
			users = users.subList(storeRequest.getStart(),
					Math.min(totalSize, storeRequest.getStart() + storeRequest.getLimit()));
		}

		return new ExtDirectStoreResponse<User>(totalSize, users);
	}

	@ExtDirectMethod(STORE_MODIFY)
	public User create(final User newUser) {
		db.update(newUser);
		return newUser;
	}

	@ExtDirectMethod(STORE_MODIFY)
	public User update(final User updatedUser) {
		db.update(updatedUser);
		return updatedUser;
	}

	@ExtDirectMethod(STORE_MODIFY)
	public void destroy(final User destroyedUser) {
		db.delete(destroyedUser);
	}

}
