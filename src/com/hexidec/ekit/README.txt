==========================
Ekit README
==========================

Ekit v1.7b (C)2000-2010 Howard Kistler/hexidec codex & contributors
App/Applet for editing and saving HTML in a Java text component.


ABOUT
--------------------------

Ekit is a program & applet that uses the Java 2 libraries to create an HTML
editor. The Ekit standalone also allows for HTML to be loaded and saved, as
well as serialized and saved as an RTF. It is currently an advanced beta,
and most features work fine.

Updates to this and other hexidec codex projects can be found here:

http://www.hexidec.com/

Please feel free to share any enhancements or bug reports with me at:

hexidec@telepath.com


ABOUT LICENSE
--------------------------

Ekit was originally distributed under the GPL license. However, it was brought
to my attention that the GPL license is not suited for programs that link to
non-GPL libraries, such as Ekit does by linking to the Sun Swing library.
Hence, I have changed the licensing on Ekit to the LGPL, which is more
appropriate. This should not impact any Ekit users adversely, as anyone who
was using it before under the GPL can still do so without any conflicts under
the LGPL. In addition, this opens up Ekit for use with more projects which
might contain some proprietary libraries of their own as well. It is my
intention through this change to make Ekit conform better to open source
licensing and to make Ekit more available and accessible to developers and
users, and I think by switching to the LGPL all of these criteria are thus met.

I am aware that releasing a product under the GPL is supposed to be an
"irreversible" process. However, as this product was probably in violation of
the stricter terms of the GPL, this reassignment to LGPL is not a change so
much as a correction of licensing.


REQUIREMENTS
--------------------------

Ekit requires the following software:
    - Java 2 (Sun JDK 1.5 or later, or compatible variant)
    - Swing Library (standard with Java 2)

EkitApplet requires the following software:
    - Browser with Java Plug-in 1.5 or later

Sun JDK Standard Edition Homepage : http://java.sun.com/javase/index.jsp


FILES
--------------------------

These are the base class source files:

EkitCore.java      - Source for the core Ekit functionality
EkitCoreSpell.java - Source for extended core with spell checking
Ekit.java          - Source for Ekit application shell
EkitApplet.java    - Source for EkitApplet shell

The "action" subdirectory contains these action class sources:

CustomAction.java         - Special situation actions (like anchor insertion)
FormatAction.java         - General text format actions (like subscript)
ListAutomationAction.java - Bulleted/Numbered List creation actions
SetFontFamilyAction.java  - Extended action for proper font settings
StylesAction.java         - CSS Style actions

The "component" subdirectory contains these component class sources:

ExtendedHTMLDocument.java    - HTMLDocument with custom functionality
ExtendedHTMLEditorKit.java   - HTMLEditorKit with custom functionality
FileDialog.java              - Dialog for loading files from server
FontSelectorDialog.java      - Dialog for applying system fonts to text
HTMLUtilities.java           - Special utility functions
ImageFileChooser.java        - Image file selector dialog
ImageFileChooserPreview.java - Image preview rendering panel
ImageFileDialog.java         - Dialog for adding images from file
ImageURLDialog.java          - Dialog for adding images by URL reference
JButtonNoFocus.java          - JButton that does not obtain focus
JComboBoxNoFocus.java        - JComboBox that does not obtain focus
JToggleButtonNoFocus.java    - JToggleButton that does not obtain focus
MutableFilter.java           - Filetype dialog filter
PropertiesDialog.java        - Generic parameter request dialog
RelativeImageView.java       - HTML Image rendering component
SearchDialog.java            - Find/Replace dialog
SimpleInfoDialog.java        - Basic message dialog
UnicodeDialog.java           - Unicode character input dialog
UserInputAnchorDialog.java   - Custom anchor input dialog
UserInputDialog.java         - Custom data input dialog

The "icons" subdirectory contains these custom icons for the menus & buttons:

AlignCenterHK.png      - "Center Text" icon
AlignJustifiedHK.png   - "Align Text Justified" icon
AlignLeftHK.png        - "Align Text Left" icon
AlignRightHK.png       - "Align Text Right" icon
AnchorHK.png           - "Make Anchor" icon
BoldHK.png             - "Bold Text" icon
CopyHK.png             - "Copy Action" icon
CutHK.png              - "Cut Action" icon
DrawHK.png             - "Drawing Symbols" icon
FindHK.png             - "Search-Find" icon
FindAgainHK.png        - "Search-Find Again" icon
ItalicHK.png           - "Italicise Text" icon
MathHK.png             - "Mathematical Symbols" icon
NewHK.png              - "New Document" icon
NewStyledHK.png        - "New Styled Document" icon
OListHK.png            - "Ordered List" icon
OpenHK.png             - "Open Document" icon
PasteHK.png            - "Paste Action" icon
PasteUnformattedHK.png - "Paste Plain" icon
RedoHK.png             - "Redo" icon
ReplaceHK.png          - "Search-Replace" icon
SaveHK.png             - "Save Document" icon
SourceHK.png           - "View Source" icon
StrikeHK.png           - "Strike Text" icon
SubHK.png              - "Subscript Text" icon
SuperHK.png            - "Superscript Text" icon
UListHK.png            - "Unordered List" icon
UnderlineHK.png        - "Underline Text" icon
UndoHK.png             - "Undo" icon
UnicodeHK.png          - "Unicode Character" icon

  The "HK" suffix merely designates that they my default icon designs.
  You can replace these images with your own icons if you wish.

