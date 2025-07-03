package com.aabuilders.Dashboard.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "paint_color_and_image")
public class PaintNameAndImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paintColor;
    @Lob
    @Column(name = "paint_image", columnDefinition = "LONGBLOB")
    private byte[] paintImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(String paintColor) {
        this.paintColor = paintColor;
    }


    public byte[] getPaintImage() {
        return paintImage;
    }

    public void setPaintImage(byte[] paintImage) {
        this.paintImage = paintImage;
    }
}
