package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Address;
import com.mediscreen.patient.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void addAddress(Address address) {
        addressRepository.save(address);
    }
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
    public void updateAddress(Address address) {
        addressRepository.save(address);
    }
}
