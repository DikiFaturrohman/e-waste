package model;

import org.apache.ibatis.annotations.*;

public interface UserInfoMapper {

    @Select("SELECT * FROM user_info WHERE id = #{id}")
    UserInfo selectUserById(int id);

    @Insert("INSERT INTO user_info (username, password, email, phone, address) VALUES (#{username}, #{password}, #{email}, #{phone}, #{address})")
    void insertUser(UserInfo user);

    @Update("UPDATE user_info SET password = #{password} WHERE id = #{id}")
    void updateUserPassword(UserInfo user);

    @Update("UPDATE user_info SET username = #{username}, phone = #{phone}, address = #{address} WHERE id = #{id}")
    int updateUserProfile(UserInfo user);

    @Select("SELECT * FROM user_info WHERE username = #{username}")
    UserInfo selectUserByUsername(String username);

    @Select("SELECT * FROM user_info WHERE username = #{username} AND password = #{password}")
    UserInfo selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user_info SET password = #{password} WHERE id = #{id}")
    int updatePassword(@Param("id") int id, @Param("password") String password);
}
