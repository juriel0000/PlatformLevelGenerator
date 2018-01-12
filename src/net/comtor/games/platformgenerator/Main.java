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
                //line = line.trim();
                lines.add(line);
                if (cols < line.length()) {
                    cols = line.length();
                }
            }
        }

        int rows = lines.size();
        
        char matrix[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c != ' ') {
                    matrix[i][j] = c;
                }
            }
        }
        
        /*
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0)
                System.out.print(""+matrix[i][j]);
                else 
                    System.out.print("+");
                
            }
            System.err.println("|");
        }*/
        
        System.out.println("LIMITS\t0\t"+cols*500+"\t-300");
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                generateCode(matrix, row, col);
            }
        }

    }

    private static void generateCode(char[][] matrix, int row, int col) {
        char c = matrix[row][col];
        if (c == 0) {
            return;
        }
        int posx = col * 500;
        int topY = matrix.length * 500;
        int posy = topY - ( (row+1)*500);
        char up = row == 0 ? 0 : matrix[row-1][col];
        char down = (row == matrix.length -1 ) ? 0 : matrix[row+1][col];
        
        char left = col == 0? 0: matrix[row][col-1];
        char right = (col == matrix[0].length-1)? 0: matrix[row][col+1];
        
        
        //System.err.println("row "+row+"  col "+col+" up "+up+" down "+down);
      
        if (c == 'X'){
            if (up != 'X'){
                System.out.println("PLATFORM\tPlatforms/platform00-500x500-top.png\t"+(posx+250)+"\t"+(posy+250)+"\tgame" );
            }
            if (up == 'X'){
                System.out.println("PLATFORM\tPlatforms/platform00-500x500-fill.png\t"+(posx+250)+"\t"+(posy+250)+"\tgame" );
            }
        }
        if (c == '_'){
            System.out.println("PLATFORM\tPlatforms/platform00-500x100-top.png\t"+(posx+250)+"\t"+(posy+80)+"\tgame" );

        }
        if (c == 'P'){
            System.out.println("INIT_POS\t"+(posx+80)+"\t"+(posy+155) );
            
        }
        if (c == '.'){
            System.out.println("COIN\tnone\t"+(posx+250)+"\t"+(posy+10)+"\tgame" );
            
        }
        if (c == 'E'){
            System.out.println("END\tend.png\t"+(posx+250)+"\t"+(posy+250)+"\tgame" );            
        }
        if (c == 'B'){
            System.out.println("ENEMY_AERIAL_PATROL_ZONE\tEnemies/zone_bat-1000.props\t"+(posx+250)+"\t"+(posy+250)+"\tgame" );            
        }
        if (c == 'V'){
            System.out.println("ENEMY_STATIC\tEnemies/seaUrchin.png\t"+(posx+250)+"\t"+(posy+150)+"\tgame" );            
        }
        if (c == 'C'){
            System.out.println("ENEMY_PATROL\tEnemies/Crab.props\t"+(posx+55)+"\t"+(posy+50)+"\tgame" );            
        }
    }


}
