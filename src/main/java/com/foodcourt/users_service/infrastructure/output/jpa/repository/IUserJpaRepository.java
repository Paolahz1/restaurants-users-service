package com.foodcourt.users_service.infrastructure.output.jpa.repository;

import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByIdentityDocument(String identityDocument);

    @Query("SELECT u.role FROM UserEntity u WHERE u.id = :id")
    Role getRoleById(Long id);


}
