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

import java.util.prefs.Preferences;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import pl.wavesoftware.netbeans.eid.generator.model.Policy;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class PolicyMapperTest {

    public PolicyMapperTest() {
    }

    @Test
    public void testSave() {
        Policy policy = new Policy(Policy.Type.RANDOM_HASH, null);
        Preferences prefs = mock(Preferences.class);
        PolicyMapper instance = new PolicyMapper(prefs);
        instance.save(policy);
        verify(prefs).put("type", Policy.Type.RANDOM_HASH.name());
        verify(prefs).remove("format");

        policy = new Policy(Policy.Type.DATE, "yyMMdd HHmmss");
        prefs = mock(Preferences.class);
        instance = new PolicyMapper(prefs);
        instance.save(policy);
        verify(prefs).put("type", Policy.Type.DATE.name());
        verify(prefs).put("format", "yyMMdd HHmmss");
    }

    @Test
    public void testLoad() {
        Preferences prefs = Mockito.mock(Preferences.class);
        when(prefs.get("type", null)).thenReturn(Policy.Type.DATE.name());
        when(prefs.get("type", Policy.Type.DATE.name())).thenReturn(Policy.Type.DATE.name());
        when(prefs.get("format", null)).thenReturn("yyMMdd:HHmmss");
        PolicyMapper instance = new PolicyMapper(prefs);
        Policy expResult = new Policy(Policy.Type.DATE, "yyMMdd:HHmmss");
        Policy result = instance.load();
        assertEquals(expResult.getFormat(), result.getFormat());
        assertEquals(expResult.getType(), result.getType());
    }

}
