/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.UserInfoMapper;
import model.UserInfo;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class UbahSandi {

    public boolean ubahPassword(int userId, String oldPassword, String newPassword) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);

            // Cari user berdasarkan ID
            UserInfo user = userMapper.selectUserById(userId);
            if (user != null && user.getPassword().equals(oldPassword)) {
                // Update password
                user.setPassword(newPassword);
                userMapper.updateUserPassword(user);
                session.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
        public boolean updatePassword(UserInfo user) {
    try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
        UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
        int result = userMapper.updatePassword(user.getId(), user.getPassword());
        session.commit();
        return result > 0; // Mengembalikan true jika update berhasil
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Gagal memperbarui password.");
    }
}
}
