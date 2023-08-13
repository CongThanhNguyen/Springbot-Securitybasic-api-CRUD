package com.example.thithu.dto;

import com.example.thithu.entity.KhachHang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KhachHangResponse {

    private Long maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private Boolean gioiTinh;
    private Integer maHang;
    private String tenHang;

    public KhachHangResponse(KhachHang khachHang) {
        this.maKhachHang = khachHang.getMaKhachHang();
        this.tenKhachHang = khachHang.getTenKhachHang();
        this.soDienThoai = khachHang.getSoDienThoai();
        this.gioiTinh = khachHang.getGioiTinh();
    }
}
