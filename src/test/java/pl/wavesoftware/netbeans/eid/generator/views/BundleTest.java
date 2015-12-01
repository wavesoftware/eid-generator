/*
 * The MIT License
 *
 * Copyright 2015 Wave Software.
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
package pl.wavesoftware.netbeans.eid.generator.views;

import java.lang.reflect.Constructor;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class BundleTest {
    private Bundle instantinate() throws Exception {
        Constructor<Bundle> constr = null;
        try {
            constr = Bundle.class.getDeclaredConstructor(new Class[]{});
            constr.setAccessible(true);
            return constr.newInstance();
        } finally {
            if (constr != null) {
                constr.setAccessible(false);
            }
        }        
    }
    
    @Test
    public void testConstructor() throws Exception {
        // given
        Bundle bundle = instantinate();
        
        // when
        assertNotNull(bundle);
    }
    
    @Test
    public void testAdvancedOption_DisplayName_EIDGenerator() {
        String displayName = Bundle.AdvancedOption_DisplayName_EIDGenerator();
        
        Assert.assertEquals("EID Generator", displayName);
    }
    
    @Test
    public void testAdvancedOption_Keywords_EIDGenerator() {
        String keywords = Bundle.AdvancedOption_Keywords_EIDGenerator();
        
        Assert.assertEquals("eid generator", keywords);
    }
}
