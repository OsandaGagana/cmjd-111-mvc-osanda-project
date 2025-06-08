/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.model;

import edu.ijse.mvc.db.DBConnection;
import edu.ijse.mvc.dto.Itemdto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Anjana
 */
public class ItemModel {
    
    public String saveItem(Itemdto itemDto) throws Exception{
        System.out.println("item model data :" +itemDto);
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Item VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, itemDto.getId());
        statement.setString(2, itemDto.getDesc());
        statement.setString(3, itemDto.getPack());
        statement.setDouble(4, itemDto.getUnitPrice());
        statement.setInt(5, itemDto.getQoh());
        
        return statement.executeUpdate() > 0 ? "Success" : "Fail";
    }
    
        public String updateItem(Itemdto itemDto) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Item SET Description =?, PackSize = ?, UnitPrice=?, QtyOnHand = ? WHERE ItemCode = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, itemDto.getDesc());
        statement.setString(2, itemDto.getPack());
        statement.setDouble(3, itemDto.getUnitPrice());
        statement.setInt(4, itemDto.getQoh());
        
        statement.setString(5, itemDto.getId());
        
        return statement.executeUpdate() > 0 ? "Success" : "Fail";
    }
    
    public String deleteItem(String code) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Item WHERE ItemCode = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, code);
        
        return statement.executeUpdate() > 0 ? "Success" : "Fail";
    }
    
    public Itemdto searchItem(String code) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Item WHERE ItemCode = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, code);
        
        ResultSet rst = statement.executeQuery();
        
        if(rst.next()){
            return new Itemdto(rst.getString("ItemCode"),
                    rst.getString("Description"),
                    rst.getString("PackSize"),
                    rst.getDouble("UnitPrice"), 
                    rst.getInt("QtyOnHand"));
        }
        return null;
    }
    
    public ArrayList<Itemdto> getAllItem() throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Item";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ResultSet rst = statement.executeQuery();
        ArrayList<Itemdto> itemDtos = new ArrayList<>();
        while(rst.next()){
            itemDtos.add(new Itemdto(rst.getString("ItemCode"),
                    rst.getString("Description"),
                    rst.getString("PackSize"),
                    rst.getDouble("UnitPrice"), 
                    rst.getInt("QtyOnHand")));
        }
        return itemDtos;
        
    }
}