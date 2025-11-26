package com.foodcourt.users_service.application.dto.get;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserByIdCommand {

    @NotBlank
   private Long id;

}
