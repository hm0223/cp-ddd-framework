package io.github.dddplus.ast.algorithm;

import io.github.dddplus.ast.model.KeyModelEntry;

/**
 * 两个业务对象关键要素的相似度分析.
 */
public interface IKeyModelSimilarity {

    /**
     * 计算两个领域模型关键要素的相似度.
     *
     * @return in percentage
     */
    double similarity(KeyModelEntry model1, KeyModelEntry model2);
}
