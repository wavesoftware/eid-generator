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

package pl.wavesoftware.netbeans.eid.generator.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import pl.wavesoftware.netbeans.eid.generator.model.Policy;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class EIDGeneratorPanelTest {

    EIDGeneratorPanel getPanel() {
        EIDGeneratorOptionsPanelController mock = new EIDGeneratorOptionsPanelController();
        return new EIDGeneratorPanel(mock);
    }


    @Test
    public void testUpdateModel() {
        Policy policy = new Policy(Policy.Type.DATE, "yyyyMMdd:HHmmss");
        EIDGeneratorPanel instance = getPanel();
        instance.updateModel(policy);
    }

    @Test
    public void testMakeModel() {
        EIDGeneratorPanel instance = getPanel();
        Policy result = instance.makeModel();
        assertNotNull(result);
    }

    @Test
    public void testUpdateModelAndMake() {
        Policy policy = new Policy(Policy.Type.DATE, "yyyyMMdd:HHmmss");
        EIDGeneratorPanel instance = getPanel();
        instance.updateModel(policy);
        Policy result = instance.makeModel();
        assertEquals(policy.getFormat(), result.getFormat());
        assertEquals(policy.getType(), result.getType());
    }

    @Test
    public void testLoad() {
        EIDGeneratorPanel instance = getPanel();
        instance.load();
    }

    @Test
    public void testStore() {
        EIDGeneratorPanel instance = getPanel();
        instance.store();
    }

    @Test
    public void testValid() {
        EIDGeneratorPanel instance = getPanel();
        boolean expResult = true;
        boolean result = instance.valid();
        assertEquals(expResult, result);
    }

}