The "com/hexidec/util" directory contains these standard hexidec codex tools:

Base64Codec.java - Base64 encoding/decoding class
Translatrix.java - Translation resource handler class

These are the ancillary files that are included with the standard Ekit
source distribution:

ekit.manifest       - Jar manifest file for ekit.jar
MakeEkit.csh        - Script to (re)build the ekit.jar file (Unix/MacOSX)
MakeEkitApplet.csh  - Script to (re)build the ekitapplet.jar file (Unix/MacOSX)
MakeEkit.bat        - Batch file to (re)build the ekit.jar file (Windows)
MakeEkitApplet.bat  - Batch file to (re)build the ekitapplet.jar file (Windows)
MakeEkit.ant        - XML file to (re)build the ekit.jar file using Ant
MakeEkitApplet.ant  - XML file to (re)build the ekitapplet.jar file using Ant
ekit.css            - Default CSS Stylesheet for Ekit documents
EkitAppletDemo.html - Example web page for displaying Ekit applet
RunEkit.csh         - Script for launching Ekit (Unix/MacOSX)
RunEkit.bat         - Batch file for launching Ekit (Windows)
RunEkit.bat         - Batch file for launching Ekit (Windows)
StartEkit.csh       - Script for launching Ekit (Unix/MacOSX),
                      remembers user preferences
APPLET_SIGNING.txt  - Instructions on how to self-sign the Ekit Applet jar
PARAMS.txt          - Descriptions of Ekit Applet PARAM tags
LGPL.txt            - Copy of the Lesser GNU Public License
README.txt          - This document

The distribution also contains these pre-compiled Ekit JARs:

Basic Version

    ekit.jar
    ekitapplet.jar

Spellcheck Version

    ekitspell.jar
    ekitappletspell.jar

NOTE : If you want to use the spellcheck versions, move or delete the basic
versions and rename the spellcheck versions to the basic names. So, for
example, ekitspell.jar should be renamed ekit.jar.


JAZZY SOURCES
--------------------------

Ekit optionally incorporates a modified subset of the Jazzy open source
spellchecker. The root directory of the Jazzy classes is "com/swabunga/spell".
You will find the following subdirectories and sources there. (See the README
file under the "swabunga" directory for more information.)

The "engine" subdirectory contains the basic mechanics of the spellchecker.

Configuration.java
DoubleMeta.java
EditDistance.java
GenericTransformator.java
PropertyConfiguration.java
SpellDictionary.java
Transformator.java
Word.java
configuration.properties

The "dictionary" subdirectory beneath "engine" houses the available
dictionary files. Currently there is only an English dictionary included.

english - English dictionary file

The "event" sudirectory contains the various event classes and functional
methods for the spellchecker.

BasicSpellCheckEvent.java
DocumentWordTokenizer.java
SpellCheckEvent.java
SpellCheckListener.java
SpellChecker.java
StringWordTokenizer.java
WordTokenizer.java

The "swing" subdirectory contains the GUI components and message lists.

JSpellDialog.java
JSpellForm.java
messages.properties
messages_sv.properties


COMPILATION (OPTIONAL)
--------------------------

If you modify the included .java files, or need to recreate the .class files,
use the following commands within the "source" directory to build them. All
commands assume you are at the top of the source tree (the directory containing
the "com" directory). All commands listed are one-line commands as well, though
some are broken to multiple lines in this file due to length.

Unix or Mac OS X users may run the MakeEkit.csh and MakeEkitApplet.csh scripts
from a terminal window. Remember to preface them with "./" when running from
within the base directory.

Windows users may double-click the MakeEkit.bat and MakeEkitApplet.bat batch
files, or execute them from the command line.

Both sets of batch files take an optional command line argument. You can enter
"basic" after the batch file to compile the standard Ekit classes, or enter
"spell" after the batch file to build in the Jazzy spellchecker classes.

