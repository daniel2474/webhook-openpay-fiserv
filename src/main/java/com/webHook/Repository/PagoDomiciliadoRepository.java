package com.webHook.Repository;



import com.webHook.Entity.PagoDomiciliado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoDomiciliadoRepository extends JpaRepository<PagoDomiciliado, Long> {
}

