package com.inventory.service;


import com.inventory.entity.Supplier;
import com.inventory.exception.*;
import com.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier createSupplier(Supplier supplier) {
        Supplier savedSupplier = supplierRepository.save(supplier);
        System.out.println("Supplier created successfully: " + savedSupplier);
        return savedSupplier;
    }

    @Override
    public Supplier updateSupplier(Long supplierId, Supplier supplier) {
        Optional<Supplier> existingSupplierOpt = supplierRepository.findById(supplierId);
        if (existingSupplierOpt.isPresent()) {
            Supplier existingSupplier = existingSupplierOpt.get();
            existingSupplier.setName(supplier.getName());
            existingSupplier.setContactInfo(supplier.getContactInfo());
            existingSupplier.setProductsSupplied(supplier.getProductsSupplied());

            Supplier updatedSupplier = supplierRepository.save(existingSupplier);
            System.out.println("Supplier updated successfully: " + updatedSupplier);
            return updatedSupplier;
        } else {
            throw new RuntimeException("Supplier not found with ID " + supplierId);
        }
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        supplierRepository.deleteById(supplierId);
        System.out.println("Supplier deleted successfully with ID: " + supplierId);
    }

    @Override
    public Supplier getSupplierById(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() ->
                new SupplierNotFoundException("Supplier with ID " + supplierId + " not found."));
        System.out.println("Fetched supplier successfully: " + supplier);
        return supplier;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        System.out.println("Fetched all suppliers successfully.");
        return suppliers;
    }
}
