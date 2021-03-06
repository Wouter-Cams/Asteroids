package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * @author Wouter Cams and Stijn Bruggeman
 * test
 * Studies:
 * Wouter Cams: 2e Bachelor Ingenieurswetenschappen
 * Hoofdrichting Elektrotechniek, nevenrichting computerwetenschappen
 * Stijn Bruggeman: 2e Bachelor Ingenieurswetenschappen
 * Hoofdrichting computerwetenschappen, nevenrichting elektrotechniek
 * 
 * @version  0.1
 * 
 * A link to the GitHub code repository:
 * https://github.com/WizzardProp12/Pom
 * 
 *
 */
public class Bullet extends Entity {
	
	// CONSTRUCTOR
	/**
	 * Initialise a new bullet with given coordinates, velocities, radius and ship.
	 * @param xCoord
	 * 		  The x-coordinate of the bullet.
	 * 		| getXCoord() == xCoord
	 * @param yCoord
	 * 		  The y-coordinate of the bullet.
	 * 		| getYCoord() == yCoord
	 * @param xVelocity
	 * 		  The velocity along the x-axis
	 * 		| getXVelocity() == xVelocity
	 * @param yVelocity
	 * 		  The velocity along the y-axis
	 * 		| getYVelocity() == yVelocity
	 * @param radius
	 * 		  The radius of the bullet.
	 * 		| getRadius() == radius
	 * @param ship
	 * 		  The ship in which the bullet is located.
	 * 		| getShip() == ship
	 * @pre   The given coordinates must be valid.
	 * 		| canHaveAsXCoordinate(xCoord) && canHaveAsYCoordinate(yCoord)
	 * @pre   The given radius must be valid.
	 * 		| canHaveAsRadius(radius)
	 * @pre   The given ship must be valid
	 *      | canHaveAsShip(ship)
	 * @post  If the bullet is loaded on a ship, it references the ships position.
	 *      | getShip().getPosition() == getPosition()
	 * @post  If the bullet is not loaded on a ship, it references the given coordinates.
	 * 	    | getPosition() == new Position(xCoord, yCoord)
	 * @post ...
	 * 		| getSpeed() <= getSpeedLimit()
	 * @post ...
	 * 		| new getRadius() >= getMinRadius()
	 * @throws IllegalArgumentException
	 * 		   If the given coordinates are not legal.
	 * 		 | ! canHaveAsXCoordinate(xCoord) || ! canHaveAsYCoordinate(yCoord)
	 * @throws IllegalArgumentException
	 * 		   If the given radius is not valid
	 * 		 | ! canHaveAsRadius(radius)
	 * @throws IllegalArgumentException
	 * 		   If the ship cannot load the given bullet.
	 * 		 | ! canHaveAsBullet(bullet)
	 * @throws IllegalArgumentException
	 * 		   If the bullet cannot be loaded on the ship.
	 * 		 | ! bullet.canHaveAsShip(this)
	 */
	public Bullet(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, Ship ship) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius);
		if (canHaveAsShip(ship) && ship != null)
			ship.load(this);
	}
	
	/**
	 * Initialise a new bullet with given coordinates, velocities, radius and no ship.
	 * @effect ...
	 * 		 | Bullet(xCoord, yCoord, xVelocity, yVelocity, radius, null)
	 */
	public Bullet(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, null);
	}

	/**
	 * Initialise a new bullet with given coordinates, velocities, default radius and no ship.
	 * @effect ...
	 * 		 | Bullet(xCoord, yCoord, xVelocity, yVelocity, MIN_RADIUS, null)
	 */
	public Bullet(double xCoord, double yCoord, double xVelocity, double yVelocity) {
		this(xCoord, yCoord, xVelocity, yVelocity, MIN_RADIUS);
	}
	
	/**
	 * Initialise a new bullet with given coordinates, no velocities, default radius and no ship.
	 * @effect ...
	 * 		 | Bullet(xCoord, yCoord, 0, 0, MIN_RADIUS, null)
	 */
	public Bullet(double xCoord, double yCoord) {
		this(xCoord, yCoord, 0, 0);
	}
	
	/**
	 * Initialise a new bullet with coordinates (0,0), no velocities, default radius and no ship.
	 * @effect ...
	 * 		 | Bullet(0, 0, 0, 0, MIN_RADIUS, null)
	 */
	public Bullet() {
		this(0, 0);
	}
	
	
	// EQUALS
	
	/**
	 * Return whether the prime object equals the argument.
	 * @param  other
	 * 		   The other bullet.
	 * @return Whether this bullet is equal to the given object.
	 * 		 | see implementation...
	 */
	@Override
	public boolean equals(Object other){
		if (! (other instanceof Bullet))
			return false;
		else if (super.equals(other)
				&& getShip() == ((Bullet) other).getShip()
				&& getNbBounces() == ((Bullet) other).getNbBounces())
			return true;
		else
			return false;
	}
	
		
	// RADIUS (defensive)
	/**
	 * The minimum radius of a bullet.
	 */
	public static final double MIN_RADIUS = 1;
	
	/**
	 * Return the minimum radius of a bullet.
	 * @return The minimum radius of a bullet.
	 */
	@Basic @Immutable @Raw
	public static double getMinRadius() {
		return MIN_RADIUS;
	}
	
	/**
	 * Return the default radius of a Bullet.
	 * @return The default radius of a Bullet.
	 */
	@Basic @Immutable @Raw
	public double getDefaultRadius() {
		return getMinRadius();
	}
	
	/**
	 * Returns whether the given radius suits the prime object.
	 * @param  radius
	 * 		   The radius to be checked
	 * @return Whether the given radius is big enough.
	 * 		 | getMinRadius() <= radius
	 */
	@Basic @Raw
	public boolean canHaveAsRadius(double radius) {
		return (getMinRadius() <= radius);
	}
	
	
	// MASS (total)
	
	/**
	 * The density of the bullet.
	 */
	private final double density = 7.8 * Math.pow(10, 12);
	
	/**
	 * Return the density of the bullet.
	 * @return The density of the bullet.
	 */
	@Basic @Raw @Immutable
	public double getDensity() {
		return density;
	}
	
	
	/**
	 * Return the mass of the bullet.
	 * @return The mass of the bullet.
	 */
	private double mass = getMinimumMass();
	
	/**
	 * Return the mass of the bullet.
	 * @invar The mass of the bullet is always greater than or equal to the minimum mass
	 *      | getMass() >= getMinimumMass()
	 * @return The mass of the bullet.
	 */
	@Basic @Raw @Immutable
	public double getMass() {
		return mass;
	}
	
	
	// POSITION (defensive)
	
	/**
	 * Set the position of the bullet.
	 * @pre    If the bullet is not loaded on a ship, the given position must be valid
	 *       | getSourceShip() != null || isValidPosition(position)
	 * @pre    If the bullet is loaded on a ship, it must reference that ships position.
	 *       | getSourceShip() != null && getSourceShip().getPosition() == position
	 * @post   The bullet references the given position.
	 *       | getPosition() == position
	 * @throws IllegalArgumentException
	 *         If bullet is not loaded on a ship and the given position is not valid.
	 *       | ! isValidPosition(position)
	 * @throws IllegalArgumentException
	 *         If the bullet is loaded on a ship and the given position doesn't 
	 *         reference the ships position.
	 *       | getSourceShip().getPosition() != position
	 */
	public void setPosition(Position position) throws IllegalArgumentException {
		if (getSourceShip() != null) super.setPosition(position);
		else this.position = position;
	}
	
	// SHIP (defensive)
	
	/**
	 * The ship that carries the bullet.
	 */
	private Ship ship;
	
	/**
	 * Return the ship that carries the bullet.
	 * @return The ship that carries the bullet.
	 */
	@Basic @Raw
	public Ship getShip() {
		return this.ship; 
	}
	
	/**
	 * Return whether the bullet can belong to the given ship.
	 * @return Whether the bullet can belong to the given ship.
	 * 		 | ship == null || ship.canHaveAsBullet(this)
	 */
	public boolean canHaveAsShip(Ship ship) {
		return ship == null || ship.canHaveAsBullet(this);
	}
	
	/**
	 * Set the ship of the bullet.
	 * @pre    The ship must be valid for the bullet
	 * 	     | canHaveAsShip(ship)
	 * @throws IllegalArgumentException
	 *         If the ship is invalid.
	 *       | ! canHaveAsShip(ship)
	 */
	protected void setShip(Ship ship) throws IllegalArgumentException {
		if (! canHaveAsShip(ship)) throw new IllegalArgumentException(
				"invalid ship, see canHaveAsShip()");
		if (getShip() == ship) return;
		if (ship == null) {
			this.ship = ship;
			setPosition(new Position());
		} else {
			this.ship = ship;
			setPosition(ship.getPosition());
		}
	}
	
	// SOURCE (total)
	
	/**
	 * The ship that fired the bullet.
	 */
	private Ship sourceShip;
	
	/**
	 * Return the ship that fired the bullet.
	 * @return The ship that fired the bullet.
	 */
	@Basic @Raw
	public Ship getSourceShip() {
		return sourceShip;
	}
	
	/**
	 * Set the ship that fired the bullet.
	 * @param ship
	 * 		  The source ship to set.
	 */
	protected void setSourceShip(Ship ship) {
		sourceShip = ship;
	}
	
	
    // FIRING (total)
	
	/**
	 * The speed with which a bullet is fired from a ship.
	 */
	public static final double FIRE_SPEED = 250;
	
	/**
	 * A static getter for the fire speed of a bullet.
	 * @return The fire speed of a bullet.
	 */
	@Basic @Immutable @Raw
	public static double getFireSpeed() {
		return Bullet.FIRE_SPEED;
	}
	
	
	// BOUNCES ()
	
	/**
	 * The amount of times a bullet can bounce of a wall.
	 */
	private final int MAX_BOUNCES = 2;
	
	/**
	 * Return the maximum amount of bounces a bullet can make.
	 * @return The maximum amount of bounces a bullet can make.
	 */
	@Basic @Immutable @Raw
	public int getMaxNbBounces() {
		return MAX_BOUNCES;
	}
	
	/**
	 * The amount of times the bullet has bounced of a wall.
	 */
	private int nbBounces = 0;
	
	/**
	 * Get how much times the bullet has bounced of a wall.
	 * @invar The amount of times a bullet has bounced is smaller than the maximum amount.
	 *      | getNbBounces() < getMaxNbBounces()
	 * @return How much times the bullet has bounced of a wall.
	 */
	public int getNbBounces() {
		return this.nbBounces;
	}
	
	/**
	 * Count a bounce of the bullet.
	 * @post The nbBounces is incremented by 1
	 * 	   | new getNbBounces() == old getNbBounces() + 1
	 */
	public void countBounce() {
		nbBounces++;
	}
	
	/**
	 * Bounce the bullet of a wall or destroy it if it has reached its
	 * maximum amount of bounces.
	 * @post The bullet is terminated or has not reached its maximum amount of bounces.
	 *     | isTerminated() || new getNbBounces() < getMaxNbBounces()
	 */
	@Override
	public void bounce(CollisionType type) {
		if (getNbBounces() == getMaxNbBounces())
			die();
		else {
			super.bounce(type);
			countBounce();
		}
	}
	
	// TERMINATE
	
	/**
	 * Terminate the bullet
	 * @effect Terminate the bullet and if it is loaded on a
	 *         ship, remove it from the ship.
	 *       | see implementation...
	 */
	public void terminate() {
		if (getShip() != null) {
			getShip().removeBullet(this);
		}
		super.terminate();
	}
}
