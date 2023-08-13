package com.example.thithu.service.impl;

import com.example.thithu.dto.KhachHangRequest;
import com.example.thithu.dto.KhachHangResponse;
import com.example.thithu.entity.KhachHang;
import com.example.thithu.repository.HangKhachHangRepository;
import com.example.thithu.repository.KhachHangRepository;
import com.example.thithu.service.KhachHangService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private HangKhachHangRepository hangKhachHangRepository;

    @Override
    public KhachHangResponse addKhachHang(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
//        BeanUtils.copyProperties(khachHangRequest, khachHang);
        khachHang.setTenKhachHang(khachHangRequest.getTenKhachHang());
        khachHang.setSoDienThoai(khachHangRequest.getSoDienThoai());
        khachHang.setGioiTinh(khachHangRequest.getGioiTinh());
        KhachHang saveKhachHang = khachHangRepository.save(khachHang);
        return  new KhachHangResponse(saveKhachHang);
//        return convertToResponse(saveKhachHang);
    }

    @Override
    public KhachHangResponse updateKhachHang(Long maKhachHang, KhachHangRequest khachHangRequest) {

        KhachHang khachHang = khachHangRepository.findById(maKhachHang)
                .orElseThrow(() -> new IllegalArgumentException("Invalid makhachHang"));
        khachHang.setTenKhachHang(khachHangRequest.getTenKhachHang());
        khachHang.setSoDienThoai(khachHangRequest.getSoDienThoai());
        khachHang.setGioiTinh(khachHangRequest.getGioiTinh());
        KhachHang updateKhachHang = khachHangRepository.save(khachHang);
        return convertToResponse(updateKhachHang);
    }

    @Override
    public void deleteKhachHang(Long maKhachHang) {
        khachHangRepository.deleteById(maKhachHang);

    }

    @Override
    public KhachHangResponse getKhachHangById(Long maKhachHang) {
        KhachHang khachHang = khachHangRepository.findById(maKhachHang).orElse(null);
        if (khachHang != null) {
            return convertToResponse(khachHang);
        }
        return null;
    }

    @Override
    public Page<KhachHangResponse> getAllKhachHangPaging(Pageable pageable) {
        Page<KhachHang> khachHangPage = khachHangRepository.findAll(pageable);

        return khachHangPage.map(this::convertToResponse);
    }

    @Override
    public List<KhachHangResponse> getAllKhachHang() {
        List<KhachHang> khachHangList = khachHangRepository.findAll();
        List<KhachHangResponse> khachHangResponses = new ArrayList<>();
//        khachHangList.stream().forEach(a -> khachHangResponses.add(new KhachHangResponse(a)));
//        return khachHangResponses;
        for (KhachHang khachHang : khachHangList) {
            khachHangResponses.add(convertToResponse(khachHang));
        }
        return khachHangResponses;

    }

    private KhachHangResponse convertToResponse(KhachHang khachHang) {
        KhachHangResponse khachHangResponse = new KhachHangResponse();
        BeanUtils.copyProperties(khachHang, khachHangResponse);
        return khachHangResponse;
    }
}
