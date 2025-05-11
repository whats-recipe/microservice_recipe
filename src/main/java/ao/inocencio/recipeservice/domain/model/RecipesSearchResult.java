package ao.inocencio.recipeservice.domain.model;

import java.util.List;

public class RecipesSearchResult {
    private List<Recipe> results;
    private int offset;
    private int number;
    private int totalResults;

    public RecipesSearchResult() {

    }

    public RecipesSearchResult(List<Recipe> results, int offset, int number, int totalResults) {
        this.results = results;
        this.offset = offset;
        this.number = number;
        this.totalResults = totalResults;
    }

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
    @Override
    public String toString() {
        return "RecipeSearchResult{" +
             "results=" + results +
             ", offset=" + offset +
             ", number=" + number +
             ", totalResults=" + totalResults +
                '}';
    }
}
