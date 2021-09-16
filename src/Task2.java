import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task2 {
    private static final int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) {
        int[][] map = new int[7][3];
        map [0] [0] = 5;
        map [0] [1] = 4;
        map [0] [2] = 4;

        map [1] [0] = 4;
        map [1] [1] = 3;
        map [1] [2] = 4;

        map [2] [0] = 3;
        map [2] [1] = 2;
        map [2] [2] = 4;

        map [3] [0] = 2;
        map [3] [1] = 2;
        map [3] [2] = 2;

        map [4] [0] = 3;
        map [4] [1] = 3;
        map [4] [2] = 4;

        map [5] [0] = 1;
        map [5] [1] = 4;
        map [5] [2] = 4;

        map [6] [0] = 4;
        map [6] [1] = 1;
        map [6] [2] = 1;

        System.out.println(getNumberOfCountries(map));
    }

    public static int getNumberOfCountries(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        Map<Integer, List<Integer[]>> colors = new HashMap<>();

        for(int row=0; row<rows; row++) {
            for (int col = 0; col < cols; col++) {
                int color = map[row][col];
                List<Integer[]> colorLocs = colors.get(color);
                if (colorLocs == null) {
                    List<Integer[]> country = new ArrayList<>();
                    country.add(new Integer[]{row, col});
                    colors.put(color, country);
                } else {
                    colorLocs.add(new Integer[]{row, col});
                }
            }
        }

        List<List<Integer[]>> countries = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer[]>> entry : colors.entrySet()) {
            List<Integer[]> colorLocs = entry.getValue();
            for(Integer[] colorLoc : colorLocs) {
                List<Integer[]> newCountryCandidate = findCountries(colorLoc, colorLocs, new ArrayList<>());
                if(!countries.stream().anyMatch(country-> {
                    return country.size() == newCountryCandidate.size() && country.stream().allMatch(loc -> newCountryCandidate.stream().anyMatch(newLoc -> newLoc[0] == loc[0] && newLoc[1] == loc[1]));
                })){
                    countries.add(newCountryCandidate);
                }
            }
        }

        return countries.size();
    }

    public static List<Integer[]> findCountries (Integer[] cell, List<Integer[]> checkCells, List<Integer[]> countryCells) {

        int row = cell[0];
        int col = cell[1];
        countryCells.add(new Integer[]{row, col});

        for(int[] direction : directions) {
            int checkRow = row + direction[0];
            int checkCol = col + direction[1];

            for(Integer[] checkCell : checkCells){
                if(checkCell[0] == checkRow && checkCell[1] == checkCol){
                    if(!countryCells.stream().anyMatch(cc -> cc[0] == checkRow && cc[1] == checkCol)){
                        findCountries(new Integer[]{checkRow, checkCol}, checkCells, countryCells);
                    }
                }
            }
        }

        return countryCells;
    }
}