For example, here is a standard build of EkitApplet on Unix:

    ./MakeEkitApplet.csh

    or

    ./MakeEkitApplet.csh basic

Here is an example of building Ekit with spellchecking on Windows:

    MakeEkit.bat spell

Apache Ant (http://jakarta.apache.org/ant/) users may use the included .ant XML
files to compile and build the Ekit jar files with Ant.

You may manually compile Ekit with the following command:

    Unix/MacOSX  javac com/hexidec/ekit/Ekit.java
    Windows      javac com\hexidec\ekit\Ekit.java
    Apache Ant   ant -buildfile MakeEkit.ant

You may manually compile EkitApplet with the following command:

    Unix/MacOSX  javac com/hexidec/ekit/EkitApplet.java
    Windows      javac com\hexidec\ekit\EkitApplet.java
    Apache Ant   ant -buildfile MakeEkitApplet.ant

Ekit takes no special arguments during compile. The above command assumes your
Java development environment is configured according to minimal standards and
can locate the relevant Java core files properly.

View the .csh or .bat files for the proper syntax to manually create the jar
files under your OS.

If you recompile the applet jar, be sure to sign the jar file so that all Ekit
functions are available. See the APPLET_SIGNING file for more information on
performing the applet signing process.


EXECUTION (APP)
--------------------------

All users with properly configured systems will be able to launch Ekit by
double-clicking on the "ekit.jar" file. Unix/MacOSX users may lanuch Ekit with
the "RunEkit.csh" script. Windows users may launch Ekit by double-clicking the
batch file "RunEkit.bat".

You may manually execute Ekit from the command line with the following command
from within the directory containing the ekit.jar file:

    java -jar ekit.jar com.hexidec.ekit.Ekit
        [-t|t+|T] [-s|S] [-m|M] [-x|X] [-b|B] [-v|V] [-d|D] [-h|H|?]
        [-fHTMLfile] [-cCSSfile] [-rRawHTML] [-uURL] [-lLangcode]

Note that your CLASSPATH settings should include the "current directory"
specifier "." for this to work.

The [optional] command line arguments are:

    -t|-t+|-T : Show Single/Multiple/Hide Toolbar (Show Multiple by default)
    -s|-S     : Show/Hide the Source window (Hide by default)
    -m|-M     : Show/Omit Menu icons (Show by default)
    -x|-X     : Exclusive Edit Mode On/Off (On by default)
    -b|-B     : Document is/isn't Base64 encoded (Isn't by default)
    -v|-V     : Include/Omit the Spellchecker (Omit by default)
    -d|-D     : Show/Hide the Debug menu (Hide by default)
    -h|H|?    : Display the Help information (Ekit only)
    -f        : Load the HTML document specified by HTMLfile
    -c        : Load the CSS stylesheet specified by CSSfile
    -r        : Load the raw HTML string specified by RawHTML
    -l        : Start Ekit in the language specified by Langcode*

[* Langcode is a combination of the 2-letter language and country codes, in the
format "xx_XX", where xx is the language code and XX is the country code. For
example, the Langcode for English/United States is "en_US", English/United
Kingdom is "en_UK", and German/Germany is "de_DE". Note that there must be
a matching "LanguageResources_xx_XX.properties" file in the com/hexidec/ekit
directory for the language you want to use. Missing language files, and
existing language files that may be missing words in use, will take words from
the "LanguageResources.properties" file, which contains default words in
"en_US" encoding marked with a trailing asterisk.]

So, for example, to start Ekit with the toolbar hidden, the source window
showing, and pre-load it with the "Home.html" document and "Styles.css" 
stylesheet, you would enter:

    java -jar ekit.jar com.hexidec.ekit.Ekit -T -s -fHome.html -cStyles.css

To start Ekit with a short example HTML document written as a raw string, you
could enter:

    java -jar ekit.jar com.hexidec.ekit.Ekit -r"<HTML><BODY>This is a <B>test
        </B></BODY></HTML>"

To start Ekit with the Italian/Italy language file:

    java -jar ekit.jar com.hexidec.ekit.Ekit -lit_IT

Note that you shouldn't have to specify the language setting if you want Ekit
to run in your native language, provided there is an appropriate language file
in the com/hexidec/ekit directory and your Java environment is configured to
use your language by default.


EXECUTION (APPLET)
--------------------------

Embed the Ekit applet in a web page and view in an appropriate browser.
The jar file should be included in the directory with the web page and
it should also be "visible" to the web server.

The included "EkitAppletDemo.html" page contains the code necessary to
view the applet properly. You may use this as the basis for including
Ekit in your own web pages.


COMMAND SUMMARY
--------------------------

