package br.com.rchlo.store.controller.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class CategoryForm {

    @NotEmpty(message = "Required value! non-empty")
    @Length(max = 255, message = "Maximum number of characters 255!")
    private String name;

    @NotEmpty(message = "Required value! non-empty")
    @Length(max = 255, message = "Maximum number of characters 255!")
    private String slug;

    public CategoryForm(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

}
