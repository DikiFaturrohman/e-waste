/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.UserInfoMapper;
import model.UserInfo;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class Registrasi {

 

    // Metode untuk melakukan registrasi pengguna
    public boolean registerUser(UserInfo user) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
            
            // Pastikan Anda memiliki query insertUser di mapper yang sesuai
            userMapper.insertUser(user);  // Menyimpan data pengguna
            session.commit();  // Menyimpan perubahan ke database
            return true;  // Registrasi berhasil
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Jika terjadi kesalahan
        }
    }

    
    
    
    public UserInfo login(String username, String password) {
    try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
        UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
        UserInfo user = userMapper.selectUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}

