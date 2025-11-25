package com.foodcourt.users_service.domain.port.api;

import com.foodcourt.users_service.domain.model.Role;
import com.foodcourt.users_service.domain.model.User;



public interface IUserGetInfoServicePort {
    public Role getUserRoleById(Long userId);
    public User getUserById(Long userId);
    public User getUserByEmail(String email);
}
