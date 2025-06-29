package ao.inocencio.recipeservice.application.port.out;

public interface ImageStoragePort {
    String uploadImage(byte[] imageData, String filename);
    byte[] downloadImage(String imageUrl);
}
