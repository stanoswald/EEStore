package cn.stanoswald.eestore.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;


@Mapper
public interface PersistentLoginMapper {

    @Insert("INSERT INTO tbl_persistent_login(series, username, token, last_used) VALUES (#{series},#{username},#{tokenValue},#{date})")
    void insertToken(PersistentRememberMeToken tokenEntity);

    @Update("UPDATE tbl_persistent_login SET token=#{tokenValue},last_used=#{date} WHERE series=#{series}")
    void updateToken(PersistentRememberMeToken tokenEntity);

    @Select("SELECT * FROM tbl_persistent_login WHERE series=#{series}")
    PersistentRememberMeToken selectTokenBySeries(String series);

    @Delete("DELETE FROM tbl_persistent_login WHERE username=#{username}")
    void deleteTokenByUsername(String username);
}
