package com.moa.server.entity.inventory.service;

import com.moa.server.entity.inventory.*;
import com.moa.server.entity.inventory.dto.DefectDTO;
import com.moa.server.entity.inventory.dto.InventoryDTO;
import com.moa.server.entity.inventory.dto.LogisticsInfoDTO;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    //재고조회
    public Page<InventoryDTO> getInventoryList(String search, Pageable pageable) {
        Page<InventoryEntity> entityPage = inventoryRepository.findByProduct_ProductNameContaining(search, pageable);

        // .map()을 통해 간단하게 DTO로 변환
        return entityPage.map(InventoryEntity::toDTO);
    }

    // 재고 상세조회
    public Page<LogisticsInfoDTO> getInventoryListInfo(Integer productId, Pageable pageable) {
        Page<LogisticsEntity> entityPage = logisticsRepository.findByProduct_ProductId(productId, pageable);

        // .map()을 통해 간단하게 DTO로 변환
        return entityPage.map(LogisticsEntity::toDTO);
    }

    public Page<DefectDTO> getDefectList (String productName, Pageable pageable) {
        Page<DefectEntity> entityPage = defectRepository.findByProduct_ProductNameContaining( productName,  pageable);

        // .map()을 통해 간단하게 DTO로 변환
        return entityPage.map(DefectEntity::toDTO);
    }


}
