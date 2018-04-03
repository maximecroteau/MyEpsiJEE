package fr.epsi.myEpsi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;

public class MultipleEmailTest {

	@Test
	public void testValidationEmail() {
		User user1 = new User();
		user1.setId("test@epsi.fr");
		
		User user2 = new User();
		user2.setId("test@epsi.fr");
		
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		
		assertTrue("loginExistant", UserDao.checkIfInBase(user1, users) == false);
		
	}

}
