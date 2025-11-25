package com.foodcourt.users_service.domain.port.api;

public interface IUpdateDishServicePort {

    void  updateDish(Long dishId, Long price, String description);

}
