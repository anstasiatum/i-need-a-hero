package heroprintingtests;

import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import static heroprintingtests.helper.ReturnAcroForm.returnAcroForm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Characteristics.CHARISMA;
import static player.dndcharacter.dndcharacterenums.Characteristics.CONSTITUTION;
import static player.dndcharacter.dndcharacterenums.Characteristics.DEXTERITY;
import static player.dndcharacter.dndcharacterenums.Characteristics.INTELLIGENCE;
import static player.dndcharacter.dndcharacterenums.Characteristics.STRENGTH;
import static player.dndcharacter.dndcharacterenums.Characteristics.WISDOM;
import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skills.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skills.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skills.INTIMIDATION;
import static player.dndcharacter.dndcharacterenums.Skills.INVESTIGATION;
import static player.dndcharacter.dndcharacterenums.Skills.MEDICINE;
import static player.dndcharacter.dndcharacterenums.Skills.NATURE;
import static player.dndcharacter.dndcharacterenums.Skills.PERCEPTION;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;
import static player.dndcharacter.dndcharacterenums.Skills.RELIGION;
import static player.dndcharacter.dndcharacterenums.Skills.SLEIGHT_OF_HAND;
import static player.dndcharacter.dndcharacterenums.Skills.STEALTH;
import static player.dndcharacter.dndcharacterenums.Skills.SURVIVAL;
import static testdata.TestData.getMockCharacterAna;
import static testdata.TestData.getMockCharacterHanzo;
import static testdata.TestData.getMockCharacterZarya;

public class FillFormTest {
    DndCharacter mockAnaAmari = getMockCharacterAna().getDndCharacter();
    DndCharacter mockHanzoShimada = getMockCharacterHanzo().getDndCharacter();
    DndCharacter mockZarya = getMockCharacterZarya().getDndCharacter();

