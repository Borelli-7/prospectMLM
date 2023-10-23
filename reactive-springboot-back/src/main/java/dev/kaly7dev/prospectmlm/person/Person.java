package dev.kaly7dev.prospectmlm.person;

import dev.kaly7dev.prospectmlm.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("persons")
public class Person {
    @Id
    private Long personID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String activity;
    private String interest;
    private String description;
    private String userName;

    @Column("address_id")
    private Long personAddressID;
    @Transient
    private Address address;
}
