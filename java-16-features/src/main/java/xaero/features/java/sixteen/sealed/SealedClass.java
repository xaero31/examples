package xaero.features.java.sixteen.sealed;

/**
 * sealed class can be extended only by classes from permits list
 * extending classes could exist
 * extending classes could be sealed, final or non-sealed
 */
public sealed class SealedClass permits SealedChild {
}
