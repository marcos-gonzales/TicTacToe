import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static boolean isWinner = false;
    private static int gameCounter = 0;
    private static String xo = "x";
    private static ArrayList<String> board = new ArrayList<String>();
    private static int[] arrayX = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static int[] arrayO = new int[]{0,0,0,0,0,0,0,0,0};
    public static boolean checkIfValidPosition(int index) {
        String value = (String) board.get(index);
        if (value.equals("x") || value.equals("o")) {
            return false;
        }
        return true;
    }

    public static void wipeBoard(){
        gameCounter = 0;
        board.removeAll(board);
        board.add("1");
        board.add("2");
        board.add("3");
        board.add("4");
        board.add("5");
        board.add("6");
        board.add("7");
        board.add("8");
        board.add("9");
        arrayX = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        arrayO = new int[]{0,0,0,0,0,0,0,0,0};
    }

    public static void checkIfWinner()
    {
        if(gameCounter >= 9 ) {
            System.out.println("Draw!!");
            wipeBoard();
        }

        ArrayList<int[]>  combinations = new ArrayList<>();
        combinations.add(new int[]{1,2,3});
        combinations.add(new int[]{1,4,7});
        combinations.add(new int[]{1,5,9});
        combinations.add(new int[]{4,5,6});
        combinations.add(new int[]{7,8,9});
        combinations.add(new int[]{2,5,8});
        combinations.add(new int[]{3,6,9});
        combinations.add(new int[]{3,5,7});

        List<Integer> listX = Arrays.stream(arrayX).boxed().collect(Collectors.toList());
        List<Integer> listO = Arrays.stream(arrayO).boxed().collect(Collectors.toList());

        // Iterate through combinations and compare
        for (int[] combination : combinations) {
            // Convert each combination to List<Integer>
            List<Integer> combinationList = Arrays.stream(combination).boxed().collect(Collectors.toList());

            // Check if listX contains all elements of combinationList
            if (listX.containsAll(combinationList)) {
                isWinner = true;
                wipeBoard();
                System.out.println("X is declared the winner!Winner Winner chicken dinner XXX");
            }

            // Check if listO contains all elements of combinationList
            if (listO.containsAll(combinationList)) {
                isWinner = true;
                wipeBoard();
                System.out.println("O is declared the winner! Winner Winner chicken dinner");
            }
        }
    }

    public static void main(String[] args) {

        board.add("1");
        board.add("2");
        board.add("3");
        board.add("4");
        board.add("5");
        board.add("6");
        board.add("7");
        board.add("8");
        board.add("9");

        while(!isWinner || gameCounter < 9) {
            Console console = System.console();
            System.out.println("Choose a number between 1-9 to enter your placement on the board");
            String input = console.readLine();
            System.out.println("you chose: " + input);
            boolean validInput = false;
            try {
                int num = Integer.parseInt(input);
                if(num > 0 && num <10) {
                    validInput= true;
                }

            } catch(NumberFormatException exception) {
                System.out.println("Please choose a number between 1 and 9;");
            }


            if(validInput) {
                gameCounter++;
                for(int i = 0; i < board.size(); i++) {
                    int indexedInput = Integer.parseInt(input) - 1;
                    String stringedInput = String.valueOf(indexedInput);
                    if(String.valueOf(i).equals(stringedInput)) {
                        if (checkIfValidPosition(i)) {
//                            xo = xo.equals("x") ? "o" : "x";
                            if(xo.equals("x")) {
                              arrayX[i] = i + 1;

                              xo = "o";
                            } else {
                                arrayO[i] = i + 1;
                                xo = "x";
                            }
                            board.set(i, xo);
                        } else {
                            System.out.println("Invalid move. try again");
                            continue;
                        }
                    }

                    System.out.print("|--" + board.get(i) + "--");

                    if(i == 3 -1 || i == 6 - 1 || i == 9 -1) {
                        System.out.println("|");
                    }
                }
                    checkIfWinner();
            }
        }
    }
}