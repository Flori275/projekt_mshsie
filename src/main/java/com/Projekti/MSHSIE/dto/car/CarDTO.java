package com.Projekti.MSHSIE.dto.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {

    private Integer id;
    @NotNull(message = "Field is required")
    private String chassis;
    private LocalDate producedAt;
    @NotNull(message = "Field is required")
    private Integer enginePower;
    @NotNull(message = "Field is required")
    private String make;
    private LocalDateTime createdAt;
    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }



}
