package edu.gatech.seclass.wordfind6300;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Board {
    String[][] currentLayout;
    HashMap<String, Integer> letterWeights;

    int boardSize;
    int boardWidth;
    int boardHeight;
    int boardTotalSize;

    int vowelWeight;
    int numVowels;

    int consonantWeight;
    int numConsonants;

    String vowels = "AEIOU";
    String notVowels = "BCDFGHJKLMNPQRSTVWXYZ";

    Board(int size, HashMap<String, Integer> weights){

        letterWeights = weights;

        boardWidth = size;
        boardHeight = size;
        currentLayout = new String[boardWidth][boardHeight];

        boardTotalSize = size * size;

        vowelWeight = 0;
        consonantWeight = 0;

        numVowels = (int) Math.ceil((float) boardTotalSize / 5.0);
        numConsonants = boardSize - numVowels;

    }
    void fillWithLetters(){
        String[] characterArray = new String[boardTotalSize];

        // Find total weight of all vowels and non-vowels
        for (HashMap.Entry<String, Integer> entry: letterWeights.entrySet()){
            String letter = entry.getKey();
            if (vowels.contains((letter))){
                vowelWeight += entry.getValue();

            }
            else{
                consonantWeight += entry.getValue();
            }
        }


        // Generate random vowels/non-vowels with correct distribution
        for (int i = 0; i < boardTotalSize; i++){
            if (i < numVowels){
                String random_letter = randomLetter(true);
                characterArray[i] = random_letter;

            }
            else{
                String random_letter = randomLetter(false);
                characterArray[i] = random_letter;
            }
        }


        // Shuffle list, add to board array
        List<String> characterList = Arrays.asList(characterArray);
        Collections.shuffle(characterList);
        characterList.toArray(characterArray);


        int counter = 0;
        currentLayout = new String[boardHeight][boardWidth];
        for (int i = 0; i < boardHeight; i++){
            for (int j = 0; j < boardWidth; j++){
                currentLayout[i][j] = characterArray[counter];
                counter += 1;
            }
        }

    }

    public String randomLetter(Boolean vowel){
        //https://stackoverflow.com/questions/9330394/how-to-pick-an-item-by-its-probability
        double random_prob = Math.random();
        double total_prob = 0.0;

        for (HashMap.Entry<String, Integer> entry: letterWeights.entrySet()){

            if (vowel && vowels.contains(entry.getKey())){
                total_prob += entry.getValue().floatValue()/vowelWeight;
                //Log.d("Tag",Double.toString(total_prob));
                if (random_prob <= total_prob) {
                    return entry.getKey();
                }
            }

            else if (!vowel && notVowels.contains(entry.getKey()) || entry.getKey().equals("QU")){
                total_prob += entry.getValue().floatValue()/consonantWeight;
                if (random_prob <= total_prob) {
                    //System.out.println(entry.getKey());
                    return entry.getKey();

                }
            }

        }
        return null;
    }

}
