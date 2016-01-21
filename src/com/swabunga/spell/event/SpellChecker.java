package com.swabunga.spell.event;

import com.swabunga.spell.engine.*;
import java.util.*;

/**
 * This is the main class for spell checking (using the new event based spell
 *  checking).
 *
 * @author     Jason Height (jheight@chariot.net.au)
 * @created    19 June 2002
 */

public class SpellChecker
{
  /** Flag indicating that the Spell Check completed without any errors present*/
  public static final int SPELLCHECK_OK=-1;
  /** Flag indicating that the Spell Check completed due to user cancellation*/
  public static final int SPELLCHECK_CANCEL=-2;

  private List eventListeners = new ArrayList();
  private SpellDictionary dictionary;
  
  private Configuration config = Configuration.getConfiguration();

  /**This variable holds all of the words that are to be always ignored */
  private Set ignoredWords = new HashSet();
  private Map autoReplaceWords = new HashMap();

  /**
   * Constructs the SpellChecker. The default threshold is used
   *
   * @param  dictionary  Description of the Parameter
   */
  public SpellChecker(SpellDictionary dictionary) {
    if (dictionary == null) {
      throw new IllegalArgumentException("dictionary must non-null");
    }
    this.dictionary = dictionary;
  }


  /**
   * Constructs the SpellChecker with a threshold
   *
   * @param  dictionary  Description of the Parameter
   * @param  threshold   Description of the Parameter
   */
  public SpellChecker(SpellDictionary dictionary, int threshold) {
    this(dictionary);
    config.setInteger( Configuration.SPELL_THRESHOLD, threshold );
  }


  /**
   *Adds a SpellCheckListener
   *
   * @param  listener  The feature to be added to the SpellCheckListener attribute
   */
  public void addSpellCheckListener(SpellCheckListener listener) {
    eventListeners.add(listener);
  }


  /**
   *Removes a SpellCheckListener
   *
   * @param  listener  Description of the Parameter
   */
  public void removeSpellCheckListener(SpellCheckListener listener) {
    eventListeners.remove(listener);
  }


  /**
   * Fires off a spell check event to the listeners.
   *
   * @param  event  Description of the Parameter
   */
  protected void fireSpellCheckEvent(SpellCheckEvent event) {
    for (int i = eventListeners.size() - 1; i >= 0; i--) {
      ((SpellCheckListener) eventListeners.get(i)).spellingError(event);
    }
  }


  /**
   * This method clears the words that are currently being remembered as
   *  Ignore All words and Replace All words.
   */
  public void reset() {
    ignoredWords.clear();
    autoReplaceWords.clear();
  }


  /**
   * Checks the text string.
   *  <p>
   *  Returns the corrected string.
   *
   * @param  text   Description of the Parameter
   * @return        Description of the Return Value
   * @deprecated    use checkSpelling(WordTokenizer)
   */
  public String checkString(String text) {
    StringWordTokenizer tokens = new StringWordTokenizer(text);
    checkSpelling(tokens);
    return tokens.getFinalText();
  }


  /**
   * Returns true iif this word contains a digit
   *
   * @param  word  Description of the Parameter
   * @return       The digitWord value
   */
  private final static boolean isDigitWord(String word) {
    for (int i = word.length() - 1; i >= 0; i--) {
      if (Character.isDigit(word.charAt(i))) {
        return true;
      }
    }
    return false;
  }


  /**
   * Returns true iif this word looks like an internet address
   *
   * @param  word  Description of the Parameter
   * @return       The iNETWord value
   */
  private final static boolean isINETWord(String word) {
    //JMH TBD
    return false;
  }


  /**
   * Returns true iif this word contains all upper case characters
   *
   * @param  word  Description of the Parameter
   * @return       The upperCaseWord value
   */
  private final static boolean isUpperCaseWord(String word) {
    for (int i = word.length() - 1; i >= 0; i--) {
      if (Character.isLowerCase(word.charAt(i))) {
        return false;
      }
    }
    return true;
  }


