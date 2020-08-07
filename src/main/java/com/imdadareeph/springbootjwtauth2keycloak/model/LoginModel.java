package com.imdadareeph.springbootjwtauth2keycloak.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoginModel {
    private String username;
    private String password;
}
