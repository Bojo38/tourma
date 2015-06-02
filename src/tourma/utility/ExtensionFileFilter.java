/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.io.File;
import java.util.Locale;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Frederic Berger
 */
public class ExtensionFileFilter extends FileFilter {

    private String description;
    private String extensions[];

    /**
     *
     * @param description
     * @param extension
     */
    public ExtensionFileFilter(final String description, final String extension) {
        this(description, new String[]{extension});
    }

    /**
     *
     * @param description
     * @param extensions
     */
    public ExtensionFileFilter(final String description, final String extensions[]) {
        if (description == null) {
            this.description = extensions[0];
        } else {
            this.description = description;
        }
        this.extensions = extensions.clone();
        toLower(this.extensions);
    }

    private void toLower(String array[]) {
        final int n = array.length;
        for (int i = 0; i < n; i++) {
            array[i] = array[i].toLowerCase(Locale.getDefault());
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
            final String path = file.getAbsolutePath().toLowerCase(Locale.getDefault());
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

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
