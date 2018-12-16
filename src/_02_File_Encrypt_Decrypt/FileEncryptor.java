package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import _01_File_Recorder.FileRecorder;

public class FileEncryptor {
	// Create a program that takes a message from the user.
	// Use the methods in the String and Character classes to save
	// an encrypted form of the message to a file
	
	public static void main(String[] args) {
		new FileEncryptor().writeToFile(JOptionPane.showInputDialog("Insert a message"));
	}
	
	public void writeToFile(String s)
	{
		try {
			FileWriter fw = new FileWriter("src/_02_File_Encrypt_Decrypt/message.txt");
			fw.write(s.hashCode());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
