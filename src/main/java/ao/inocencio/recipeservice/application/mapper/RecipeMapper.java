package ao.inocencio.recipeservice.application.mapper;

import ao.inocencio.recipeservice.application.dto.RecipeDTO;
import ao.inocencio.recipeservice.domain.model.Recipe;
import ao.inocencio.recipeservice.infrastruture.persistence.RecipeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    //Domain to DTO
    RecipeDTO toDTO(Recipe recipe);

    //DTO to Domain
    Recipe toRecipe(RecipeDTO recipeDTO);

    //Domain to Entity
    @Mapping(target = "id", ignore = true) // ID is auto-generated
    RecipeEntity toEntity(Recipe recipe);

    // Entity to Domain
    Recipe toDomain(RecipeEntity recipeEntity);

    //List conversions
    default List<RecipeDTO> toDTOList(List<Recipe> recipes) {
        return recipes.stream()
                .map(this::toDTO)
                .toList();
    }
    default List<Recipe> toDomainList(List<RecipeEntity> entities){
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }
}
