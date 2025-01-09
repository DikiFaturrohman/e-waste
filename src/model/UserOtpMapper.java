/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import org.apache.ibatis.annotations.*;

public interface UserOtpMapper {
    @Insert("INSERT INTO otp_requests (email, otp_code, expires_at, is_used) VALUES (#{email}, #{otpCode}, #{expiresAt}, #{isUsed})")
    void insertOtp(UserOtp otp);

    @Select("SELECT * FROM otp_requests WHERE email = #{email} AND otp_code = #{otpCode} AND is_used = FALSE AND expires_at > NOW()")
    UserOtp validateOtp(@Param("email") String email, @Param("otpCode") String otpCode);

    @Update("UPDATE otp_requests SET is_used = TRUE WHERE id = #{id}")
    void markOtpAsUsed(@Param("id") int id);
}
