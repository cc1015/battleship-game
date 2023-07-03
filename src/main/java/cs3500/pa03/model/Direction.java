package cs3500.pa03.model;

import java.util.ArrayList;

/**
 * Represents a direction.
 */
public enum Direction {
  NORTH, SOUTH, EAST, WEST;

  /**
   * Returns the change in x for this Direction.
   *
   * @return The change in x for this Direction.
   */
  public int getDx() {
    switch (this) {
      case EAST:
        return 1;

      case WEST:
        return -1;

      default:
        return 0;
    }
  }

  /**
   * Returns the change in y for this Direction.
   *
   * @return The change in y for this Direction.
   */
  public int getDy() {
    switch (this) {
      case SOUTH:
        return 1;

      case NORTH:
        return -1;

      default:
        return 0;
    }
  }

  /**
   * Returns the only necessary directions for traversing a 2d array.
   *
   * @return The only necessary directions for traversing a 2d array.
   */
  public static ArrayList<Direction> getNecessaryValues() {
    ArrayList<Direction> directions = new ArrayList<>();
    directions.add(Direction.NORTH);
    directions.add(Direction.EAST);
    return directions;
  }
}
