package com.webHook.Service;


 
 import com.webHook.Entity.PagoDomiciliado;
import com.webHook.Repository.PagoDomiciliadoRepository;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 @Service
 @Transactional
 public class PagoDomiciliadoService {
   @Autowired
   PagoDomiciliadoRepository pagoDomiciliadoRepository;
   
   public PagoDomiciliado save(PagoDomiciliado pagoDomiciliado) {
	   return pagoDomiciliadoRepository.save(pagoDomiciliado);
   }
 }
