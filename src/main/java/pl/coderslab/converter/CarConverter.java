package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.entity.Car;
import pl.coderslab.repository.CarRepository;



public class CarConverter implements Converter<String, Car> {

	@Autowired
	private CarRepository carRepo;

	//@Override
	public Car convert(String source) {

		Car entity =carRepo.findById(Long.parseLong(source));
		return entity;

	}
}
