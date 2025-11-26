package com.foodcourt.users_service.domain.port.api;
import com.foodcourt.users_service.domain.model.Owner;

public interface ICreateOwnerServicePort
{
    void createOwner(Owner owner);
}
