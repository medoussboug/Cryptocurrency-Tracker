package com.example.cryptocurrencytracker.data.mappers;

import com.example.cryptocurrencytracker.domain.models.dtos.UserAccountDTO;
import com.example.cryptocurrencytracker.domain.models.entities.UserAccount;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserAccountMapper {

    public UserAccount toEntity(UserAccountDTO userAccountDTO) {
        return UserAccount.builder()
                .id(userAccountDTO.id())
                .username(userAccountDTO.username().trim().toLowerCase())
                .email(userAccountDTO.email().trim().toLowerCase())
                .fullName(userAccountDTO.fullName().trim().toLowerCase())
                .title(userAccountDTO.title().trim().toLowerCase())
                .dateOfBirth(userAccountDTO.dateOfBirth())
                .dateOfCreation(userAccountDTO.dateOfCreation())
                .organization(userAccountDTO.organization().trim().toLowerCase())
                .build();
    }

    public UserAccountDTO toDTO(UserAccount user) {
        return new UserAccountDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFullName(),
                user.getTitle(),
                user.getOrganization(),
                user.getDateOfBirth(),
                user.getDateOfCreation(),
                user.getUserRole());
    }

    public List<UserAccountDTO> toDTO(Collection<UserAccount> userAccounts) {
        return userAccounts.stream()
                .map(this::toDTO)
                .toList();
    }
}
