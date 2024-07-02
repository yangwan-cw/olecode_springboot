package com.ioomex.olecodeApp.common;

import java.io.Serializable;
import lombok.Data;

/**
 * 删除请求
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}