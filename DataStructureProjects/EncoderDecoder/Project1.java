package EncoderDecoder;

import java.io.*;
import java.util.Scanner;

/**
 * Project 1
 * 
 * @author Justin Kim 1385008
 * 
 * @Project1   1. Create a basic encrypter/decrypter based on a valid user-generated key
 *
 */
public class Project1 {
	
	/*
	 * Purpose:
	 * 
	 * param String []args
	 * return none
	 */
	public static void main (String []args) throws Exception 
	{
		String key,infileName,line,alphabet="abcdefghijklmnopqrstuvwxyz";
		Scanner input = new Scanner(System.in),inputFile;;
		boolean keyIsBad;
		File infile;
		char[] alphabetArr = new char [26];
		
		for (int i=0;i<alphabet.length();i++)
			alphabetArr[i] = alphabet.charAt(i);
		
		//loop ensuring key is valid
		do{
		System.out.println("Enter encoding key: ");
		key = input.next();
		
		keyIsBad=checkInput(key);
		if(keyIsBad)
			System.out.println("Key is not valid");
		
		}
		while(keyIsBad);
		
		
		char[] keyArr = new char[key.length()];
		
		for(int i =0;i<key.length();i++)
			keyArr[i]=key.charAt(i);
		
		//Loop ensuring file exists
		do{
		System.out.println("Enter valid file name: ");
		infileName = input.next();
		}while(new File(infileName).exists()==false);
		
		input.close();
		infile = new File(infileName);
		inputFile= new Scanner(infile);
		
		System.out.println("************** Encoded Contents ****************");
		while(inputFile.hasNextLine())
		{
			line = inputFile.nextLine();
			char [] lineArr = new char[line.length()];
				for(int i =0;i<line.length();i++)
					lineArr[i]=line.charAt(i);
			
				
	//Encodes characters depending on their ASCII code value.  
			for(int i=0;i< lineArr.length;i++)
				if((lineArr[i]>64)&&(lineArr[i]<91))
					lineArr[i]=(char)(keyArr[positionChar(alphabetArr,(char)(lineArr[i]+32))]-32);
				  else if((lineArr[i]<97)||(lineArr[i]>122))
					  ; //Skips if character is not a letter
				  else
					lineArr[i]=keyArr[positionChar(alphabetArr,lineArr[i])];
				
			
			System.out.println();
				for(int i=0;i<lineArr.length;i++)
					System.out.print(lineArr[i]);
		}
		System.out.println("\n************************************************");
		System.out.println("Good Bye!");
		
	}
	
	/*
	 * Purpose: Method checks  user's key to see if it has 26 unique letters
	 * 
	 * @param key This is the user-inputed key to be tested
	 * @return boolean Returns the boolean value depending on whether the key satisfies conditions
	 */
	public static boolean checkInput(String key)
	{
		if(key.length()!=26)
			return true;
		
		for(int i =0; i<key.length();i++)
			if((key.charAt(i)<97)||(key.charAt(i)>122))
				return true;
		
		for(int i=0;i<key.length();i++)
		if(checkDupeChar(key.charAt(i),key)){
			return true;
				}
			return false;
	}
	
	/*
	 * Purpose: Method checks string for duplicate characters
	 * 
	 * @param c The character that is checked throughout the string
	 * @param string THis is the string that the character is checked on
	 * @return boolean Returns boolean value depending on if the count for a char is greater than 1
	 */
	public static boolean checkDupeChar(char c, String string)
	{
		int count=0;
		for(int i =0;i<string.length();i++)
			if(string.charAt(i)==c)
				count++;
		if(count>1)
				return true;
			else 
				return false;
	}
	
	/*
	 * Purpose: Looks at the position in key and returns corresponding position in alphabet
	 * 
	 * param arr The alphabet array
	 * param c The character in the key that is to be compared to the alphabet 
	 * return int Returns the position of character c in the alphabet
	 */
	public static int positionChar(char [] arr, char c)
	{
			for(int i=0; i<arr.length;i++)
				if(arr[i]==c)	
					return i;

			return 99;//should never reach this code
	}
}