    @Test
    @DisplayName("PDF filling: Character name")
    void PDFFillingCharacterName() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getCharacterName(), acroForm.getField("CharacterName").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character class")
    void PDFFillingCharacterClass() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Bard", acroForm.getField("ClassLevel").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character race")
    void PDFFillingCharacterRace() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Tiefling", acroForm.getField("Race ").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character alignment")
    void PDFFillingCharacterAlignment() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getAlignment(), acroForm.getField("Alignment").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character strength")
    void PDFFillingCharacterStrength() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getStrength()), acroForm.getField("STR").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character strength modifier")
    void PDFFillingCharacterStrengthModifier() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getStrengthModifier()), acroForm.getField("STRmod").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character dexterity")
    void PDFFillingCharacterDexterity() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getDexterity()), acroForm.getField("DEX").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character dexterity modifier")
    void PDFFillingCharacterDexterityModifier() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getDexterityModifier()), acroForm.getField("DEXmod ").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character constitution")
    void PDFFillingCharacterConstitution() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getConstitution()), acroForm.getField("CON").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character dexterity modifier")
    void PDFFillingCharacterConstitutionModifier() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getConstitutionModifier()), acroForm.getField("CONmod").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character intelligence")
    void PDFFillingCharacterIntelligence() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getIntelligence()), acroForm.getField("INT").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character intelligence modifier")
    void PDFFillingCharacterIntelligenceModifier() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getIntelligenceModifier()), acroForm.getField("INTmod").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character wisdom")
    void PDFFillingCharacterWisdom() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getWisdom()), acroForm.getField("WIS").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character wisdom modifier")
    void PDFFillingCharacterWisdomModifier() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getWisdomModifier()), acroForm.getField("WISmod").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character charisma")
    void PDFFillingCharacterCharisma() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getCharisma()), acroForm.getField("CHA").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character charisma modifier")
    void PDFFillingCharacterCharismaModifier() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getCharismaModifier()), acroForm.getField("CHamod").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character proficiency bonus")
    void PDFFillingCharacterProficiencyBonus() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getProficiencyBonus()), acroForm.getField("ProfBonus").getValueAsString());
    }


    @Test
    @DisplayName("PDF filling: Character strength saving throw")
    void PDFFillingCharacterStrengthSavingThrow() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSavingThrowModifier(STRENGTH)), acroForm.getField("ST Strength").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character dexterity saving throw")
    void PDFFillingCharacterDexteritySavingThrow() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSavingThrowModifier(DEXTERITY)), acroForm.getField("ST Dexterity").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character constitution saving throw")
    void PDFFillingCharacterConstitutionSavingThrow() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSavingThrowModifier(CONSTITUTION)), acroForm.getField("ST Constitution").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character intelligence saving throw")
    void PDFFillingCharacterIntelligenceSavingThrow() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSavingThrowModifier(INTELLIGENCE)), acroForm.getField("ST Intelligence").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character wisdom saving throw")
    void PDFFillingCharacterWisdomSavingThrow() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSavingThrowModifier(WISDOM)), acroForm.getField("ST Wisdom").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character charisma saving throw")
    void PDFFillingCharacterCharismaSavingThrow() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSavingThrowModifier(CHARISMA)), acroForm.getField("ST Charisma").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character strength saving throw checkbox not set")
    void PDFFillingCharacterStrengthSavingThrowCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 11").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character dexterity saving throw checkbox not set")
    void PDFFillingCharacterDexteritySavingThrowCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 18").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character constitution saving throw checkbox not set")
    void PDFFillingCharacterConstitutionSavingThrowCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 19").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character intelligence saving throw checkbox not set")
    void PDFFillingCharacterIntelligenceSavingThrowCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 20").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character wisdom saving throw checkbox not set")
    void PDFFillingCharacterWisdomSavingThrowCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 21").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character charisma saving throw checkbox not set")
    void PDFFillingCharacterCharismaSavingThrowCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 22").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character strength saving throw checkbox set")
    void PDFFillingCharacterStrengthSavingThrowCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 11").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character dexterity saving throw checkbox set")
    void PDFFillingCharacterDexteritySavingThrowCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 18").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character constitution saving throw checkbox set")
    void PDFFillingCharacterConstitutionSavingThrowCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 19").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character intelligence saving throw checkbox set")
    void PDFFillingCharacterIntelligenceSavingThrowCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 20").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character wisdom saving throw checkbox set")
    void PDFFillingCharacterWisdomSavingThrowCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 21").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character charisma saving throw checkbox set")
    void PDFFillingCharacterCharismaSavingThrowCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 22").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character acrobatics")
    void PDFFillingCharacterAcrobatics() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(ACROBATICS)), acroForm.getField("Acrobatics").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character animal handling")
    void PDFFillingCharacterAnimalHandling() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(ANIMAL_HANDLING)), acroForm.getField("Animal").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character arcana")
    void PDFFillingCharacterArcana() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(ARCANA)), acroForm.getField("Arcana").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character athletics")
    void PDFFillingCharacterAthletics() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(ATHLETICS)), acroForm.getField("Athletics").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character deception")
    void PDFFillingCharacterDeception() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(DECEPTION)), acroForm.getField("Deception ").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character history")
    void PDFFillingCharacterHistory() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(HISTORY)), acroForm.getField("History ").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character insight")
    void PDFFillingCharacterInsight() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(INSIGHT)), acroForm.getField("Insight").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character intimidation")
    void PDFFillingCharacterIntimidation() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(INTIMIDATION)), acroForm.getField("Intimidation").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character investigation")
    void PDFFillingCharacterInvestigation() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(INVESTIGATION)), acroForm.getField("Investigation ").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character medicine")
    void PDFFillingCharacterMedicine() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(MEDICINE)), acroForm.getField("Medicine").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character nature")
    void PDFFillingCharacterNature() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(NATURE)), acroForm.getField("Nature").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character perception")
    void PDFFillingCharacterPerception() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(PERCEPTION)), acroForm.getField("Perception ").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character performance")
    void PDFFillingCharacterPerformance() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(PERFORMANCE)), acroForm.getField("Performance").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character persuasion")
    void PDFFillingCharacterPersuasion() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(PERSUASION)), acroForm.getField("Persuasion").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character religion")
    void PDFFillingCharacterReligion() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(RELIGION)), acroForm.getField("Religion").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character sleight of hand")
    void PDFFillingCharacterSleightOfHand() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(SLEIGHT_OF_HAND)), acroForm.getField("SleightofHand").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character stealth")
    void PDFFillingCharacterStealth() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(STEALTH)), acroForm.getField("Stealth ").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character survival")
    void PDFFillingCharacterSurvival() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSkillModifier(SURVIVAL)), acroForm.getField("Survival").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character acrobatics check box not set")
    void PDFFillingCharacterAcrobaticsCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 23").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character animal handling check box not set")
    void PDFFillingCharacterAnimalHandlingCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 24").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character arcana check box not set")
    void PDFFillingCharacterArcanaCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 25").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character athletics check box not set")
    void PDFFillingCharacterAthleticsCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 26").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character deception check box not set")
    void PDFFillingCharacterDeceptionCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 27").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character history check box not set")
    void PDFFillingCharacterHistoryCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 28").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character insight check box not set")
    void PDFFillingCharacterInsightCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 29").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character intimidation check box not set")
    void PDFFillingCharacterIntimidationCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 30").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character investigation check box not set")
    void PDFFillingCharacterInvestigationCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 31").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character medicine check box not set")
    void PDFFillingCharacterMedicineCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 32").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character nature check box not set")
    void PDFFillingCharacterNatureCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 33").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character perception check box not set")
    void PDFFillingCharacterPerceptionCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 34").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character performance check box not set")
    void PDFFillingCharacterPerformanceCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 35").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character persuasion check box not set")
    void PDFFillingCharacterPersuasionCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 36").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character religion check box not set")
    void PDFFillingCharacterReligionCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 37").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character sleight of hand check box not set")
    void PDFFillingCharacterSleightOfHandCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 38").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character stealth check box not set")
    void PDFFillingCharacterStealthCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 39").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character survival check box not set")
    void PDFFillingCharacterSurvivalCheckBoxNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Off", acroForm.getField("Check Box 40").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character acrobatics check box set")
    void PDFFillingCharacterAcrobaticsCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 23").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character animal handling check box set")
    void PDFFillingCharacterAnimalHandlingCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 24").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character arcana check box set")
    void PDFFillingCharacterArcanaCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 25").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character athletics check box set")
    void PDFFillingCharacterAthleticsCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 26").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character deception check box set")
    void PDFFillingCharacterDeceptionCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 27").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character history check box set")
    void PDFFillingCharacterHistoryCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 28").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character insight check box set")
    void PDFFillingCharacterInsightCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 29").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character intimidation check box set")
    void PDFFillingCharacterIntimidationCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 30").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character investigation check box set")
    void PDFFillingCharacterInvestigationCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 31").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character medicine check box set")
    void PDFFillingCharacterMedicineCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 32").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character nature check box set")
    void PDFFillingCharacterNatureCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 33").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character perception check box set")
    void PDFFillingCharacterPerceptionCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 34").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character performance check box set")
    void PDFFillingCharacterPerformanceCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 35").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character persuasion check box set")
    void PDFFillingCharacterPersuasionCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 36").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character religion check box set")
    void PDFFillingCharacterReligionCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 37").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character sleight of hand check box set")
    void PDFFillingCharacterSleightOfHandCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 38").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character stealth check box set")
    void PDFFillingCharacterStealthCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 39").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character survival check box set")
    void PDFFillingCharacterSurvivalCheckBoxSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("Yes", acroForm.getField("Check Box 40").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character passive perception")
    void PDFFillingCharacterPassivePerception() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getPassivePerception()), acroForm.getField("Passive").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character languages without tool proficiencies")
    void PDFFillingCharacterLanguagesWithTools() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getLanguages()), acroForm.getField("ProficienciesLang").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character languages with tool proficiencies")
    void PDFFillingCharacterLanguagesWithoutTools() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        String expectedResult = mockHanzoShimada.getLanguages().toString() + "\n" + mockHanzoShimada.getToolProficiency().toString();

        assertEquals(expectedResult, acroForm.getField("ProficienciesLang").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character AC")
    void PDFFillingCharacterAC() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getArmourClass()), acroForm.getField("AC").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character initiative")
    void PDFFillingCharacterInitiative() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getInitiative()), acroForm.getField("Initiative").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character speed")
    void PDFFillingCharacterSpeed() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getSpeed()), acroForm.getField("Speed").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character max HP")
    void PDFFillingCharacterMaxHP() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getHitPoints()), acroForm.getField("HPMax").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character hit dice total")
    void PDFFillingCharacterHitDiceTotal() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getHitDiceTotal()), acroForm.getField("HDTotal").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character hit dice")
    void PDFFillingCharacterHitDice() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getHitDice()), acroForm.getField("HD").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character gold")
    void PDFFillingCharacterGold() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(String.valueOf(mockAnaAmari.getGold()), acroForm.getField("GP").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character equipment")
    void PDFFillingCharacterEquipment() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getEquipment(), acroForm.getField("Equipment").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character traits")
    void PDFFillingCharacterTraits() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getPersonalityTraits(), acroForm.getField("PersonalityTraits ").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character ideals")
    void PDFFillingCharacterIdeals() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getIdeals(), acroForm.getField("Ideals").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character bonds")
    void PDFFillingCharacterBonds() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getBonds(), acroForm.getField("Bonds").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character flaws")
    void PDFFillingCharacterFlaws() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getFlaws(), acroForm.getField("Flaws").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character features and traits")
    void PDFFillingCharacterFeaturesAndTraits() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getFeaturesAndTraits(), acroForm.getField("Features and Traits").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character name on the second page")
    void PDFFillingCharacterNameSecondPage() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getCharacterName(), acroForm.getField("CharacterName 2").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character age")
    void PDFFillingCharacterAge() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getAge(), acroForm.getField("Age").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character height")
    void PDFFillingCharacterHeight() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getHeight(), acroForm.getField("Height").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character weight")
    void PDFFillingCharacterWeight() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getWeight(), acroForm.getField("Weight").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character eyes")
    void PDFFillingCharacterEyes() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getEyes(), acroForm.getField("Eyes").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character skin")
    void PDFFillingCharacterSkin() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getSkin(), acroForm.getField("Skin").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character hair")
    void PDFFillingCharacterHair() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getHair(), acroForm.getField("Hair").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character allies")
    void PDFFillingCharacterAlies() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getAlliesAndOrganizations(), acroForm.getField("Allies").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character backstory")
    void PDFFillingCharacterBackstory() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getBackstory(), acroForm.getField("Backstory").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character treasure")
    void PDFFillingCharacterTreasure() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getTreasure(), acroForm.getField("Treasure").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character spellcasting class")
    void PDFFillingCharacterSpellcastingClass() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Bard", acroForm.getField("Spellcasting Class 2").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character spellcasting ability when set")
    void PDFFillingCharacterSpellcastingAbilitySet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Wisdom", acroForm.getField("SpellcastingAbility 2").getValueAsString());
    }


    @Test
    @DisplayName("PDF filling: Character spellcasting ability when not set")
    void PDFFillingCharacterSpellcastingAbilityNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("", acroForm.getField("SpellcastingAbility 2").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character spell save DC set")
    void PDFFillingCharacterSpellSaveDCSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals("Wisdom", acroForm.getField("SpellcastingAbility 2").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character spell save DC not set")
    void PDFFillingCharacterSpellSaveDCNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("", acroForm.getField("SpellcastingAbility 2").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character spell attack bonus set")
    void PDFFillingCharacterSpellAttackBonusSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterAna());
        assertEquals(mockAnaAmari.getSpellAttackBonus().toString(), acroForm.getField("SpellAtkBonus 2").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Character spell attack bonus not set")
    void PDFFillingCharacterSpellAttackBonusNotSet() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterHanzo());
        assertEquals("", acroForm.getField("SpellAtkBonus 2").getValueAsString());
    }

    @Test
    @DisplayName("PDF filling: Cyrillic characters")
    void PDFFillingCyrillicCharacters() {
        PDAcroForm acroForm = returnAcroForm(getMockCharacterZarya());
        assertEquals(mockZarya.getCharacterName(), acroForm.getField("CharacterName").getValueAsString());
    }
}
