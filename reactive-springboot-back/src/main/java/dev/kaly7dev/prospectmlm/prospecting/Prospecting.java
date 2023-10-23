package dev.kaly7dev.prospectmlm.prospecting;

import java.time.LocalDateTime;

import dev.kaly7dev.prospectmlm.enums.ProspectingType;
import dev.kaly7dev.prospectmlm.prospects.Prospect;
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
@Table("prospectings")
public class Prospecting {
    @Id
    private Long prospectingID;
    private String prospectingType;
    private LocalDateTime createdDate;
    private LocalDateTime validatedDate;
    private LocalDateTime updatedDate;
    private Boolean firstProspectingValidated;
    private Boolean validated;
    private String userName;

    @Column("prospect_id")
    private Long prospectID;
    @Transient
    private Prospect prospect;
}
