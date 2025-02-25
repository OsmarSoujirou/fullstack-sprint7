package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUser(String user);

}