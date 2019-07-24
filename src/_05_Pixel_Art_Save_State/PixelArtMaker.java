package _05_Pixel_Art_Save_State;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import _04_Serialization.SaveData;

public class PixelArtMaker implements MouseListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	private JButton sb;
	ColorSelectionPanel csp;
	private static final String DATA_FILE = "src/_05_Pixel_Art_Save_State/saved.dat";
	
	private void save(GridPanel gip2) {
		try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gip2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		
		sb = new JButton("Save");
		gip = new GridInputPanel(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			gp = (GridPanel) ois.readObject();
			csp = new ColorSelectionPanel();
			window.add(gp);
			window.add(csp);
			window.add(sb);
			gp.repaint();
			gp.addMouseListener(this);
		} 
		catch (Exception e) {
			e.printStackTrace();
			window.add(gip);
		}
		
		sb.addActionListener(exp -> {
			save(gp);
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
