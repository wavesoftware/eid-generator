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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;
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
import org.openide.util.NbPreferences;
import pl.wavesoftware.netbeans.eid.generator.mapper.GeneratorFactory;
import pl.wavesoftware.netbeans.eid.generator.mapper.PolicyMapper;
import pl.wavesoftware.netbeans.eid.generator.model.Policy;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class EidItem implements CompletionItem {

    private static final Color FIELDCOLOR = Color.decode("0xB20000");

    private static final ImageIcon FIELDICON;

    private final transient String text;

    private final transient int caretOffset;

    static {
        final String path = EidItem.class.getPackage().getName().replace(".", File.separator);
        final Path full = Paths.get(path, "id.png");
        final Image image = ImageUtilities.loadImage(full.toString(), false);
        FIELDICON = new ImageIcon(image);
    }

    public static String getNewEid() {
        final Preferences prefs = NbPreferences.forModule(EidItem.class);
        final PolicyMapper mapper = new PolicyMapper(prefs);
        final Policy policy = mapper.load();
        return GeneratorFactory.create(policy).generate();
    }

    public EidItem(final int caretOffset) {
        this.text = getNewEid();
        this.caretOffset = caretOffset;
    }

    @Override
    public void defaultAction(final JTextComponent jtc) {
        try {
            final StyledDocument doc = (StyledDocument) jtc.getDocument();
            doc.insertString(caretOffset, text, null);
            //This statement will close the code completion box:
            Completion.get().hideAll();
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void processKeyEvent(final KeyEvent event) {
        // do nothing here
    }

    @Override
    public int getPreferredWidth(final Graphics graphics, final Font font) {
        return CompletionUtilities.getPreferredWidth(text, null, graphics, font);
    }

    @Override
    public void render(final Graphics graphics, final Font defaultFont, final Color defaultColor,
            final Color backgroundColor, final int width, final int height, final boolean selected) {

        CompletionUtilities.renderHtml(FIELDICON, text, "[generated]", graphics, defaultFont,
                (selected ? Color.white : FIELDCOLOR),
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
            protected void query(final CompletionResultSet resultSet, final Document document, final int offset) {
                final JToolTip toolTip = new JToolTip();
                toolTip.setTipText("Press Enter to insert EID: \"" + text + "\"");
                resultSet.setToolTip(toolTip);
                resultSet.finish();
            }
        });
    }

    @Override
    public boolean instantSubstitution(final JTextComponent jtc) {
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
