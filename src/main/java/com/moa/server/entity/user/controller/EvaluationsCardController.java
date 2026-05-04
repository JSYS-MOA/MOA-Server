package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.EvaluationsCardRequestDTO;
import com.moa.server.entity.user.dto.HrCardRequestPageDTO;
import com.moa.server.entity.user.dto.HrCardResponseDTO;
import com.moa.server.entity.user.service.HrCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hr")
@RequiredArgsConstructor
public class EvaluationsCardController {

    private final HrCardService hrCardService;

    @GetMapping("/evaluations")
    public ResponseEntity<?> evaluationsCardList() {
        List<HrCardResponseDTO> users = hrCardService.hrCardResponseList();

        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "등록된 인사 평가 정보가 없습니다.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/evaluations/page")
    public ResponseEntity<HrCardRequestPageDTO<HrCardResponseDTO>> evaluationsCardPageList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(hrCardService.hrCardPageList(page, size));
    }

    @GetMapping("/evaluations/search")
    public ResponseEntity<?> evaluationsCardSearch(
            @RequestParam(required = false) String searchCondition,
            @RequestParam(required = false) String searchKeyword
    ) {
        try {
            List<HrCardResponseDTO> users =
                    hrCardService.hrCardResponseSearch(searchCondition, searchKeyword);

            if (!users.isEmpty()) {
                return ResponseEntity.ok(users);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "검색 결과가 없습니다.");
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/evaluations/search/page")
    public ResponseEntity<?> evaluationsCardPageSearch(
            @RequestParam(required = false) String searchCondition,
            @RequestParam(required = false) String searchKeyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return ResponseEntity.ok(
                    hrCardService.hrCardPageSearch(searchCondition, searchKeyword, page, size)
            );

        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/evaluations/{user_id}")
    public ResponseEntity<?> evaluationsCardInfo(@PathVariable Integer user_id) {
        HrCardResponseDTO user = hrCardService.hrCardInfo(user_id);

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "해당 사번의 직원을 찾을 수 없습니다.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/evaluations/{user_id}")
    public ResponseEntity<?> evaluationsCardUpdate(
            @PathVariable Integer user_id,
            @RequestBody EvaluationsCardRequestDTO request
    ) {
        try {
            UserEntity updatedUser = hrCardService.evaluationsCardUpdate(
                    user_id,
                    request.getPerformance()
            );

            Map<String, Object> response = new HashMap<>();

            if (updatedUser != null) {
                response.put("result", true);
                response.put("message", "인사 평가 수정 완료");
                response.put("user", updatedUser);
                return ResponseEntity.ok(response);
            }

            response.put("result", false);
            response.put("message", "해당 사용자가 없습니다.");
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "인사 평가 수정 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}