File Menu

    New Document - Create new document (clear current one if exists).
    New Styled Document - Create new document, keeping any active style definitions.
    Open Document - Load an HTML document into the editor.
    Open Stylesheet - Load CSS stylesheet and apply it to the current document.
    Open Base64 Document - Load a Base64 encoded document into the editor.
    Save - Save the current document (prompts for filename if new document).
    Save As - Save the document with the name specified in the file chooser.
    Save Body - Save the document after deleting the HEAD element.
    Save RTF - Save the document in Rich Text Format (RTF).
    Save Base64 - Save the document in Base64 encoding.
    Serialize - Save the document in the default Java serialization form.
    Read From Serialized - Load a serialized document into the editor.
    Exit - Close the app (you will lose any unsaved work).

Edit Menu

    Cut - Remove the selected text and place it in the clipboard.
    Copy - Place a copy of the selected text in the clipboard.
    Paste - Place the contents of clipboard into the document at the current
        cursor position.
    Paste Plain - Like Paste, only clipboard contents are added with all
        formatting removed first.
    Undo - Undo the previous action (some may not be undoable).
    Redo - Redo the last undone action (some may not be redoable).
    Select All - Select all the text in the editor.
    Select Paragraph - Select the paragraph at the current cursor postion.
    Select Line - Select the line at the current cursor postion.
    Select Word - Select the word at the current cursor postion.
	Enter Key Inserts... - Changes what the editor uses when the ENTER key is pressed.
		Paragraph <P> - ENTER key ends the current paragraph tag and starts a new one.
		Break <BR> - ENTER key inserts a new line break tag.
		(Note that the SHIFT-ENTER key always inserts a break tag.)

View Menu

    Toolbar - Toggles display of the toolbar. (Single toolbar verion only.)
    Toolbars - Submenu of toolbars. (Multiple toolbar version only.)
        Main - Toggles display of the main toolbar. 
        Format - Toggles display of the format toolbar. 
        Styles - Toggles display of the styles toolbar. 
    Source - Toggles display of the Source window, where the HTML can be
        observed and directly manipulated.

Font Menu

    Bold - Bold the selected text.
    Italic - Italicise the selected text.
    Underline - Underline the selected text.
    Strike-through - Strike through the selected text.
    Big - Increase the font size of the selected text.
    Small - Decrease the font size of the selected text.
    Size - Submenu of HTML defined size choices.
        Smallest - Set text to HTML size 1.
        Smaller  - Set text to HTML size 2.
        Small    - Set text to HTML size 3.
        Regular  - Set text to HTML size 4.
        Larger   - Set text to HTML size 6.
        Largest  - Set text to HTML size 7.
    Superscript - Change the selected text to superscripted text.
    Subscript - Change the selected text to subscripted text.
    Monospaced - Render the select text in the Monospaced font.
    Sans-serif - Render the select text in the Sans-serif font.
    Serif - Render the select text in the Serif font.
    Select Font... - Choose a system font via the font selection dialog.
    Color - Submenu of custom and HTML defined color choices.
        Custom... - Color the selected text with a color defined in the custom
            color dialog.
        Aqua - Color the selected text aqua.
        Black - Color the selected text black.
        Blue - Color the selected text blue.
        Fuschia - Color the selected text fuschia.
        Gray - Color the selected text gray.
        Green - Color the selected text green.
        Lime - Color the selected text lime.
        Maroon - Color the selected text maroon.
        Navy - Color the selected text navy.
        Olive - Color the selected text olive.
        Purple - Color the selected text purple.
        Red - Color the selected text red.
        Silver - Color the selected text silver.
        Teal - Color the selected text teal.
        White - Color the selected text white.
        Yellow - Color the selected text yellow.

Format Menu

    Align - Submenu of text alignment options.
        Align Left - Left align the selected text (may include unselected text
            that is part of the enclosing element).
        Align Center - Center align the selected text (same proviso as Left).
        Align Right - Right align the selected text (same proviso as Left).
        Align Justified - Justify the selected text (same proviso as Left).
    Heading - Submenu of HTML heading format options.
        Heading 1 - Render the selected text in the HTML <H1> style.
        Heading 2 - Render the selected text in the HTML <H2> style.
        Heading 3 - Render the selected text in the HTML <H3> style.
        Heading 4 - Render the selected text in the HTML <H4> style.
        Heading 5 - Render the selected text in the HTML <H5> style.
        Heading 6 - Render the selected text in the HTML <H6> style.
    Unordered List - Convert the selected text to an unordered (bulleted)
        list. New list items are created at each paragraph mark in the text.
    Ordered List - Convert the selected text to an ordered (numeric)
        list. New list items are created at each paragraph mark in the text.
    List Item - Convert the selected text to a list item (will create an
        enclosing unordered list if not part of a defined list).
    Blockquote - Place the selected text inside BLOCKQUOTE tags.
    Preformatted - Place the selected text inside PRE (preformat) tags.
    Strong - Place the selected text inside STRONG tags.
    Emphasis - Place the selected text inside EM (emphasis) tags.
    Typewritten - Place the selected text inside TT (typewriter) tags.
    Span - Place the selected text inside SPAN tags.

