/*
 * The MIT License
 *
 * Copyright 2014 Wave Software.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package pl.wavesoftware.netbeans.eid.generator.mapper;

import pl.wavesoftware.netbeans.eid.generator.model.EidGenerator;
import pl.wavesoftware.netbeans.eid.generator.model.Policy;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public final class GeneratorFactory {

    /**
     * Factorizes a eid generator
     *
     * @param policy a policy
     * @return a generator
     */
    public EidGenerator create(Policy policy) {
        switch (policy.getType()) {
            case DATE:
                return new DateGenerator(policy.getFormat());
            case RANDOM_NUMBER:
                return new RandomNumberGenerator();
            case RANDOM_HASH:
                return new RandomHashGenerator();
            default:
                throw new UnsupportedOperationException("Unreachable code!");
        }
    }
}
