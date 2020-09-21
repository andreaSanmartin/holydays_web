package app.repository;

import app.model.Provincia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    /*@Query("select provincia from Provincia provincia where provincia.id = :provincia")
    Provincia findById(int id);*/
}
