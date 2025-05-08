package ao.inocencio.recipeservice.domain.model;

import java.util.List;

public class Recipe {
    private Long id;
    private String externalId;
    private String title;
    private String description;
    private List<String> ingredients;
    private List<String> instructions;
    private int preparationTime;
    private int servings;
    private boolean vegetarian;
}
