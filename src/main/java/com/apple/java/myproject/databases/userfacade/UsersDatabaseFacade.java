package com.apple.java.myproject.databases.userfacade;

import com.apple.java.myproject.Users;
import com.apple.java.myproject.exception.UsersBaseException;
import com.apple.java.myproject.textprocessing.UsersBase;

import java.util.ArrayList;
import java.util.List;

public interface UsersDatabaseFacade {
    void createUser(Users user) throws UsersBaseException;
    void updateUser(Users user) throws UsersBaseException;
    Users selectUser(Integer id) throws UsersBaseException;
    void deleteUser(Integer id) throws UsersBaseException;
}
