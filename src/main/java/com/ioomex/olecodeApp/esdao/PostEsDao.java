package com.ioomex.olecodeApp.esdao;

import com.ioomex.olecodeApp.model.dto.post.PostEsDTO;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    List<PostEsDTO> findByUserId(Long userId);
}