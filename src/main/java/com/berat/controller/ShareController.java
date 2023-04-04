package com.berat.controller;
import com.berat.dto.request.ShareCreateRequestDto;
import com.berat.dto.request.ShareDeleteRequestDto;
import com.berat.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.berat.constant.EndPoint.*;
@RestController
@RequestMapping(SHARE)
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @PostMapping(CREATE)
    public ResponseEntity<Void> createShare(@RequestBody ShareCreateRequestDto dto){
        shareService.createShare(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteShare(@RequestBody ShareDeleteRequestDto dto){
        return ResponseEntity.ok(shareService.deleteShare(dto));
    }
}
