package com.solak.expensetrackapi.Service;

import com.solak.expensetrackapi.Exception.EtAuthException;
import com.solak.expensetrackapi.Model.Et_Users;
import com.solak.expensetrackapi.Repository.UserRepository;
import com.solak.expensetrackapi.Repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositoryImpl userRepository;

    @Override
    public Et_Users validateUser(String email, String password) throws EtAuthException {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Et_Users registerUser(Et_Users etUsers) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if (!pattern.matcher(etUsers.getEmail()).matches()) {
            throw new EtAuthException("Invalid email format");
        }

        Integer count = userRepository.getCountByEmail(etUsers.getEmail());
        if (count > 0) {
            throw new EtAuthException("Email already in use");
        }

        return userRepository.create(etUsers);

    }
}
