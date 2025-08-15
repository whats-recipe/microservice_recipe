package ao.inocencio.recipeservice.infrastruture.adapter.out.persistence.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface JpaRecipeRepository extends JpaRepository<RecipeJpaEntity, UUID>{
}
