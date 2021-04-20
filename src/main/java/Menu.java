public class Menu {
    String name;
    String category;
    String path;

    public Menu(String name, String category, String path) {
        this.name = name;
        this.category = category;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
