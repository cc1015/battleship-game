package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a BattleSalvo board.
 */
public class Board {
  private char[][] grid;
  private ArrayList<Coord> emptyCoords;
  private ArrayList<Ship> ships;

  /**
   * Sets the grid field to a new 2d char array of the given height and width and fills all values
   * with '0'.
   *
   * @param height The height of the board.
   * @param width  The width of the board.
   */
  public Board(int height, int width) {
    this.grid = new char[height][width];
    this.emptyCoords = new ArrayList<>();
    this.ships = new ArrayList<>();

    // for each cell, make value '0' and create new coordinate of the current cell and add to list
    // of empty Coords
    for (int i = 0; i < height; i += 1) {
      for (int j = 0; j < width; j += 1) {
        this.grid[i][j] = '0';

        Coord c = new Coord(j, i);
        this.emptyCoords.add(c);
      }
    }
  }

  /**
   * Returns this Board's height.
   *
   * @return The Board's height.
   */
  public int getHeight() {
    return this.grid.length;
  }

  /**
   * Returns this Board's width.
   *
   * @return This Board's width.
   */
  public int getWidth() {
    return this.grid[0].length;
  }

  /**
   * Returns this Board's grid.
   *
   * @return This Board's grid.
   */
  public char[][] getGrid() {
    return grid;
  }

  /**
   * Returns this Board's empty coordinates.
   *
   * @return This Board's empty coordinates.
   */
  public ArrayList<Coord> getEmptyCoords() {
    return this.emptyCoords;
  }

  /**
   * Returns the total number of ships on this Board (hit and not hit).
   *
   * @return The total number of ships on this Board (hit and not hit).
   */
  public int getTotalShipCount() {
    return this.ships.size();
  }

  /**
   * Returns the possible coordinates that a ship of the given ship size could be placed at on
   * this Board.
   *
   * @param shipSize The size of the ship that needs to be placed.
   * @return A list of possible coordinates that a ship of the given ship size could be placed at.
   */
  public List<List<Coord>> getPossibleCoords(int shipSize) {
    ArrayList<List<Coord>> possibleCoords = new ArrayList<>();

    // for each empty coordinate on this Board, check if there are enough empty coordinates in
    // north and east directions from the empty coordinate such that a ship of the given ship
    // size can be held.
    for (Coord emptyCoord : this.emptyCoords) {
      int startingX = emptyCoord.getX();
      int startingY = emptyCoord.getY();

      // check north and east directions for each empty coordinate
      for (Direction direction : Direction.getNecessaryValues()) {
        int dx = direction.getDx();
        int dy = direction.getDy();

        ArrayList<Coord> coordinates = new ArrayList<>();
        coordinates.add(emptyCoord);

        // check that subsequent cells in the current direction is empty and in bounds for the
        // ship size
        for (int i = 1; i < shipSize; i += 1) {
          Coord newCoord = new Coord(startingX + (dx * i), startingY + (dy * i));

          // if the coordinate is in bounds and empty (can hold ship)
          if (inBounds(newCoord) && emptyCoords.contains(newCoord)) {
            coordinates.add(newCoord);
          }
        }

        // if the number of coordinates that are in bounds and empty equals the size of the ship,
        // add it to the list of possible coordinates (can hold ship)
        if (coordinates.size() == shipSize) {
          possibleCoords.add(coordinates);
        }
      }
    }

    return possibleCoords;
  }

  /**
   * Determines if the given coordinates are in this Board's range.
   *
   * @param coord The coordinate that is being evaluated.
   * @return Whether the given coordinate is in this Board's range.
   */
  private boolean inBounds(Coord coord) {
    int x = coord.getX();
    int y = coord.getY();

    return ((x >= 0) && (x < grid[0].length) && (y >= 0) && (y < grid.length));
  }

  /**
   * Places the given ShipType at the Ship's coordinates. Returns a new ship representing the ship
   * that has been placed.
   *
   * @param ship        The ShipType to be placed.
   *
   * @param coordinates The coordinates the ShipType is placed at.
   *
   * @return The ship that has been placed.
   */
  public Ship placeShip(ShipType ship, List<Coord> coordinates) {
    for (Coord coord : coordinates) {
      int i = coord.getY();
      int j = coord.getX();

      this.grid[i][j] = ship.toChar();
      this.emptyCoords.remove(coord);
    }

    Ship newShip = new Ship(ship, coordinates);
    this.ships.add(newShip);
    return newShip;
  }

  /**
   * Accepts a shot on this Board. Sets the given shot coordinate to 'H' if the coordinate
   * previously had a ship or 'M' if the coordinate was previously empty or already missed.
   * Returns true of the shot hit a ship, false otherwise.
   *
   * @param shot The shot being accepted.
   * @return Whether the given coordinate was a successful shot (hit a ship).
   */
  public boolean shoot(Coord shot) {
    final char EMPTY = '0';
    final char MISS = 'M';

    int i = shot.getY();
    int j = shot.getX();

    char shotCoord = this.grid[i][j];

    if (shotCoord == EMPTY || shotCoord == MISS) {
     placeMiss(shot);
      return false;
    } else {
      placeHit(shot);
      return true;
    }
  }

  /**
   * Places a 'M' at the given coordinate and removes the given coordinate from this Board's list
   * of empty coordinates if the coordinate was previously empty.
   *
   * @param coord The coordinate the miss is placed at.
   */
  public void placeMiss(Coord coord) {
    int i = coord.getY();
    int j = coord.getX();

    this.grid[i][j] = 'M';
    this.emptyCoords.remove(coord);
  }

  /**
   * Places a 'H' at the given coordinate.
   *
   * @param coord The coordinate the hit is placed at.
   */
  public void placeHit(Coord coord) {
    int i = coord.getY();
    int j = coord.getX();

    this.grid[i][j] = 'H';
    updateShipsSunk();
  }

  /**
   * Updates the number of ships hit in this Board.
   */
  public void updateShipsSunk() {
    ArrayList<Ship> updatedShipsToRemove = new ArrayList<>();

    int count = 0;
    // for each ship, check if all the ship's coordinates are hit
    for (Ship ship : ships) {
      count += 1;
      List<Coord> coordinates = ship.getCoordinates();
      boolean allHit = true;

      // for each coordinate, check if they are hit
      for (Coord coord : coordinates) {
        int i = coord.getY();
        int j = coord.getX();

        if (!(this.grid[i][j] == 'H') && allHit) {
          allHit = false;
        }
      }
      if (allHit) {
        updatedShipsToRemove.add(ship);
      }
    }

    // removes all ships that were sunk if they were previously not sunk
    for (Ship ship : updatedShipsToRemove) {
      this.ships.remove(ship);
    }
  }

  /**
   * Determines if all ships in this Board have been hit.
   *
   * @return Whether all the ships in this Board have been hit.
   */
  public boolean allShipsSunk() {
    return ships.size() == 0;
  }

  /**
   * Returns this Board's grid as one String.
   *
   * @return This Board's grid as one String.
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();

    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < grid.length; i += 1) {
      for (int j = 0; j < grid[0].length; j += 1) {
        char character = grid[i][j];
        output.append(character);
        output.append(" ");
      }
      output.append("\n");
    }

    return output.toString();
  }

  /**
   * Whether two Boards are equal (same toString output, then equal).
   *
   * @param other The other object being compared to.
   *
   * @return Whether two Boards are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Board)) {
      return false;
    }
    Board that = (Board) other;
    return this.toString().equals(that.toString());
  }
}
