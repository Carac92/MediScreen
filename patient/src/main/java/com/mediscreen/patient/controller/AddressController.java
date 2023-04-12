package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Address;
import com.mediscreen.patient.service.AddressService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    public final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PutMapping("/add")
    public void addAddress(@RequestParam Address address) {
        addressService.addAddress(address);
    }
    @PutMapping("/delete/{id}")
    public void deleteAddress(@RequestParam Long id) {
        addressService.deleteAddress(id);
    }
    @PutMapping("/update")
    public void updateAddress(@RequestParam Address address) {
        addressService.updateAddress(address);
    }
}
