package br.com.rchlo.store.controller;

import br.com.rchlo.store.controller.form.CategoryForm;
import br.com.rchlo.store.domain.Category;
import br.com.rchlo.store.dto.CategoryDetailDto;
import br.com.rchlo.store.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CategoryRepository categoryRepository;

    public AdminController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<CategoryDetailDto> categoryDetail(@PathVariable Long id) {

        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new CategoryDetailDto(category.get()));
    }

    @PostMapping("/categories")
    @Transactional
    public ResponseEntity<CategoryDetailDto> createdPayment(@RequestBody @Valid CategoryForm category, UriComponentsBuilder uriBuilder) {

        Category newCategory = new Category(
                category.getName(),
                category.getSlug(),
                Integer.sum(categoryRepository.getMaxPosition(), 1)
        );

        categoryRepository.save(newCategory);
        URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(newCategory.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoryDetailDto(newCategory));
    }

}
