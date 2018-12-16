package _01_File_Recorder;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileRecorder {
	// Create a program that takes a message from the user and saves it to a file.\
	public static void main(String[] args) {
		new FileRecorder().writeToFile(JOptionPane.showInputDialog("Insert a message"));
	}
	
	public void writeToFile(String s)
	{
		try {
			FileWriter fw = new FileWriter("src/_01_File_Recorder/message.txt");
			fw.write(s);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