Insert Menu

    Hyperlink - Convert the selected text to a hyperlink after specifying
        the URL in the pop-up dialog.
    Break - Insert an HTML break <BR> element.
    Nonbreaking Space - Insert an HTML non-breaking space (&nbsp;).
    Unicode Characters... - Submenu of Unicode character insertion choices.
        All Characters... - Open the Unicode selection tool with default set.
        Math Symbols... - Open the Unicode selection tool with math set.
        Drawing Symbols... - Open the Unicode selection tool with drawing set.
        Dingbats... - Open the Unicode selection tool with dingbat set.
        Signifiers... - Open the Unicode selection tool with signifier set.
        Specials... - Open the Unicode selection tool with special set.
    Horizontal Rule - Insert an HTML horizontal rule <HR> element.
    Image From File - Insert an image from file chooser pop-up.
    Image From URL - Insert an image from a valid URL.

Table Menu

    Create Table... - Insert a table based on settings in pop-up.
    Insert Row - Insert a row in the current table.
    Insert Column - Insert a column in the current table.
    Delete Row - Delete a row in the current table.
    Delete Column - Delete a column in the current table.

Forms Menu

    Insert Form - Add the surrounding FORM tags for an HTML form.
    Text Field - Insert an HTML Text Field <INPUT TYPE="text">.
    Text Area - Insert an HTML Text Area <TEXTAREA></TEXTAREA>.
    Checkbox - Insert an HTML Checkbox <INPUT TYPE="checkbox">.
    Text Field - Insert an HTML Radio Button <INPUT TYPE="radio">.
    Button - Insert an HTML Button <INPUT TYPE="button">. (NON-RENDERING!)
    Submit Button - Insert an HTML Submit Button <INPUT TYPE="submit">.
    Resest Button - Insert an HTML Reset Button <INPUT TYPE="reset">.

Help Menu

    About - Displays the Ekit information dialog.

Debug Menu (not normally visible)

    Describe Doc - Describe the document nodes to the console window.
    Describe CSS - Describe the stylesheet properties to the console window.
    What Tags? - List the node tags applied to the element under the cursor.


VERSION HISTORY
--------------------------

0.1  (08/08/2000)
    - initial creation
0.2  (08/24/2000)
    - added tag attribute management
    - added anchors
    - cleaned up menu actions
0.3  (08/30/2000)
    - added appName & currentFile
    - added Save menu item
    - added updateTitle method
    - changed to only empty constructor
    - added refreshOnUpdate() optional refresh code
0.4  (08/31/2000)
    - added table insertion (experimental)
    - added image insertion (experimental)
0.45 (09/05/2000)
    - added list formatting
    - added break insertion
    - added horizontal rule insertion
    - added purgeUndos() convenience method for resetting the UndoManager
    - changed "load" commands to "open" commands
      (more in line with current application conventions)
    - merged some menus so that they no longer build an explicit JMenuItem
      (sacrifices some readability for object allocation improvements)
0.5  (09/18/2000)
    - added CSS support
    - moved calls to JFileChooser to a reusable method
    - moved "debug" types of functions to their own menu
    - renamed the "Styles" menu "Format", so as not to be confused with
      StyleSheet Styles
0.6  (09/29/2000)
    - added optional toolbar
    - added table row, table cell, and table-within-table insertion
    - vastly improved break & horizontal rule insertion
    - allow pre-loading of HTML document and CSS stylesheet from command line
    - centralized exception handling
    - fixed getFileFromChooser method to correctly display OPEN/SAVE dialogs
    - now exists as a JAR file
0.7  (11/03/2000)
    - added Source window for viewing/editing HTML tags
    - added Shift-Return insertion of BR tags
0.8  (05/05/2001)
    - added JButtonNoFocus inner class to keep toolbar buttons from taking
      focus from editor canvas
    - added JToggleButtonNoFocus inner class for toolbar elements that indicate
      toggle-state features (such as View Source)
0.9 (01/06/2002)
    - added mnemonics for menu items (this also removed need for KeyListener)
    - added View menu
    - added Help menu
    - added table settings dialog
    - added Internationalization capabilities
    - added language files for Italian, German, Spanish, Portuguese, and
      Slovenian
    - fixed lack of repaint after cancelling "Open Stylesheet"
    - Debug menu now optional, defaults to OFF
    - moved View Source and Edit Mode to new View menu
