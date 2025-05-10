package payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Random;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product {
  private String title;
  private String description;
  private int price;
  private double discountPercentage;
  private double rating;
  private int stock;
  private String brand;
  private String category;
  private String thumbnail;

  private static final Random random = new Random();

  public Product() {
    this(
        "Default Title",
        "Default Description",
        0,
        0.0,
        0.0,
        0,
        "Default Brand",
        "Default Category",
        "");
  }

  public Product(
      String title,
      String description,
      int price,
      double discountPercentage,
      double rating,
      int stock,
      String brand,
      String category,
      String thumbnail) {
    this.title = title;
    this.description = description;
    this.price = price;
    this.discountPercentage = discountPercentage;
    this.rating = rating;
    this.stock = stock;
    this.brand = brand;
    this.category = category;
    this.thumbnail = thumbnail;
  }

  public static Product createRandomProduct() {
    Product product = new Product();
    product.setTitle(generateRandomTitle());
    product.setDescription(generateRandomDescription());
    product.setPrice(generateRandomPrice());
    product.setDiscountPercentage(generateRandomDiscountPercentage());
    product.setRating(generateRandomRating());
    product.setStock(generateRandomStock());
    product.setBrand("Random Brand");
    product.setCategory("Random Category");
    product.setThumbnail("https://i.dummyjson.com/data/products/11/thumbnail.jpg");

    return product;
  }

  private static String generateRandomTitle() {
    return "Product-" + random.nextInt(1000);
  }

  private static String generateRandomDescription() {
    return "Description-" + random.nextInt(1000);
  }

  private static int generateRandomPrice() {
    return 10 + random.nextInt(91);
  }

  private static double generateRandomDiscountPercentage() {
    return random.nextDouble() * 20.0;
  }

  private static double generateRandomRating() {
    return 1.0 + random.nextDouble() * 4.0;
  }

  private static int generateRandomStock() {
    return random.nextInt(101);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public double getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(double discountPercentage) {
    this.discountPercentage = discountPercentage;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }
}
