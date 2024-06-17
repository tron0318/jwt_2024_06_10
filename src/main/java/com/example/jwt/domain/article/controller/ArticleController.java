package com.example.jwt.domain.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/article", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ArticleController {
}