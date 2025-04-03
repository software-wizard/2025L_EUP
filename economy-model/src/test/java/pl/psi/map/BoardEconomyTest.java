package pl.psi.map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import pl.psi.Point;
import pl.psi.hero.EconomyHero;

import java.util.HashMap;
import java.util.Map;

class BoardEconomyTest
{
    private EconomyHero hero1;
    private EconomyHero hero2;

    @BeforeEach
    void init()
    {
        hero1 = Mockito.mock( EconomyHero.class );
        hero2 = Mockito.mock( EconomyHero.class );
    }

    @Test
    void unitsMoveProperly()
    {
        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, 0)
                .addHero(hero2,14)
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
                .addHero(hero1, 0)
                .addHero(hero2,14)
                .build();
        Mockito.when(hero1.getMoveRange()).thenReturn(1);
        board.move(hero1, new Point( 3, 3 ) );
        assertThat( board.getHero( new Point( 3, 3 ) )
                .isPresent() ).isFalse();
    }

    @Test
    void setterSetsGoldCorrectly(){

        EconomyHero hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 0);
        Map<Point, InteractableIf> interactables = new HashMap<>();
        interactables.put(new Point(5,5),new Gold(500));
        interactables.put(new Point(10,10),new Gold(1000));
        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, 0)
                .addHero(hero2,14)
                .addInteractables(interactables)
                .build();

        board.move(hero1, new Point(5,5));
        assertThat(hero1.getGold()).isEqualTo(500);
        board.move(hero1,new Point(10,10));
        assertThat(hero1.getGold()).isEqualTo(1500);
    }
    
    @Test
    void goldDissapearsUponPickup()
    {
        Map<Point, InteractableIf> interactables = new HashMap<>();
        EconomyHero hero1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS, 0);
        interactables.put(new Point(5,5),new Gold(500));
        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, 0)
                .addHero(hero2,14)
                .addInteractables(interactables)
                .build();

        board.move(hero1, new Point(5,5));
        board.move(hero1, new Point(10,10));
        board.move(hero1, new Point(5,5));
        assertThat(hero1.getGold()).isEqualTo(500);

    }



}