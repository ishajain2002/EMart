package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "product_master")
public class Productmaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "prod_name", length = 20)
    private String prodName;

    @Column(name = "prod_sdesc", length = 200)
    private String prodSdesc;

    @Column(name = "prod_bdesc", length = 1000)
    private String prodBdesc;

    @Column(name = "mrp_price")
    private Integer mrpPrice;

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;

    @Column(name = "product_img", length = 50)
    private String productImg;

    @Column(name = "loyal_price")
    private Integer loyalPrice;

    // Many products belong to one category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctg_master_id", nullable = false)
    @JsonIgnore
    private ctg_master category;

    // Getters and Setters

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdSdesc() {
        return prodSdesc;
    }

    public void setProdSdesc(String prodSdesc) {
        this.prodSdesc = prodSdesc;
    }

    public String getProdBdesc() {
        return prodBdesc;
    }

    public void setProdBdesc(String prodBdesc) {
        this.prodBdesc = prodBdesc;
    }

    public Integer getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(Integer mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getLoyalPrice() {
        return loyalPrice;
    }

    public void setLoyalPrice(Integer loyalPrice) {
        this.loyalPrice = loyalPrice;
    }

    public ctg_master getCategory() {
        return category;
    }

    public void setCategory(ctg_master category) {
        this.category = category;
    }
}