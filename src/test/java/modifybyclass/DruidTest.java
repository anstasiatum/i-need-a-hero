package modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;
import player.dndcharacter.dndclass.Druid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DruidTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(12);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);
        dndCharacter.getLanguages().add("Elvish");
        dndCharacter.getArmourProficiency().add("test armour");
        dndCharacter.getWeaponProficiency().add("test weapon");
        dndCharacter.setFeaturesAndTraits("test feature\n");
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        assertEquals(9, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(3);
        expectedResult.add("Elvish");
        expectedResult.add("Common");
        expectedResult.add("Druidic");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        assertEquals(8, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        assertEquals(2, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(4);
        expectedResult.add("test armour");
        expectedResult.add("Light Armour");
        expectedResult.add("Medium Armour");
        expectedResult.add("Shields (druids will not wear armor or use shields made of metal)");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(11);
        expectedResult.add("test weapon");
        expectedResult.add("Clubs");
        expectedResult.add("Daggers");
        expectedResult.add("Darts");
        expectedResult.add("Javelins");
        expectedResult.add("Maces");
        expectedResult.add("Quarterstuffs");
        expectedResult.add("Scimitars");
        expectedResult.add("Sickles");
        expectedResult.add("Slings");
        expectedResult.add("Spears");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Tool Proficiency")
    void setToolProficiencyForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(1);
        expectedResult.add("Herbalism kit");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.INTELLIGENCE);
        expectedResult.add(Characteristics.WISDOM);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Features And Proficiencies")
    void setFeaturesAndTraitsForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        String expectedResult = """
                test feature
                Druidic
                You know Druidic, the secret language of druids. You can speak the language and use it to leave hidden messages. You and others who know this language automatically spot such a message. Others spot the message's presence with a successful DC 15 Wisdom (Perception) check but can't decipher it without magic.
                Ritual Casting
                You can cast a druid spell as a ritual if that spell has the ritual tag and you have the spell prepared.
                Spellcasting Focus
                You can use a druidic focus as a spellcasting focus for your druid spells.
                """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set Spellcasting Ability")
    void setSpellcastingAbilityForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        assertEquals(SpellcastingAbility.WISDOM, dndCharacter.getSpellcastingAbility());
    }

    @Test
    @DisplayName("Set Spells Known")
    void setSpellsKnownForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        assertEquals(3, dndCharacter.getSpellsKnown());
    }

    @Test
    @DisplayName("Set Spells Known Per Level")
    void setSpellsKnownPerLevelForDruid() {
        Druid druid = new Druid();
        druid.modifyByClass(dndCharacter);

        List<Integer> expectedResult = new ArrayList<>(Collections.nCopies(8, 0));
        expectedResult.add(0, 2);
        expectedResult.add(1, 2);

        assertEquals(expectedResult, dndCharacter.getSpellsKnownPerLevel());
    }
}
