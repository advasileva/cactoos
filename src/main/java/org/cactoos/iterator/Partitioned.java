/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.cactoos.list.Immutable;
import org.cactoos.list.ListOf;

/**
 * Iterator implementation for {@link Iterator} partitioning.
 *
 * @param <T> Partitions value type
 * @since 0.29
 */
public final class Partitioned<T> implements Iterator<List<T>> {

    /**
     * Iterator to decorate.
     */
    private final Iterator<? extends T> decorated;

    /**
     * Size of the partitions.
     */
    private final int size;

    /**
     * Ctor.
     *
     * @param sze Size of the partitions.
     * @param src Source iterator.
     */
    public Partitioned(final int sze, final Iterator<? extends T> src) {
        this.size = sze;
        this.decorated = src;
    }

    @Override
    public boolean hasNext() {
        return this.decorated.hasNext();
    }

    @Override
    public List<T> next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No partition left.");
        }
        if (this.size < 1) {
            throw new IllegalArgumentException("Partition size < 1");
        }
        return new Immutable<>(
            new ListOf<>(new Sliced<>(0, this.size, this.decorated))
        );
    }

}
