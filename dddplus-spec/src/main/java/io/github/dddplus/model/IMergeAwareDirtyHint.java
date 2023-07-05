/*
 * Copyright DDDplus Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.dddplus.model;

import java.io.Serializable;

/**
 * 可以合并的脏数据提示.
 *
 * <p>Example:</p>
 * <pre>
 * {@code
 * public class OrderDirtyHint implements IMergeAwareDirtyHint<Long> {
 *     public enum Type {
 *         BindOrder(0),
 *         TransferFrom(1);
 *         int bit;
 *     }
 *
 *     private BitSet dirtyMap = new BitSet(8);
 *     private final Order order;
 *     private BigDecimal price;
 *
 *     public CaronDirtyHint(Order order, Type type) {
 *         this.order = order;
 *         this.dirtyMap.set(type.bit);
 *     }
 *
 *     @Override
 *     public void onMerge(IDirtyHint thatHint) {
 *         OrderDirtyHint that = (OrderDirtyHint) thatHint;
 *         that.dirtyMap.or(this.dirtyMap);
 *         if (this.price != null) {
 *             that.price = this.price;
 *         }
 *     }
 *
 *     @Override
 *     public Long getId() {
 *         return order.getId();
 *     }
 * }
 * }
 * </pre>
 *
 * @param <ID> 该hint的唯一标识
 */
public interface IMergeAwareDirtyHint<ID extends Serializable> extends IDirtyHint, IdentifiableDomainObject<ID> {

    /**
     * Merge预留的hook.
     *
     * <p>注意：合并过程中要改变状态，要改变{@code thatHint}入参的状态，而不是改变{@code this}</p>
     *
     * @param thatHint {@link DirtyMemento}里现存的该hint
     */
    void onMerge(IDirtyHint thatHint);
}