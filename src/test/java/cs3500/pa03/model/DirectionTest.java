package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for Direction enum class.
 */
class DirectionTest {
  /**
   * Checks that getDx for NORTH returns 0.
   */
  @Test
  public void testGetDxNorth() {
    assertEquals(Direction.NORTH.getDx(), 0);
  }

  /**
   * Checks that getDx for SOUTH returns 0.
   */
  @Test
  public void testGetDxSouth() {
    assertEquals(Direction.SOUTH.getDx(), 0);
  }


  /**
   * Checks that getDx for WEST returns -1.
   */
  @Test
  public void testGetDxWest() {
    assertEquals(Direction.WEST.getDx(), -1);
  }


  /**
   * Checks that getDx for EAST returns 1.
   */
  @Test
  public void testGetDxEast() {
    assertEquals(Direction.EAST.getDx(), 1);
  }

  /**
   * Checks that getDy for NORTH returns -1.
   */
  @Test
  public void testGetDyNorth() {
    assertEquals(Direction.NORTH.getDy(), -1);
  }

  /**
   * Checks that getDy for SOUTH returns 1.
   */
  @Test
  public void testGetDySouth() {
    assertEquals(Direction.SOUTH.getDy(), 1);
  }


  /**
   * Checks that getDy for WEST returns 0.
   */
  @Test
  public void testGetDyWest() {
    assertEquals(Direction.WEST.getDy(), 0);
  }


  /**
   * Checks that getDy for EAST returns 0.
   */
  @Test
  public void testGetDyEast() {
    assertEquals(Direction.EAST.getDy(), 0);
  }

  /**
   * Checks that onlyNecessaryDirections returns NORTH and EAST in a list.
   */
  @Test
  public void testOnlyNeccessaryDirections() {
    ArrayList<Direction> directions = new ArrayList<>();

    directions.add(Direction.NORTH);
    directions.add(Direction.EAST);

    assertEquals(Direction.getNecessaryValues(), directions);
  }
}