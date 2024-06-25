package com.majornick.ecommerce.customer;

import com.majornick.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public Long saveCustomer(CustomerDTO customerDTO) {
        return customerRepo.save(customerMapper.toCustomer(customerDTO)).getId();
    }
    public CustomerDTO findById(Long id) {
        return customerRepo.findById(id)
                .map(customerMapper::toDTO)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("customer  with the id: %d not Found", id)));
    }

    public void updateCustomer(@Valid CustomerDTO customerDTO) {
        Customer customer = customerRepo.findById(customerDTO.getId()).orElseThrow( () -> new CustomerNotFoundException(
                String.format("Cannot update customer,  customer  with the id: %d not Found", customerDTO.getId())
        ));
        mergeCustomer(customer, customerDTO);
        customerRepo.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerDTO customerDTO) {
        if (StringUtils.isNotBlank(customerDTO.getFirstName())) {
            customer.setFirstName(customerDTO.getFirstName());
        }
        if (StringUtils.isNotBlank(customerDTO.getLastName())) {
            customer.setLastName(customerDTO.getLastName());
        }
        if (customerDTO.getAddress() != null) {
            customer.setAddress(customerDTO.getAddress());
        }
        if (StringUtils.isNotBlank(customerDTO.getEmail())) {
            customer.setEmail(customerDTO.getEmail());
        }
    }
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }


    public List<CustomerDTO> getAll() {
        return customerRepo
                .findAll()
                .stream()
                .map(customerMapper::toDTO)
                .toList();
    }
}
