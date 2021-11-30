package edu.rice.comp504.model.strategy;


import edu.rice.comp504.model.agent.ACharacter;

public class GhostStrategyFac implements IStrategyFac{

    /**
     * Constructor.
     */
    public GhostStrategyFac() {

    }

    public static GhostStrategyFac singleton;

    /**
     * Only makes 1 eat strategy factory.
     * @return The eat strategy factory.
     */
    public static GhostStrategyFac makeStrategyFactory() {
        if (singleton == null ) {
            singleton = new GhostStrategyFac();
        }
        return singleton;
    }

    @Override
    public IUpdateStrategy make(String strategy) {
        switch (strategy) {
            case "avoid":   return AvoidStrategy.make();   //add
            case "random":  return RandomChaseStrategy.make();   //add
            case "chase":   return ChaseStrategy.make();    //add
            case "death": return DeathStrategy.make();
            case "ambush": return AmbushStrategy.make();
            case "stupid": return StupidStrategy.make();
            default:
                return NullStrategy.make();  //add
        }
    }

    /**
     * Switch the strategy.
     * @param context The current line.
     * @return The new line strategy for the line
     */
    public IUpdateStrategy switchStrategy(ACharacter context) {
        return null;
    }


}
