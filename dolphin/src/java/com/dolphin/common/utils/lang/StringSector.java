package com.dolphin.common.utils.lang;


public class StringSector {
  /**
   * the string to be parsed
   */
  private String text;

  /**
   * the parse format of the string
   */
  private int[] format;

  /**
   * the current parse position of the string
   */
  private int currentPos;

  /**
   * the current parse sect of the string
   */
  private String currentSect;

  /**
   * the parsed sects of the string
   */
  private String[] sects;

  /**
   * Constructor of the FormatStringParser,parse the string in the constructor.
   * 
   * @param text
   *          String the string to be parsed
   * @param format
   *          int[] the given String format
   * @throws Exception
   */
  public StringSector(String text, int[] format) throws Exception {
    this.text = text;
    this.format = format;
    this.sects = new String[format.length];
    if (text == null || format.length == 0) {
      throw new Exception("Error initiate parameters!");
    }
    int sum = 0;
    int strLength = text.length();
    if (format.length == 1 && strLength > format[0]) {
      throw new Exception("Error format!");
    }
    for (int i = 0; i < format.length; i++) {
      sum += format[i];
      if (i == format.length - 2) {
        if (strLength < sum) {
          throw new Exception("Error format!");
        }
      }
    }
    if (strLength > sum) {
      throw new Exception("Error format!");
    }
    int flag = 0;
    for (int i = 0; i < format.length; i++) {
      if (i == format.length - 1) {
        sects[i] = text.substring(flag, text.length());
      }
      else {
        sects[i] = text.substring(flag, flag + format[i]);
      }
      flag += format[i];
    }
    this.currentSect = sects[0];
    this.currentPos = 1;
  }

  /**
   * get the count of the sects of the string
   * 
   * @return int the count of the sects of the string
   */
  public int countSector() {
    return this.format.length;
  }

  /**
   * get the current sect of the parsed string
   * 
   * @return String the current sect of the String
   */
  public String getCurrentSect() {
    return this.currentSect;
  }

  /**
   * get the specified sect of the string
   * 
   * @param index
   *          int the index of the string
   * @return String the specified string sect
   */
  public String getSect(int index) {
    return this.sects[index - 1];
  }

  /**
   * get the next sect of the string
   * 
   * @return String the next sect
   */
  public String getNext() {
    String nextSect = this.sects[this.currentPos];
    this.currentPos++;
    return nextSect;
  }

  /**
   * check whether the next sect is null
   * 
   * @return boolean
   */
  public boolean hasNext() {
    if (this.currentPos == sects.length) {
      return false;
    }
    return true;
  }

  /**
   * get the parsed string array
   * 
   * @return String[] the parsed string sects
   */
  public String[] getSects() {
    return this.sects;
  }

  public static void main(String[] args) {
    int[] format = new int[] { 1, 2, 3, 4, 5, 6, 7 };
    try {
      StringSector ss = new StringSector("abcdefghijklmnopqrstuvwxyz", format);
      String[] strs = ss.getSects();
      for (int i = 0; i < strs.length; i++) {
        System.out.println("string " + i + " is : " + strs[i]);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
