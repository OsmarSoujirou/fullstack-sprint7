package br.com.rchlo.store.domain;

import javax.persistence.*;

@Entity
public class ProductImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

}
