package pl.psi.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.economy.Point;
import pl.psi.hero.EconomyHero;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BoardApiTest {

    private EconomyHero hero1;
    private EconomyHero hero2;

    @BeforeEach
    void setUp() {
        hero1 = mock(EconomyHero.class);
        hero2 = mock(EconomyHero.class);
    }

    @Test
    void shouldCallInteractOnMapObjectWhenInteracted() {
        // given
        Point interactionPoint = new Point(1, 1);
        MapObjectIf mapObject = spy(new testMapObject());

        Map<Point, MapObjectIf> map = new HashMap<>();
        map.put(interactionPoint, mapObject);

        BoardEconomyEngine engine = new BoardEconomyEngine(hero1, hero2, map);

        // when
        engine.interact(interactionPoint);

        // then
        verify(mapObject, times(1)).interact(eq(hero1));
    }

    @Test
    void shouldCallInteractOnMapObjectWhenWalkedOver() {
        // given
        Point interactionPoint = new Point(1, 1);
        MapObjectIf mapObject = spy(new testMapObject());

        Map<Point, MapObjectIf> map = new HashMap<>();
        map.put(interactionPoint, mapObject);

        BoardEconomy board = BoardEconomy.builder()
                .addHero(hero1, new Point(0,0))
                .addHero(hero2,new Point(14,14))
                .addInteractables(map)
                .build();


        // when
        board.move(hero1, interactionPoint);
        board.interact(hero1, interactionPoint);

        // then
        verify(mapObject, times(1)).interact(eq(hero1));
    }

    @Test
    void shouldDissapearMapObjectWhenInteractedIfHasPickupable() {
        // given
        Point interactionPoint = new Point(1, 1);
        MapObjectIf mapObject = spy(new testMapObjectPickupable());

        Map<Point, MapObjectIf> map = new HashMap<>();
        map.put(interactionPoint, mapObject);

        BoardEconomyEngine engine = new BoardEconomyEngine(hero1, hero2, map);

        // when
        engine.interact(interactionPoint);

        // then
        verify(mapObject, times(1)).interact(eq(hero1));
        assertThat(engine.getMapObject(interactionPoint).isEmpty()).isTrue();
    }

    @Test
    void shouldStayMapObjectWhenInteractedIfHasPickupable() {
        // given
        Point interactionPoint = new Point(1, 1);
        MapObjectIf mapObject = spy(new testMapObject());

        Map<Point, MapObjectIf> map = new HashMap<>();
        map.put(interactionPoint, mapObject);

        BoardEconomyEngine engine = new BoardEconomyEngine(hero1, hero2, map);

        // when
        engine.interact(interactionPoint);

        // then
        verify(mapObject, times(1)).interact(eq(hero1));
        assertThat(engine.getMapObject(interactionPoint).isEmpty()).isFalse();
    }
}

