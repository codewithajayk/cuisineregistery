package de.quandoo.recruitment.registry;

import static java.util.Comparator.comparingInt;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.logging.*;

import de.quandoo.recruitment.registry.api.CuisinesRegistry;
import de.quandoo.recruitment.registry.model.Customer;
import de.quandoo.recruitment.registry.model.cusine.Cuisine;

public class InMemoryCuisinesRegistry implements CuisinesRegistry {

	private Map<Cuisine, Set<Customer>> cusineCustomersMap = new HashMap<>();
	private Map<Customer, Set<Cuisine>> customerCusinesMap = new HashMap<>();

	private Log logger = LogFactory.getLog(getClass());

	@Override
	public void register(Customer customer, Cuisine cuisine) {
		logger.info("Registering the customer with ID " + customer.getId() +  " with cuisine " +  cuisine.getCuisineName() + "And Vice Versa");
		initRelations(customer, cuisine);
	}

	private void initRelations(Customer customer, Cuisine cuisine) {
		cusineCustomersMap.computeIfAbsent(cuisine,  customers -> new HashSet<>()).add(customer);
		customerCusinesMap.computeIfAbsent(customer,  cuisines -> new HashSet<>()).add(cuisine);
	}

	@Override
	public List<Cuisine> customerCuisines(Customer customer) {
		logger.info("Getting Cuisines for a " + customer);
		return new ArrayList<>(customerCusinesMap.getOrDefault(customer, Collections.emptySet()));
	}

	@Override
	public List<Cuisine> topCuisines(int limit) {
		logger.info("Getting Top " + limit + " Cuisines");
		return cusineCustomersMap.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(comparingInt(customers -> customers.getValue().size())))
				.limit(limit)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}

	@Override
	public List<Customer> cuisineCustomers(Cuisine cuisine) {
		logger.info("Getting all customers for " + cuisine);
		return new ArrayList<>(cusineCustomersMap.getOrDefault(cuisine, Collections.emptySet()));
	}
}