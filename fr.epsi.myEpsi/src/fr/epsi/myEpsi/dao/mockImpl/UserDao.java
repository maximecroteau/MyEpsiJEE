package fr.epsi.myEpsi.dao.mockImpl;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.IUserDao;

public class UserDao implements IUserDao {

    private static List<User> listOfUsers;

    public UserDao() {
        listOfUsers = new ArrayList<>();
        User u = new User();
        u.setId("ADMIN");
        u.setPassword("ADMIN");
        listOfUsers.add(u);
        u = new User();
        u.setId("TOTO");
        u.setPassword("TOTO");
        listOfUsers.add(u);
    }

    @Override
    public boolean checkLogin(User user) {
        boolean accesOk = false;
        User existingUser = null;
        for (User u: listOfUsers) {
            if (u.getId().equals(user.getId())) {
                existingUser = u;
                break;
            }
        }
        if (existingUser != null) {
            accesOk = (user.getId().equals(existingUser.getId())
                    && user.getPassword().equals(existingUser.getPassword()));
        }
        return accesOk;
    }

}