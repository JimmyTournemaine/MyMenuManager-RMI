package bean;

import java.util.Set;
import database.annotation.Column;

public class Menu {


    public Menu() {
    }

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Float price;

    private Set<MenuEntry> entries;

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public Float getPrice() {
        return price;
    }

    
    public void setPrice(Float price) {
        this.price = price;
    }

    
    public Set<MenuEntry> getEntries() {
        return entries;
    }

    
    public void setEntries(Set<MenuEntry> entries) {
        this.entries = entries;
    }

}