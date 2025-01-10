package model;

import java.util.List;
import org.apache.ibatis.annotations.*;

public interface UserInfoMapper {

    @Select("SELECT id, username, password, email, phone, address, profile_picture FROM user_info WHERE id = #{id}")
    UserInfo selectUserById(int id);
    
    @Select("SELECT * FROM user_info WHERE email = #{email}")
    UserInfo selectUserByEmail(String email);


    @Insert("INSERT INTO user_info (username, password, email, phone, address) VALUES (#{username}, #{password}, #{email}, #{phone}, #{address})")
    void insertUser(UserInfo user);

    @Update("UPDATE user_info SET password = #{password} WHERE id = #{id}")
    void updateUserPassword(UserInfo user);

    @Update("UPDATE user_info SET username = #{username}, phone = #{phone}, address = #{address}, profile_picture = #{profilePicturePath} WHERE id = #{id}")
    int updateUserProfile(UserInfo user);

    @Select("SELECT id, username, password, email, phone, address, profile_picture, role FROM user_info WHERE username = #{username}")
    UserInfo selectUserByUsername(String username);


    @Select("SELECT * FROM user_info WHERE username = #{username} AND password = #{password}")
    UserInfo selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user_info SET password = #{password} WHERE id = #{id}")
    int updatePassword(@Param("id") int id, @Param("password") String password);
    
        // Add this method to fetch all users
    @Select("SELECT id, username, email, address, role FROM user_info")
    List<UserInfo> selectAllUsers();  // This will retrieve all users
    
    @Delete("DELETE FROM user_info WHERE id = #{id}")
    void deleteUserById(int id);

}
