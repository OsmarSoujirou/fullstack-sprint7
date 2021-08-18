package br.com.rchlo.store.domain;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slug;

    private Integer position;

    /**
     * @deprecated
     */
    protected Category() {
    }

    public Category(Long id, String name, String slug, Integer position) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getPosition() {
        return position;
    }
}
