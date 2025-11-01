package com.library.authConfig;

import com.library.dto.auth.CurrentUserDTO;
import com.library.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CurrentUserAuthentication implements Authentication {
    private CurrentUserDTO currentUserEntity;

    @Autowired
    private UserService userService;

    public CurrentUserDTO getCurrentUserEntity() {
        return currentUserEntity;
    }

    public void setCurrentUserEntity(CurrentUserDTO currentUserEntity) {
        this.currentUserEntity = currentUserEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.currentUserEntity;
    }

    @Override
    public boolean isAuthenticated() {
        return currentUserEntity != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return this.currentUserEntity.getName();
    }
}
