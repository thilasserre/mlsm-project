import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class deleteGraphNodes {
    //It removed the edges that are not included in file 1 that are not in file 2

	
	//Erreurs
	private static final String FNFError = "Fichier source inexistant";
	private static final String WError = "Impossible d'écrire dans le fichier de destination";
	
    public static void main(String args[]) {
		deleteGraphNodes copieur = new deleteGraphNodes();
		File fSource1 = new File(args[0]);
        File fSource2 = new File(args[1]);
		File fDest = new File(args[2]);
		
		copieur.deleteGraphNodes(fSource1, fSource2, fDest);
	}
	
	private void deleteGraphNodes(File fSource1, File fSource2, File fDest) {
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(fSource1));
            //BufferedReader reader2 = new BufferedReader(new FileReader(fSource2));
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(fDest));
			String line1 = "";
            String line2 = "";
            float total = 0;
            int present;
            String[] temp1;
            String[] temp2;
			while (null != (line1 = reader1.readLine())){
                BufferedReader reader2 = new BufferedReader(new FileReader(fSource2));
                present = 0;
                temp1 = line1.split(" ");
                while (present == 0 && null != (line2 = reader2.readLine())) {
                    //System.out.println("coucou");
                    temp2 = line2.split(" ");
                    //System.out.println(temp2[0] + " " + temp2[1]);
                    if(temp1[0].equals(temp2[0]) && temp1[1].equals(temp2[1])){
                        System.out.println("add one line");
                        writer1.write(temp1[0] + " " + temp1[1]);
                        writer1.newLine();
                        present = 1;
                        reader2.close();
                    }
                }
            }

            
            
			reader1.close();
            //reader2.close();
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
