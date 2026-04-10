package com.moa.server.entity.inventory.service;

import com.moa.server.entity.inventory.*;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//물품 재교 관련
@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {
    private final UserRepository userRepository;

    private final VendorRepository vendorRepository;
    private final OrderformRepository orderformRepository;
    private final OrdererRepository ordererRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final DefectRepository defectRepository;
    private final TransactionRepository transactionRepository;
    private final StorageRepository storageRepository;
    private final LogisticsRepository logisticsRepository;


}
