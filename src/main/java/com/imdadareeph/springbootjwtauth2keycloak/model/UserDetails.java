package com.imdadareeph.springbootjwtauth2keycloak.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDetails {
    private String username;
    private Boolean isAuthenticated;
    private String description;
}
