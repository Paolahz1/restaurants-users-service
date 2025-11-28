package com.foodcourt.users_service.infrastructure.output.jpa.repository;

import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.EmployeeDetailsEntity;
import com.foodcourt.users_service.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IEmployeeDetailsJpaRepository extends JpaRepository<EmployeeDetailsEntity, Long> {

    List<EmployeeDetailsEntity> findByRestaurantId(Long restaurantId);

}
