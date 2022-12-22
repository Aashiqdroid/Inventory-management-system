package com.aashiq.controller;

import com.aashiq.entity.Item;
import com.aashiq.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("")
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        itemService.addItem(item);
        return ResponseEntity.ok(item);
    }
    @GetMapping("{id}")
    public Optional<Item> itemById(@PathVariable Long id){
        return itemService.findItemById(id);
    }

    @GetMapping("")
    public List<Item> showItemList() {
        return itemService.showAllItems();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteByID(@PathVariable Long id) {
        itemService.deleteByID(id);
        return ResponseEntity.ok(id);
    }

    //UPDATE ITEM
    @PutMapping("{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody Item item) {
        itemService.updateItem(id, item);
        return ResponseEntity.ok(item);
    }



    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchProducts(@RequestParam("name") String name,@RequestParam("brand") String brand){
        return ResponseEntity.ok(itemService.searchProducts(name, brand));
    }

    @GetMapping("/search0")
    public ResponseEntity<List<Item>> searchProducts0(@RequestParam("name") String name,@RequestParam("brand") String brand){
        return ResponseEntity.ok(itemService.getFilteredItems0(name, brand));
    }


    @GetMapping("/filterbyprice")
    public ResponseEntity<List<Item>> filterByPrice(@RequestParam("from") Long from,@RequestParam("to") Long to){

        return ResponseEntity.ok(itemService.filter(from, to));
    }




}
