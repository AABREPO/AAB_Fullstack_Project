package com.aabuilders.Dashboard.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PaintTile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Column(columnDefinition = "TEXT")
    private String length;
    @Column(columnDefinition = "TEXT")
    private String lengthInputs;
    private String length1;
    private String length2;
    private String length3;
    private String length4;
    private String length5;
    private String length6;
    private String length7;
    private String length8;
    private String length9;
    private String length10;
    private String length11;
    private String length12;
    private String length13;
    private String length14;
    private String length15;
    private String length16;
    private String length17;
    private String length18;
    private String length19;
    private String length20;
    private String length21;
    private String length22;
    private String length23;
    private String length24;
    private String length25;
    private String length26;
    private String length27;
    private String length28;
    private String length29;
    private String length30;
    @Column(columnDefinition = "TEXT")
    private String breadth;
    @Column(columnDefinition = "TEXT")
    private String breadthInputs;
    @Column(columnDefinition = "TEXT")
    private String height;
    @Column(columnDefinition = "TEXT")
    private String heightInputs;
    private String deductionThickness;
    private String deductionThicknessInputs;
    private double deductionArea;
    private String deductionInput;
    private String wallDeductionInputs;
    private String ceilingDeductionInputs;
    private String bothDeductionInputs;
    private String wallDeductionArea;
    private String ceilingDeductionArea;
    private String bothDeductionArea;
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
    private double totalOrderedTile;
    private double wastagePercentage;
    private String selectedPaint;
    private String selectedPrimer;
    private String wallCoat;
    private String ceilingCoat;
    private String selectedPutty;
    private String selectedWaterProof;
    private String selectedColorCode;
    private double orderQty;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLength1() {
        return length1;
    }

    public void setLength1(String length1) {
        this.length1 = length1;
    }

    public String getLength2() {
        return length2;
    }

    public void setLength2(String length2) {
        this.length2 = length2;
    }

    public String getLength3() {
        return length3;
    }

    public void setLength3(String length3) {
        this.length3 = length3;
    }

    public String getLength4() {
        return length4;
    }

    public void setLength4(String length4) {
        this.length4 = length4;
    }

    public String getLength5() {
        return length5;
    }

    public void setLength5(String length5) {
        this.length5 = length5;
    }

    public String getLength6() {
        return length6;
    }

    public void setLength6(String length6) {
        this.length6 = length6;
    }

    public String getLength7() {
        return length7;
    }

    public void setLength7(String length7) {
        this.length7 = length7;
    }

    public String getLength8() {
        return length8;
    }

    public void setLength8(String length8) {
        this.length8 = length8;
    }

    public String getLength9() {
        return length9;
    }

    public void setLength9(String length9) {
        this.length9 = length9;
    }

    public String getLength10() {
        return length10;
    }

    public void setLength10(String length10) {
        this.length10 = length10;
    }

    public String getLength11() {
        return length11;
    }

    public void setLength11(String length11) {
        this.length11 = length11;
    }

    public String getLength12() {
        return length12;
    }

    public void setLength12(String length12) {
        this.length12 = length12;
    }

    public String getLength13() {
        return length13;
    }

    public void setLength13(String length13) {
        this.length13 = length13;
    }

    public String getLength14() {
        return length14;
    }

    public void setLength14(String length14) {
        this.length14 = length14;
    }

    public String getLength15() {
        return length15;
    }

    public void setLength15(String length15) {
        this.length15 = length15;
    }

    public String getLength16() {
        return length16;
    }

    public void setLength16(String length16) {
        this.length16 = length16;
    }

    public String getLength17() {
        return length17;
    }

    public void setLength17(String length17) {
        this.length17 = length17;
    }

    public String getLength18() {
        return length18;
    }

    public void setLength18(String length18) {
        this.length18 = length18;
    }

    public String getLength19() {
        return length19;
    }

    public void setLength19(String length19) {
        this.length19 = length19;
    }

    public String getLength20() {
        return length20;
    }

    public void setLength20(String length20) {
        this.length20 = length20;
    }

    public String getLength21() {
        return length21;
    }

    public void setLength21(String length21) {
        this.length21 = length21;
    }

    public String getLength22() {
        return length22;
    }

    public void setLength22(String length22) {
        this.length22 = length22;
    }

    public String getLength23() {
        return length23;
    }

    public void setLength23(String length23) {
        this.length23 = length23;
    }

    public String getLength24() {
        return length24;
    }

    public void setLength24(String length24) {
        this.length24 = length24;
    }

    public String getLength25() {
        return length25;
    }

    public void setLength25(String length25) {
        this.length25 = length25;
    }

    public String getLength26() {
        return length26;
    }

    public void setLength26(String length26) {
        this.length26 = length26;
    }

    public String getLength27() {
        return length27;
    }

    public void setLength27(String length27) {
        this.length27 = length27;
    }

    public String getLength28() {
        return length28;
    }

    public void setLength28(String length28) {
        this.length28 = length28;
    }

    public String getLength29() {
        return length29;
    }

    public void setLength29(String length29) {
        this.length29 = length29;
    }

    public String getLength30() {
        return length30;
    }

    public void setLength30(String length30) {
        this.length30 = length30;
    }

    public String getBreadth() {
        return breadth;
    }

    public void setBreadth(String breadth) {
        this.breadth = breadth;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
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
    public double getDeductionArea() {
        return deductionArea;
    }

    public void setDeductionArea(double deductionArea) {
        this.deductionArea = deductionArea;
    }

    public String getDeductionInput() {
        return deductionInput;
    }

    public void setDeductionInput(String deductionInput) {
        this.deductionInput = deductionInput;
    }

    public String getWallDeductionInputs() {
        return wallDeductionInputs;
    }

    public void setWallDeductionInputs(String wallDeductionInputs) {
        this.wallDeductionInputs = wallDeductionInputs;
    }

    public String getCeilingDeductionInputs() {
        return ceilingDeductionInputs;
    }

    public void setCeilingDeductionInputs(String ceilingDeductionInputs) {
        this.ceilingDeductionInputs = ceilingDeductionInputs;
    }

    public String getBothDeductionInputs() {
        return bothDeductionInputs;
    }

    public void setBothDeductionInputs(String bothDeductionInputs) {
        this.bothDeductionInputs = bothDeductionInputs;
    }

    public String getWallDeductionArea() {
        return wallDeductionArea;
    }

    public void setWallDeductionArea(String wallDeductionArea) {
        this.wallDeductionArea = wallDeductionArea;
    }

    public String getCeilingDeductionArea() {
        return ceilingDeductionArea;
    }

    public void setCeilingDeductionArea(String ceilingDeductionArea) {
        this.ceilingDeductionArea = ceilingDeductionArea;
    }

    public String getBothDeductionArea() {
        return bothDeductionArea;
    }

    public void setBothDeductionArea(String bothDeductionArea) {
        this.bothDeductionArea = bothDeductionArea;
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

    public String getLengthInputs() {
        return lengthInputs;
    }

    public void setLengthInputs(String lengthInputs) {
        this.lengthInputs = lengthInputs;
    }

    public String getBreadthInputs() {
        return breadthInputs;
    }

    public void setBreadthInputs(String breadthInputs) {
        this.breadthInputs = breadthInputs;
    }

    public String getHeightInputs() {
        return heightInputs;
    }

    public void setHeightInputs(String heightInputs) {
        this.heightInputs = heightInputs;
    }

    public double getTotalOrderedTile() {
        return totalOrderedTile;
    }

    public void setTotalOrderedTile(double totalOrderedTile) {
        this.totalOrderedTile = totalOrderedTile;
    }

    public double getWastagePercentage() {
        return wastagePercentage;
    }

    public void setWastagePercentage(double wastagePercentage) {
        this.wastagePercentage = wastagePercentage;
    }

    public String getSelectedPaint() {
        return selectedPaint;
    }

    public void setSelectedPaint(String selectedPaint) {
        this.selectedPaint = selectedPaint;
    }

    public String getSelectedPrimer() {
        return selectedPrimer;
    }

    public void setSelectedPrimer(String selectedPrimer) {
        this.selectedPrimer = selectedPrimer;
    }

    public String getWallCoat() {
        return wallCoat;
    }

    public void setWallCoat(String wallCoat) {
        this.wallCoat = wallCoat;
    }

    public String getCeilingCoat() {
        return ceilingCoat;
    }

    public void setCeilingCoat(String ceilingCoat) {
        this.ceilingCoat = ceilingCoat;
    }

    public String getSelectedPutty() {
        return selectedPutty;
    }

    public void setSelectedPutty(String selectedPutty) {
        this.selectedPutty = selectedPutty;
    }

    public String getSelectedWaterProof() {
        return selectedWaterProof;
    }

    public void setSelectedWaterProof(String selectedWaterProof) {
        this.selectedWaterProof = selectedWaterProof;
    }

    public String getSelectedColorCode() {
        return selectedColorCode;
    }

    public void setSelectedColorCode(String selectedColorCode) {
        this.selectedColorCode = selectedColorCode;
    }

    public double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(double orderQty) {
        this.orderQty = orderQty;
    }
}