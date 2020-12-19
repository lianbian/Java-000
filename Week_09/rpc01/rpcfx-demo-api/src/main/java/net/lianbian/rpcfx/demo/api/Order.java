package net.lianbian.rpcfx.demo.api;

public class Order {

    private int id;

    private String name;

    private float amount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;

    public Order(int id, String name, float amount, Integer userId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.userId = userId;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
