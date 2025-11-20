package com.foodcourt.users_service.domain.port.spi;

public interface IRestaurantValidatorPort {

    boolean existById(Long restaurantId);

}
