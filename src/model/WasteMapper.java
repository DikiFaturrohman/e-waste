/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import model.Waste;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface WasteMapper {

    @Select("SELECT * FROM wastes")
    List<Waste> selectAllWaste();
    
    @Insert("INSERT INTO wastes (category, type) VALUES (#{category}, #{type})")
    void insertWaste(Waste waste);

    @Update("UPDATE wastes SET category = #{category}, type = #{type} WHERE id = #{id}")
    void updateWaste(Waste waste);

    @Delete("DELETE FROM wastes WHERE id = #{id}")
    void deleteWaste(int id);

    @Select("SELECT * FROM wastes WHERE id = #{id}")
    Waste selectWasteById(int id);

}
