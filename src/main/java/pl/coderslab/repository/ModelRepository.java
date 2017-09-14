package pl.coderslab.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Models;



public interface ModelRepository extends JpaRepository<Models, Long> {
	Models findById(long id);
}
