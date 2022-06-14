package cn.stanoswald.eestore.mapper;

import cn.stanoswald.eestore.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("SELECT * FROM tbl_user WHERE username=#{username}")
    User selectByUsername(String username);

    @Insert("INSERT INTO tbl_user (uid, username, `password`, `role`, tel, email, avatar) VALUES (#{uid}, #{username}, #{password}, #{role}, #{tel}, #{email}, #{avatar})")
    int insert(User user);

    @Delete("DELETE FROM tbl_user WHERE username=#{username}")
    int deleteByUsername(String username);
}
