package tn.esprit.msproductquery.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document
@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
//@FieldDefaults(level = AccessLevel.NONE)
public class Product implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    long id;
    String name;
    int quantity;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
