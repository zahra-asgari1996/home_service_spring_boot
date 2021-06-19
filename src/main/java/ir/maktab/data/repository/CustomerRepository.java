package ir.maktab.data.repository;


import ir.maktab.data.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    void saveNewCustomer(Customer customer);
//    void updateCustomer(Customer customer);
//    void deleteCustomer(Customer customer);
//    List<Customer> fetchAllCustomers();

    Optional<Customer> findByEmail(String email);
}
