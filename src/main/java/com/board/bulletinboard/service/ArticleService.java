package com.board.bulletinboard.service;

import com.board.bulletinboard.domain.Article;
import com.board.bulletinboard.dto.ArticleDto;
import com.board.bulletinboard.dto.ArticleWithCommentsDto;
import com.board.bulletinboard.dto.type.SearchType;
import com.board.bulletinboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    // SearchType 에 따른 게시글 조회
    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) { // 키워드 없이 검색된다면 전체 게시글 조회
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    // 특정 게시글 조회
    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId : " + articleId));
    }

    // 게시글 저장
    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    // 게시글 업데이트
    public void updateArticle(ArticleDto dto) {
        try {
            // getReferenceById : 가져오려는 대상이 없을 시, 내부에서 예외 발생
            // 반환된 객체 내부의 정보를 요청하는 시점에 DB 를 조회 == 내부의 값을 필요로 하지는 않고, 다른 객체에게 할당하는 목적으로만 조회하는 경우 이점이 있음
            Article article = articleRepository.getReferenceById(dto.id());

            if (dto.title() != null) {
                article.setTitle(dto.title());
            }
            if (dto.content() != null) {
                article.setContent(dto.content());
            }
            article.setHashtag(dto.hashtag());
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    // 게시글 삭제
    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }

    public long getArticleCount() {
        return articleRepository.count();
    }
}
