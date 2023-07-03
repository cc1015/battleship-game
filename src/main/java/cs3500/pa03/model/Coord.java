package cs3500.pa03.model;

/**
 * Represents a coordinate on a Battle Salvo board.
 */
public class Coord {
  private int x;
  private int y;

  /**
   * Sets x and y to the given x and y values.
   *
   * @param x The x value.
   *
   * @param y The y value.
   */
  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns this x value.
   *
   * @return This Coord's x value.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns this y value.
   *
   * @return This Coord's y value.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Overriding equals() method to return true if x and y values are equal.
   *
   * @param other The other object being compared to.
   *
   * @return Whether this Coord and the given object are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Coord)) {
      return false;
    }
    Coord that = (Coord) other;
    return this.x == that.getX() && this.y == that.getY();
  }

  @Override
  public String toString() {
    return this.x + " " + this.y;}
}
