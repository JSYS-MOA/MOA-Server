package com.moa.server.entity.user.controller;

import com.moa.server.entity.user.UserEntity;
import com.moa.server.entity.user.dto.HrCardRequestPageDTO;
import com.moa.server.entity.user.dto.HrCardResponseDTO;
import com.moa.server.entity.user.service.HrCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hr")
@RequiredArgsConstructor
public class LeaversController {

    private final HrCardService hrCardService;

    @GetMapping("/leavers")
    public ResponseEntity<?> hrCardList() {
        List<HrCardResponseDTO> user = hrCardService.hrCardResponseList();

        if (!user.isEmpty()) {
            return ResponseEntity.ok(user);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "No leaver cards found.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/leavers/page")
    public ResponseEntity<HrCardRequestPageDTO<HrCardResponseDTO>> hrCardPageList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(hrCardService.hrCardPageList(page, size));
    }

    @GetMapping("/leavers/search")
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
            response.put("message", "No matching leaver cards found.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/leavers/sep/page")
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

    @GetMapping("/leavers/{user_id}")
    public ResponseEntity<?> hrCardInfo(@PathVariable Integer user_id) {
        HrCardResponseDTO user = hrCardService.hrCardInfo(user_id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee not found.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/leavers/{user_id}")
    public ResponseEntity<?> hrCardUpdate(@PathVariable Integer user_id, @RequestBody UserEntity user) {
        try {
            UserEntity updatedUser = hrCardService.hrCardUpdate(user_id, user);

            Map<String, Object> response = new HashMap<>();

            if (updatedUser != null) {
                response.put("result", true);
                response.put("message", "Leaver card updated successfully.");
                response.put("user", updatedUser);
                return ResponseEntity.ok(response);
            }

            response.put("result", false);
            response.put("message", "Employee not found.");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "Failed to update leaver card: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/leavers/{user_id}/restore")
    public ResponseEntity<?> hrCardRestore(@PathVariable Integer user_id) {
        try {
            HrCardResponseDTO user = hrCardService.hrCardRestore(user_id);

            Map<String, Object> response = new HashMap<>();

            if (user != null) {
                response.put("result", true);
                response.put("message", "Employee restored successfully.");
                response.put("user", user);
                return ResponseEntity.ok(response);
            }

            response.put("result", false);
            response.put("message", "Employee not found.");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "Failed to restore employee: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/leavers/add")
    public ResponseEntity<?> hrCardAdd(@RequestBody Map<String, Object> request) {
        try {
            Integer userId = parseInteger(request.get("userId"), "Employee");
            LocalDate quitDate = parseDate(request.get("quitDate"), "Quit date");
            HrCardResponseDTO user = hrCardService.hrCardRegisterLeaver(userId, quitDate);

            Map<String, Object> response = new HashMap<>();
            if (user != null) {
                response.put("result", true);
                response.put("message", "Leaver registered successfully.");
                response.put("user", user);
                return ResponseEntity.ok(response);
            }

            response.put("result", false);
            response.put("message", "Employee not found.");
            return ResponseEntity.badRequest().body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "Failed to register leaver: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/leavers/{user_id}")
    public ResponseEntity<?> hrCardDelete(@PathVariable Integer user_id) {
        try {
            hrCardService.hrCardDelete(user_id);

            Map<String, Object> response = new HashMap<>();
            response.put("result", true);
            response.put("message", "Leaver card deleted successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("result", false);
            response.put("message", "Failed to delete leaver card: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    private Integer parseInteger(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }

        if (value instanceof Number number) {
            return number.intValue();
        }

        String raw = value.toString().trim();

        if (raw.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }

        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " is invalid.");
        }
    }

    private LocalDate parseDate(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }

        String raw = value.toString().trim();

        if (raw.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }

        try {
            return LocalDate.parse(raw);
        } catch (Exception e) {
            throw new IllegalArgumentException(fieldName + " format is invalid.");
        }
    }
}