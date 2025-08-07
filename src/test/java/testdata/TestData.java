package testdata;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;
import player.userinputhandler.commands.db.Character;

import static player.dndcharacter.dndcharacterenums.Background.ACOLYTE;
import static player.dndcharacter.dndcharacterenums.Background.GUILD_ARTISAN;
import static player.dndcharacter.dndcharacterenums.Background.SOLDIER;
import static player.dndcharacter.dndcharacterenums.CharacterClass.BARD;
import static player.dndcharacter.dndcharacterenums.CharacterClass.WARLOCK;
import static player.dndcharacter.dndcharacterenums.CharacterClass.WIZARD;
import static player.dndcharacter.dndcharacterenums.Characteristics.CHARISMA;
import static player.dndcharacter.dndcharacterenums.Characteristics.CONSTITUTION;
import static player.dndcharacter.dndcharacterenums.Characteristics.DEXTERITY;
import static player.dndcharacter.dndcharacterenums.Characteristics.INTELLIGENCE;
import static player.dndcharacter.dndcharacterenums.Characteristics.STRENGTH;
import static player.dndcharacter.dndcharacterenums.Characteristics.WISDOM;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Race.DARK_ELF;
import static player.dndcharacter.dndcharacterenums.Race.HALF_ELF;
import static player.dndcharacter.dndcharacterenums.Race.TIEFLING;
import static player.dndcharacter.dndcharacterenums.Skill.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skill.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skill.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skill.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skill.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skill.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skill.INTIMIDATION;
import static player.dndcharacter.dndcharacterenums.Skill.INVESTIGATION;
import static player.dndcharacter.dndcharacterenums.Skill.MEDICINE;
import static player.dndcharacter.dndcharacterenums.Skill.NATURE;
import static player.dndcharacter.dndcharacterenums.Skill.PERCEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.PERFORMANCE;
import static player.dndcharacter.dndcharacterenums.Skill.PERSUASION;
import static player.dndcharacter.dndcharacterenums.Skill.RELIGION;
import static player.dndcharacter.dndcharacterenums.Skill.SLEIGHT_OF_HAND;
import static player.dndcharacter.dndcharacterenums.Skill.STEALTH;
import static player.dndcharacter.dndcharacterenums.Skill.SURVIVAL;

public class TestData {

    private static final Long chatID = 123L;

    public static Character getMockCharacterAna() {

        //Doesn't have proficiencies
        DndCharacter mockDNDCharacterAna = new DndCharacter();

        mockDNDCharacterAna.setCharacterName("Ana Amari");
        mockDNDCharacterAna.setCharacterClass(BARD);
        mockDNDCharacterAna.setBackground(ACOLYTE);
        mockDNDCharacterAna.setRace(TIEFLING);
        mockDNDCharacterAna.setAlignment("Chaotic good");
        mockDNDCharacterAna.setArmourClass(15);
        mockDNDCharacterAna.setSpeed(30);
        mockDNDCharacterAna.setHitDice(8);

        mockDNDCharacterAna.setStrength(10);
        mockDNDCharacterAna.setDexterity(12);
        mockDNDCharacterAna.setConstitution(14);
        mockDNDCharacterAna.setIntelligence(16);
        mockDNDCharacterAna.setWisdom(18);
        mockDNDCharacterAna.setCharisma(20);

        mockDNDCharacterAna.getLanguages().add("Lang1");
        mockDNDCharacterAna.getLanguages().add("Lang2");

        mockDNDCharacterAna.setGold(100);
        mockDNDCharacterAna.setEquipment("Test Equipment");

        mockDNDCharacterAna.setPersonalityTraits("Test personality traits");
        mockDNDCharacterAna.setIdeals("Test ideals");
        mockDNDCharacterAna.setBonds("Test bonds");
        mockDNDCharacterAna.setFlaws("Test flaws");
        mockDNDCharacterAna.setFeaturesAndTraits("Test features and traits");
        mockDNDCharacterAna.setAge("50");
        mockDNDCharacterAna.setHeight("5");
        mockDNDCharacterAna.setWeight("70");
        mockDNDCharacterAna.setEyes("Eyes");
        mockDNDCharacterAna.setSkin("Skin");
        mockDNDCharacterAna.setHair("Hair");
        mockDNDCharacterAna.setAlliesAndOrganizations("Test Allies");
        mockDNDCharacterAna.setBackstory("Test Backstory");
        mockDNDCharacterAna.setTreasure("Test Treasure");
        mockDNDCharacterAna.setSpellcastingAbility(SpellcastingAbility.WISDOM);

        return new Character(31, chatID, mockDNDCharacterAna);
    }

    public static Character getMockCharacterHanzo() {

        //Proficient in everything
        DndCharacter mockDNDCharacterHanzo = new DndCharacter();
        mockDNDCharacterHanzo.setCharacterName("Hanzo Shimada");
        mockDNDCharacterHanzo.setCharacterClass(WARLOCK);
        mockDNDCharacterHanzo.setBackground(GUILD_ARTISAN);
        mockDNDCharacterHanzo.setRace(HALF_ELF);

        mockDNDCharacterHanzo.setStrength(10);
        mockDNDCharacterHanzo.setDexterity(12);
        mockDNDCharacterHanzo.setConstitution(14);
        mockDNDCharacterHanzo.setIntelligence(16);
        mockDNDCharacterHanzo.setWisdom(18);
        mockDNDCharacterHanzo.setCharisma(20);

        mockDNDCharacterHanzo.getSavingThrowsWithProficiency().add(STRENGTH);
        mockDNDCharacterHanzo.getSavingThrowsWithProficiency().add(DEXTERITY);
        mockDNDCharacterHanzo.getSavingThrowsWithProficiency().add(CONSTITUTION);
        mockDNDCharacterHanzo.getSavingThrowsWithProficiency().add(INTELLIGENCE);
        mockDNDCharacterHanzo.getSavingThrowsWithProficiency().add(WISDOM);
        mockDNDCharacterHanzo.getSavingThrowsWithProficiency().add(CHARISMA);

        mockDNDCharacterHanzo.getSkillsWithProficiency().put(ACROBATICS, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(ANIMAL_HANDLING, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(ARCANA, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(ATHLETICS, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(HISTORY, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(INSIGHT, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(INTIMIDATION, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(INVESTIGATION, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(MEDICINE, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(NATURE, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(PERCEPTION, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(PERFORMANCE, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(PERSUASION, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(RELIGION, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(SLEIGHT_OF_HAND, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        mockDNDCharacterHanzo.getSkillsWithProficiency().put(SURVIVAL, PROFICIENT);

        mockDNDCharacterHanzo.getLanguages().add("Lang1");
        mockDNDCharacterHanzo.getLanguages().add("Lang2");

        mockDNDCharacterHanzo.getToolProficiency().add("Tool1");
        mockDNDCharacterHanzo.getToolProficiency().add("Tool2");

        return new Character(32, chatID, mockDNDCharacterHanzo);
    }

    public static Character getMockCharacterZarya() {
        //Has name in Cyrillic
        DndCharacter mockDNDCharacterZarya = new DndCharacter();
        mockDNDCharacterZarya.setCharacterName("Заря");
        mockDNDCharacterZarya.setCharacterClass(WIZARD);
        mockDNDCharacterZarya.setBackground(SOLDIER);
        mockDNDCharacterZarya.setRace(DARK_ELF);

        return new Character(33, chatID, mockDNDCharacterZarya);
    }
}
