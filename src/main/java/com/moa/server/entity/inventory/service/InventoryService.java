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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    // 구매관리할 떄 거래처 정보를 가져오기 위해서
    public Page<VendorCordMapDTO> getVendorCord(Pageable pageable) {
        Page<VendorEntity> entityPage = vendorRepository.findAll(pageable);
        return entityPage.map(VendorEntity::toDTO);
    }

    // 입고할 떄 창고 정보를 가져오기 위해서
    public Page<StorageCordMapDTO> getStorageCord(Pageable pageable) {
        Page<StorageEntity> entityPage = storageRepository.findAll(pageable);
        return entityPage.map(StorageEntity::toDTO);
    }

    // 출고할 떄 인벤토리 정보 가져오기 위해서
    public Page<InventoryCordMapDTO> getInventoryCord(String search, Pageable pageable) {
        Page<InventoryEntity> entityPage = inventoryRepository.findByProduct_ProductNameContaining(search, pageable);

        return entityPage.map(InventoryEntity::MapDTO);
    }

    // 오더 수량 수정 및 추가 및 삭제
    @Transactional
    public void putOrdererSno(Integer orderFormId ,List<OrderPutDTO> dtoList) {

        List<OrdererEntity> existingEntities = ordererRepository.findByOrderformId(orderFormId);

        List<Integer> incomingIds = dtoList.stream()
                .map(OrderPutDTO::getOrdererId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        existingEntities.stream()
                .filter(entity -> !incomingIds.contains(entity.getOrdererId()))
                .forEach(ordererRepository::delete);

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

    // 발주폼 추가
    @Transactional
    public void insertOrderForm(OrderFormPostDTO dto) {

        OrderformEntity orderForm = OrderformEntity.builder()
                .vendorId(dto.getVendorId())
                .stockInDate(dto.getStockInDate())
                .orderformDate(dto.getOrderformDate())
                .orderStatus(dto.getOrderStatus())
                .build();

        OrderformEntity savedForm = orderformRepository.save(orderForm);

        List<OrdererEntity> ordererDetails = dto.getItems().stream()
                .map(itemDto -> OrdererEntity.builder()
                        .orderformId(savedForm.getOrderformId()) // 마스터의 ID를 주입
                        .productId(itemDto.getProductId())
                        .orderSno(itemDto.getOrderSno())
                        .unitPrice(itemDto.getUnitPrice())
                        // ordererId(상세 PK)는 Auto Increment로 자동 생성됨
                        .build())
                .collect(Collectors.toList());

        // 상세 내역 리스트를 orderer 테이블에 일괄 저장
        ordererRepository.saveAll(ordererDetails);

        //발주서 등록 시 일반전표 생성
        int totalPrice = dto.getItems().stream()
                .mapToInt(item -> item.getUnitPrice() * item.getOrderSno())
                .sum();

        TransactionEntity transaction = TransactionEntity.builder()
                .vendorId(dto.getVendorId())
                .orderformId(savedForm.getOrderformId())
                .transactionNum(savedForm.getOrderformId())
                .transactionType("일반전표")
                .transactionPrice(totalPrice)
                .transactionMemo("")
                .build();

        transactionRepository.save(transaction);
    }

    // 발주폼 삭제
    @Transactional
    public void DeleteOrderForm(Integer orderFormId) {
        ordererRepository.deleteByOrderformId(orderFormId);
        orderformRepository.deleteById(orderFormId);
    }

    //입고 리스트
    public Page<LogisticsInfoDTO> getLogisticsList ( Pageable pageable) {
        List<LogisticsEntity> allEntities = logisticsRepository.findByLogisticsType();

        Map<Integer, LogisticsInfoDTO> groupedMap = allEntities.stream()
                .collect(Collectors.groupingBy(
                        LogisticsEntity::getLogisticsOrderNum,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {

                            int totalSno = list.stream().mapToInt(o -> o.getLogisticSno() != null ? o.getLogisticSno() : 0).sum();

                            //  물품합치기용
                            String firstProductName = list.get(0).getProduct() != null ? list.get(0).getProduct().getProductName() : "알 수 없음";
                            String combinedProductName = (list.size() > 1)
                                    ? firstProductName + " 외 " + (list.size() - 1) + "건"
                                    : firstProductName;

                            LogisticsEntity first = list.get(0);

                            return LogisticsInfoDTO.builder()
                                    .logisticsId(first.getLogisticsId())
                                    .productName(combinedProductName)
                                    .logisticSno(totalSno)
                                    .storageId(first.getStorageId())
                                    .productId(first.getProductId())
                                    .logisticsOrderNum(first.getLogisticsOrderNum())
                                    .logisticsType(first.getLogisticsType())
                                    .logisticDate(first.getLogisticDate())
                                    .logisticsPrice(first.getLogisticsPrice())
                                    .productCord(first.getProduct() != null ? first.getProduct().getProductCord() : null)
                                    .productPrice(first.getProduct() != null ? first.getProduct().getProductPrice() : null)
                                    .storageCord(first.getStorage() != null ? first.getStorage().getStorageCord() : null)
                                    .storageName(first.getStorage() != null ? first.getStorage().getStorageName() : null)
                                    .build();
                        })
                ));

        List<LogisticsInfoDTO> groupedList = new ArrayList<>(groupedMap.values());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), groupedList.size());

        if (start > end) return new PageImpl<>(new ArrayList<>(), pageable, groupedList.size());

        return new PageImpl<>(groupedList.subList(start, end), pageable, groupedList.size());

    }

    //입고  상세리스트
    public Page<LogisticsInfoDTO> getLogisticsListInfo(Integer logisticsOrderNum, Pageable pageable) {
        Page<LogisticsEntity> entityPage = logisticsRepository.findByLogisticsTypeAndLogisticsOrderNum(logisticsOrderNum , pageable);

        return entityPage.map(LogisticsEntity::toDTO);
    }

    //출고 리스트
    public Page<LogisticsInfoDTO> getLogisticsOutList ( Pageable pageable) {
        List<LogisticsEntity> allEntities = logisticsRepository.findByLogisticsTypeOut();

        Map<Integer, LogisticsInfoDTO> groupedMap = allEntities.stream()
                .collect(Collectors.groupingBy(
                        LogisticsEntity::getLogisticsOrderNum,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {

                            int totalSno = list.stream().mapToInt(o -> o.getLogisticSno() != null ? o.getLogisticSno() : 0).sum();

                            //  물품합치기용
                            String firstProductName = list.get(0).getProduct() != null ? list.get(0).getProduct().getProductName() : "알 수 없음";
                            String combinedProductName = (list.size() > 1)
                                    ? firstProductName + " 외 " + (list.size() - 1) + "건"
                                    : firstProductName;

                            LogisticsEntity first = list.get(0);

                            return LogisticsInfoDTO.builder()
                                    .logisticsId(first.getLogisticsId())
                                    .productName(combinedProductName)
                                    .logisticSno(totalSno)
                                    .storageId(first.getStorageId())
                                    .productId(first.getProductId())
                                    .logisticsOrderNum(first.getLogisticsOrderNum())
                                    .logisticsType(first.getLogisticsType())
                                    .logisticDate(first.getLogisticDate())
                                    .logisticsPrice(first.getLogisticsPrice())
                                    .productCord(first.getProduct() != null ? first.getProduct().getProductCord() : null)
                                    .productPrice(first.getProduct() != null ? first.getProduct().getProductPrice() : null)
                                    .storageCord(first.getStorage() != null ? first.getStorage().getStorageCord() : null)
                                    .storageName(first.getStorage() != null ? first.getStorage().getStorageName() : null)
                                    .build();
                        })
                ));

        List<LogisticsInfoDTO> groupedList = new ArrayList<>(groupedMap.values());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), groupedList.size());

        if (start > end) return new PageImpl<>(new ArrayList<>(), pageable, groupedList.size());

        return new PageImpl<>(groupedList.subList(start, end), pageable, groupedList.size());

    }

    //출고  상세리스트
    public Page<LogisticsInfoDTO> getLogisticsOutListInfo(Integer logisticsOrderNum, Pageable pageable) {
        Page<LogisticsEntity> entityPage = logisticsRepository.findByLogisticsTypeOutAndLogisticsOrderNum(logisticsOrderNum , pageable);

        return entityPage.map(LogisticsEntity::toDTO);
    }

    // 입고 처리 ( 오더폼 발주일 수정 => 입고 , 출고 처리 => 재고 추가 => 불량 추가 )
    @Transactional
    public void processLogisticsAction( Integer orderformId ,List<LogisticsRequestDTO> dtoList) {

        OrderformEntity orderForm = orderformRepository.findById(orderformId)
                .orElseThrow(() -> new RuntimeException("발주 정보를 찾을 수 없습니다."));

        for (LogisticsRequestDTO dto : dtoList) {
            if (dto.getStockInDate() != null) {
                orderForm.setStockInDate(dto.getStockInDate());
            }

            // 각 요청 뭉치 안의 아이템들 순회
            for (LogisticsRequestDTO.LogisticsItemDTO item : dto.getItems()) {

                Integer amount = "IN".equals(dto.getLogisticsType()) ? item.getLogisticSno() : item.getDefectSno();
                if (amount == null || amount <= 0) continue;

                // 2 & 4. Logistics 공통 저장 (IN 또는 OUT 분기)
                LogisticsEntity logistics = LogisticsEntity.builder()
                        .productId(item.getProductId())
                        .storageId(item.getStorageId())
                        .logisticsOrderNum(orderformId)
                        .logisticsType(dto.getLogisticsType())
                        .logisticDate(LocalDate.now())
                        .logisticSno(amount) // 결정된 수량 적용
                        .logisticsPrice(item.getPrice())
                        .build();

                LogisticsEntity savedLogistics = logisticsRepository.save(logistics);

                // 3. 입고(IN) 처리 시 Inventory 테이블 추가
                if ("IN".equals(dto.getLogisticsType())) {
                    InventoryEntity inventory = InventoryEntity.builder()
                            .productId(item.getProductId())
                            .storageId(item.getStorageId())
                            .inventorySno(amount)
                            .expirationDate(item.getExpirationDate())
                            .inventoryMemo(item.getMemo())
                            .logisticsId(savedLogistics.getLogisticsId())
                            .build();
                    inventoryRepository.save(inventory);
                }

                // 5. 출고(OUT) 처리 시 Defect 테이블 추가
                else if ("OUT".equals(dto.getLogisticsType())) {
                    DefectEntity defect = DefectEntity.builder()
                            .userId(dto.getUserId())
                            .inventoryId(item.getInventoryId())
                            .defectSno(amount)
                            .defectStatus(item.getDefectStatus()) // 폼 데이터
                            .reqDate(LocalDateTime.now())
                            .disposalDate(item.getDisposalDate()) // 폼 데이터
                            .defectMemo(item.getMemo())
                            .build();
                    defectRepository.save(defect);
                }
            }
        }
        orderForm.setOrderStatus("완료");
    }

    // 출고 처리
    @Transactional
    public void processOutLogisticsAction ( List<OutLogisticsRequestDTO> dtoList ) {
        for (OutLogisticsRequestDTO dto : dtoList) {
            // 1. DTO 내의 아이템들 순회
            for (OutLogisticsRequestDTO.OutLogisticsItemDTO item : dto.getItems()) {

                // 수량 결정 (OUT 처리이므로 defectSno 또는 logisticSno 중 있는 값 사용)
                Integer amount = item.getDefectSno() != null ? item.getDefectSno() : item.getLogisticSno();

                if (amount == null || amount <= 0) continue;

                // 2. Logistics (출고 이력) 테이블 저장
                LogisticsEntity logistics = LogisticsEntity.builder()
                        .productId(item.getProductId())
                        .storageId(item.getStorageId())
                        .logisticsType("OUT")
                        .logisticDate(LocalDate.now())
                        .logisticSno(amount)
                        .logisticsPrice(item.getProductPrice())
                        .logisticsOrderNum(item.getInventoryId())
                        .build();
                LogisticsEntity savedLogistics = logisticsRepository.save(logistics);

                // 3. Inventory (재고) 차감 로직
                if (item.getInventoryId() != null) {
                    InventoryEntity inventory = inventoryRepository.findById(item.getInventoryId())
                            .orElseThrow(() -> new RuntimeException("재고 정보를 찾을 수 없습니다. ID: " + item.getInventoryId()));

                    // 현재 재고보다 많이 나가는지 체크 (선택 사항)
                    if (inventory.getInventorySno() < amount) {
                        throw new RuntimeException("재고가 부족합니다. 현재 재고: " + inventory.getInventorySno());
                    }

                    // 재고 차감 후 업데이트
                    inventory.setInventorySno(inventory.getInventorySno() - amount);
                    inventoryRepository.save(inventory);
                }

                // 4. Defect (불량) 테이블 저장 (불량 상태일 경우에만)
                if ("불량".equals(item.getDefectStatus())) {
                    DefectEntity defect = DefectEntity.builder()
                            .userId(item.getUserId())
                            .inventoryId(item.getInventoryId())
                            .defectSno(amount)
                            .defectStatus(item.getDefectStatus())
                            .reqDate(LocalDateTime.now())
                            .disposalDate(item.getDisposalDate())
                            .defectMemo(item.getMemo())
                            .build();
                    defectRepository.save(defect);
                }
            }
        }

    }
}