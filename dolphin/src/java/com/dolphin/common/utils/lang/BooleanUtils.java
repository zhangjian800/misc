package com.dolphin.common.utils.lang;

//import com.ebao.foundation.app.CodeConstant;

public class BooleanUtils {
	
  private static final String YES_NO__YES = "Y";
  private static final String YES_NO__NO = "N";  
  private static final String YES_NO__NA = "N/A";    
  /**
   * <p>
   * Converts a boolean to a String returning <code>'Y'</code> or
   * <code>'N'</code>.
   * </p>
   * 
   * @param bool
   *          the Boolean to check
   * @return <code>'Y'</code>, <code>'N'</code>, or <code>null</code>
   */
  public static String toStringYN(boolean bool) {
    return org.apache.commons.lang.BooleanUtils.toString(bool,
        YES_NO__YES, YES_NO__NO);
  }

  /**
   * <p>
   * Converts a Boolean to a String returning <code>'Y'</code>,
   * <code>'N'</code>, or <code>null</code>.
   * </p>
   * 
   * @param bool
   *          the Boolean to check
   * @return <code>'Y'</code>, <code>'N'</code>, or <code>null</code>
   */
  public static String toStringYN(Boolean bool) {
    return org.apache.commons.lang.BooleanUtils.toString(bool,
        YES_NO__YES, YES_NO__NO, YES_NO__NA);
  }
}
