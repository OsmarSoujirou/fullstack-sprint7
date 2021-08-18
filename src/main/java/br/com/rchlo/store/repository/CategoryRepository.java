package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByName();

    List<Category> findAllByOrderByPositionAsc();

    @Query(value = "SELECT max(position) FROM Category")
    int getMaxPosition();

}
