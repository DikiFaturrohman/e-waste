/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.WasteMapper;
import model.Waste;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import java.util.List;

public class Kategori {

    public List<Waste> getAllWaste() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            WasteMapper wasteMapper = session.getMapper(WasteMapper.class);
            return wasteMapper.selectAllWaste();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil data kategori sampah.");
        }
    }
}