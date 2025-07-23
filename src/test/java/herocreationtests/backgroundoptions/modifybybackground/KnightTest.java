package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Knight;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Background.KNIGHT;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;

public class KnightTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skills> skills = new HashSet<>(1);
        skills.add(ATHLETICS);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.setFeaturesAndTraits("test feature. ");

        Knight knight = new Knight();
        knight.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForKnight() {

        assertEquals(KNIGHT, dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForKnight() {
        Set<Skills> expectedResult = new HashSet<>(3);
        expectedResult.add(ATHLETICS);
        expectedResult.add(HISTORY);
        expectedResult.add(PERSUASION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForKnight() {

        assertEquals(35, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForKnight() {
        String expectedResult = "test equipment. A set of fine clothes, a signet ring, a scroll of pedigree. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }


    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForKnight() {
        String expectedResult = "test feature. Retainers\n You have the service of three retainers loyal to your family. These retainers can be attendants or messengers, and one might be a majordomo. Your retainers are commoners who can perform mundane tasks for you, but they do not fight for you, will not follow you into obviously dangerous areas (such as dungeons), and will leave if they are frequently endangered or abused.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
