package com.example.jwt.domain.article.service;

import com.example.jwt.domain.article.entity.Article;
import com.example.jwt.domain.article.repository.ArticleRepository;
import com.example.jwt.domain.member.entity.Member;
import com.example.jwt.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public RsData<Article> write(Member author, String subject, String content) {
        Article article = Article.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);

        return RsData.of(
                "S-3",
                "게시물이 생성 되었습니다.",
                article
        );
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public RsData canModify(Member actor, Article article) {
        if (Objects.equals(actor.getId(), article.getAuthor().getId())) {
            return RsData.of(
                    "S-1",
                    "게시물을 수정할 수 있습니다."
            );
        }

        return RsData.of(
                "F-1",
                "게시물을 수정할 수 없습니다."
        );
    }

    public RsData<Article> modify(Article article, String subject, String content) {
        article.setSubject(subject);
        article.setContent(content);
        articleRepository.save(article);

        return RsData.of(
                "S-4",
                "%d번 게시물이 수정되었습니다.".formatted(article.getId()),
                article
        );
    }
}