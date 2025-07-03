package com.aabuilders.Dashboard.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "paint_name_details")
public class PaintVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paintName;
    private String paintType;
    private String paintCoverBySqft;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaintName() {
        return paintName;
    }

    public void setPaintName(String paintName) {
        this.paintName = paintName;
    }

    public String getPaintType() {
        return paintType;
    }

    public void setPaintType(String paintType) {
        this.paintType = paintType;
    }

    public String getPaintCoverBySqft() {
        return paintCoverBySqft;
    }

    public void setPaintCoverBySqft(String paintCoverBySqft) {
        this.paintCoverBySqft = paintCoverBySqft;
    }


}
