package com.webHook.Service;


/*    */  
/*    */ import com.webHook.Entity.Order;
/*    */ import com.webHook.Repository.OrderRepository;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class OrderService {
/*    */   @Autowired
/*    */   OrderRepository OrderRepository;
/*    */   
/*    */   public void save(Order order) {
/* 16 */     this.OrderRepository.save(order);
/*    */   }
/*    */ }


/* Location:              C:\Users\stark\Desktop\webHook-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\webHook\Service\OrderService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */