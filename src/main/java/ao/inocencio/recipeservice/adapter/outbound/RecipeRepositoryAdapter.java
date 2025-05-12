package ao.inocencio.recipeservice.adapter.outbound;

import ao.inocencio.recipeservice.application.mapper.RecipeMapper;
import ao.inocencio.recipeservice.domain.model.Recipe;
import ao.inocencio.recipeservice.domain.ports.RecipeRepositoryPort;
import ao.inocencio.recipeservice.infrastruture.persistence.RecipeEntity;
import ao.inocencio.recipeservice.infrastruture.persistence.RecipeJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RecipeRepositoryAdapter implements RecipeRepositoryPort {
    private final RecipeJpaRepository recipeJpaRepository;
    private final RecipeMapper recipeMapper;

    public RecipeRepositoryAdapter(RecipeJpaRepository recipeJpaRepository, RecipeMapper recipeMapper) {
        this.recipeJpaRepository = recipeJpaRepository;
        this.recipeMapper = recipeMapper;
    }


    @Override
    public Recipe save(Recipe recipe) {
        RecipeEntity entity = recipeMapper.toEntity(recipe);
        RecipeEntity savedEntity = recipeJpaRepository.save(entity);
        return recipeMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Recipe> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Recipe> findAll() {
        return List.of();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public boolean existsByExternalId(String externalId) {
        return false;
    }
}
