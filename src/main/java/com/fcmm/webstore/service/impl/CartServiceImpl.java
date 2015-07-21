package com.fcmm.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcmm.webstore.domain.Cart;
import com.fcmm.webstore.domain.repository.CartRepository;
import com.fcmm.webstore.service.CartService;

@Service
public class CartServiceImpl implements CartService{
  
  @Autowired
  private CartRepository cartRepository;

  public Cart create(Cart pCart) {
    return cartRepository.create(pCart);
  }

  public Cart read(String pCartId) {
    return cartRepository.read(pCartId);
  }

  public void update(String pCartId, Cart pCart) {
    cartRepository.update(pCartId, pCart);
  }

  public void delete(String pCartId) {
    cartRepository.delete(pCartId);
    
  }

}