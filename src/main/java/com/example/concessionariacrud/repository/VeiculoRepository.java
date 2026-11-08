package com.example.concessionariacrud.repository;

import com.example.concessionariacrud.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    // Método para filtrar por marca
    List<Veiculo> findByMarca(String marca);

    // Método para filtrar por status
    List<Veiculo> findByStatus(String status);

    // Método personalizado para filtrar por marca e status
    @Query("SELECT v FROM Veiculo v WHERE (:marca IS NULL OR v.marca = :marca) AND (:status IS NULL OR v.status = :status)")
    List<Veiculo> findByMarcaAndStatus(@Param("marca") String marca, @Param("status") String status);
}
