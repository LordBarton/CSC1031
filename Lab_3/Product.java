import java.util.ArrayList;
import java.util.List;

public class Product {
    private String productName;
    private long price;
    private boolean inStock;
    private List<String> tags;

    public Product() {
        this.productName = "Unknown";
        this.price = 0;
        this.inStock = false;
        this.tags = new ArrayList<>();
    }

    public Product(String productName) {

        if (productName == null || productName.isEmpty()) {
            this.productName = "Unknown";
        } else {
            this.productName = productName;
        }

        this.price = 0;
        this.inStock = false;
        this.tags = new ArrayList<>();

    }

    public Product(String productName, long price) {
        if (productName == null || productName.isEmpty()) {
            this.productName = "Unknown";
        } else {
            this.productName = productName;
        }

        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
        this.inStock = false;

        this.tags = new ArrayList<>();

    }

    public Product(String productName, long price, boolean inStock) {

        if (productName == null || productName.isEmpty()) {
            this.productName = "Unknown";
        } else {
            this.productName = productName;
        }

        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
        this.inStock = inStock;

        this.tags = new ArrayList<>();

    }

    public Product(String productName, long price, List<String> tags) {
        if (productName == null || productName.isEmpty()) {
            this.productName = "Unknown";
        } else {
            this.productName = productName;
        }

        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
        this.inStock = false;

        if (tags == null) {
            this.tags = new ArrayList<>();
        } else {
            this.tags = new ArrayList<>(tags);
        }
    }

    public Product(String productName, long price, boolean inStock, List<String> tags) {
        if (productName == null || productName.isEmpty()) {
            this.productName = "Unknown";
        } else {
            this.productName = productName;
        }

        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
        this.inStock = inStock;

        if (tags == null) {
            this.tags = new ArrayList<>();
        } else {
            this.tags = new ArrayList<>(tags);
        }
    }

    public Product(Product other) {
        this.productName = other.productName;
        this.price = other.price;
        this.inStock = other.inStock;
        this.tags = new ArrayList<>(other.tags);
    }

    public List<String> getTags() {
        return new ArrayList<>(tags);
    }

    public void setTags(List<String> tags) {
        this.tags = new ArrayList<>(tags);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                ", tags=" + tags +
                '}';
    }
}
