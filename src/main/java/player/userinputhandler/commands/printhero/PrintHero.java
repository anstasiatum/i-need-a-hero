package player.userinputhandler.commands.printhero;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import static player.userinputhandler.enums.Processes.PRINT_HERO;
import static player.userinputhandler.enums.Steps.PRINT_PDF;


public class PrintHero {
    final static CharacterDao characterJpaDao = new CharacterDaoImpl();

    public static Response printHero(Long chatId) {
        try {
            List<Character> userCharacters = characterJpaDao.findByChatId(chatId);
            List<String> outputList = userCharacters.stream()
                    .map(character -> String.format("Character ID: %d, Name: %s \n", character.getId(), character.getDndCharacter().getCharacterName()))
                    .toList();

            State newState = new State(PRINT_HERO, PRINT_PDF, null);
            return new Response(newState, "Ok, let's print your hero. First, enter the ID of the character you want to get a PDF version of\n" + outputList);
        } catch (Exception e) {
            State newState = new State(PRINT_HERO, null, null);
            return new Response(newState, "Something went wrong when I was getting the list of your characters");
        }
    }

    public static Response heroPrintingAnswer(State state, String userAnswer, Long chatId) {
        Response response;
        State newState;

        if (state.getStepId() == PRINT_PDF) {
            try {
                File file = printHeroPdf(Integer.valueOf(userAnswer), chatId);
                response = new Response(null, "Here is your PDF. Good luck in your games and may the dice spirits favour you!", file);
            } catch (Exception ex) {
                newState = new State(PRINT_HERO, PRINT_PDF, state.getDndCharacter());
                response = new Response(newState, "Couldn't find a hero with this ID. Please enter an available one.");
            }
        } else {
            newState = new State(PRINT_HERO, PRINT_PDF, state.getDndCharacter());
            response = new Response(newState, "Wrong printing step ID. Redirecting back to the character printing");
        }
        return response;
    }

