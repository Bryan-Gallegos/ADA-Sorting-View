package Frames;

public class BubbleThread extends Thread{
	int[] ar;
	ChartPanel panel;
	int milis;
	
	public BubbleThread(int[] ar,ChartPanel panel, int milis) {
		this.ar = ar;
		this.panel = panel;
		this.milis = milis;
	}
	
	@Override
	public void run() {
		int n = ar.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) { 
                if (ar[j] > ar[j+1]) { 
                    int temp = ar[j]; 
                    ar[j] = ar[j+1]; 
                    ar[j+1] = temp;
                    
                    panel.paintComponent(panel.getGraphics());
                }
                try {
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
	}
}
