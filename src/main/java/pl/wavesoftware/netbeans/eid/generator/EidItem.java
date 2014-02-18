/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.wavesoftware.netbeans.eid.generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JToolTip;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class EidItem implements CompletionItem {

	private String text;

	private static Color fieldColor = Color.decode("0xB20000");

	private int caretOffset;

	public EidItem(int caretOffset) {
		GregorianCalendar cal = new GregorianCalendar();
		Date date = cal.getTime();
		this.text = new SimpleDateFormat("yyyyMMdd:HHmmss").format(date);
		this.caretOffset = caretOffset;
	}

	@Override
	public void defaultAction(JTextComponent jtc) {
		try {
			StyledDocument doc = (StyledDocument) jtc.getDocument();
			doc.insertString(caretOffset, text, null);
			//This statement will close the code completion box:
			Completion.get().hideAll();
		} catch (BadLocationException ex) {
			Exceptions.printStackTrace(ex);
		}
	}

	@Override
	public void processKeyEvent(KeyEvent ke) {
	}

	@Override
	public int getPreferredWidth(Graphics graphics, Font font) {
		return CompletionUtilities.getPreferredWidth(text, null, graphics, font);
	}

	@Override
	public void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor,
			int width, int height, boolean selected) {
		String path = this.getClass().getPackage().getName().replace(".", File.separator);
		Path full = Paths.get(path, "id.png");
		Image image = ImageUtilities.loadImage(full.toString(), false);
		ImageIcon fieldIcon = new ImageIcon(image);
		CompletionUtilities.renderHtml(fieldIcon, text, "[generated]", g, defaultFont,
				(selected ? Color.white : fieldColor),
				width, height, selected);
	}

	@Override
	public CompletionTask createDocumentationTask() {
		return null;
	}

	@Override
	public CompletionTask createToolTipTask() {
		return new AsyncCompletionTask(new AsyncCompletionQuery() {
			@Override
			protected void query(CompletionResultSet completionResultSet, Document document, int i) {
				JToolTip toolTip = new JToolTip();
				toolTip.setTipText("Press Enter to insert EID: \"" + text + "\"");
				completionResultSet.setToolTip(toolTip);
				completionResultSet.finish();
			}
		});
	}

	@Override
	public boolean instantSubstitution(JTextComponent jtc) {
		return false;
	}

	@Override
	public int getSortPriority() {
		return 0;
	}

	@Override
	public CharSequence getSortText() {
		return text;
	}

	@Override
	public CharSequence getInsertPrefix() {
		return text;
	}

}
