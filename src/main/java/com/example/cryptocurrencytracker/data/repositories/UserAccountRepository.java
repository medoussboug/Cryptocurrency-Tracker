package com.example.cryptocurrencytracker.data.repositories;

import com.example.cryptocurrencytracker.domain.models.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
    Optional<UserAccount> findByUsername(String username);

    Optional<UserAccount> findByEmail(String email);

    @Query(value = "FROM UserAccount b WHERE b.username = :usernameOrEmail OR b.email = :usernameOrEmail")
    Optional<UserAccount> findByUsernameOrEmail(String usernameOrEmail);

}
