package dev.kaly7dev.prospectmlm.following;

import dev.kaly7dev.prospectmlm.prospects.Prospect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("followings")
public class Following {
    @Id
    private Long followID;
    private Integer followNumber;
    private LocalDateTime createdDate;
    private LocalDateTime validatedDate;
    private LocalDateTime nextFollowDate;
    private LocalDateTime lastUpdatedDate;
    private Boolean validated;
    private String userName;

    @Column("prospect_id")
    private Long prospectID;
    @Transient
    private Prospect prospect;
}
