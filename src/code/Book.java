package code;

/**
 * @author gengxuelong
 * @date 2021-11-19 14:04
 * <p>
 * 图书类，为整个系统的最基本的单位结构
 */
public class Book {
    private String name;
    private String author;
    private Double price;
    private String describe;

    /**
     * constructor function
     *
     * @param name
     * @param author
     * @param price
     * @param describe
     */
    public Book(String name, String author, Double price, String describe) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.describe = describe;
    }

    /**
     * getter and setter
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * toString 的覆写
     */
    @Override
    public String toString() {
        return name + "-" + author + "-" + price + "-" + describe;
    }
}
