package ao.inocencio.recipeservice.domain.model;

public class Recipe {
    private String id;
    private String externalId;
    private int usedIngredientCount;
    private int missedIngredientCount;
    private int likes;
    private int title;
    private int image;
    private int imageType;

    public Recipe (String id, String externalId , int usedIngredientCount, int missedIngredientCount, int likes, int title, int image) {
    this.id = id;
    this.externalId = externalId;
    this.usedIngredientCount = usedIngredientCount;
    this.missedIngredientCount = missedIngredientCount;
    this.likes = likes;
    this.title = title;
    this.image = image;
    this.imageType = imageType;
    }

    public String getId() {
        return id;
    }
    public String getExternalId() {
        return externalId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public int getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public void setUsedIngredientCount(int usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    public int getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public void setMissedIngredientCount(int missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", usedIngredientCount=" + usedIngredientCount +
                ", missedIngredientCount=" + missedIngredientCount +
                ", likes=" + likes +
                ", title=" + title +
                ", image=" + image +
                ", imageType=" + imageType +
                '}';
    }
}
