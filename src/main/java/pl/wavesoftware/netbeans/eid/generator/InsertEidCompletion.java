/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Wave Software

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package pl.wavesoftware.netbeans.eid.generator;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.util.Exceptions;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
@MimeRegistration(mimeType = "text/x-java", service = CompletionProvider.class)
public class InsertEidCompletion implements CompletionProvider {

    private static final CompletionTask NOT = null;

    @Override
    public CompletionTask createTask(final int queryType, final JTextComponent jtc) {
        final AsyncCompletionTask task = new AsyncCompletionTask(new AsyncCompletionQuery() {
            @Override
            protected void query(final CompletionResultSet resultSet, final Document document,
                    final int caretOffset) {
                fillResultSet(resultSet, document, caretOffset);
            }
        }, jtc);
        return (queryType == CompletionProvider.COMPLETION_QUERY_TYPE) ? task : NOT;
    }

    protected void fillResultSet(final CompletionResultSet resultSet, final Document document, final int caretOffset) {
        try {
            final String prev = document.getText(caretOffset - 1, 1);
            final String actual = document.getText(caretOffset, 1);
            if ("\"".equals(prev) && "\"".equals(actual)) {
                resultSet.addItem(new EidItem(caretOffset));
            }
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            resultSet.finish();
        }
    }

    @Override
    public int getAutoQueryTypes(final JTextComponent component, final String typedText) {
        return 0;
    }

}
