package pl.coderslab.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Brand;
import pl.coderslab.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	Car findById(long id);
	List<Car> findByShape(String shape);
	List<Car> findByBrand(Brand brand);
	
	List<Car> findByHorsepowerGreaterThanAndEngineSizeGreaterThan(long horsepower, double engineSize);
//	List<Car> findByEngine_sizeAndHorsepower(long horsepower, double engine_size);
	
	List<Car> findByPriceLessThan(double price);
	List<Car> findByPriceGreaterThan(double price);
}
