package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@TableName("tbl_user")
@JsonIgnoreProperties({"password"})
public class User implements UserDetails {

    @TableId
    private String uid;
    private String username;
    private String password;
    private String role;
    private String tel;
    private String email;
    private String avatar;
    private Boolean enable;
    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password)
                && Objects.equals(role, user.role)
                && Objects.equals(tel, user.tel)
                && Objects.equals(email, user.email)
                && Objects.equals(avatar, user.avatar)
                && Objects.equals(enable, user.enable);
    }
}
