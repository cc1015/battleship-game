package cs3500.pa03.model;

import java.util.List;

/**
 * Represents a ship with a type and coordinates in a Battle Salvo game.
 */
public class Ship {
  private List<Coord> coordinates;
  private final ShipType type;

  /**
   * Sets the given ShipType and Coord to the type and position fields.
   *
   * @param type The ShipType the type field is set to.
   */
  public Ship(ShipType type, List<Coord> coordinates) {
    this.type = type;
    this.coordinates = coordinates;
  }

  /**
   * Returns this Ship's coordinates.
   *
   * @return The Ship's coordinates.
   */
  public List<Coord> getCoordinates() {
    return this.coordinates;
  }

  /**
   * Returns this Ship's type.
   *
   * @return The Ship's type.
   */
  public ShipType getType() {
    return type;
  }

  /**
   * Whether two Ships are equal (if the coordinates and type are the same, then two
   * Ships are equal).
   *
   * @param other The other object being compared to.
   *
   * @return Whether two Ships are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Ship)) {
      return false;
    }
    Ship that = (Ship) other;
    return this.coordinates.equals(that.coordinates) && this.type.equals(that.type);
  }
}
