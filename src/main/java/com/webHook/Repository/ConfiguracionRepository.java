package com.webHook.Repository;



import com.webHook.Entity.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionRepository extends JpaRepository<Token, Long> {
  Optional<Token> findById(long paramLong);
  
  boolean existsById(long paramLong);
}


/* Location:              C:\Users\stark\Desktop\webHook-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes\com\webHook\Repository\ConfiguracionRepository.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */