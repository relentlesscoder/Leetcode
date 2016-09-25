package org.wshuai.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Wei on 9/24/2016.
 */
public class In {

  private static final String CHARSET_NAME = "UTF-8";

  private static final Locale LOCALE = Locale.US;

  private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

  private static final Pattern EMPTY_PATTERN = Pattern.compile("");

  private Scanner scanner;

  public In(){
    scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
    scanner.useLocale(LOCALE);
  }

  public In(File file){
    if(file == null){
      throw new NullPointerException("argument is null");
    }
    try{
      // for consistency with StdIn, wrap with BufferedInputStream instead of use
      // file as argument to Scanner
      FileInputStream fis = new FileInputStream(file);
      scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
      scanner.useLocale(LOCALE);
    }
    catch (IOException ioe){
      throw new IllegalArgumentException("Could not open " + file);
    }
  }

  public boolean exists(){
    return scanner != null;
  }

  public boolean isEmpty(){
    return scanner.hasNext();
  }

  public boolean hasNextLine(){
    return scanner.hasNextLine();
  }

  public boolean hasNextChar(){
    scanner.useDelimiter(EMPTY_PATTERN);
    boolean result = scanner.hasNext();
    scanner.useDelimiter(WHITESPACE_PATTERN);
    return result;
  }

  public String readLine(){
    String line;
    try{
      line = scanner.nextLine();
    }
    catch (NoSuchElementException e){
      line = null;
    }
    return line;
  }
}
