/*
 * The MIT License
 *
 * Copyright 2014 Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>.
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

package pl.wavesoftware.netbeans.eid.generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Field;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.netbeans.spi.editor.completion.CompletionTask;
import static pl.wavesoftware.util.RegexMatcher.matches;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class EidItemTest {

    private EidItem instance;

    @Before
    public void setUp() {
        instance = new EidItem(100);
    }

    private <T> T get(Object target, String name) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> targetClass = target.getClass();
        Field field = targetClass.getDeclaredField(name);
        boolean wasAccess = field.isAccessible();
        if (!wasAccess) {
            field.setAccessible(true);
        }

        @SuppressWarnings("unchecked")
        T ret = (T) field.get(target);

        if (!wasAccess) {
            field.setAccessible(false);
        }
        return ret;
    }

    @Test
    public void testGetNewEid() {
        String id = EidItem.getNewEid();
        assertThat(id, matches("^[0-9]{8}:[0-9]{6}$"));
    }

    @Test
    public void testDefaultAction() throws Exception {
        JTextComponent jtc = Mockito.mock(JTextComponent.class);
        Document doc = Mockito.mock(StyledDocument.class);
        when(jtc.getDocument()).thenReturn(doc);
        instance.defaultAction(jtc);
        String id = get(instance, "text");
        assertThat(id, matches("^[0-9]{8}:[0-9]{6}$"));
        verify(doc).insertString(100, id, null);
    }

    @Test
    public void testProcessKeyEvent() {
        KeyEvent event = null;
        instance.processKeyEvent(event);
    }

    @Test
    public void testGetPreferredWidth() throws Exception {
        Graphics graphics = Mockito.mock(Graphics.class);
        Font font = Mockito.mock(Font.class);
        FontMetrics metrics = Mockito.mock(FontMetrics.class);
        when(graphics.getFontMetrics()).thenReturn(metrics);
        Rectangle2D rd = Mockito.mock(Rectangle2D.class);
        when(rd.getWidth()).thenReturn(120.4d);
        when(rd.getHeight()).thenReturn(11.0d);
        String id = get(instance, "text");
        when(metrics.getStringBounds(eq(id.toCharArray()), Mockito.anyInt(), Mockito.anyInt(), eq(graphics)))
                .thenReturn(rd);
        int expResult = 144;
        int result = instance.getPreferredWidth(graphics, font);
        assertEquals(expResult, result);
    }

    @Test
    public void testRender() throws Exception {
        Graphics graphics = Mockito.mock(Graphics.class);
        Font defaultFont = Mockito.mock(Font.class);
        Color defaultColor = Mockito.mock(Color.class);
        Color backgroundColor = Mockito.mock(Color.class);
        FontMetrics metrics = Mockito.mock(FontMetrics.class);
        when(graphics.getFontMetrics(eq(defaultFont))).thenReturn(metrics);
        when(graphics.getFontMetrics()).thenReturn(metrics);
        Rectangle2D rd = Mockito.mock(Rectangle2D.class);
        when(rd.getWidth()).thenReturn(120.4d);
        when(rd.getHeight()).thenReturn(11.0d);
        String id = get(instance, "text");
        when(metrics.getStringBounds(eq(id.toCharArray()), Mockito.anyInt(), Mockito.anyInt(), eq(graphics)))
                .thenReturn(rd);
        Rectangle2D rd2 = Mockito.mock(Rectangle2D.class);
        when(rd2.getWidth()).thenReturn(120.4d);
        when(rd2.getHeight()).thenReturn(11.0d);
        String generated = "[generated]";
        when(metrics.getStringBounds(eq(generated.toCharArray()), Mockito.anyInt(), Mockito.anyInt(), eq(graphics)))
                .thenReturn(rd);
        int width = 0;
        int height = 0;
        boolean selected = false;
        instance.render(graphics, defaultFont, defaultColor, backgroundColor, width, height, selected);
    }

    @Test
    public void testCreateDocumentationTask() {
        CompletionTask expResult = null;
        CompletionTask result = instance.createDocumentationTask();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateToolTipTask() {
        CompletionTask result = instance.createToolTipTask();
        assertNotNull(result);
    }

    @Test
    public void testInstantSubstitution() {
        JTextComponent jtc = null;
        boolean expResult = false;
        boolean result = instance.instantSubstitution(jtc);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSortPriority() {
        int expResult = 0;
        int result = instance.getSortPriority();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSortText() {
        CharSequence expResult = null;
        CharSequence result = instance.getSortText();
        assertThat(result, matches("^[0-9]{8}:[0-9]{6}$"));
    }

    @Test
    public void testGetInsertPrefix() {
        CharSequence expResult = null;
        CharSequence result = instance.getInsertPrefix();
        assertThat(result, matches("^[0-9]{8}:[0-9]{6}$"));
    }

}
