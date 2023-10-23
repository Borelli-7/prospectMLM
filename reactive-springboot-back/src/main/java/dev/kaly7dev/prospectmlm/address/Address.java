package dev.kaly7dev.prospectmlm.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("addresses")
public class Address {
    @Id
    private Long addressID;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String residenceCountry;
    private String nationality;
    
}
