import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class mergeGraph {
    // It adds edges of graph1 and graph2 in the same graph.
    

	
	//Erreurs
	private static final String FNFError = "Fichier source inexistant";
	private static final String WError = "Impossible d'écrire dans le fichier de destination";
	
    public static void main(String args[]) {
		mergeGraph copieur = new mergeGraph();
		File fSource1 = new File(args[0]);
        File fSource2 = new File(args[1]);
		File fDest = new File(args[2]);
		
		copieur.mergeGraph(fSource1, fSource2, fDest);
	}
	
	private void mergeGraph(File fSource1, File fSource2, File fDest) {
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(fSource1));
            BufferedReader reader2 = new BufferedReader(new FileReader(fSource2));
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(fDest));
			String line1 = "";
            String line2 = "";
            while (null != (line1 = reader1.readLine())){
                writer1.write(line1);
                writer1.newLine();
            }
                   
                   
            while(null != (line2 = reader2.readLine())) {
                writer1.write(line2);
                writer1.newLine();
            }
             
			reader1.close();
            reader2.close();
			writer1.close();
		} catch (FileNotFoundException e1) {
			System.err.println(FNFError);
			return;
		}catch (IOException e) {
			System.err.println(WError);
			return;
		}
	}
}
