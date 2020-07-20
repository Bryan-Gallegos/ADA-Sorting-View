package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainFrame extends JFrame{
	
	boolean first;
	
	
	//COMPONENTS
	JPanel menuPanel;
	JPanel laminasPanel;
	
	
	//CANVAS
	
	ChartPanel lamina1;
	ChartPanel lamina2;
	ChartPanel lamina3;
	ChartPanel lamina4;
	
	
	JSlider sldElements;
	JSlider sldTime;
	JLabel elementsLabel;
	JLabel timeLabel;
	JLabel message;
	JButton btnStart;
	//JButton btnEnd;
	JButton btnExit;
	
	
	public MainFrame() {
		
		
		setBounds(75,5,1220,720);
		setTitle("Sorting Animations");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initComponents();
		first = true;
		
		setVisible(true);
		
		
	}
	
	public void initComponents() {
		//LAMINAS PANEL CONFIGURATION
		laminasPanel = new JPanel();
		laminasPanel.setLayout(new GridLayout(2,2));
		laminasPanel.setSize(new Dimension(1000,720));
		
		
		
		//ADDING LAMINAS TO LAMINAS PANEL
			int[] ar = generateArray(30,100);
		
			lamina1 = new ChartPanel(Arrays.copyOf(ar, ar.length),100);
			lamina2 = new ChartPanel(Arrays.copyOf(ar, ar.length),100);
			lamina3 = new ChartPanel(Arrays.copyOf(ar, ar.length),100);
			lamina4 = new ChartPanel(Arrays.copyOf(ar, ar.length),100);
			
			//SET LAMINAS CONFIGURATION
			Border bubbleBorder = new TitledBorder(new EtchedBorder(), "Bubble Sort");
			Border insertionBorder = new TitledBorder(new EtchedBorder(), "Insertion Sort");
			Border quickBorder = new TitledBorder(new EtchedBorder(), "Quick Sort");
			Border mergeBorder = new TitledBorder(new EtchedBorder(), "Merge Sort");
		
			//bubbleLamina.setBorder(bubbleBorder);
			//insertionLamina.setBorder(insertionBorder);
			//quickLamina.setBorder(quickBorder);
			//mergeLamina.setBorder(mergeBorder);
			lamina1.setBorder(bubbleBorder);
			lamina2.setBorder(insertionBorder);
			lamina3.setBorder(quickBorder);
			lamina4.setBorder(mergeBorder);
			
		laminasPanel.add(lamina1);
		laminasPanel.add(lamina2);
		laminasPanel.add(lamina3);
		laminasPanel.add(lamina4);
		
		//MENU PANEL CONFIGURATION
		
		menuPanel = new JPanel();
		Border menuBorder = new TitledBorder(new EtchedBorder(), "Options");
		menuPanel.setBorder(menuBorder);
		menuPanel.setPreferredSize(new Dimension(200,720));
		menuPanel.setMaximumSize(new Dimension(200,720));
		menuPanel.setMinimumSize(new Dimension(200,720));
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		message = new JLabel("Sorting Animations with the same data");
		//menuPanel.add(message);
		
		//SPINNERS & LABELS
		sldElements = new JSlider(10,100,20);
		sldTime = new JSlider(30,100,30);
		elementsLabel = new JLabel();
		timeLabel = new JLabel();
		elementsLabel.setText("Elements: "+Integer.toString(sldElements.getValue()));
		timeLabel.setText("Delay(ms): "+Integer.toString(sldTime.getValue()));
		
		sldElements.addChangeListener((ChangeListener) new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				elementsLabel.setText("Elements: "+Integer.toString(sldElements.getValue()));
				
			}
			
		});
		sldTime.addChangeListener((ChangeListener) new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				timeLabel.setText("Delay(ms): "+Integer.toString(sldTime.getValue()));
				
			}
			
		});
		
		
		//BUTTONS ACTIONS
		btnStart = new JButton("START");
		//btnEnd = new JButton("Parar");
		btnExit = new JButton("EXIT");
		btnStart.setPreferredSize(new Dimension(150,40));
		btnExit.setPreferredSize(new Dimension(150,40));
		btnStart.setBackground(Color.GREEN);
		btnStart.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.WHITE);
		btnStart.setFont(new Font("Open Sans",Font.BOLD,20));
		btnExit.setFont(new Font("Open Sans",Font.BOLD,20));
		ponerBoton(menuPanel, btnStart, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int milis=sldTime.getValue();
				int elements = sldElements.getValue();
				
				if(first) {
					Thread bubbleThread = new BubbleThread(lamina1.getArray(),lamina1,milis);
					Thread insertionThread = new InsertionThread(lamina2.getArray(),lamina2,milis);
					Thread quickThread = new QuickThread(lamina3.getArray(),lamina3,milis);
					Thread mergeThread = new MergeThread(lamina4.getArray(),lamina4,milis);
					
					bubbleThread.start();
					insertionThread.start();
					quickThread.start();
					mergeThread.start();
					
					first = false;
				}else {
					lamina1.clearWindow(lamina1.getGraphics());
					lamina2.clearWindow(lamina2.getGraphics());
					lamina3.clearWindow(lamina3.getGraphics());
					lamina4.clearWindow(lamina4.getGraphics());
					int[] ar = generateArray(elements,elements*2);
					
					lamina1.updateData(Arrays.copyOf(ar, ar.length),elements*2);
					lamina2.updateData(Arrays.copyOf(ar, ar.length),elements*2);
					lamina3.updateData(Arrays.copyOf(ar, ar.length),elements*2);
					lamina4.updateData(Arrays.copyOf(ar, ar.length),elements*2);
					
					Thread bubbleThread = new BubbleThread(lamina1.getArray(),lamina1,milis);
					Thread insertionThread = new InsertionThread(lamina2.getArray(),lamina2,milis);
					Thread quickThread = new QuickThread(lamina3.getArray(),lamina3,milis);
					Thread mergeThread = new MergeThread(lamina4.getArray(),lamina4,milis);
					
					bubbleThread.start();
					insertionThread.start();
					quickThread.start();
					mergeThread.start();
				}
			}
			
		});
		
		ponerBoton(menuPanel,btnExit, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
			
		});
		
		//ADDING COMPOMENTS
		
		menuPanel.add(sldElements);
		menuPanel.add(elementsLabel);
		menuPanel.add(sldTime);
		menuPanel.add(timeLabel);
		
		add(laminasPanel, BorderLayout.CENTER);
		add(menuPanel,BorderLayout.EAST);
		
		
	}
	
	public void ponerBoton(Container c, JButton boton, ActionListener oyente){
		
		
		c.add(boton);
		
		boton.addActionListener(oyente);
		
	}
	
	private int[] generateArray(int length, int maxValue) {
		int[] ar = new int[length];
		Random rd = new Random();
		for(int i=0;i<ar.length;i++)
			ar[i] = rd.nextInt(maxValue-10)+11;
		
		return ar;
	}
	
}
