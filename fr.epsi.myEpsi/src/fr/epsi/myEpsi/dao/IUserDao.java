package fr.epsi.myEpsi.dao;

import fr.epsi.myEpsi.beans.User;

public interface IUserDao {

	boolean checkLogin(User user);
}
