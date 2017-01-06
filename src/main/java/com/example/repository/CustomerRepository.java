
package com.example.repository;

import com.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Paul Samsotha
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
