package com.example.thithu.controller;

import com.example.thithu.dto.KhachHangRequest;
import com.example.thithu.dto.KhachHangResponse;
import com.example.thithu.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import java.util.List;

@RestController
@RequestMapping("/api/khachHang")
public class KhachHangController {

    @Autowired
    private KhachHangService service;

    @GetMapping
    public ResponseEntity<List<KhachHangResponse>> getAllKhachHang() {
        List<KhachHangResponse> khachHangResponses = service.getAllKhachHang();
        return new ResponseEntity<>(khachHangResponses, HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<KhachHangResponse>> getKhachHangPage(
            @RequestParam(defaultValue ="1") int page
    ){
        Pageable pageable = PageRequest.of(page -1, 2);
        Page<KhachHangResponse> khachHangResponsePage = service.getAllKhachHangPaging(pageable);
        return new ResponseEntity<>(khachHangResponsePage,HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<KhachHangResponse> addKhachHang(@Valid @RequestBody KhachHangRequest khachHangRequest) {
        KhachHangResponse khachHangResponse = this.service.addKhachHang(khachHangRequest);
        return new ResponseEntity<>(khachHangResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<KhachHangResponse> deleteKhachHang(@PathVariable Long id) {
        service.deleteKhachHang(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<KhachHangResponse> putKhachHang(@PathVariable Long id,@RequestBody KhachHangRequest khachHangRequest) {
        KhachHangResponse khachHangResponse = service.updateKhachHang(id,khachHangRequest);
        if (khachHangResponse != null) {
            return new ResponseEntity<>(khachHangResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<KhachHangResponse> getOne(@PathVariable Long  id){
        KhachHangResponse khachHangResponse = service.getKhachHangById(id);
        if (khachHangResponse != null) {
            return new ResponseEntity<>(khachHangResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
