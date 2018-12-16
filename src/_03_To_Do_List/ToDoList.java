package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class ToDoList implements ActionListener{
	
	JFrame jf;
	JPanel jp;
	
	JButton add;
	JButton view;
	JButton remove;
	JButton save;
	JButton load;
	
	ArrayList<String> tasks = new ArrayList<String>();
	
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 * 
	 * When add task is clicked:
	 * 		ask the user for a  task and save it to an array list
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list.
	 */
	
	public static void main(String[] args) {
		new ToDoList().createUI();
	}
	
	public void createUI()
	{
		jf = new JFrame();
		jp = new JPanel();
		
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add = new JButton();
		add.setText("Add Task");
		add.addActionListener(this);
		jp.add(add);
		add.setVisible(true);
		
		view = new JButton();
		view.setText("View Tasks");
		view.addActionListener(this);
		jp.add(view);
		view.setVisible(true);
		
		remove = new JButton();
		remove.setText("Remove Tasks");
		remove.addActionListener(this);
		jp.add(remove);
		view.setVisible(true);
		
		save = new JButton();
		save.setText("Save Tasks");
		save.addActionListener(this);
		jp.add(save);
		save.setVisible(true);
		
		load = new JButton();
		load.setText("Load Tasks");
		jp.add(load);
		load.addActionListener(this);
		load.setVisible(true);

		jf.pack();
		jf.setVisible(true);
		jp.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(add))
		{
			tasks.add(JOptionPane.showInputDialog("Insert task"));
		}
		
		if(e.getSource().equals(view))
		{
			JOptionPane.showMessageDialog(null, tasks);
		}
		
		if(e.getSource().equals(remove))
		{
			String s = JOptionPane.showInputDialog("Insert task to remove");
			
			for(int i = 0; i < tasks.size(); i++)
			{
				if(s.equalsIgnoreCase(tasks.get(i)))
				{
					tasks.remove(i);
					break;
				}
			}
		}
		
		if(e.getSource().equals(save))
		{
			try {
				FileWriter fw = new FileWriter("src/_03_To_Do_List/list.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
