package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Category;

public class CategoryDetailDto {

    private final Long id;

    private final String name;

    private final String slug;

    private final Integer position;

    public CategoryDetailDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.slug = category.getSlug();
        this.position = category.getPosition();

    }

    public Long getId() { return id; }

    public Integer getPosition() { return position; }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