0.9a (02/02/2002)
    - added image rendering & image selector
    - image types supported : JPEG, GIF, & PNG
    - added fix for dealing with HTML character encoding issue
0.9b (03/08/2002)
    - added CSS style selector
    - added more standard HTML tags : STRONG, EM, BLOCKQUOTE, PRE, and TT
0.9c (03/23/2002)
    - added Search menu and Find/Replace functions
    - added auto-bulleted lists
    - added warning dialog for actions that fail
    - split-pane divider now remembers its position
    - changed and consolidated some menus
    - altered "Save Body" method to work more as expected
0.9d (09/24/2002)
    - added language files for French and Norwegian
    - broke out inner classes to separate component classes
    - added EkitCallback class to allow easier access by shared components
    - much improved anchor handling and formatting
    - changes to get app to work on JDK 1.4
0.9e (10/26/2002)
    - Ekit split into core (EkitCore) and shell (Ekit, EkitApplet) classes
    - added language files for Finnish and Dutch
    - added support for HTML forms and form elements
    - added <STRIKE> tag support
    - font familes and <HR> insertion now through inherent editor actions
    - "Insert Table Row" now inserts the correct number of cells
    - added generic PropertiesDialog
    - SimpleInfoDialog auto-centers and allows different dialog types
    - added calls to build semi-custom menus and toolbars
    - removed EkitCallback (obsolesced by EkitCore)
    - Change of license to LGPL (see ABOUT LICENSE section)
0.9f (11/25/2002)
    - optional integration of Jazzy spellchecker
    - addition of font selector
    - greater menu customization
0.9g (5/5/2003)
    - can load and save documents in Base64 encoded format
    - bulleted lists now create new entries upon hitting ENTER
    - added color picker to font selector
    - support for access to the System Clipboard
    - support for loading images from server via servlet
    - added dispose() method to core
    - added more insert and delete options for tables
    - tables now have their own menu and better functionality
    - fixed problem with IMG tag SRC URLs containing spaces
    - fixed the getDocumentBody error where the contents did not reflect
          the current document
    - fixed the known memory/resource leaks caused by the SpellChecker
    - added language files for Spanish (Mexican), Hungarian and Chinese
0.9h (4/28/2004)
    - new symbol insertion dialog
    - rewritten EkitCoreSpell now extends EkitCore, adding just spell checker
    - EkitCore includes hooks to support EkitCoreSpell
    - source view panel replaced with JTextArea (lighter weight)
    - StyledDocument constructors and setter method
    - optional multiple "themed" toolbars in place of single catch-all toolbar
    - command-line usage options available in Ekit (use -h or -? argument)
1.0 (1/1/2005)
    - Unicode character insertion tool
    - symbol tool removed (obsolesced by Unicode tool)
    - font selector toolbar tool
    - vastly improved font handling (via new SetFontFamilyAction class)
          changing size and face doesn't erase other settings
    - improved and expanded code behind text alignment options
    - text cursor now appears correctly in edit panes
    - actions that don't apply to the source editor (such as formatting)
          are now disabled when the source pane has focus
    - removed "Clear Format" function
          (no longer works dues to changes in underlying classes)
    - added more toolbar buttons (Align, Undo/Redo, Find, Unicode, Math)
    - undo/redo no longer display action name, as this wasn't very useful
    - more icons
    - added language file for Russian
    - added PARAMS document which explains Ekit applet parameter settings
1.1 (1/5/2006)
    - Shift-Space key combination inserts &nbsp; character
    - ImageURLDialog class added
    - Can add images from URLs
    - Removed "Image From Server" code, as it caused more problems than it
      fixed (better to use new URL insertion anyway)
    - FileDialog class & associated method removed
    - added language files for Swedish, Polish and Ukrainian
    - removed most constructors from EkitCore & EkiCoreSpell, as they were
        unused and superfluous
1.2 (10/8/2006)
    - removed GIF icons, added full set of PNG icons
      (Thanks to Vincent Vu for pointing out the related slowdown in the
       search-for-PNG loading code!)
    * Future icon sets should avoid GIF format and be done in PNG only
    - Updated code to use Java 1.5 compliant constructors (still backwardly
       compatible)
    - Updated Spanish translation file (Thanks to Francisco Morero Peyrona)
1.3 (7/4/2007)
    - greatly enhanced table and cell editing features (Thanks to Elisabeth Novotny)
    - added printing capabilities (Thanks to Kei Gauthier and Maciej Kubicki)
    - added language files for Turkish (Thanks to Melih Çetin)
