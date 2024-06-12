package com.Projekti.MSHSIE.dto.user;


import com.Projekti.MSHSIE.dto.bill.BillDTO;
import com.Projekti.MSHSIE.dto.car.CarDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO{

private Integer id;
@NotNull(message = "Name is requried")
private String name;
@NotNull(message = "Surname is required")
private String surname;
@NotNull(message = "Email is required")
private String email;
@NotNull(message = "Password is required")
private String password;
private List<CarDTO> carDTOS;
@JsonIgnore
private List<BillDTO> billDTOS;

}

