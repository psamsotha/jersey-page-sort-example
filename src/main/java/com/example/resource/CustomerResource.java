package com.example.resource;

import com.example.domain.Customer;
import com.example.provider.Pagination;
import com.example.repository.CustomerRepository;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;


@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerRepository repository;

    @Autowired
    public CustomerResource(CustomerRepository repository) {
        this.repository = repository;
    }

    @GET
    public Response getAllCustomers(@Pagination Pageable pageable) {
        List<Customer> customers = repository.findAll(pageable).getContent();
        return Response.ok(new GenericEntity<List<Customer>>(customers) {}).build();
    }
}
