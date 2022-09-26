package com.webHook.Service;


/*    */ 
/*    */ import com.webHook.Entity.Token;
/*    */ import com.webHook.Repository.ConfiguracionRepository;
/*    */ import java.util.Optional;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ConfiguracionService {
/*    */   @Autowired
/*    */   ConfiguracionRepository ConfiguracionRepository;
/*    */   
/*    */   public Optional<Token> getById(int id) {
/* 17 */     return this.ConfiguracionRepository.findById(id);
/*    */   }
/*    */ }


/* Location:              C:\Users\stark\Desktop\webHook-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\webHook\Service\ConfiguracionService.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */