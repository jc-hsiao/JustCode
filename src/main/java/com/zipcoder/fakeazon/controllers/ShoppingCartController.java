package com.zipcoder.fakeazon.controllers;

import com.zipcoder.fakeazon.models.ItemCount;
import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.services.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartController {

    private final ShoppingCartServices cartService;

    @Autowired
    public ShoppingCartController(ShoppingCartServices cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<?> findCartById(@PathVariable Integer id){
        return cartService.findOne(id).map( cart ->
                 ResponseEntity.ok().body(cart)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<?> findCartByUser(@PathVariable Integer userId){
        return cartService.findCartByUser(userId).map( cart ->
                ResponseEntity.ok().body(cart)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cart/all")
    public ResponseEntity<List<ShoppingCart>> findAll(){
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }

    // TODO PUT Mappings for adding items to cart, removing, and also clearing cart items, updating quantities
//    @PutMapping("/cart/{cartId}/item/{countId}")
//    public ResponseEntity<ShoppingCart> addItemCountToCart(@PathVariable Integer cartId, @PathVariable Integer countId, @RequestParam int quantity){
//        return new ResponseEntity<>(cartService.addItemCountToCart(cartId, countId, quantity), HttpStatus.OK);
//    }
}
