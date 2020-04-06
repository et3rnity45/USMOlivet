package com.ttolivet.usmolivet.repositories;

import com.ttolivet.usmolivet.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByOrderByDateDesc();

    List<Article> findTop2ByOrderByDateDesc();

}
