package com.example.teststm.security;

import com.example.teststm.entities.Customer;
import com.example.teststm.mappers.converters.DtoConverter;
import com.example.teststm.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DtoConverter dtoConverter;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.getCustomerByLogin(username);
        return dtoConverter.simpleConvert(customer, UserDetailsImpl.class);
    }
}
