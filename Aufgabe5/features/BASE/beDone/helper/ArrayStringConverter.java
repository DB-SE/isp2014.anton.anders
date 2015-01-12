package beDone.helper;

public class ArrayStringConverter {
	public static String implode(String[] array, String delimiter){
		String st = "";
		for(String s : array){
			st += s + delimiter;
		}
		if(st.length()>2)
			return st.substring(0, st.length()-delimiter.length());
		return "";
	}
	
	public static String[] explode(String s, String delimiter){
		return s.split(delimiter);
	}
}
