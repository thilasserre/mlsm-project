import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class adaptMallet {
    //to convert tweets in mallet format
    

	
	//Erreurs
	private static final String FNFError = "Fichier source inexistant";
	private static final String WError = "Impossible d'écrire dans le fichier de destination";
	
    public static void main(String args[]) {
		adaptMallet copieur = new adaptMallet();
		File fSource = new File(args[0]);
		File fDest = new File(args[1]);
		
		copieur.adapt(fSource, fDest);
	}
	
	private void adapt(File fSource, File fDest) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fSource));
			BufferedWriter writer = new BufferedWriter(new FileWriter(fDest));
			String line = "";
            int i = 0;
            int j = 0;
            String[] temp;
			while (i < 500000 && null != (line = reader.readLine())) {
				temp = line.split("\\t");
                if(temp.length == 5){
                    String newline = temp[0] + " null " + temp[4];
                    if(temp[0] != "id"){
                        writer.write(newline);
                        j = j + 1;
                        writer.newLine();
                    }
                }
                i = i + 1;
			}
			reader.close();
			writer.close();
            System.out.println("transcripted lines: " + Integer.toString(j) + "\n");
		} catch (FileNotFoundException e1) {
			System.err.println(FNFError);
			return;
		}catch (IOException e) {
			System.err.println(WError);
			return;
		}
	}
}
