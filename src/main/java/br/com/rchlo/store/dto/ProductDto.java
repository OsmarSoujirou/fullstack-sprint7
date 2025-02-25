package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.domain.ProductImage;
import br.com.rchlo.store.domain.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {

    private final Long code;

    private final String name;

    private final String description;

    private final String slug;

    private final String brand;

    private final BigDecimal originalPrice;

    private final boolean hasDiscount;

    private final BigDecimal effectivePrice;

    private final String color;

    private final Integer weightInGrams;

    private final List<String> productImages;

    private final CategoryDto category;

    private final List<String> availableSizes;

    public ProductDto(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.description = product.getDescription();
        this.slug = product.getSlug();
        this.brand = product.getBrand();
        this.originalPrice = product.getPrice();
        this.hasDiscount = product.getDiscount() != null;
        this.effectivePrice = this.hasDiscount ? this.originalPrice.subtract(product.getDiscount()) : this.originalPrice;
        this.color = product.getColor().getDescription();
        this.weightInGrams = product.getWeightInGrams();
        this.productImages = product.getProductImage().stream().map(ProductImage::getImageUrl).collect(Collectors.toList());
        this.category = new CategoryDto(product.getCategory());
        this.availableSizes = product.getAvailableSizes().stream().map(Size::name).collect(Collectors.toList());
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSlug() {
        return slug;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public BigDecimal getEffectivePrice() {
        return effectivePrice;
    }

    public String getColor() {
        return color;
    }

    public Integer getWeightInGrams() {
        return weightInGrams;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public List<String> getAvailableSizes() {
        return availableSizes;
    }
}
