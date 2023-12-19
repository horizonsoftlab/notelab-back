package sn.hsl.notelabback.security.service;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sn.hsl.notelabback.entities.AccountEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDetailsImpl implements UserDetails, Serializable {
    Long id;

    String username;

    String password;

    boolean firstAttempt;

    boolean enabled;

    Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(AccountEntity account) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(Objects.nonNull(account.getRole().getPermissions()) && !account.getRole().getPermissions().isEmpty()) {
            authorities = account.getRole()
                    .getPermissions().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                    .collect(Collectors.toList());
        }

        return UserDetailsImpl.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .firstAttempt(account.isFirstAttempt())
                .enabled(account.isEnabled())
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }
}
