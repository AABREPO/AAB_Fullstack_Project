package com.example.Dashboard2.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class POItemNameList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String category;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "po_item_name_list_id")
    private List<POItemNameListWithAllOtherPOEntity> otherPOEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<POItemNameListWithAllOtherPOEntity> getOtherPOEntityList() {
        return otherPOEntityList;
    }

    public void setOtherPOEntityList(List<POItemNameListWithAllOtherPOEntity> otherPOEntityList) {
        this.otherPOEntityList = otherPOEntityList;
    }
}
