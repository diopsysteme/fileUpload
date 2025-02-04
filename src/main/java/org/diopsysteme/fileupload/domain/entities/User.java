package org.diopsysteme.fileupload.domain.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import prog.dependancy.Datas.Entity.EntityAbstract;

import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@ToString
@Table(name = "\"user\"")
public class User extends EntityAbstract implements UserDetails {
    private String login;
    private String password;
    private String type;
    private String idK;
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return login;
    }




    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+type));
    }
}
