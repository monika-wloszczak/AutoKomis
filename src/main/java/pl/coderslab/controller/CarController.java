package pl.coderslab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.entity.Models;
import pl.coderslab.entity.Brand;
import pl.coderslab.entity.Car;
import pl.coderslab.entity.Check;
import pl.coderslab.repository.BrandRepository;
import pl.coderslab.repository.CarRepository;
import pl.coderslab.repository.ModelRepository;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarRepository carRepo;
	@Autowired
	private BrandRepository brandRepo;
	@Autowired
	private ModelRepository modelRepo;

	@GetMapping("/add")
	public String addForm(Model m) {
		m.addAttribute("Car", new Car());
		return "addForm";
	}

	@PostMapping("/add")
	public String postAddForm(Model m, @Valid Car Car, BindingResult result) {
		if (result.hasErrors()) {
			m.addAttribute("Car", new Car());
			System.out.println("sa bledy");
			System.out.println(Car.toString());
			return "addForm";
		} else {

			String name = Car.getModel();
			Brand brand = Car.getBrand();
			boolean contain = false;
			for (Models modell : modelRepo.findAll()) {
				if ((name).equals(modell.getName())) {
					Car.setModels(modell);
					carRepo.save(Car);
					contain = true;
					break;
				}

			}
			if (contain == false) {
				Models model1 = new Models(name, brand);
				modelRepo.save(model1);
				long id = model1.getId();
				Models model2 = modelRepo.findById(id);
				Car.setModels(model2);
				carRepo.save(Car);
			}
			return "accept";
			// return "home";
		}
	}

	// @GetMapping("/remove")
	// public String removeForm(Model m){
	// Car car = carRepo.findById(2);
	// this.daoBook.delete(b);
	// return "book deleted";
	// m.addAttribute("Car", new Car());
	// return "addForm";
	// }
	// @PostMapping("/add")
	// public String removeForm(Model m){

	@GetMapping("/login")
	public String loginForm(Model m) {

		return "login";
	}

	@PostMapping("/login")
	public void postAddForm(HttpServletResponse response, Model m, @RequestParam String login,
			@RequestParam String password) {
		if (login.equals("admin") && password.equals("admin")) {

			try {
				response.sendRedirect("add");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {
				response.sendRedirect("car/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@ModelAttribute("brand")
	public List<Brand> getBrand() {
		List<Brand> brands = brandRepo.findAll();
		return brands;
	}

	@ModelAttribute("models")
	public List<Models> getModels() {
		List<Models> models = modelRepo.findAll();
		return models;
	}

	@RequestMapping("/addForm")
	public String showForm() {
		return "addForm";
	}

	@RequestMapping("/byShape")
	@Validated
	public String returnView(Model model, @Valid Check check, BindingResult result, @RequestParam String shape) {

		if (result.hasErrors()) {

			return "/AutoKomis";
		} else {
			List<Car> cars = carRepo.findByShape(shape);
			model.addAttribute("findCars", cars);
			return "brandView";
		}
	}

	@RequestMapping("/byBrand")
	public String returnBrand(Model model, @RequestParam String name) {
		Brand brand1 = brandRepo.findByName(name);
		List<Car> cars = carRepo.findByBrand(brand1);
		model.addAttribute("findCars", cars);
		return "brandView";

	}

	@RequestMapping("/byHorsePowerOrEngine")
	public void returnHPandSize(Model model, @RequestParam String horsepower, @RequestParam String engineSize,
			HttpServletResponse response) throws IOException {
		try {
			int horsepower1 = Integer.parseInt(horsepower);
			int engineSize1 = Integer.parseInt(engineSize);
			List<Car> cars = carRepo.findByHorsepowerGreaterThanAndEngineSizeGreaterThan(horsepower1, engineSize1);
			model.addAttribute("findCars", cars);
			response.sendRedirect("/AutoKomis/WEB-INF/views/brandView");

		} catch (NumberFormatException e) {
			String cars = "Bledne dane";
			
			JOptionPane.showMessageDialog(new JFrame(), "Eggs are not supposed to be green.");
			response.sendRedirect("/AutoKomis");

		}

	}

	@RequestMapping("/byPrice")
	public void returnPrice(Model model, @RequestParam String price, @RequestParam String how,
			HttpServletResponse response) throws IOException {
		try {
			double price1 = Double.parseDouble(price);
			List<Car> cars = new ArrayList<>();
			if (how.equals("lower")) {
				cars = carRepo.findByPriceLessThan(price1);
			} else {
				cars = carRepo.findByPriceGreaterThan(price1);
			}
			model.addAttribute("findCars", cars);
			response.sendRedirect("/AutoKomis/WEB-INF/views/brandView");

		} catch (NumberFormatException e) {
			String cars = "Bledne dane";		
			JOptionPane.showMessageDialog(new JFrame(), "Eggs are not supposed to be green.");
			response.sendRedirect("/AutoKomis");

		}

	}

	@RequestMapping("/remove/{id}")

	public String removeCar(@PathVariable int id) {
		Car car = carRepo.findById(id);
		this.carRepo.delete(car);
		;
		return "car/showAll";
	}

	@RequestMapping("/showAll")
	public String showAll(Model model) {
		List<Car> cars = carRepo.findAll();
		model.addAttribute("findCars", cars);
		return "brandView";
	}

	@GetMapping("/update/{id}")
	public String formBinding(@PathVariable long id, Model m) {

		Car car = carRepo.findById(id);
		m.addAttribute("Car", car);
		return "addForm";
	}

	@PostMapping("/update")

	public String postForm(@ModelAttribute Car Car) {
		this.carRepo.saveAndFlush(Car);

		return "car/showAll";
	}
}
