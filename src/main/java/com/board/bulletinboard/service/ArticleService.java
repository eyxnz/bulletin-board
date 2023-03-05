package com.board.bulletinboard.service;

import com.board.bulletinboard.dto.ArticleDto;
import com.board.bulletinboard.dto.type.SearchType;
import com.board.bulletinboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    // SearchType 에 따른 게시글 조회
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String search_keyword) {
        return Page.empty();
    }

    // 특정 게시글 조회
    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long articleId) {
        return null;
    }

    // 게시글 저장
    public void saveArticle(ArticleDto dto) {
    }

    /*
    // 게시글 업데이트
    public void updateArticle(long articleId, ArticleUpdateDto dto) {
    }
    */

    // 게시글 삭제
    public void deleteArticle(long articleId) {
    }
}
