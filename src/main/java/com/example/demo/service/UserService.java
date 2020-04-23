package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository repoRole;

    public List<User> listAll() {
        return repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repo.findByLogin(s);
    }

    public List<Role> listAllRoles() {
        return repoRole.findAll();
    }

}


