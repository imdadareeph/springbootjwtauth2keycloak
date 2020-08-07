package com.imdadareeph.springbootjwtauth2keycloak.controller;


import com.imdadareeph.springbootjwtauth2keycloak.config.SecurityContextUtils;
import com.imdadareeph.springbootjwtauth2keycloak.model.LoginModel;
import com.imdadareeph.springbootjwtauth2keycloak.model.UserDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author imdadareeph
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping(path = "/username")
    @PreAuthorize("hasAnyAuthority('ROLE_manage-account')")
    public ResponseEntity<String> getAuthorizedUserName() {
        return ResponseEntity.ok(SecurityContextUtils.getUserName());
    }

    @GetMapping(path = "/userdetails")
    @PreAuthorize("hasAnyAuthority('ROLE_manage-account')")
    public ResponseEntity<UserDetails> getAuthorizedUserDetails() {
        return ResponseEntity.ok(SecurityContextUtils.getUserDetails());
    }

    @PostMapping(value = "/userdetails", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity postResponseJsonContent(
            @RequestBody LoginModel loginModel) {
        return ResponseEntity.ok(SecurityContextUtils.getUserDetails(loginModel));
    }

    @GetMapping(path = "/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_view-profile')")
    public ResponseEntity<Set<String>> getAuthorizedUserRoles() {
        return ResponseEntity.ok(SecurityContextUtils.getUserRoles());
    }
}