1.4 (3/27/2008)
    - all code updated to use Java 1.5 features
    - added ParserCallback methods (Thanks to Janko Jochimsen)
    - removed deprecated focus methods from component classes
    - made writer methods public
    - added public method for getting RTF from applet
    - removed image "Insert From File" from applet (disallowed by security model)
    - added language files for Japanese (Thanks to Procelerate Technologies Inc.)
    - numerous improvements, including corrected HR insertion (Thanks to Luca Mari)
    - fix for null image insertion (Thanks to Kurt Penrose)
1.5 (5/17/2009)
    - select paragraph <P> or break <BR> insertion on carriage return (ENTER)
    - updated toolbar initialisation code
    - removed extraneous imports
    - better dialog invocation
    - additional minor fixes
1.5b (7/3/2009)
    - new AlignAction class makes alignment changes render more consistently
1.6 (12/2/2009)
    - better clipboard handling of text pasting from other programs
    - improved bulleted list handling, including fix on backspace and trailing break
    - separate New Document and New Styled Document functions
    - new StartEkit.csh script allows for command line execution
      that also saves user preferences (Thanks to Ivan Trendafilov)
    - added Bulgarian and updated German translation files (Thanks to Ivan Trendafilov)
    - updated Spanish translation file (Thanks to Francisco Morero Peyrona)
1.7 (2/10/2010)
    - support for pasting plain text
    - new Image dialogs (both file and URL) that allow setting IMG tag attribs
      (alt, width, height)
    - fixed font dialog so that it sets editor font when used
      (stopped functioning due to post 1.4 JDK changes), plus altered component
      order in dialog to make font preview more useful (you can now arrow-key
      through the font list and watch the sample text change dynamically to the
      highlighted font)
    - List Improvements:
        - improved ListAutomationAction no longer adds <br/> to lists nor
          deletes previous list item content when editing a subsequent item
        - adds extra paragraph to the end if a list is the last item in the
          document created
        - hitting return on an empty list element removes it
        - a list that is empty as a result of losing all its <li> children is
          automatically deleted
        - click the list creation button inside a list reverts it back to text
    - removed obsolete TableInputDialog & ImageDialog classes
    - more Java 2 standardised updates & cleanup


KNOWN BUGS
--------------------------

Issues caused by Sun's Java classes:

    - <INPUT TYPE="button"> does not always render in documents. Unknown why.
    - Deleting the last column of a table appears to take the closing </TR> tag
         with it, causing the next row to be appended to the current one.
         Appears to be yet another problem with Sun's classes mangling the DOM.
    - "Justified" alignment is incompletely supported by the editor.
         Appears to center the text on most platforms.
    - Changing the color of already colored text does not always work.
         This is one more known bug in the Sun classes.
    - HTML forms can cause runtime exceptions and other problems, due to
         the way in which the editor pane document structure handles them.


CONTRIBUTORS
--------------------------

Thanks to the many people who have downloaded and used Ekit, as well as
providing valuable feedback. The following people have made additional
contributions to Ekit. (List in is chronological order of contribution.)

Note that since the Ekit source is being published on various websites now,
I have obscured the email addresses using the AT and DOT keywords to stand for
the '@' and '.' characters.

Yaodong Liu (yaodongliu AT yahoo DOT com)
    - CSS Support Code
    - HTML Insertion Support Code
Gyoergy Magoss (GYOERGY DOT MAGOSS AT bhf-bank DOT com)
    - Table Support Code
Oliver Moser (omoser AT dkf DOT de)
    - HTML Insertion Support Code
Michael Goldberg (MGoldberg AT yet2 DOT com)
    - README Support
Cecile Rostaing (cecile DOT rostaing AT free DOT fr)
    - Feature Suggestions
Thomas Gauweiler (gauweiler AT fzi DOT de)
    - Early development demo files
Frits Jalvingh (jal AT grimor DOT com)
    - Correct image rendering and character encoding support
    - CSS Styles Selector
    - Original ExtendedHTMLEditorKit & RelativeImageView classes
    - Original ImageFileChooser & ImageFileChooserPreview classes
Jerry Pommer (jpommer AT brainfood DOT com)
    - ExtendedHTMLDocument.java & ExtendedHTMLEditorKit.java code
        to fix format toggling
Ruud Noordermeer (ruud DOT noordermeer AT back2front DOT nl)
    - Fix for split-pane rendering issue in EkitApplet
Mindaugas Idzelis (aim4min AT users DOT sourceforge DOT net)
    - Provided the excellent Jazzy spellchecker
Raymond Penners (dotsphinx AT users DOT sourceforge DOT net)
    - Fix for IMG tag SRC URLs containing spaces
Steve Birmingham (steve DOT birmingham AT c3bgroup DOT com)
    - Server image support classes and methods
    - Color picker code in font dialog
    - Numerous bug fixes, including the dispose() for the SpellChecker