  /**
   * Returns true iif this word contains mixed case characters
   *
   * @param  word  Description of the Parameter
   * @param startsSentance True if this word is at the start of a sentance
   * @return       The mixedCaseWord value
   */
  private final static boolean isMixedCaseWord(String word, boolean startsSentance) {
    int strLen = word.length();
    boolean isUpper = Character.isUpperCase(word.charAt(0));
    //Ignore the first character if this word starts the sentance and the first
    //character was upper cased, since this is normal behaviour
    if ((startsSentance) && isUpper && (strLen > 1))
      isUpper = Character.isUpperCase(word.charAt(1));
    if (isUpper) {
      for (int i = word.length() - 1; i > 0; i--) {
        if (Character.isLowerCase(word.charAt(i))) {
          return true;
        }
      }
    } else {
      for (int i = word.length() - 1; i > 0; i--) {
        if (Character.isUpperCase(word.charAt(i))) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * This method will fire the spell check event and then handle the event
   *  action that has been selected by the user.
   *
   * @param  tokenizer        Description of the Parameter
   * @param  event            Description of the Parameter
   * @return                  Returns true if the event action is to cancel the current spell checking, false if the spell checking should continue
   */
  protected boolean fireAndHandleEvent(WordTokenizer tokenizer, SpellCheckEvent event) {
    fireSpellCheckEvent(event);
    String word = event.getInvalidWord();
    //Work out what to do in response to the event.
    switch (event.getAction()) {
      case SpellCheckEvent.INITIAL:
        break;
      case SpellCheckEvent.IGNORE:
        break;
      case SpellCheckEvent.IGNOREALL:
        if (!ignoredWords.contains(word)) {
          ignoredWords.add(word);
        }
        break;
      case SpellCheckEvent.REPLACE:
        tokenizer.replaceWord(event.getReplaceWord());
        break;
      case SpellCheckEvent.REPLACEALL:
        String replaceAllWord = event.getReplaceWord();
        if (!autoReplaceWords.containsKey(word)) {
          autoReplaceWords.put(word, replaceAllWord);
        }
        tokenizer.replaceWord(replaceAllWord);
        break;
      case SpellCheckEvent.ADDTODICT:
        String addWord = event.getReplaceWord();
        tokenizer.replaceWord(addWord);
        dictionary.addWord(addWord);
        break;
      case SpellCheckEvent.CANCEL:
        return true;
      default:
        throw new IllegalArgumentException("Unhandled case.");
    }
    return false;
  }


  /**
   * This method is called to check the spelling of the words that are returned
   * by the WordTokenizer.
   * <p>For each invalid word the action listeners will be informed with a new SpellCheckEvent</p>
   *
   * @param  tokenizer  Description of the Parameter
   * @return Either SPELLCHECK_OK, SPELLCHECK_CANCEL or the number of errors found. The number of errors are those that are found BEFORE and corretions are made.
   */
  public final int checkSpelling(WordTokenizer tokenizer) {
    int errors = 0;
    boolean terminated = false;
    //Keep track of the previous word
    String previousWord = null;
    while (tokenizer.hasMoreWords() && !terminated) {
      String word = tokenizer.nextWord();
      //Check the spelling of the word
      if (!dictionary.isCorrect(word)) {
 		if (
          	  (config.getBoolean(Configuration.SPELL_IGNOREMIXEDCASE) && isMixedCaseWord(word, tokenizer.isNewSentance())) ||
              (config.getBoolean(Configuration.SPELL_IGNOREUPPERCASE) && isUpperCaseWord(word)) ||
              (config.getBoolean(Configuration.SPELL_IGNOREDIGITWORDS) && isDigitWord(word)) ||
              (config.getBoolean(Configuration.SPELL_IGNOREINTERNETADDRESSES) && isINETWord(word))) {
          //Null event. Since we are ignoring this word due
          //to one of the above cases.
        } else {
          //We cant ignore this misspelt word
          //For this invalid word are we ignoreing the misspelling?
          if (!ignoredWords.contains(word)) {
            errors++;
            //Is this word being automagically replaced
            if (autoReplaceWords.containsKey(word)) {
              tokenizer.replaceWord((String) autoReplaceWords.get(word));
            } else {
              //JMH Need to somehow capitalise the suggestions if
              //ignoreSentanceCapitalisation is not set to true
              //Fire the event.
              SpellCheckEvent event = new BasicSpellCheckEvent(word, dictionary.getSuggestions(word,
                  config.getInteger(Configuration.SPELL_THRESHOLD)), tokenizer);
              terminated = fireAndHandleEvent(tokenizer, event);
            }
          }
        }
      } else {
        //This is a correctly spelt word. However perform some extra checks
        /*
         *  JMH TBD          //Check for multiple words
         *  if (!ignoreMultipleWords &&) {
         *  }
         */
        //Check for capitalisation
        if ((!config.getBoolean(Configuration.SPELL_IGNORESENTANCECAPITALIZATION)) && (tokenizer.isNewSentance())
            && (Character.isLowerCase(word.charAt(0)))) {
          errors++;
          StringBuffer buf = new StringBuffer(word);
          buf.setCharAt(0, Character.toUpperCase(word.charAt(0)));
          List suggestion = new LinkedList();
          suggestion.add(new Word(buf.toString(), 0));
          SpellCheckEvent event = new BasicSpellCheckEvent(word, suggestion,
              tokenizer);
          terminated = fireAndHandleEvent(tokenizer, event);
        }
      }
    }
    if (terminated)
      return SPELLCHECK_CANCEL;
    else if (errors == 0)
      return SPELLCHECK_OK;
    else return errors;
  }

	/** Added to free up the class memory and resources,
	  * which otherwise trash the system quickly (code by Steve Birmingham)
	  */
	public void dispose()
	{
		eventListeners   = null;
		ignoredWords     = null;
		autoReplaceWords = null;
		config           = null;
		dictionary.dispose();
	}

}


