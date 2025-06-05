//package pl.psi.spells;
//
//import com.google.common.collect.Range;
//import pl.psi.Spells.BuffSpell;
//import pl.psi.Spells.DamageSpell;
//import pl.psi.Spells.FireWallSpell;
//import pl.psi.Spells.Spell;
//import pl.psi.creatures.CreatureStats;
//import pl.psi.map.resources.Resources;
//
//import java.util.function.Supplier;
//
//public enum SpellLibrary {
//    // ATTACK BOOST
//    ATTACK_BOOST_LEVEL_1(
//            new Resources(100, 0, 0, 0, 0, 0, 0),
//            () -> new BuffSpell("Attack Boost I", 1, 3, CreatureStats.builder()
//                    .attack(3)
//                    .armor(0)
//                    .maxHp(0)
//                    .moveRange(0)
//                    .name("Attack Boost I Buff")
//                    .description("Increases attack by 3 for 3 turns.")
//                    .tier(0)
//                    .damage(Range.closed(0, 0))
//                    .isUpgraded(false)
//                    .build())
//    ),
//
//    ATTACK_BOOST_LEVEL_2(
//            new Resources(150, 0, 0, 0, 0, 0, 0),
//            () -> new BuffSpell("Attack Boost II", 2, 3, CreatureStats.builder()
//                    .attack(5)
//                    .armor(0)
//                    .maxHp(0)
//                    .moveRange(0)
//                    .name("Attack Boost II Buff")
//                    .description("Increases attack by 5 for 3 turns.")
//                    .tier(0)
//                    .damage(Range.closed(0, 0))
//                    .isUpgraded(false)
//                    .build())
//    ),
//
//    // SPEED BOOST
//    SPEED_BOOST_LEVEL_1(
//            new Resources(100, 0, 0, 0, 0, 0, 0),
//            () -> new BuffSpell("Speed Boost I", 1, 3, CreatureStats.builder()
//                    .attack(0)
//                    .armor(0)
//                    .maxHp(0)
//                    .moveRange(2)
//                    .name("Speed Boost I Buff")
//                    .description("Increases move range by 2 for 3 turns.")
//                    .tier(0)
//                    .damage(Range.closed(0, 0))
//                    .isUpgraded(false)
//                    .build())
//    ),
//
//    SPEED_BOOST_LEVEL_2(
//            new Resources(150, 0, 0, 0, 0, 0, 0),
//            () -> new BuffSpell("Speed Boost II", 2, 3, CreatureStats.builder()
//                    .attack(0)
//                    .armor(0)
//                    .maxHp(0)
//                    .moveRange(3)
//                    .name("Speed Boost II Buff")
//                    .description("Increases move range by 3 for 3 turns.")
//                    .tier(0)
//                    .damage(Range.closed(0, 0))
//                    .isUpgraded(false)
//                    .build())
//    ),
//
//    // SLOW DEBUFF
//    SLOW_LEVEL_1(
//            new Resources(80, 0, 0, 0, 0, 0, 0),
//            () -> new BuffSpell("Slow I", 1, 3, CreatureStats.builder()
//                    .attack(0)
//                    .armor(0)
//                    .maxHp(0)
//                    .moveRange(-1)
//                    .name("Slow I Debuff")
//                    .description("Reduces move range by 1 for 3 turns.")
//                    .tier(0)
//                    .damage(Range.closed(0, 0))
//                    .isUpgraded(false)
//                    .build())
//    ),
//
//    SLOW_LEVEL_2(
//            new Resources(100, 0, 0, 0, 0, 0, 0),
//            () -> new BuffSpell("Slow II", 2, 3, CreatureStats.builder()
//                    .attack(0)
//                    .armor(0)
//                    .maxHp(0)
//                    .moveRange(-2)
//                    .name("Slow II Debuff")
//                    .description("Reduces move range by 2 for 3 turns.")
//                    .tier(0)
//                    .damage(Range.closed(0, 0))
//                    .isUpgraded(false)
//                    .build())
//    ),
//
//    // WEAKNESS DEBUFF
//    WEAKNESS_LEVEL_1(
//            new Resources(80, 0, 0, 0, 0, 0, 0),
//            () -> new BuffSpell("Weakness I", 1, 3, CreatureStats.builder()
//                    .attack(-1)
//                    .armor(-1)
//                    .maxHp(0)
//                    .moveRange(0)
//                    .name("Weakness I Debuff")
//                    .description("Reduces attack and armor by 1 for 3 turns.")
//                    .tier(0)
//                    .damage(Range.closed(0, 0))
//                    .isUpgraded(false)
//                    .build())
//    ),
//
//    WEAKNESS_LEVEL_2(
//            new Resources(100, 0, 0, 0, 0, 0, 0),
//            () -> new BuffSpell("Weakness II", 2, 3, CreatureStats.builder()
//                    .attack(-3)
//                    .armor(-2)
//                    .maxHp(0)
//                    .moveRange(0)
//                    .name("Weakness II Debuff")
//                    .description("Reduces attack by 3 and armor by 2 for 3 turns.")
//                    .tier(0)
//                    .damage(Range.closed(0, 0))
//                    .isUpgraded(false)
//                    .build())
//    ),
//
//    // Obrażenia
//    FIREBALL_LEVEL_1(
//            new Resources(150, 0, 0, 0, 0, 0, 0),
//            () -> new DamageSpell("Fireball I", 1, 0)
//    ),
//
//    FIREBALL_LEVEL_2(
//            new Resources(250, 0, 0, 0, 0, 0, 0),
//            () -> new DamageSpell("Fireball II", 2, 0)
//    ),
//
//    FIREBALL_LEVEL_3(
//            new Resources(400, 0, 0, 0, 0, 0, 0),
//            () -> new DamageSpell("Fireball III", 3, 0)
//    ),
//
//    LIGHTNING_BOLT_LEVEL_1(
//            new Resources(120, 0, 0, 0, 0, 0, 0),
//            () -> new DamageSpell("Lightning Bolt I", 1, 0)
//    ),
//
//    LIGHTNING_BOLT_LEVEL_2(
//            new Resources(200, 0, 0, 0, 0, 0, 0),
//            () -> new DamageSpell("Lightning Bolt II", 2, 0)
//    ),
//
//    LIGHTNING_BOLT_LEVEL_3(
//            new Resources(350, 0, 0, 0, 0, 0, 0),
//            () -> new DamageSpell("Lightning Bolt III", 3, 0)
//    ),
//
//// Fire Wall
//    FIRE_WALL_SMALL(
//            new Resources(200, 0, 0, 0, 0, 0, 0),
//            () -> new FireWallSpell("Fire Wall Small", 1, new BattlePoint(2, 1), 4.0)
//    ),
//
//    FIRE_WALL_MEDIUM(
//            new Resources(300, 0, 0, 0, 0, 0, 0),
//            () -> new FireWallSpell("Fire Wall Medium", 2, new BattlePoint(3, 1), 6.0)
//    ),
//
//    FIRE_WALL_LARGE(
//            new Resources(450, 0, 0, 0, 0, 0, 0),
//            () -> new FireWallSpell("Fire Wall Large", 3, new BattlePoint(5, 1), 8.5)
//    ),
//
//    ADVANCED_FIRE_WALL(
//            new Resources(600, 0, 0, 0, 0, 0, 0),
//            () -> new FireWallSpell("Advanced Fire Wall", 4, new BattlePoint(7, 1), 11.0)
//    );
//
//    private final Resources cost;
//    private final Supplier<Spell> spellSupplier;
//
//    SpellLibrary(Resources cost, Supplier<Spell> spellSupplier) {
//        this.cost = cost;
//        this.spellSupplier = spellSupplier;
//    }
//
//    public Spell createSpell() {
//        return spellSupplier.get();
//    }
//
//    public EconomySpell createEconomySpell() {
//        return new EconomySpell(createSpell(), cost.getGold());
//    }
//
//
//}
