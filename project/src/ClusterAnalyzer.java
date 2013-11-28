import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ClusterAnalyzer {
    // To print percentages of total tweets in each cluster
    

	
	//Erreurs
	private static final String FNFError = "Fichier source inexistant";
	private static final String WError = "Impossible d'écrire dans le fichier de destination";
	
    public static void main(String args[]) {
		ClusterAnalyzer copieur = new ClusterAnalyzer();
		File fSource1 = new File(args[0]);
        File fSource2 = new File(args[1]);
		File fDest = new File(args[2]);
		
		copieur.ClusterAnalyzer(fSource1, fSource2, fDest);
	}
	
	private void ClusterAnalyzer(File fSource1, File fSource2, File fDest) {
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(fSource1));
            BufferedReader reader2 = new BufferedReader(new FileReader(fSource2));
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(fDest));
			String line1 = "";
            String line2 = "";
            float cluster1 = 0;
            float cluster2 = 0;
            float cluster3 = 0;
            float cluster4 = 0;
            float cluster5 = 0;
            float cluster6 = 0;
            float cluster7 = 0;
            float cluster8 = 0;
            float cluster9 = 0;
            float cluster0 = 0;
            float total = 0;
            String[] temp1;
            String[] temp2;
			while (null != (line1 = reader1.readLine()) && null != (line2 = reader2.readLine())) {
				temp1 = line1.split("\\t");
                temp2 = line2.split("\\t");
                //System.out.println(temp2[0] + " " + temp2[1] + " " + temp2[2]);
                if (!temp2[0].equals("#doc name topic proportion ...")){
                    String newline = temp1[0] + " " + temp2[2];
                    if(temp1[0] != "id"){
                        writer1.write(newline);
                        writer1.newLine();
                    }
                    if(temp2[2].equals("0")){
                        cluster0 = cluster0 + 1;
                    }
                    if(temp2[2].equals("1")){
                        cluster1 = cluster1 + 1;
                    }
                    if(temp2[2].equals("2")){
                        cluster2 = cluster2 + 1;
                    }
                    if(temp2[2].equals("3")){
                        cluster3 = cluster3 + 1;
                    }
                    if(temp2[2].equals("4")){
                        cluster4 = cluster4 + 1;
                    }
                    if(temp2[2].equals("5")){
                        cluster5 = cluster5 + 1;
                    }
                    if(temp2[2].equals("6")){
                        cluster6 = cluster6 + 1;
                    }
                    if(temp2[2].equals("7")){
                        cluster7 = cluster7 + 1;
                    }
                    if(temp2[2].equals("8")){
                        cluster8 = cluster8 + 1;
                    }
                    if(temp2[2].equals("9")){
                        cluster9 = cluster9 + 1;
                    }

                    total = total + 1;
                }
			}
            
            System.out.println("cluster0: " + Float.toString(cluster0 * 100 / total));
            System.out.println("cluster1: " + Float.toString(cluster1 * 100 / total));
            System.out.println("cluster2: " + Float.toString(cluster2 * 100 / total));
            System.out.println("cluster3: " + Float.toString(cluster3 * 100 / total));
            System.out.println("cluster4: " + Float.toString(cluster4 * 100 / total));
            System.out.println("cluster5: " + Float.toString(cluster5 * 100 / total));
            System.out.println("cluster6: " + Float.toString(cluster6 * 100 / total));
            System.out.println("cluster7: " + Float.toString(cluster7 * 100 / total));
            System.out.println("cluster8: " + Float.toString(cluster8 * 100 / total));
            System.out.println("cluster9: " + Float.toString(cluster9 * 100 / total));
            
            
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
