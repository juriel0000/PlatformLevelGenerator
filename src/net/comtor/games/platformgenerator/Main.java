package net.comtor.games.platformgenerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author juriel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String filename = args[0];
        LinkedList<String> lines = new LinkedList<>();
        int cols = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename));) {

            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                lines.add(line);
                if (cols < line.length()){
                    cols = line.length();
                }
            }
        }
        
        int rows = lines.size();
        int posx = rows* 500;
        int posy = 0;
        
        for (int i = 0 ; i < rows; i ++){
            String line = lines.get(i);
            for (int j = 0 ; j < line.length() ; j++){
                int x = posx+500*j;
                int y = posy+500*i;
                char c = line.charAt(j);
                
                
                
                
                if (c != ' '){
                    processChar(c, x, y );
                }
            }
        }

    }

    private static void processChar(char c, int x , int y) {
        if (c == 'X'){
            
        }
    }

}
