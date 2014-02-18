/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

	@Override
	public CompletionTask createTask(int queryType, JTextComponent jtc) {
		if (queryType != CompletionProvider.COMPLETION_QUERY_TYPE) {
			return null;
		}

		return new AsyncCompletionTask(new AsyncCompletionQuery() {
			protected void query(CompletionResultSet completionResultSet, Document document, int caretOffset) {
				try {
					String prev = document.getText(caretOffset - 1, 1);
					String actual = document.getText(caretOffset, 1);
					if ("\"".equals(prev) && "\"".equals(actual)) {
						completionResultSet.addItem(new EidItem(caretOffset));
					}
				} catch (BadLocationException ex) {
					Exceptions.printStackTrace(ex);
				} finally {
					completionResultSet.finish();
				}
			}
		}, jtc);
	}

	@Override
	public int getAutoQueryTypes(JTextComponent component, String typedText) {
		return 0;
	}

}
