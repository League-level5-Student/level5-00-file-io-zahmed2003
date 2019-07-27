package _05_Pixel_Art_Save_State;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import _04_Serialization.SaveData;

public class PixelArtMaker implements MouseListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	private JButton sb;
	private JButton lb;
	ColorSelectionPanel csp;
	private static final String DATA_FILE = "src/_05_Pixel_Art_Save_State/saved.dat";
	
	private void save(GridPanel gip2) {
		String FILE_NAME = JOptionPane.showInputDialog("Input the name of the file you wish to save to.");
		try (FileOutputStream fos = new FileOutputStream(new File("src/_05_Pixel_Art_Save_State/" + FILE_NAME + ".dat")); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gip2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private GridPanel load(String FILE)
	{
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FILE))))
		{
			return (GridPanel) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void start() {
		
		sb = new JButton("Save");
		lb = new JButton("Load");
		gip = new GridInputPanel(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		
		window.add(gip);
		window.add(lb);
		
		sb.addActionListener(exp -> {
			save(gp);
		}); 
		
		lb.addActionListener(exp -> {
			JFileChooser jfc = new JFileChooser();
			int returnValue = jfc.showOpenDialog(null);
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				gp = load(jfc.getSelectedFile().getAbsolutePath());
				csp = new ColorSelectionPanel();
				
				if(gip != null) {window.remove(gip);}
				
				window.add(gp);
				window.add(csp);
				window.add(sb);
				
				gp.repaint();
				gp.addMouseListener(this);
				window.pack();
			}
			
		});
		
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		window.add(sb);
		
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		//System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
