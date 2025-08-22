package com.example.dtos;

public class ProductdetailsDTO {

	    private Integer productId;
	    private String config_name;
	    private String config_dtls;

	    public ProductdetailsDTO(Integer productId, String configName, String configDtls) {
	        this.productId = productId;
	        this.config_name = configName;
	        this.config_dtls = configDtls;
	    }

	    public Integer getProductId() { return productId; }
	    public String getConfigName() { return config_name; }
	    public String getConfigDtls() { return config_dtls; }
}
