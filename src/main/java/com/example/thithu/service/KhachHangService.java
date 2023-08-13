package com.example.thithu.service;

import com.example.thithu.dto.KhachHangRequest;
import com.example.thithu.dto.KhachHangResponse;
import com.example.thithu.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KhachHangService {

    KhachHangResponse addKhachHang(KhachHangRequest khachHangRequest);
    KhachHangResponse updateKhachHang(Long maKhachHang,KhachHangRequest khachHangRequest);
    void deleteKhachHang(Long maKhachHang);
    KhachHangResponse getKhachHangById(Long maKhachHang);
    Page<KhachHangResponse> getAllKhachHangPaging(Pageable pageable);
    List<KhachHangResponse> getAllKhachHang();

}
