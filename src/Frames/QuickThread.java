package Frames;

public class QuickThread extends Thread{
	int[] ar;
	ChartPanel panel;
	int milis;
	
	public QuickThread(int[] ar, ChartPanel panel, int milis) {
		this.ar = ar;
		this.panel = panel;
		this.milis = milis;
	}
	
	@Override
	public void run() {
		quickSort(ar,0,ar.length-1);
	}
	

    private void quickSort(int vec[], int inicio, int fin){
                if(inicio>=fin) return;
                int pivote=vec[inicio];
                int elemIzq=inicio+1;
                int elemDer=fin;
                while(elemIzq<=elemDer){
                        while(elemIzq<=fin && vec[elemIzq]<pivote){
                                elemIzq++;
                        }
                        while(elemDer>inicio && vec[elemDer]>=pivote){
                                elemDer--;
                        }
                        if(elemIzq<elemDer){
                                int temp=vec[elemIzq];
                                vec[elemIzq]=vec[elemDer];
                                vec[elemDer]=temp;
                        }
                        panel.paintComponent(panel.getGraphics());
                        
                        try {
							Thread.sleep(milis);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                }
                
                if(elemDer>inicio){
                        int temp=vec[inicio];
                        vec[inicio]=vec[elemDer];
                        vec[elemDer]=temp;
                        
                }
                panel.paintComponent(panel.getGraphics());
                
                try {
					Thread.sleep(milis);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                quickSort(vec, inicio, elemDer-1);
                quickSort(vec, elemDer+1, fin);
        }
}
