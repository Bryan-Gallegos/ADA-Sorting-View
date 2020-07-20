package Frames;

public class InsertionThread extends Thread{
	int[] ar;
	ChartPanel panel;
	int milis;
	
	public InsertionThread(int[] ar, ChartPanel panel, int milis) {
		this.ar = ar;
		this.panel = panel;
		this.milis = milis;
	}
	
	@Override
	public void run() {
		int n = ar.length; 
        for (int i = 1; i < n; ++i) { 
            int key = ar[i]; 
            int j = i - 1; 
  
            while (j >= 0 && ar[j] > key) { 
                ar[j + 1] = ar[j]; 
                j = j - 1; 
                
                panel.paintComponent(panel.getGraphics());
                
                try {
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } 
            ar[j + 1] = key; 
            panel.paintComponent(panel.getGraphics());
        } 
	}
}