Rafael Cieplinski (cieplinski AT web DOT de)
    - Better auto-bulleting function
    - Improved table handling
    - Original HTMLUtilities class
Nico Mack (nico DOT mack AT mmp DOT lu)
    - System clipboard access code
    - Other bug fixes
Michael Pearce (michaelgpearce AT yahoo DOT com)
    - Previous symbol insertion dialog & code
Murray Altheim (m DOT altheim AT open DOT ac DOT uk)
    - StyledDocument support code
Mattias Malmgren (mattias AT freefarm DOT se)
    - ImageURLDialog code
Maciej Kubicki (maciej DOT kubicki AT zst-softel DOT pl)
    - Modified DocumentRenderer class
Elisabeth Novotny (hylight AT onthenet DOT ch)
    - Table edit and Cell edit functionality
James Rapp (james DOT rapp AT yale DOT edu)
    - Cross-platform Control key mask tip
Janko Jochimsen (janko DOT jochimsen AT urios-beratung DOT de)
    - EkitStandardParserCallback class and associated methods
Luca Mari (lmari@liuc.it)
    - Corrected HR insertion
    - Many code cleanups and removal of orphaned methods
Kurt Penrose (ktpabc AT yahoo DOT com)
    - Null image trap fix
Ivan Trendafilov (ivan DOT trendafilov AT gmail DOT com)
	- StartEkit.csh script

TRANSLATIONS
--------------------------

Thanks also to the people who volunteered to translate Ekit into their
languages. The following people have made translations available.
(List in is chronological order of contribution. Any current errors in the
language files are most likely due to my own attempts to add new terms to them,
and I appreciate receiving any corrections.)

Silvio Moioli (moio AT tiscali DOT it) &
Nick Schwendener (nschwendener AT vtxnet DOT ch)
    - Italian
        LanguageResources_it_CH.properties
        LanguageResources_it_IT.properties
Gyoergy Magoss (GYOERGY DOT MAGOSS AT bhf-bank DOT com) &
Ivan Trendafilov (ivan DOT trendafilov AT gmail DOT com)
    - German
        LanguageResources_de_DE.properties
Jesus Escanero (jescanero AT yahoo DOT es) &
Eusebio Barriga (fametown AT users.sourceforge DOT net) &
Francisco Morero Peyrona (peyrona AT gmail DOT com)
    - Spanish
        LanguageResources_es_ES.properties
Fernando Luiz (responsavel2 AT hotmail DOT com)
    - Portuguese
        LanguageResources_pt_BR.properties
        LanguageResources_pt_PT.properties
Jernej Vicic (jernej AT activetools DOT si)
    - Slovenian
        LanguageResources_sl_SI.properties
Gerald Estadieu (gerald DOT estadieu AT cem-macau DOT com) &
Michel Poulain (mike AT lashampoo DOT com)
    - French
        LanguageResources_fr_FR.properties
Anders Bjorvand (anders AT kommunion DOT no)
    - Norwegian
        LanguageResources_no_NO.properties
Vesa Kotilainen (vesa DOT kotilainen AT sonera DOT com)
    - Finnish
        LanguageResources_fi_FI.properties
Mark de Haan (mark AT starwave DOT nl)
    - Dutch
        LanguageResources_nl_NL.properties
Samuel Dmaz
    - Spanish (Mexican)
        LanguageResources_es_MX.properties
Tamás Érdfalvi (devnull AT eagent DOT hu)
    - Hungarian
        LanguageResources_hu_HU.properties
Yang Yu (yangyu AT users DOT sourceforge DOT net)
    - Chinese
        LanguageResources_zh_CN.properties
Dmitry Lihachev (lda AT tusur DOT ru)
    - Russian
        LanguageResources_ru_RU.properties
Hans Holmlund (hans AT excito DOT se)
    - Swedish
        LanguageResources_sv_SE.properties
Jonatan Olofsson (jonatan DOT olofsson AT brpsystems DOT se)
    - Swedish
        LanguageResources_se_SE.properties
Piotr Malinski (riklaunim AT gmail DOT com)
    - Polish
        LanguageResources_pl_PL.properties
Vitaliy Yakovchuk (yakovchuk25 AT ukr DOT net)
    - Ukrainian
        LanguageResources_ru_UK.properties
Melih Çetin (cetin DOT melih AT gmail.com)
    - Turkish
        LanguageResources_tr_TR.properties
Procelerate Technologies, Inc.
    - Japanese
        LanguageResources_ja_JP.properties
Ivan Trendafilov (ivan DOT trendafilov AT gmail DOT com)
	- Bulgarian
		LanguageResources_bg_BG.properties
