package com.diegocardoso.apirestjunit.domain.userDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String email;

    //escrita liberado e leitura block
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}


