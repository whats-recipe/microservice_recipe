package ao.inocencio.recipeservice.infrastruture.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeJpaRepository extends JpaRepository<RecipeEntity, Long> {
    boolean existsByExternalId(String externalId);

    Optional<RecipeEntity> findByExternalId(String externalId);
}
