package com.board.bulletinboard.service;

import com.board.bulletinboard.dto.ArticleCommentDto;
import com.board.bulletinboard.repository.ArticleCommentRepository;
import com.board.bulletinboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    // 특정 게시글의 댓글 조회
    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return List.of();
    }

    // 댓글 저장
    public void saveArticleComment(ArticleCommentDto dto) {
    }

    // 댓글 삭제
    public void deleteArticleComment(Long articleCommentId) {
    }
}
