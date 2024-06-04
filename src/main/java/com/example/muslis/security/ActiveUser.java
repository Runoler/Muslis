package com.example.muslis.security;

import com.example.muslis.models.Artist;
import com.example.muslis.models.Listener;
import com.example.muslis.models.UserInfo;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ActiveUser implements UserDetails {

    private final UserInfo userInfo;
    private Artist artistPart;
    private Listener listenerPart;

    public ActiveUser(UserInfo userInfo) {
        this.userInfo = userInfo;
        this.artistPart = userInfo.getArtistPart();
        this.listenerPart = userInfo.getListenerPart();
    }

    public void MakeArtist() {
        this.artistPart = userInfo.getArtistPart();
    }

    public void MakeListener() {
        this.listenerPart = userInfo.getListenerPart();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userInfo.getUserRole().name()));
    }

    @Override
    public String getPassword() {
        return this.userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
