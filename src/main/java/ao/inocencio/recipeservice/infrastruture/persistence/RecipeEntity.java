package ao.inocencio.recipeservice.infrastruture.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Version;

@Entity
@Table(name = "recipes")
@Data
public class RecipeEntity {
    @Id
    private String id;

    @Column(name = "external_id", unique = true, nullable = false)
    private String externalId;

    @Column(name = "used_ingredient_count", nullable = false)
    private Integer usedIngredientCount;

    @Column(name = "missed_ingredient_count", nullable = false)
    private Integer missedIngredientCount;

    @Column(nullable = false)
    private Integer likes;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(name = "image_type", nullable = false)
    private String imageType;

    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExternalRecipeEntity)) return false;
        return id != null && id.equals(((ExternalRecipeEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
