package com.aabuilders.Dashboard.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String length;
    private String breadth;
    private String height;
    private String lengthInput;
    private String breadthInput;
    private String heightInput;
    private String deductionThickness;
    private String deductionThicknessInputs;
    private double deductionArea;
    private String deductionInput;
    private String deduction1;
    private String deduction2;
    private String deduction3;
    private String deduction4;
    private String deduction5;
    private String deduction6;
    private String deduction7;
    private String deduction8;
    private String deduction9;
    private String deduction10;
    private String deduction11;
    private String deduction12;
    private String deduction13;
    private String deduction14;
    private String deduction15;
    private String deduction16;
    private double wastagePercentage;
    private double skirtingArea;
    private double correctQuantityBox;
    private double totalOrderedTile;
    private String tileName;
    private String tileSize;
    private String size;
    private double qtyPerBox;
    private double areaInSqft;
    private double noOfBoxes;
    private String isUserChanged;
    private String rate;
    private String vendors;
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getLength() { return length; }
    public void setLength(String length) { this.length = length; }
    public String getBreadth() { return breadth; }
    public void setBreadth(String breadth) { this.breadth = breadth; }
    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }

    public String getLengthInput() {
        return lengthInput;
    }

    public void setLengthInput(String lengthInput) {
        this.lengthInput = lengthInput;
    }

    public String getBreadthInput() {
        return breadthInput;
    }

    public void setBreadthInput(String breadthInput) {
        this.breadthInput = breadthInput;
    }

    public String getHeightInput() {
        return heightInput;
    }

    public void setHeightInput(String heightInput) {
        this.heightInput = heightInput;
    }

    public String getDeductionThickness() {
        return deductionThickness;
    }

    public void setDeductionThickness(String deductionThickness) {
        this.deductionThickness = deductionThickness;
    }

    public String getDeductionThicknessInputs() {
        return deductionThicknessInputs;
    }

    public void setDeductionThicknessInputs(String deductionThicknessInputs) {
        this.deductionThicknessInputs = deductionThicknessInputs;
    }

    public double getDeductionArea() { return deductionArea; }
    public void setDeductionArea(double deductionArea) { this.deductionArea = deductionArea; }

    public String getDeductionInput() {
        return deductionInput;
    }

    public void setDeductionInput(String deductionInput) {
        this.deductionInput = deductionInput;
    }

    public String getDeduction1() {
        return deduction1;
    }

    public void setDeduction1(String deduction1) {
        this.deduction1 = deduction1;
    }

    public String getDeduction2() {
        return deduction2;
    }

    public void setDeduction2(String deduction2) {
        this.deduction2 = deduction2;
    }

    public String getDeduction3() {
        return deduction3;
    }

    public void setDeduction3(String deduction3) {
        this.deduction3 = deduction3;
    }

    public String getDeduction4() {
        return deduction4;
    }

    public void setDeduction4(String deduction4) {
        this.deduction4 = deduction4;
    }

    public String getDeduction5() {
        return deduction5;
    }

    public void setDeduction5(String deduction5) {
        this.deduction5 = deduction5;
    }

    public String getDeduction6() {
        return deduction6;
    }

    public void setDeduction6(String deduction6) {
        this.deduction6 = deduction6;
    }

    public String getDeduction7() {
        return deduction7;
    }

    public void setDeduction7(String deduction7) {
        this.deduction7 = deduction7;
    }

    public String getDeduction8() {
        return deduction8;
    }

    public void setDeduction8(String deduction8) {
        this.deduction8 = deduction8;
    }

    public String getDeduction9() {
        return deduction9;
    }

    public void setDeduction9(String deduction9) {
        this.deduction9 = deduction9;
    }

    public String getDeduction10() {
        return deduction10;
    }

    public void setDeduction10(String deduction10) {
        this.deduction10 = deduction10;
    }

    public String getDeduction11() {
        return deduction11;
    }

    public void setDeduction11(String deduction11) {
        this.deduction11 = deduction11;
    }

    public String getDeduction12() {
        return deduction12;
    }

    public void setDeduction12(String deduction12) {
        this.deduction12 = deduction12;
    }

    public String getDeduction13() {
        return deduction13;
    }

    public void setDeduction13(String deduction13) {
        this.deduction13 = deduction13;
    }

    public String getDeduction14() {
        return deduction14;
    }

    public void setDeduction14(String deduction14) {
        this.deduction14 = deduction14;
    }
    public String getDeduction15() {
        return deduction15;
    }
    public void setDeduction15(String deduction15) {
        this.deduction15 = deduction15;
    }
    public String getDeduction16() {
        return deduction16;
    }
    public void setDeduction16(String deduction16) {
        this.deduction16 = deduction16;
    }
    public double getWastagePercentage() { return wastagePercentage; }
    public void setWastagePercentage(double wastagePercentage) { this.wastagePercentage = wastagePercentage; }
    public double getSkirtingArea() { return skirtingArea; }
    public void setSkirtingArea(double skirtingArea) { this.skirtingArea = skirtingArea; }
    public double getCorrectQuantityBox() {
        return correctQuantityBox;
    }
    public void setCorrectQuantityBox(double correctQuantityBox) {
        this.correctQuantityBox = correctQuantityBox;
    }
    public double getTotalOrderedTile() { return totalOrderedTile; }
    public void setTotalOrderedTile(double totalOrderedTile) { this.totalOrderedTile = totalOrderedTile; }
    public String getTileName() {
        return tileName;
    }
    public void setTileName(String tileName) {
        this.tileName = tileName;
    }
    public String getTileSize() {
        return tileSize;
    }
    public void setTileSize(String tileSize) {
        this.tileSize = tileSize;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public double getQtyPerBox() {
        return qtyPerBox;
    }
    public void setQtyPerBox(double qtyPerBox) {
        this.qtyPerBox = qtyPerBox;
    }
    public double getAreaInSqft() {
        return areaInSqft;
    }
    public void setAreaInSqft(double areaInSqft) {
        this.areaInSqft = areaInSqft;
    }
    public double getNoOfBoxes() {
        return noOfBoxes;
    }
    public void setNoOfBoxes(double noOfBoxes) {
        this.noOfBoxes = noOfBoxes;
    }
    public String getIsUserChanged() {
        return isUserChanged;
    }
    public void setIsUserChanged(String isUserChanged) {
        this.isUserChanged = isUserChanged;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getVendors() {
        return vendors;
    }

    public void setVendors(String vendors) {
        this.vendors = vendors;
    }
}