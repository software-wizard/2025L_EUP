package pl.psi.map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import pl.psi.economy.Point;
import pl.psi.hero.EconomyHero;
import pl.psi.map.resources.Gold;
import pl.psi.map.resources.Resources;
import pl.psi.map.resources.generators.ResourceGenType;
import pl.psi.map.resources.generators.ResourceGenerator;

import java.util.HashMap;
import java.util.Map;

class BoardEconomyTest
{
    private EconomyHero hero1;
    private EconomyHero hero2;

    @BeforeEach
    void init()
    {
        hero1 = Mockito.mock(EconomyHero.class);
        hero2 = Mockito.mock( EconomyHero.class );
    }

    @Test
    void unitsMoveProperly()
    {
        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, new Point(0,0))
                .addHero(hero2,new Point(14,0))
                .build();
        Mockito.when(hero1.getMoveRange()).thenReturn(10);
        Mockito.when(hero2.getMoveRange()).thenReturn(20);
        board.move(hero1, new Point( 3, 3 ) );
        board.move(hero2, new Point( 10, 10 ) );

        assertThat( board.getHero( new Point( 3, 3 ) )
                .isPresent() ).isTrue();
        assertThat(board.getHero( new Point( 10, 10 ) ).isPresent() ).isTrue();
    }

    @Test
    void heroesCannotMove()
    {
        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, new Point(0,0))
                .addHero(hero2,new Point(14,14))
                .build();
        Mockito.when(hero1.getMoveRange()).thenReturn(1);
        board.move(hero1, new Point( 3, 3 ) );
        assertThat( board.getHero( new Point( 3, 3 ) )
                .isPresent() ).isFalse();
    }

    @Test
    void setterSetsGoldCorrectly(){


        Map<Point, MapObjectIf> interactables = new HashMap<>();
        interactables.put(new Point(5,5),new Gold(new Resources(500,0,0,0,0,0,0)));
        interactables.put(new Point(10,10),new Gold(new Resources(1000,0,0,0,0,0,0)));
        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, new Point(0,0))
                .addHero(hero2,new Point(14,14))
                .addInteractables(interactables)
                .build();

        board.move(hero1, new Point(5,5));
        assertThat(hero1.getResources()).isEqualTo(new Resources(500,0,0,0,0,0,0));
        board.move(hero1,new Point(10,10));
        assertThat(hero1.getResources()).isEqualTo(new Resources(1500,0,0,0,0,0,0));
    }
    
    @Test
    void goldDissapearsUponPickup()
    {
        Map<Point, MapObjectIf> interactables = new HashMap<>();
        interactables.put(new Point(5,5),new Gold(new Resources(500,0,0,0,0,0,0)));
        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, new Point(0,0))
                .addHero(hero2,new Point(14,14))
                .addInteractables(interactables)
                .build();

        board.move(hero1, new Point(5,5));
        board.move(hero1, new Point(10,10));
        board.move(hero1, new Point(5,5));
        assertThat(hero1.getResources().getGold()).isEqualTo(500);

    }

    @Test
    void mineGeneratesGoldProperlyForOwner()
    {
        Map<Point, MapObjectIf> map = new HashMap<>();
        map.put(new Point(5,5), new ResourceGenerator(ResourceGenType.GOLD));
        BoardEconomyEngine engine = new BoardEconomyEngine(hero1, hero2, map);

        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, new Point(0,0))
                .addInteractables(map)
                .build();

        board.move(hero1, new Point(5,5));
        board.move(hero1, new Point(10,10));
        engine.pass();
        assertThat(hero1.getResources().getGold()).isEqualTo(500);
        engine.pass();
        assertThat(hero1.getResources().getGold()).isEqualTo(1000);
    }




}