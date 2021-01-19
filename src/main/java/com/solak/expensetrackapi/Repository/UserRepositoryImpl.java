package com.solak.expensetrackapi.Repository;

import com.solak.expensetrackapi.Exception.EtAuthException;
import com.solak.expensetrackapi.Model.Et_Users;
import org.apache.tomcat.util.json.JSONParser;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepositoryImpl {


    @Autowired
    UserRepository userRepository;

    public Et_Users create(Et_Users etUsers) throws EtAuthException {
        try {
            String hashedPassword = BCrypt.hashpw(etUsers.getPassword(), BCrypt.gensalt(10));
            etUsers.setPassword(hashedPassword);
            return userRepository.save(etUsers);

        } catch (Exception e) {
            throw new EtAuthException("invalid details. Failed to create Account");
        }
    }

    public Et_Users findByEmailAndPassword(String email, String password) throws EtAuthException {

        try {
            Optional<Et_Users> etUsers = userRepository.findByEmail(email);

            if (etUsers.isPresent()) {
                if (!BCrypt.checkpw(password, etUsers.get().getPassword())) {
                    throw new EtAuthException("invalid details. Failed to login");
                }

                return etUsers.get();
            }
            else{
                return etUsers.orElseThrow(() -> new EtAuthException("no User with Email: " + email));
            }


        } catch (Exception e) {
            System.out.println("THE EXPCETIONN: " + e.getMessage());
            throw new EtAuthException("otherinvalid");
        }
    }


    public Integer getCountByEmail(String email) {
        return userRepository.countByEmail(email);
    }


    public Et_Users findById(Long userId) {
        return userRepository.findById(userId).get();
    }



}
