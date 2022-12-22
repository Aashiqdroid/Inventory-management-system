package com.aashiq.service;

import com.aashiq.entity.Item;
import com.aashiq.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> showAllItems() {
        return itemRepository.findAll();
    }

    public void deleteByID(Long id) {
        itemRepository.deleteById(id);
    }

    public void updateItem(Long id, Item item) {
        Item updatedItem = itemRepository.findById(id).get();
        updatedItem.setItemName(item.getItemName());
        updatedItem.setItemBrand(item.getItemBrand());
        updatedItem.setType(item.getType());
        updatedItem.setExpiryDate(item.getExpiryDate());
        updatedItem.setPrice(item.getPrice());
        itemRepository.save(updatedItem);
    }

    public List<Item> getFilteredItems0(String itemName, String itemBrand) {
        List<Item> items=itemRepository.findByItemNameOrItemBrand(itemName,itemBrand);
        return items;
    }
    public List<Item> searchProducts(String name,String brand) {
        List<Item> products=itemRepository.searchProducts(name,brand);
        return products;
    }


    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> filter(Long from,Long to){
        return itemRepository.filter(from,to);
    }
}
