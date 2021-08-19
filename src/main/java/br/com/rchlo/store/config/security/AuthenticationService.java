package br.com.rchlo.store.config.security;

import br.com.rchlo.store.domain.Users;
import br.com.rchlo.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Users> user = repository.findByUser(s);

        if(user.isEmpty()) throw new UsernameNotFoundException("Invalid data!");

        return user.get();
    }
}
