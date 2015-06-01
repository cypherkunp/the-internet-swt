package io.github.devvratplus.theinternet.core;

public final class CheckOS
{
   private static String OS = null;
   
   public static String getOsName()
   {
      if(OS == null) { OS = System.getProperty("os.name"); }
      return OS;
   }
   public static boolean isWindows()
   {
      return getOsName().startsWith("Windows");
   }

   public static boolean isUnix(){
	return false; // and so on
}
   
   public static boolean isLinux(){
		return false; // and so on
	}
   
   public static boolean isMacOS(){
		return false; // and so on
	}
}