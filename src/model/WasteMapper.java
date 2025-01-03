/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import model.Waste;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WasteMapper {

    @Select("SELECT * FROM wastes")
    List<Waste> selectAllWaste();
}
