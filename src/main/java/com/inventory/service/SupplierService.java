package com.inventory.service;

import com.inventory.entity.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);
    Supplier updateSupplier(Long supplierId, Supplier supplier);
    void deleteSupplier(Long supplierId);
    Supplier getSupplierById(Long supplierId);
    List<Supplier> getAllSuppliers();
}
