
package edu.ijse.mvc.controller;

import edu.ijse.mvc.dto.Itemdto;
import edu.ijse.mvc.model.ItemModel;
import java.util.ArrayList;


/**
 *
 * @author Anjana
 */
public class ItemController {
    private  ItemModel itemModel =new ItemModel();
    
    public String saveItem(Itemdto itemdto) throws Exception{
        return itemModel.saveItem(itemdto);
        
    }
    
    public String updateItem(Itemdto itemDto) throws Exception{
        return itemModel.updateItem(itemDto);
    }
    
    public String deleteItem(String itemId) throws Exception{
        return itemModel.deleteItem(itemId);
    }
    
    public Itemdto searchItem(String itemId) throws Exception{
        return itemModel.searchItem(itemId);
    }
    
    public ArrayList<Itemdto> getAllItem() throws Exception{
        return itemModel.getAllItem();
    }
    
    
}