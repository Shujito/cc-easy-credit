package org.shujito.ec.user;

import org.shujito.ec.Database;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ro.pippo.controller.Controller;
import ro.pippo.controller.POST;
import ro.pippo.controller.Path;
import ro.pippo.controller.Produces;
import ro.pippo.controller.extractor.Param;

/**
 * @author shujito, 8/3/18
 */
@Slf4j
@Path("/user")
public class UserController extends Controller {
	private final UserDao userDao = Database.getJdbi().onDemand(UserDao.class);

	public UserController() {
		this.userDao.createTable();
	}

	@POST
	@Produces(Produces.JSON)
	public User post(@NonNull @Param String username, @Param Integer age) {
		User userByUsername = this.userDao.findByUsername(username);
		if (userByUsername != null) {
			return userByUsername;
		}
		User user = new User();
		user.setUsername(username);
		user.setAge(age);
		int inserted = this.userDao.insert(user);
		return this.userDao.find(inserted);
	}
}
