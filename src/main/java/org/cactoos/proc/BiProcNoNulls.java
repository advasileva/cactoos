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
package org.cactoos.proc;

import org.cactoos.BiProc;

/**
 * BiProc check for no nulls.
 * @param <X> Type of input
 * @param <Y> Type of input
 * @since 0.20
 */
public final class BiProcNoNulls<X, Y> implements BiProc<X, Y> {

    /**
     * The proc.
     */
    private final BiProc<? super X, ? super Y> origin;

    /**
     * Ctor.
     * @param proc The function
     */
    public BiProcNoNulls(final BiProc<? super X, ? super Y> proc) {
        this.origin = proc;
    }

    @Override
    public void exec(final X first, final Y second) throws Exception {
        if (this.origin == null) {
            throw new IllegalArgumentException(
                "NULL instead of a valid function"
            );
        }
        if (first == null) {
            throw new IllegalArgumentException(
                "NULL instead of a valid first argument"
            );
        }
        if (second == null) {
            throw new IllegalArgumentException(
                "NULL instead of a valid second argument"
            );
        }
        this.origin.exec(first, second);
    }
}
