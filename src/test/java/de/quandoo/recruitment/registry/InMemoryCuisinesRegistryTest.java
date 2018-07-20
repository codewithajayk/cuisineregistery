package de.quandoo.recruitment.registry;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.*;

import de.quandoo.recruitment.registry.model.Customer;
import de.quandoo.recruitment.registry.model.cusine.*;

public class InMemoryCuisinesRegistryTest {

	private InMemoryCuisinesRegistry cuisinesRegistry ;
	Cuisine frenchCuisine, germanCuisine, italianCuisine, invalidCuisin;

	private Customer customerWitId1, customerWitId13, customerWitId15 ,customerWitId5
	,customerWitId8,customerWitId12, invalidCustomer;

	private static final String CUSTOMER_ID_1 = "1";
	private static final String CUSTOMER_ID_12 = "12";
	private static final String CUSTOMER_ID_13 = "13";
	private static final String CUSTOMER_ID_15 = "15";
	private static final String CUSTOMER_ID_5 = "5";
	private static final String CUSTOMER_ID_8 = "8";
	private static final String CUSTOMER_ID_INVALID = "101";

	/*
	 * This method is used to initialize various customers with their favorite cuisine.
	 */
	@Before
	public void setUp(){
		initCuisines();
		initCustomers();
		initRelations();
	}

	private void initCustomers() {
		customerWitId1 = new Customer(CUSTOMER_ID_1);
		customerWitId13 = new Customer(CUSTOMER_ID_13);
		customerWitId15 = new Customer(CUSTOMER_ID_15);
		customerWitId5 = new Customer(CUSTOMER_ID_5);
		customerWitId8 = new Customer(CUSTOMER_ID_8);
		customerWitId12 = new Customer(CUSTOMER_ID_12);
		invalidCustomer = new Customer(CUSTOMER_ID_INVALID);
	}

	private void initCuisines() {
		cuisinesRegistry = new InMemoryCuisinesRegistry();
		frenchCuisine = new Cuisine(CuisineType.FRENCH.getCusineName());
		germanCuisine = new Cuisine(CuisineType.GERMAN.getCusineName());
		italianCuisine = new Cuisine(CuisineType.ITALIAN.getCusineName());
		invalidCuisin = new Cuisine("Invalid Name");
	}

	private void initRelations() {
		cuisinesRegistry.register(customerWitId1, germanCuisine);
		cuisinesRegistry.register(customerWitId13, germanCuisine);
		cuisinesRegistry.register(customerWitId15, italianCuisine);
		cuisinesRegistry.register(customerWitId5, frenchCuisine);
		cuisinesRegistry.register(customerWitId8, frenchCuisine);
		cuisinesRegistry.register(customerWitId12, germanCuisine);
		cuisinesRegistry.register(customerWitId1, frenchCuisine);
		cuisinesRegistry.register(customerWitId5, germanCuisine);
	}

	@Test
	public void shouldReturnTop2CusinesWhenAsked() {
		assertThat(cuisinesRegistry.topCuisines(2)).isEqualTo(Arrays.asList(germanCuisine, frenchCuisine));
	}

	@Test
	public void shouldReturnCusinesForAValidCustomer() {
		assertThat(cuisinesRegistry.customerCuisines(new Customer(CUSTOMER_ID_1)))
		.isEqualTo(Arrays.asList(frenchCuisine, germanCuisine));
	}

	@Test
	public void shouldReturnEmptyListWhenCustomerIsNotPresent() {
		assertThat(cuisinesRegistry.customerCuisines(invalidCustomer))
		.isEmpty();
	}

	@Test
	public void shouldReturnEmptyListWhenCuisineIsNotPresent() {
		assertThat(cuisinesRegistry.cuisineCustomers(invalidCuisin))
		.isEmpty();
	}

	@Test
	public void shouldReturnCustomersForAValidCuisine() {
		assertThat(cuisinesRegistry.cuisineCustomers(germanCuisine))
		.isEqualTo(Arrays.asList(customerWitId1, customerWitId12, customerWitId13, customerWitId5));
	}
	@Test
	public void shouldReturnEmptyListWhenCustomerIsNull() {
		assertThat(cuisinesRegistry.customerCuisines(null))
		.isEmpty();
	}

	@Test
	public void shouldReturnEmptyListWhenCuisineIsNull() {
		assertThat(cuisinesRegistry.cuisineCustomers(null))
		.isEmpty();
	}
}