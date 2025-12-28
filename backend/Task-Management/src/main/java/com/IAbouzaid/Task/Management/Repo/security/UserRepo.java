package com.IAbouzaid.Task.Management.Repo.security;



import com.IAbouzaid.Task.Management.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

}