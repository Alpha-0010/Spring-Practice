package co.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = {"picture"})
@NoArgsConstructor
@Getter
@Setter
public class Category {
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
    private byte[] picture;
}
