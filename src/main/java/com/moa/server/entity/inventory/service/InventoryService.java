package com.moa.server.entity.inventory.service;

import com.moa.server.entity.inventory.*;
import com.moa.server.entity.inventory.dto.*;
import com.moa.server.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    //불량 재고 조회
    public Page<DefectDTO> getDefectList (String productName, Pageable pageable) {
        Page<DefectEntity> entityPage = defectRepository.findByInventory_Product_ProductNameContaining( productName,  pageable);

        // .map()을 통해 간단하게 DTO로 변환
        return entityPage.map(DefectEntity::toDTO);
    }

    //불량 재고 상세조회
    public Page<DefectDTO> getDefectListInfo(Integer productId, Pageable pageable) {
        Page<DefectEntity> entityPage = defectRepository.findByInventoryId(productId, pageable);

        return entityPage.map(DefectEntity::toDTO);
    }

    //구매관리 (발주현항) 리스트
    public Page<OrderDTO> getOrdererList (String productName, Pageable pageable) {
        List<OrdererEntity> allEntities = ordererRepository.findByProduct_ProductNameContaining( productName);

        Map<Integer, OrderDTO> groupedMap = allEntities.stream()
                .collect(Collectors.groupingBy(
                        OrdererEntity::getOrderformId,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            int totalSno = list.stream().mapToInt(o -> o.getOrderSno() != null ? o.getOrderSno() : 0).sum();
                            int totalUnitPrice = list.stream().mapToInt(o -> o.getUnitPrice() != null ? o.getUnitPrice() : 0).sum();
                            int totalPrice = list.stream().mapToInt(o -> (o.getOrderSno() != null && o.getUnitPrice() != null)
                                    ? o.getOrderSno() * o.getUnitPrice() : 0).sum();
                            //  물품합치기용
                            String firstProductName = list.get(0).getProduct() != null ? list.get(0).getProduct().getProductName() : "알 수 없음";
                            String combinedProductName = (list.size() > 1)
                                    ? firstProductName + " 외 " + (list.size() - 1) + "건"
                                    : firstProductName;


                            OrdererEntity first = list.get(0);
                            // 기존 toDTO() 대신 여기서 합산된 값을 넣어 빌드합니다.
                            return OrderDTO.builder()
                                    .ordererId(first.getOrdererId())
                                    .productName(combinedProductName)
                                    .orderSno(totalSno)         // DTO의 수량 필드에 합계 대입
                                    .unitPrice(totalUnitPrice)   // DTO의 단가 필드에 합계 대입
                                    .totalPrice(totalPrice)     // 총 금액 대입.
                                    .ordererId(first.getOrdererId())
                                    .productId(first.getProductId())
                                    .orderformId(first.getOrderformId())
                                    .orderformDate(first.getOrderform() != null ? first.getOrderform().getOrderformDate() : null)
                                    .stockInDate(first.getOrderform() != null ? first.getOrderform().getStockInDate() : null)
                                    .orderStatus(first.getOrderform() != null ? first.getOrderform().getOrderStatus() : null)
                                    .vendorId(first.getOrderform() != null ? first.getOrderform().getVendor().getVendorId() : null)
                                    .vendorCord(first.getOrderform().getVendor()  != null ? first.getOrderform().getVendor().getVendorCord() : null)
                                    .vendorName(first.getOrderform().getVendor() != null ? first.getOrderform().getVendor().getVendorName() : null)
                                    .build();
                        })
                ));

        List<OrderDTO> groupedList = new ArrayList<>(groupedMap.values());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), groupedList.size());

        if (start > end) return new PageImpl<>(new ArrayList<>(), pageable, groupedList.size());

        return new PageImpl<>(groupedList.subList(start, end), pageable, groupedList.size());

    }

    //구매관리 (발주현항) 상세리스트
    public Page<OrderDTO> getOrdererListInfo(Integer orderformId, Pageable pageable) {
        Page<OrdererEntity> entityPage = ordererRepository.findByOrderformId(orderformId, pageable);

        return entityPage.map(OrdererEntity::toinfoDTO);
    }

    // 구매관리할 떄 물품 정보를 가져오기 위해서
    public Page<ProductCordMapDTO> getProductCord(Pageable pageable) {
        Page<ProductEntity> entityPage = productRepository.findAll(pageable);
        return entityPage.map(ProductEntity::toDTO);
    }

    // 오더 수량 수정 및 추가
    @Transactional
    public void putOrdererSno(Integer orderFormId ,List<OrderPutDTO> dtoList) {
        for (OrderPutDTO dto : dtoList) {
            if (dto.getOrdererId() != null) {
                // 1. 기존 데이터 수정 (UPDATE)
                ordererRepository.findById(dto.getOrdererId()).ifPresent(orderer -> {
                    orderer.setOrderSno(dto.getOrderSno());
                });
            } else {

                OrdererEntity newOrderer = OrdererEntity.builder()
                        .orderformId(orderFormId)
                        .productId(dto.getProductId())
                        .unitPrice(dto.getUnitPrice())
                        .orderSno(dto.getOrderSno())
                        // ... 기타 필요한 필드 세팅
                        .build();
                ordererRepository.save(newOrderer);
            }
        }
    }
}