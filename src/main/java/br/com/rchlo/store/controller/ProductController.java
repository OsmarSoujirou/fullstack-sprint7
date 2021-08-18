package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.ProductByColorDto;
import br.com.rchlo.store.dto.ProductDto;
import br.com.rchlo.store.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    @Cacheable(value = "productsList")
    public List<ProductDto> products(@PageableDefault(sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        return productRepository.findAll(pageable)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/reports/by-color")
    @Cacheable(value = "productsListByColor")
    public List<ProductByColorDto> productByColorReport() {
        return productRepository.productsByColor();
    }

    @GetMapping("/clear-cache")
    @CacheEvict(value = "productsList", allEntries = true)
    public void clearCache() {
        // Do nothing because of X and Y.
    }
}
