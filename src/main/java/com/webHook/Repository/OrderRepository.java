package com.webHook.Repository;



import com.webHook.Entity.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  Optional<Order> findByNoPedido(String paramString);
  
  boolean existsByNoPedido(String paramString);
}


/* Location:              C:\Users\stark\Desktop\webHook-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\webHook\Repository\OrderRepository.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */