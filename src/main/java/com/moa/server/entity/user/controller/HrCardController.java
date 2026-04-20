package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.HrCardRequestPageDTO;
import com.moa.server.entity.user.dto.HrCardResponseDTO;
import com.moa.server.entity.user.service.HrCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hr")
@RequiredArgsConstructor
public class HrCardController {

    private final HrCardService hrCardService;

    @GetMapping("/cards")
    public ResponseEntity<?> hrCardList() {
        List<HrCardResponseDTO> user = hrCardService.hrCardResponseList();

        if (!user.isEmpty()) {
            return ResponseEntity.ok(user);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "등록된 인사카드가 없습니다.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cards/page")
    public ResponseEntity<HrCardRequestPageDTO<HrCardResponseDTO>> hrCardPageList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(hrCardService.hrCardPageList(page, size));
    }

    @GetMapping("/cards/search")
    public ResponseEntity<?> hrCardSearch(
            @RequestParam(required = false) String searchCondition,
            @RequestParam(required = false) String searchKeyword
    ) {
        try {
            List<HrCardResponseDTO> user = hrCardService.hrCardResponseSearch(searchCondition, searchKeyword);

            if (!user.isEmpty()) {
                return ResponseEntity.ok(user);
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

    @GetMapping("/cards/search/page")
    public ResponseEntity<?> hrCardPageSearch(
            @RequestParam(required = false) String searchCondition,
            @RequestParam(required = false) String searchKeyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return ResponseEntity.ok(hrCardService.hrCardPageSearch(searchCondition, searchKeyword, page, size));
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/cards/{user_id}")
    public ResponseEntity<?> hrCardInfo(@PathVariable Integer user_id) {
        HrCardResponseDTO user = hrCardService.hrCardInfo(user_id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "해당 사번의 직원을 찾을 수 없습니다.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cards/{user_id}")
    public ResponseEntity<?> hrCardUpdate(@PathVariable Integer user_id, @RequestBody UserEntity user) {
        try {
            UserEntity users = hrCardService.hrCardUpdate(user_id, user);

            Map<String, Object> response = new HashMap<>();

            if (users != null) {
                response.put("result", true);
                response.put("message", "인사카드 수정 완료");
                response.put("user", users);
                return ResponseEntity.ok(response);
            }

            response.put("result", false);
            response.put("message", "해당 사용자가 없습니다.");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "인사카드 수정 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/cards/add")
    public ResponseEntity<?> hrCardAdd(@RequestBody UserEntity user) {
        try {
            hrCardService.hrCardAdd(user);

            Map<String, Object> response = new HashMap<>();
            response.put("result", true);
            response.put("message", "인사카드 등록 완료");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "인사카드 등록 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/cards/{user_id}")
    public ResponseEntity<?> hrCardDelete(@PathVariable Integer user_id) {
        try {
            hrCardService.hrCardDelete(user_id);

            Map<String, Object> response = new HashMap<>();
            response.put("result", true);
            response.put("message", "인사카드 삭제 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "인사카드 삭제 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
