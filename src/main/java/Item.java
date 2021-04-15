public class Item {
    String idItem;
    String description;
    int price;
    int oldPrice;
    int difference;



    public Item(String description, int price, int oldPrice,String idItem ) {
        this.idItem = idItem;
        this.description = description;
        this.price = price;
        this.oldPrice = oldPrice;
        this.difference = oldPrice - price;
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

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }


    @Override
    public String toString() {
        return "Item{" +
                "idItem='" + idItem + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", difference=" + difference +
                '}';
    }
}
