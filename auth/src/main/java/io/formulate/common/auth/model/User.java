package io.formulate.common.auth.model;

import io.formulate.common.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class User extends BaseDomain implements UserDetails {
    protected boolean accountNonExpired;
    protected boolean accountNonLocked;
    protected List<GrantedAuthority> authorities;
    protected DateTime createTime;
    protected boolean credentialsNonExpired;
    protected String email;
    protected boolean enabled;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected DateTimeZone timeZone;
    protected String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
