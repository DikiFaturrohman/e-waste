/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper {

    @Select("SELECT * FROM user_info WHERE id = #{id}")
    UserInfo selectUserById(int id);

  @Insert("INSERT INTO user_info (username, password, email) VALUES (#{username}, #{password}, #{email})")
    void insertUser(UserInfo user);

    @Update("UPDATE user_info SET password = #{password} WHERE id = #{id}")
    void updateUserPassword(UserInfo user);

    @Update("UPDATE user_info SET username = #{username}, email = #{email} WHERE id = #{id}")
    void updateUserProfile(UserInfo user);
    
    @Select("SELECT * FROM user_info WHERE username = #{username}")
UserInfo selectUserByUsername(String username);



}

