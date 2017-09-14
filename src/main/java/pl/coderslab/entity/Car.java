package pl.coderslab.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.ui.Model;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Transient
	@NotEmpty
	private String model;
	@ManyToOne
	private Models models;
	@ManyToOne
	private Brand brand;
	private double engineSize;
	private long horsepower;
	private double price;
	private String shape;
	
	
	public Car(){
		
	}
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Car(String model, Models models, Brand brand, double engineSize, long horsepower, double price, String shape) {
		super();
		this.model = model;
		this.models = models;
		this.brand = brand;
		this.engineSize = engineSize;
		this.horsepower = horsepower;
		this.price = price;
		this.shape=shape;
	}



	
	public String getShape() {
		return shape;
	}



	public void setShape(String shape) {
		this.shape = shape;
	}



	public Brand getBrand() {
		return brand;
	}



	public void setBrand(Brand brand) {
		this.brand = brand;
	}



	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Models getModels() {
		return models;
	}
	public void setModels(Models models) {
		this.models = models;
	}
	public long getHorsepower() {
		return horsepower;
	}
	public void setHorsepower(long horsepower) {
		this.horsepower = horsepower;
	}
	public double getengineSize() {
		return engineSize;
	}
	public void setengineSize(double engineSize) {
		this.engineSize = engineSize;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}



	@Override
	public String toString() {
		return "Car Model: " + models + ", brand:" + brand + ", engineSize=" + engineSize
				+ ", horsepower=" + horsepower + ", price=" + price + ", shape=" + shape ;
	}
	
	
	
	
	
	
}
