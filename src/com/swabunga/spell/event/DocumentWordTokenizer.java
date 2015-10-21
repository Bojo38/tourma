package com.swabunga.spell.event;

import java.util.*;
import java.text.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Segment;
import javax.swing.text.BadLocationException;

/** This class tokenizes a swing document model. It also allows for the
 *  document model to be changed when corrections occur.
 *
 * @author Jason Height (jheight@chariot.net.au)
 */
public class DocumentWordTokenizer implements WordTokenizer {
  /** Holds the start character position of the current word*/
  private int currentWordPos = 0;
  /** Holds the end character position of the current word*/
  private int currentWordEnd = 0;
  /** Holds the start character position of the next word*/
  private int nextWordPos = -1;
  /** The actual text that is being tokenized*/
  private Document document;
  /** The character iterator over the document*/
  private Segment text;
  /** The cumulative word count that have been processed*/
  private int wordCount = 0;
  /** Flag indicating if there are any more tokens (words) left*/
  private boolean moreTokens = true;
  /** Is this a special case where the currentWordStart, currntWordEnd and
   *  nextWordPos have already been calculated. (see nextWord)
   */
  private boolean first = true;

  private BreakIterator sentanceIterator;
  private boolean startsSentance = true;


  public DocumentWordTokenizer(Document document) {
    this.document = document;
    //Create a text segment over the etire document
    text = new Segment();
    sentanceIterator = BreakIterator.getSentenceInstance();
    try {
      document.getText(0, document.getLength(), text);
      sentanceIterator.setText(text);
      currentWordPos = getNextWordStart(text, 0);
      //If the current word pos is -1 then the string was all white space
      if (currentWordPos != -1) {
        currentWordEnd = getNextWordEnd(text, currentWordPos);
        nextWordPos = getNextWordStart(text, currentWordEnd);
      } else {
        moreTokens = false;
      }
    } catch (BadLocationException ex) {
      moreTokens = false;
    }
  }

  /** This helper method will return the start character of the next
   * word in the buffer from the start position
   */
  private static int getNextWordStart(Segment text, int startPos) {
    if (startPos <= text.getEndIndex())
      for (char ch = text.setIndex(startPos);ch != Segment.DONE;ch = text.next()) {
        if (Character.isLetterOrDigit(ch)) {
          return text.getIndex();
        }
      }
    return -1;
  }

  /** This helper method will return the end of the next word in the buffer.
   *
   */
  private static int getNextWordEnd(Segment text, int startPos) {
    for (char ch = text.setIndex(startPos); ch != Segment.DONE;ch = text.next()) {
      if (!Character.isLetterOrDigit(ch)) {
        return text.getIndex();
      }
    }
    return text.getEndIndex();
  }


  /** Returns true if there are more words that can be processed in the string
   *
   */
  public boolean hasMoreWords() {
    return moreTokens;
  }

  /** Returns the current character position in the text
   *
   */
  public int getCurrentWordPosition() {
    return currentWordPos;
  }

  /** Returns the current end word position in the text
   *
   */
  public int getCurrentWordEnd() {
    return currentWordEnd;
  }


  /** Returns the next word in the text
   *
   */
  public String nextWord() {
    if (!first) {
      currentWordPos = nextWordPos;
      currentWordEnd = getNextWordEnd(text, currentWordPos);
      nextWordPos = getNextWordStart(text, currentWordEnd+1);
      int current = sentanceIterator.current();
      if (current == currentWordPos)
        startsSentance = true;
      else {
        startsSentance = false;
        if (currentWordEnd > current)
          sentanceIterator.next();
      }

    }
    //The nextWordPos has already been populated
    String word = null;
    try {
      word = document.getText(currentWordPos, currentWordEnd-currentWordPos);
    } catch (BadLocationException ex) {
      moreTokens = false;
    }
    wordCount++;
    first = false;
    if (nextWordPos == -1)
      moreTokens = false;
    return word;
  }

  /** Returns the current number of words that have been processed
   *
   */
  public int getCurrentWordCount() {
    return wordCount;
  }

  /** Replaces the current word token*/
  public void replaceWord(String newWord) {
    if (currentWordPos != -1) {
      try {
      /* ORIGINAL
        document.remove(currentWordPos, currentWordEnd - currentWordPos);
        document.insertString(currentWordPos, newWord, null);
      */
      // Howard's Version for Ekit
		Element	element = ((javax.swing.text.html.HTMLDocument)document).getCharacterElement(currentWordPos);
		AttributeSet attribs = element.getAttributes();
        document.remove(currentWordPos, currentWordEnd - currentWordPos);
        document.insertString(currentWordPos, newWord, attribs);
      // End Howard's Version
        //Need to reset the segment
        document.getText(0, document.getLength(), text);
      } catch (BadLocationException ex) {
        throw new RuntimeException(ex.getMessage());
      }
      //Position after the newly replaced word(s)
      //Position after the newly replaced word(s)
      first = true;
      currentWordPos = getNextWordStart(text, currentWordPos+newWord.length());
      if (currentWordPos != -1) {
        currentWordEnd = getNextWordEnd(text, currentWordPos);
        nextWordPos = getNextWordStart(text, currentWordEnd);
        sentanceIterator.setText(text);
        sentanceIterator.following(currentWordPos);
      } else moreTokens = false;
    }
  }

  /** Returns the current text that is being tokenized (includes any changes
   *  that have been made)
   */
  public String getContext() {
    return text.toString();
  }

  /** Returns true iif the current word is at the start of a sentance*/
  public boolean isNewSentance() {
    return startsSentance;
  }

}