    public static File printHeroPdf(Integer characterId, Long chatId) {
        Path resourcePath;
        try {
            resourcePath = Paths.get(PrintHero.class.getResource("/DnD_5E_CharacterSheet_FormFillable.pdf").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try (PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(resourcePath))) {
            PDDocumentCatalog docCatalog = document.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            DndCharacter dndCharacter = characterJpaDao.findByCharacterId(characterId, chatId).getDndCharacter();
            fillForm(dndCharacter, acroForm);
            File tempFile = File.createTempFile("DndCharacter-" + dndCharacter.getCharacterName(), ".pdf");
            document.save(tempFile);
            System.out.println(tempFile.getAbsolutePath());
            return tempFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void fillForm(DndCharacter dndCharacter, PDAcroForm acroForm) throws IOException {

        //First page
        PDField characterName = acroForm.getField("CharacterName");
        characterName.setValue(dndCharacter.getCharacterName());

        PDField classLevel = acroForm.getField("ClassLevel");
        classLevel.setValue(String.valueOf(dndCharacter.getCharacterClass()));

        PDField background = acroForm.getField("Background");
        background.setValue(String.valueOf(dndCharacter.getBackground()));

        PDField race = acroForm.getField("Race ");
        race.setValue(String.valueOf(dndCharacter.getRace()));

        PDField alignment = acroForm.getField("Alignment");
        alignment.setValue(dndCharacter.getAlignment());

        PDField strength = acroForm.getField("STR");
        strength.setValue(String.valueOf(dndCharacter.getStrength()));

        PDField strengthModifier = acroForm.getField("STRmod");
        strengthModifier.setValue(String.valueOf(dndCharacter.getStrengthModifier()));

        PDField dexterity = acroForm.getField("DEX");
        dexterity.setValue(String.valueOf(dndCharacter.getDexterity()));

        PDField dexterityModifier = acroForm.getField("DEXmod ");
        dexterityModifier.setValue(String.valueOf(dndCharacter.getDexterityModifier()));

        PDField constitution = acroForm.getField("CON");
        constitution.setValue(String.valueOf(dndCharacter.getConstitution()));

        PDField constitutionModifier = acroForm.getField("CONmod");
        constitutionModifier.setValue(String.valueOf(dndCharacter.getConstitutionModifier()));

        PDField intelligence = acroForm.getField("INT");
        intelligence.setValue(String.valueOf(dndCharacter.getIntelligence()));

        PDField intelligenceModifier = acroForm.getField("INTmod");
        intelligenceModifier.setValue(String.valueOf(dndCharacter.getIntelligenceModifier()));

        PDField wisdom = acroForm.getField("WIS");
        wisdom.setValue(String.valueOf(dndCharacter.getWisdom()));

        PDField wisdomModifier = acroForm.getField("WISmod");
        wisdomModifier.setValue(String.valueOf(dndCharacter.getWisdomModifier()));

        PDField charisma = acroForm.getField("CHA");
        charisma.setValue(String.valueOf(dndCharacter.getCharisma()));

        PDField charismaModifier = acroForm.getField("CHamod");
        charismaModifier.setValue(String.valueOf(dndCharacter.getCharismaModifier()));

        PDField proficiencyBonus = acroForm.getField("ProfBonus");
        proficiencyBonus.setValue(String.valueOf(dndCharacter.getProficiencyBonus()));

        PDField strengthSavingThrow = acroForm.getField("ST Strength");
        strengthSavingThrow.setValue(String.valueOf(dndCharacter.getSavingThrowModifier(STRENGTH)));

        PDField dexteritySavingThrow = acroForm.getField("ST Dexterity");
        dexteritySavingThrow.setValue(String.valueOf(dndCharacter.getSavingThrowModifier(DEXTERITY)));

        PDField constitutionSavingThrow = acroForm.getField("ST Constitution");
        constitutionSavingThrow.setValue(String.valueOf(dndCharacter.getSavingThrowModifier(CONSTITUTION)));

        PDField intelligenceSavingThrow = acroForm.getField("ST Intelligence");
        intelligenceSavingThrow.setValue(String.valueOf(dndCharacter.getSavingThrowModifier(INTELLIGENCE)));

        PDField wisdomSavingThrow = acroForm.getField("ST Wisdom");
        wisdomSavingThrow.setValue(String.valueOf(dndCharacter.getSavingThrowModifier(WISDOM)));

        PDField charismaSavingThrow = acroForm.getField("ST Charisma");
        charismaSavingThrow.setValue(String.valueOf(dndCharacter.getSavingThrowModifier(CHARISMA)));

        PDField strengthSavingThrowCheckBox = acroForm.getField("Check Box 11");
        if (dndCharacter.getSavingThrowsWithProficiency().contains(STRENGTH)) {
            strengthSavingThrowCheckBox.setValue("Yes");
        }

        PDField constitutionSavingThrowCheckBox = acroForm.getField("Check Box 18");
        if (dndCharacter.getSavingThrowsWithProficiency().contains(DEXTERITY)) {
            constitutionSavingThrowCheckBox.setValue("Yes");
        }

        PDField dexteritySavingThrowCheckBox = acroForm.getField("Check Box 19");
        if (dndCharacter.getSavingThrowsWithProficiency().contains(CONSTITUTION)) {
            dexteritySavingThrowCheckBox.setValue("Yes");
        }

        PDField intelligenceSavingThrowCheckBox = acroForm.getField("Check Box 20");
        if (dndCharacter.getSavingThrowsWithProficiency().contains(INTELLIGENCE)) {
            intelligenceSavingThrowCheckBox.setValue("Yes");
        }

        PDField wisdomSavingThrowCheckBox = acroForm.getField("Check Box 21");
        if (dndCharacter.getSavingThrowsWithProficiency().contains(WISDOM)) {
            wisdomSavingThrowCheckBox.setValue("Yes");
        }

        PDField charismaSavingThrowCheckBox = acroForm.getField("Check Box 22");
        if (dndCharacter.getSavingThrowsWithProficiency().contains(CHARISMA)) {
            charismaSavingThrowCheckBox.setValue("Yes");
        }

        PDField acrobatics = acroForm.getField("Acrobatics");
        acrobatics.setValue(String.valueOf(dndCharacter.getSkillModifier(ACROBATICS)));

        PDField animalHandling = acroForm.getField("Animal");
        animalHandling.setValue(String.valueOf(dndCharacter.getSkillModifier(ANIMAL_HANDLING)));

        PDField arcana = acroForm.getField("Arcana");
        arcana.setValue(String.valueOf(dndCharacter.getSkillModifier(ARCANA)));

        PDField athletics = acroForm.getField("Athletics");
        athletics.setValue(String.valueOf(dndCharacter.getSkillModifier(ATHLETICS)));

        PDField deception = acroForm.getField("Deception ");
        deception.setValue(String.valueOf(dndCharacter.getSkillModifier(DECEPTION)));

        PDField history = acroForm.getField("History ");
        history.setValue(String.valueOf(dndCharacter.getSkillModifier(HISTORY)));

        PDField insight = acroForm.getField("Insight");
        insight.setValue(String.valueOf(dndCharacter.getSkillModifier(INSIGHT)));

        PDField intimidation = acroForm.getField("Intimidation");
        intimidation.setValue(String.valueOf(dndCharacter.getSkillModifier(INTIMIDATION)));

        PDField investigation = acroForm.getField("Investigation ");
        investigation.setValue(String.valueOf(dndCharacter.getSkillModifier(INVESTIGATION)));

        PDField medicine = acroForm.getField("Medicine");
        medicine.setValue(String.valueOf(dndCharacter.getSkillModifier(MEDICINE)));

        PDField nature = acroForm.getField("Nature");
        nature.setValue(String.valueOf(dndCharacter.getSkillModifier(NATURE)));

        PDField perception = acroForm.getField("Perception ");
        perception.setValue(String.valueOf(dndCharacter.getSkillModifier(PERCEPTION)));

        PDField performance = acroForm.getField("Performance");
        performance.setValue(String.valueOf(dndCharacter.getSkillModifier(PERFORMANCE)));

        PDField persuasion = acroForm.getField("Persuasion");
        persuasion.setValue(String.valueOf(dndCharacter.getSkillModifier(PERSUASION)));

        PDField religion = acroForm.getField("Religion");
        religion.setValue(String.valueOf(dndCharacter.getSkillModifier(RELIGION)));

        PDField sleightOfHand = acroForm.getField("SleightofHand");
        sleightOfHand.setValue(String.valueOf(dndCharacter.getSkillModifier(SLEIGHT_OF_HAND)));

        PDField stealth = acroForm.getField("Stealth ");
        stealth.setValue(String.valueOf(dndCharacter.getSkillModifier(STEALTH)));

        PDField survival = acroForm.getField("Survival");
        survival.setValue(String.valueOf(dndCharacter.getSkillModifier(SURVIVAL)));

        PDField acrobaticsCheckBox = acroForm.getField("Check Box 23");
        if (dndCharacter.getSkillsWithProficiency().contains(ACROBATICS)) {
            acrobaticsCheckBox.setValue("Yes");
        }

        PDField animalHandlingCheckBox = acroForm.getField("Check Box 24");
        if (dndCharacter.getSkillsWithProficiency().contains(ANIMAL_HANDLING)) {
            animalHandlingCheckBox.setValue("Yes");
        }

        PDField arcanaCheckBox = acroForm.getField("Check Box 25");
        if (dndCharacter.getSkillsWithProficiency().contains(ARCANA)) {
            arcanaCheckBox.setValue("Yes");
        }

        PDField athleticsCheckBox = acroForm.getField("Check Box 26");
        if (dndCharacter.getSkillsWithProficiency().contains(ATHLETICS)) {
            athleticsCheckBox.setValue("Yes");
        }

        PDField deceptionCheckBox = acroForm.getField("Check Box 27");
        if (dndCharacter.getSkillsWithProficiency().contains(DECEPTION)) {
            deceptionCheckBox.setValue("Yes");
        }

        PDField historyCheckBox = acroForm.getField("Check Box 28");
        if (dndCharacter.getSkillsWithProficiency().contains(HISTORY)) {
            historyCheckBox.setValue("Yes");
        }

        PDField insightCheckBox = acroForm.getField("Check Box 29");
        if (dndCharacter.getSkillsWithProficiency().contains(INSIGHT)) {
            insightCheckBox.setValue("Yes");
        }

        PDField intimidationCheckBox = acroForm.getField("Check Box 30");
        if (dndCharacter.getSkillsWithProficiency().contains(INTIMIDATION)) {
            intimidationCheckBox.setValue("Yes");
        }

        PDField investigationCheckBox = acroForm.getField("Check Box 31");
        if (dndCharacter.getSkillsWithProficiency().contains(INVESTIGATION)) {
            investigationCheckBox.setValue("Yes");
        }

        PDField medicineCheckBox = acroForm.getField("Check Box 32");
        if (dndCharacter.getSkillsWithProficiency().contains(MEDICINE)) {
            medicineCheckBox.setValue("Yes");
        }

        PDField natureCheckBox = acroForm.getField("Check Box 33");
        if (dndCharacter.getSkillsWithProficiency().contains(NATURE)) {
            natureCheckBox.setValue("Yes");
        }

        PDField perceptionCheckBox = acroForm.getField("Check Box 34");
        if (dndCharacter.getSkillsWithProficiency().contains(PERCEPTION)) {
            perceptionCheckBox.setValue("Yes");
        }

        PDField performanceCheckBox = acroForm.getField("Check Box 35");
        if (dndCharacter.getSkillsWithProficiency().contains(PERFORMANCE                                                                                                               )) {
            performanceCheckBox.setValue("Yes");
        }

        PDField persuasionCheckBox = acroForm.getField("Check Box 36");
        if (dndCharacter.getSkillsWithProficiency().contains(PERSUASION)) {
            persuasionCheckBox.setValue("Yes");
        }

        PDField religionCheckBox = acroForm.getField("Check Box 37");
        if (dndCharacter.getSkillsWithProficiency().contains(RELIGION)) {
            religionCheckBox.setValue("Yes");
        }

        PDField sleightOfHandCheckBox = acroForm.getField("Check Box 38");
        if (dndCharacter.getSkillsWithProficiency().contains(SLEIGHT_OF_HAND)) {
            sleightOfHandCheckBox.setValue("Yes");
        }

        PDField stealthCheckBox = acroForm.getField("Check Box 39");
        if (dndCharacter.getSkillsWithProficiency().contains(STEALTH)) {
            stealthCheckBox.setValue("Yes");
        }

        PDField survivalCheckBox = acroForm.getField("Check Box 40");
        if (dndCharacter.getSkillsWithProficiency().contains(SURVIVAL)) {
            survivalCheckBox.setValue("Yes");
        }

        PDField passivePerception = acroForm.getField("Passive");
        passivePerception.setValue(String.valueOf(dndCharacter.getPassivePerception()));

        PDField proficienciesAndLanguages = acroForm.getField("ProficienciesLang");
        if (dndCharacter.getToolProficiency().isEmpty()) {
            proficienciesAndLanguages.setValue(dndCharacter.getLanguages().toString());
        } else {
            proficienciesAndLanguages.setValue(dndCharacter.getLanguages().toString() + "\n" + dndCharacter.getToolProficiency().toString());
        }

        PDField armorClass = acroForm.getField("AC");
        armorClass.setValue(String.valueOf(dndCharacter.getArmourClass()));

        PDField initiative = acroForm.getField("Initiative");
        initiative.setValue(String.valueOf(dndCharacter.getInitiative()));

        PDField speed = acroForm.getField("Speed");
        speed.setValue(String.valueOf(dndCharacter.getSpeed()));

        PDField maxHitPoints = acroForm.getField("HPMax");
        maxHitPoints.setValue(String.valueOf(dndCharacter.getHitPoints()));

        PDField field = acroForm.getField("HDTotal");
        field.setValue(String.valueOf(dndCharacter.getHitDiceTotal()));

        PDField hitDice = acroForm.getField("HD");
        hitDice.setValue(String.valueOf(dndCharacter.getHitDice()));

        PDField gold = acroForm.getField("GP");
        gold.setValue(String.valueOf(dndCharacter.getGold()));

        PDField equipment = acroForm.getField("Equipment");
        equipment.setValue(String.valueOf(dndCharacter.getEquipment()));

        PDField traits = acroForm.getField("PersonalityTraits ");
        traits.setValue(dndCharacter.getPersonalityTraits());

        PDField ideals = acroForm.getField("Ideals");
        ideals.setValue(dndCharacter.getIdeals());

        PDField bonds = acroForm.getField("Bonds");
        bonds.setValue(dndCharacter.getBonds());

        PDField flaws = acroForm.getField("Flaws");
        flaws.setValue(dndCharacter.getFlaws());

        PDField featuresAndTraits = acroForm.getField("Features and Traits");
        featuresAndTraits.setValue(dndCharacter.getFeaturesAndTraits());

        // Second page
        PDField characterNameTwo = acroForm.getField("CharacterName 2");
        characterNameTwo.setValue(dndCharacter.getCharacterName());

        PDField age = acroForm.getField("Age");
        age.setValue(dndCharacter.getAge());

        PDField height = acroForm.getField("Height");
        height.setValue(dndCharacter.getHeight());

        PDField weight = acroForm.getField("Weight");
        weight.setValue(dndCharacter.getWeight());

        PDField eyes = acroForm.getField("Eyes");
        eyes.setValue(dndCharacter.getEyes());

        PDField skin = acroForm.getField("Skin");
        skin.setValue(dndCharacter.getSkin());

        PDField hair = acroForm.getField("Hair");
        hair.setValue(dndCharacter.getHair());

        PDField alliesAndOrganizations = acroForm.getField("Allies");
        alliesAndOrganizations.setValue(dndCharacter.getAlliesAndOrganizations());

        PDField backstory = acroForm.getField("Backstory");
        backstory.setValue(dndCharacter.getBackstory());

        PDField treasure = acroForm.getField("Treasure");
        treasure.setValue(dndCharacter.getTreasure());

        // Third page
        PDField spellcastingClass = acroForm.getField("Spellcasting Class 2");
        spellcastingClass.setValue(String.valueOf(dndCharacter.getCharacterClass()));

        PDField spellcastingAbility = acroForm.getField("SpellcastingAbility 2");
        if (dndCharacter.getSpellcastingAbility() != null) {
            spellcastingAbility.setValue(String.valueOf(dndCharacter.getSpellcastingAbility()));
        }

        PDField spellSaveDc = acroForm.getField("SpellSaveDC  2");
        if (dndCharacter.getSpellSaveDC() != null) {
            spellSaveDc.setValue(String.valueOf(dndCharacter.getSpellSaveDC()));
        }

        PDField spellAttackBonus = acroForm.getField("SpellAtkBonus 2");
        if (dndCharacter.getSpellAttackBonus() != null) {
            spellAttackBonus.setValue(String.valueOf(dndCharacter.getSpellAttackBonus()));
        }
    }
}

