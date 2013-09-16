/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.io.File;
import java.util.ResourceBundle;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Frederic Berger
 */
public class ExtensionFileFilter extends FileFilter {

    static final ResourceBundle LANGUAGE = ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
    String description;
    String extensions[];

    public ExtensionFileFilter(final String description, final String extension) {
        this(description, new String[]{extension});
    }

    public ExtensionFileFilter(final String description, final String extensions[]) {
        if (description == null) {
            this.description = extensions[0];
        } else {
            this.description = description;
        }
        this.extensions = (String[]) extensions.clone();
        toLower(this.extensions);
    }

    private void toLower(String array[]) {
        final int n = array.length;
        for (int i = 0; i < n; i++) {
            array[i] = array[i].toLowerCase();
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean accept(final File file) {
        boolean result = false;
        if (file.isDirectory()) {
            result = true;
        } else {
            final int n = extensions.length;
            final String path = file.getAbsolutePath().toLowerCase();
            for (int i = 0; i < n; i++) {
                final String extension = extensions[i];
                if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
