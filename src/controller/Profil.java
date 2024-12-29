/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.UserInfoMapper;
import model.UserInfo;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

public class Profil {

    public UserInfo getUserProfile(int userId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
            return userMapper.selectUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mendapatkan data profil.");
        }
    }

    public void updateUserProfile(UserInfo updatedUser) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
            userMapper.updateUserProfile(updatedUser);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal memperbarui data profil.");
        }
    }
}
