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

import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class EIDGeneratorOptionsPanelControllerTest {

    /**
     * Test of update method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testUpdate() {
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        instance.update();
        assertNotNull(instance);
    }

    /**
     * Test of applyChanges method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testApplyChanges() {
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        instance.applyChanges();
        assertNotNull(instance);
    }

    /**
     * Test of cancel method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testCancel() {
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        instance.cancel();
        assertNotNull(instance);
    }

    /**
     * Test of isValid method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testIsValid() {
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        boolean expResult = true;
        boolean result = instance.isValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of isChanged method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testIsChanged() {
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        boolean expResult = false;
        boolean result = instance.isChanged();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHelpCtx method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testGetHelpCtx() {
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        HelpCtx expResult = null;
        HelpCtx result = instance.getHelpCtx();
        assertEquals(expResult, result);
    }

    /**
     * Test of getComponent method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testGetComponent() {
        Lookup masterLookup = null;
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        JComponent result = instance.getComponent(masterLookup);
        assertNotNull(result);
    }

    /**
     * Test of addPropertyChangeListener method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testAddPropertyChangeListener() {
        PropertyChangeListener l = null;
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        instance.addPropertyChangeListener(l);
        assertNotNull(instance);
    }

    /**
     * Test of removePropertyChangeListener method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testRemovePropertyChangeListener() {
        PropertyChangeListener l = null;
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        instance.removePropertyChangeListener(l);
        assertNotNull(instance);
    }

    /**
     * Test of changed method, of class EIDGeneratorOptionsPanelController.
     */
    @Test
    public void testChanged() {
        EIDGeneratorOptionsPanelController instance = new EIDGeneratorOptionsPanelController();
        instance.changed();
        assertNotNull(instance);
    }
    
}
