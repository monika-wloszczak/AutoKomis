package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.entity.Models;
import pl.coderslab.repository.ModelRepository;

public class ModelConverter implements Converter<String, Models> {

	@Autowired
	private ModelRepository ModelsRepo;

	//@Override
	public Models convert(String source) {

		Models entity =ModelsRepo.findById(Long.parseLong(source));
		return entity;

	}
}
