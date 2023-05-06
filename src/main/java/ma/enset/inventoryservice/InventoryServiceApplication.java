package ma.enset.inventoryservice;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {
	ProductRepository productRepository;
	RepositoryRestConfiguration repositoryRestConfiguration;

	public InventoryServiceApplication(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
		this.productRepository = productRepository;
		this.repositoryRestConfiguration = repositoryRestConfiguration;
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Product.class);

		for (int i=0; i<10; i++){
			Product product = Product.builder()
					.id(null)
					.name("product"+(i+1))
					.price(Math.random()*100)
					.quantity((int) Math.random()*10)
					.build();

			productRepository.save(product);
		}
	}
}
