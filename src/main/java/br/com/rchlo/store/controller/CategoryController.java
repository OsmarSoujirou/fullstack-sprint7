package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.CategoryDto;

import br.com.rchlo.store.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<CategoryDto> categories() {
        return categoryRepository.findAllByOrderByPositionAsc()
                .stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }
}
