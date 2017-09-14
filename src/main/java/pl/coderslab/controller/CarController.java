package pl.coderslab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

	@RequestMapping("/addForm")
	public String showForm() {
		return "addForm";
	}

	@GetMapping("/add")
	public String addForm(Model m, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		if (((String) sess.getAttribute("logged")).equals("true")) {
			m.addAttribute("Car", new Car());
			return "addForm";
		} else {
			return "/AutoKomis";
		}
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

		}
	}

	@GetMapping("/login")
	public String loginForm(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		System.out.println(sess.getAttribute("logged"));
		if (sess.getAttribute("logged")==null) {
			return "login";
		} else {
			return "redirect:/car/add";
		}

	}

	@PostMapping("/login")
	public String postAddForm(Model m, @RequestParam String login, @RequestParam String password) {
		if (login.equals("admin") && password.equals("admin")) {
			m.addAttribute("logged", "true");
			return "redirect:/car/setSession";
		} else {
			return "redirect:/car/login";

		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		if ((String) sess.getAttribute("logged") == "true") {
			System.out.println("aaaaaa");
			sess.invalidate();
		}
		return "redirect:/";

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
	public String returnHPandSize(Model model, @RequestParam String horsepower, @RequestParam String engineSize,
			HttpServletResponse response) throws IOException {
		try {
			int horsepower1 = Integer.parseInt(horsepower);
			int engineSize1 = Integer.parseInt(engineSize);
			List<Car> cars = carRepo.findByHorsepowerGreaterThanAndEngineSizeGreaterThan(horsepower1, engineSize1);
			model.addAttribute("findCars", cars);
			return "brandView";
		} catch (NumberFormatException e) {
			String cars = "Bledne dane";

			JOptionPane.showMessageDialog(new JFrame(), "Eggs are not supposed to be green.");
			return "/AutoKomis";
			//response.sendRedirect("/AutoKomis");

		}

	}

	@RequestMapping("/byPrice")
	public String returnPrice(Model model, @RequestParam String price, @RequestParam String how,
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
			return "brandView";
			//response.sendRedirect("/AutoKomis/WEB-INF/views/brandView");

		} catch (NumberFormatException e) {
			String cars = "Bledne dane";
			JOptionPane.showMessageDialog(new JFrame(), "Eggs are not supposed to be green.");
			return "/AutoKomis";
			//response.sendRedirect("/AutoKomis");

		}

	}

	@RequestMapping("/remove/{id}")

	public String removeCar(@PathVariable int id, HttpServletResponse response) throws IOException {
		Car car = carRepo.findById(id);
		this.carRepo.delete(car);
		return "redirect:/car/showAll";
//		response.sendRedirect("/AutoKomis/car/showAll");

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

	@PostMapping("/update/{id}")

	public String postForm(@ModelAttribute Car Car, HttpServletResponse response) throws IOException {
		String model = Car.getModel();
		Brand brand = Car.getBrand();

		boolean contain = false;
		for (Models modell : modelRepo.findAll()) {
			if ((model).equals(modell.getName())) {
				Car.setModels(modell);
				contain = true;
				break;
			}

		}
		if (contain == false) {
			Models model1 = new Models(model, brand);
			modelRepo.save(model1);
			long id = model1.getId();
			Models model2 = modelRepo.findById(id);
			Car.setModels(model2);

		}
		this.carRepo.saveAndFlush(Car);
		return "redirect:/car/showAll";
//		response.sendRedirect("/AutoKomis/car/showAll");
	}

	@RequestMapping("/setSession")
	public String setSession(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();

		sess.setAttribute("logged", "true");
		return "redirect:/car/add";
		//response.sendRedirect("/AutoKomis/car/add");

	}
}
