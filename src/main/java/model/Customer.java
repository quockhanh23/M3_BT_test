package model;

public class Customer {
    private int id;
    private String name;
    private int age;
    private int productId;

    public Customer(int id, String name, int age, int productId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.productId = productId;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
