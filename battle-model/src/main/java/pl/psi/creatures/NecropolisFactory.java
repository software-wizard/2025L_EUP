package pl.psi.creatures;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStatistic;
import pl.psi.Hero;

public class NecropolisFactory
{

    private static final String EXCEPTION_MESSAGE = "We support tiers from 1 to 7";
    private final Hero hero;

    public NecropolisFactory(Hero aHero)
    {
        hero = aHero;
    }

    public Creature create( final boolean aIsUpgraded, final int aTier, final int aAmount )
    {
        if( !aIsUpgraded )
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.SKELETON )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.WALKING_DEAD )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.WIGHT )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 4:
                    return new Creature.Builder().statistic( CreatureStatistic.VAMPIRE )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.LICH )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.BLACK_KNIGHT )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.BONE_DRAGON )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
        else
        {
            switch( aTier )
            {
                case 1:
                    return new Creature.Builder().statistic( CreatureStatistic.SKELETON_WARRIOR )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 2:
                    return new Creature.Builder().statistic( CreatureStatistic.ZOMBIE )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 3:
                    return new Creature.Builder().statistic( CreatureStatistic.WRAITH )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 4:
                    return new Creature.Builder().statistic( CreatureStatistic.VAMPIRE_LORD )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 5:
                    return new Creature.Builder().statistic( CreatureStatistic.POWER_LICH )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 6:
                    return new Creature.Builder().statistic( CreatureStatistic.DREAD_KNIGHT )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                case 7:
                    return new Creature.Builder().statistic( CreatureStatistic.GHOST_DRAGON )
                        .amount( aAmount )
                        .hero( hero )
                        .build();
                default:
                    throw new IllegalArgumentException( EXCEPTION_MESSAGE );
            }
        }
    }
}
