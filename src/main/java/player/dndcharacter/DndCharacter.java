package player.dndcharacter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import player.dndcharacter.dndcharacterenums.CharacterClass;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Race;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.dndcharacterenums.Skills;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DndCharacter {
    private String playerName;
    private String characterName;

    private String background;
    private String backstory;

    private String age;
    private String height;
    private String weight;
    private String eyes;
    private String skin;
    private String hair;

    private Size size;

    private int level = 1;

    private String alignment;
    private String personalityTraits;
    private String ideals;
    private String bonds;
    private String flaws;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int proficiencyBonus = 2;

    private Set<Skills> skillsWithProficiency = EnumSet.noneOf(Skills.class);
    private Set<Characteristics> savingThrowsWithProficiency = EnumSet.noneOf(Characteristics.class);

    private Race race;

    private CharacterClass characterClass;

    private int speed;

    private int hitPoints;
    private int hitDice;
    private int hitDiceTotal = 1;

    private int spellsKnown;
    private List<Integer> spellsKnownPerLevel = new ArrayList<>(Collections.nCopies(10, 0));

    private SpellcastingAbility spellcastingAbility;

    private String featuresAndTraits = "";
    private String equipment = "";
    private String alliesAndOrganizations;
    private String treasure;
    private Set<String> languages = new HashSet<>();
    private String proficiencies;

    private Integer draconicAncestryDamage = null;

    private int gold;
    private int startGoldModifier;
    private int copper;
    private int silver;
    private int electrum;
    private int platinum;

    private int armourClass;

    private Set<String> armourProficiency = new HashSet<>();
    private Set<String> weaponProficiency = new HashSet<>();
    private Set<String> toolProficiency = new HashSet<>();

    @JsonIgnore
    public Integer getSpellcastingAbilityModifier(SpellcastingAbility spellcastingAbility) {
        return switch (spellcastingAbility) {
            case STRENGTH -> getStrengthModifier() + getProficiencyBonus();
            case DEXTERITY -> getDexterityModifier() + getProficiencyBonus();
            case CONSTITUTION -> getConstitutionModifier() + getProficiencyBonus();
            case INTELLIGENCE -> getIntelligenceModifier() + getProficiencyBonus();
            case WISDOM -> getWisdomModifier() + getProficiencyBonus();
            case CHARISMA -> getCharismaModifier() + getProficiencyBonus();
            case null -> null;
        };
    }

    @JsonIgnore
    public Integer getSpellAttackBonus() {
        return getSpellcastingAbilityModifier(spellcastingAbility);
    }

    @JsonIgnore
    public Integer getSpellSaveDC() {
        if (getSpellcastingAbilityModifier(spellcastingAbility) != null) {
            return 8 + getSpellcastingAbilityModifier(spellcastingAbility) + proficiencyBonus;
        } else {
            return null;
        }
    }

    @JsonIgnore
    public int getInitiative() {
        return getDexterityModifier();
    }

    @JsonIgnore
    public int getPassivePerception() {
        return getSkillsWithProficiency().contains(Skills.PERCEPTION) ? 10 + getWisdomModifier() + proficiencyBonus : 10 + getWisdomModifier();
    }

    @JsonIgnore
    public int getCharismaModifier() {
        return (charisma - 10) / 2;
    }

    @JsonIgnore
    public int getWisdomModifier() {
        return (wisdom - 10) / 2;
    }

    @JsonIgnore
    public int getIntelligenceModifier() {
        return (intelligence - 10) / 2;
    }

    @JsonIgnore
    public int getConstitutionModifier() {
        return (constitution - 10) / 2;
    }

    @JsonIgnore
    public int getDexterityModifier() {
        return (dexterity - 10) / 2;
    }

    @JsonIgnore
    public int getStrengthModifier() {
        return (strength - 10) / 2;
    }

    @JsonIgnore
    public int getSavingThrowModifier(Characteristics characteristics) {
        int baseSavingThrowModifier = switch (characteristics) {
            case STRENGTH -> getStrengthModifier();
            case DEXTERITY -> getDexterityModifier();
            case CONSTITUTION -> getConstitutionModifier();
            case INTELLIGENCE -> getIntelligenceModifier();
            case WISDOM -> getWisdomModifier();
            case CHARISMA -> getCharismaModifier();
        };
        return getSavingThrowsWithProficiency().contains(characteristics) ? baseSavingThrowModifier + getProficiencyBonus() : baseSavingThrowModifier;
    }

    @JsonIgnore
    public int getSkillModifier(Skills skill) {
        int baseSkillModifier = switch (skill) {
            case ACROBATICS, STEALTH, SLEIGHT_OF_HAND -> getDexterityModifier();
            case ANIMAL_HANDLING, INSIGHT, MEDICINE, PERCEPTION, SURVIVAL -> getWisdomModifier();
            case ARCANA, HISTORY, INVESTIGATION, NATURE, RELIGION -> getIntelligenceModifier();
            case ATHLETICS -> getStrengthModifier();
            case DECEPTION, INTIMIDATION, PERFORMANCE, PERSUASION -> getCharismaModifier();
        };
        return getSkillsWithProficiency().contains(skill) ? baseSkillModifier + getProficiencyBonus() : baseSkillModifier;
    }

    @JsonIgnore
    public int getHitPoints() {
        return getHitDice() + getConstitutionModifier();
    }

    @JsonIgnore
    public void setSpellsKnownPerLevel(int index, int numberOfSpells) {
        this.spellsKnownPerLevel.set(index, numberOfSpells);
    }
}