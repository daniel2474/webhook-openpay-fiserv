package com.webHook.dto;


/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConfiguracionDTO
/*    */ {
/*    */   private long id;
/*    */   private String tokenAlpha;
/*    */   private String endpointAlpha;
/*    */   private String copiaoculta;
/*    */   
/*    */   ConfiguracionDTO() {}
/*    */   
/*    */   public ConfiguracionDTO(String tokenAlpha, String endpointAlpha, String copiaoculta) {
/* 15 */     this.tokenAlpha = tokenAlpha;
/* 16 */     this.endpointAlpha = endpointAlpha;
/* 17 */     this.copiaoculta = copiaoculta;
/*    */   }
/*    */   
/*    */   public long getId() {
/* 21 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(long id) {
/* 25 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getTokenAlpha() {
/* 29 */     return this.tokenAlpha;
/*    */   }
/*    */   
/*    */   public void setTokenAlpha(String tokenAlpha) {
/* 33 */     this.tokenAlpha = tokenAlpha;
/*    */   }
/*    */   
/*    */   public String getEndpointAlpha() {
/* 37 */     return this.endpointAlpha;
/*    */   }
/*    */   
/*    */   public void setEndpointAlpha(String endpointAlpha) {
/* 41 */     this.endpointAlpha = endpointAlpha;
/*    */   }
/*    */   
/*    */   public String getCopiaoculta() {
/* 45 */     return this.copiaoculta;
/*    */   }
/*    */   
/*    */   public void setCopiaoculta(String copiaoculta) {
/* 49 */     this.copiaoculta = copiaoculta;
/*    */   }
/*    */ }


/* Location:              C:\Users\stark\Desktop\webHook-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\webHook\DTO\ConfiguracionDTO.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */