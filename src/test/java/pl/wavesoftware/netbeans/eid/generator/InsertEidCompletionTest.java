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

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.netbeans.modules.editor.completion.CompletionImpl;
import org.netbeans.modules.editor.completion.CompletionResultSetImpl;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import static pl.wavesoftware.util.RegexMatcher.matches;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class InsertEidCompletionTest {

    private CompletionResultSet resultSet;

    private CompletionTask task;

    private CompletionResultSetImpl resultSetImpl;

    @Before
    public void setUp() throws Exception {
        Object resultId = "1qaz2wsx";
        task = Mockito.mock(CompletionTask.class);
        int queryType = CompletionProvider.COMPLETION_QUERY_TYPE;

        CompletionImpl compl = CompletionImpl.get();
        resultSetImpl = compl.createTestResultSet(task, queryType);
        resultSet = resultSetImpl.getResultSet();
    }

    /**
     * Test of createTask method, of class InsertEidCompletion.
     */
    @Test
    public void testCreateTask() {
        int queryType = 0;
        JTextComponent jtc = Mockito.mock(JTextComponent.class);
        InsertEidCompletion instance = new InsertEidCompletion();
        CompletionTask expResult = null;
        CompletionTask result = instance.createTask(queryType, jtc);
        assertNull(result);
        queryType = CompletionProvider.COMPLETION_QUERY_TYPE;
        result = instance.createTask(queryType, jtc);
        assertNotNull(result);
        assertThat(result, instanceOf(AsyncCompletionTask.class));
    }

    @Test
    public void testFillResultSetEmpty() throws BadLocationException {
        Document document = Mockito.mock(Document.class);
        int caretOffset = 100;
        InsertEidCompletion instance = new InsertEidCompletion();
        when(document.getText(caretOffset - 1, 1)).thenReturn("a");
        when(document.getText(caretOffset, 1)).thenReturn("l");
        instance.fillResultSet(resultSet, document, caretOffset);
        assertEquals(0, resultSetImpl.getItems().size());
    }

    @Test
    public void testFillResultSet() throws BadLocationException {
        Document document = Mockito.mock(Document.class);
        int caretOffset = 100;
        InsertEidCompletion instance = new InsertEidCompletion();
        when(document.getText(caretOffset - 1, 1)).thenReturn("\"");
        when(document.getText(caretOffset, 1)).thenReturn("\"");
        instance.fillResultSet(resultSet, document, caretOffset);
        assertEquals(1, resultSetImpl.getItems().size());
        CompletionItem item = resultSetImpl.getItems().iterator().next();
        assertNotNull(item);
        String test = item.getSortText().toString();
        assertThat(test, matches("^[0-9]{8}:[0-9]{6}$"));
    }

    /**
     * Test of getAutoQueryTypes method, of class InsertEidCompletion.
     */
    @Test
    public void testGetAutoQueryTypes() {
        JTextComponent component = Mockito.mock(JTextComponent.class);
        String typedText = "";
        InsertEidCompletion instance = new InsertEidCompletion();
        int expResult = 0;
        int result = instance.getAutoQueryTypes(component, typedText);
        assertEquals(expResult, result);
    }

}
