package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents an AI player in the BattleSalvo game.
 */
public class AutomatedPlayer extends AbPlayer {

  /**
   * Calls super constructor to initalize.
   */
  public AutomatedPlayer(GameData gameData, Random rand) {
    super(gameData, rand);
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "AI";
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    ArrayList<Coord> emptyCoords = gameData.getOpponentEmptyCoords();

    // create a list of coordinates that are adjacent to hit coordinates
    // pick n (number of remaining ships) number of random numbers in this list
    // if list length is less than the number of shots, pick random from all empty coords
    // check if adjacent cell has at least 3 empty next to it

    ArrayList<Coord> chosenCoords = new ArrayList<>();

    int currentIdx = 0;

    while (currentIdx < gameData.getOwnShipCount()) {
      int random = rand.nextInt(emptyCoords.size());

      Coord chosenCoord = emptyCoords.get(random);

      if (!chosenCoords.contains(chosenCoord)) {
        chosenCoords.add(chosenCoord);
        currentIdx += 1;
      }
    }

    return chosenCoords;
  }

}
