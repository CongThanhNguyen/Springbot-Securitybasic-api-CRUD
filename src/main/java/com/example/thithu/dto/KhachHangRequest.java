package com.example.thithu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class KhachHangRequest {

    @NotNull(message = "Trường này không được để NULL !")
    @NotBlank(message = "Trường này không được để trống !")
    private String tenKhachHang;
    @NotNull
    @Pattern(regexp = "^\\d{10}$",message = "số điện thoại phải đủ 10 số")
    private String soDienThoai;
    private Boolean gioiTinh;
    private Integer maHang;
}
