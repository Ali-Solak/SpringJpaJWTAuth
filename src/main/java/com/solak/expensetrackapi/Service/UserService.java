package com.solak.expensetrackapi.Service;

import com.solak.expensetrackapi.Exception.EtAuthException;
import com.solak.expensetrackapi.Model.Et_Users;


public interface UserService {

    public Et_Users validateUser(String email, String password) throws EtAuthException;
    public Et_Users registerUser(Et_Users etUsers) throws  EtAuthException;

}
