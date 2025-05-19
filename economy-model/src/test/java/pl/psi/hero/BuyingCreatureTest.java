package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.psi.EconomyEngine;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.map.resources.Resources;

public class BuyingCreatureTest
{

    private final EconomyNecropolisFactory creatureFactory = new EconomyNecropolisFactory();
    private EconomyHero hero1;
    private EconomyEngine economyEngine;
    private EconomyHero hero2;
    private Resources resources;


    @BeforeEach
    void init()
    {
        Statistics aStats = new Statistics(10, 10, 10, 10); ///
        resources = new Resources(1000,0,0,0,0,0,0);
        hero1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, resources, aStats);
        hero2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, resources, aStats);
        economyEngine = new EconomyEngine( hero1 );
    }

    @Test
    void heroShouldCanBuyCreature()
    {
        economyEngine.buy( creatureFactory.create( false, 1, 1) );

        assertEquals( 940, hero1.getResources().getGold() );
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInOneStack()
    {
        economyEngine.buy( creatureFactory.create( false, 1, 2) );

        assertEquals( 880, hero1.getResources().getGold() );
    }

    @Test
    void heroShouldCanBuyMoreThanOneCreatureInFewStack()
    {
        economyEngine.buy( creatureFactory.create( false, 1, 2 ) );
        economyEngine.buy( creatureFactory.create( true, 2, 2 ) );

        assertEquals( 630, hero1.getResources().getGold() );
    }

    @Test
    void heroCannotBuyCreatureWhenHasNotEnoughtGold()
    {
        assertThrows( IllegalStateException.class,
            () -> economyEngine.buy( creatureFactory.create( false, 1, 100 ) ) );
        assertEquals( 1000, hero1.getResources().getGold() );
        assertEquals( 0, hero1.getCreatures()
            .size() );
    }

    @Test
    void heroCannotBuyCreatureIfHeIsFull()
    {
        economyEngine.buy( creatureFactory.create( false, 1, 1 ) );
        economyEngine.buy( creatureFactory.create( false, 1, 1 ) );
        economyEngine.buy( creatureFactory.create( false, 1, 1 ) );
        economyEngine.buy( creatureFactory.create( false, 1, 1 ) );
        economyEngine.buy( creatureFactory.create( false, 1, 1 ) );
        economyEngine.buy( creatureFactory.create( false, 1, 1 ) );
        economyEngine.buy( creatureFactory.create( false, 1, 1 ) );
        assertThrows( IllegalStateException.class,
            () -> economyEngine.buy( creatureFactory.create( false, 1, 1 ) ) );

        assertEquals( 580, hero1.getResources().getGold() );
        assertEquals( 7, hero1.getCreatures()
            .size() );
    }
}
