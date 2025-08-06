package player.userinputhandler.commands.createnewhero;

import static java.lang.String.format;
import static player.dndcharacter.background.Features.badReputationText;
import static player.dndcharacter.background.Features.shipsPassageText;
import static player.dndcharacter.dndcharacterenums.Background.getAllBackgrounds;
import static player.dndcharacter.dndcharacterenums.CharacterClass.getAllClasses;
import static player.dndcharacter.dndcharacterenums.Race.getAllRaces;
import static player.dndcharacter.dndcharacterenums.Skill.getAllSkills;
import static player.userinputhandler.enums.Alignment.getAllAlignments;
import static player.userinputhandler.enums.ArtisanTool.getAllArtisanTools;

public class OutputTexts {
    public static final String notANumberInput = "Please enter a number";
    public static final String chooseClass = "Choose your class:\n" + getAllClasses();
    public static final String allRaces = getAllRaces();
    public static final String allSkills = getAllSkills();
    public static final String chooseSecondSkill = "Enter the second skill your hero will be proficient in. Available ones:\n";
    public static final String chooseThirdSkill = "Enter the third skill your hero will be proficient in. Available ones:\n";
    public static final String wrongSkill = "Cannot understand your input. Please enter a skill";
    public static final String alreadyHaveProficiencyInThisSkill = "You already have proficiency in this skill. Choose another one";
    public static final String chooseAlignment = "Set your hero's alignment\n" + getAllAlignments();
    public static final String allBackgrounds = getAllBackgrounds() + "Not that ustom editing for the Custom background is not supported yet, but you will be able to edit your character in the exported PDF file)";
    public static final String chooseBackground = "Choose background from the following options:\n" + allBackgrounds;
    public static final String chooseGamingSet = "Enter a gaming set your hero will be proficient with";
    public static final String chooseSecondAbilityScore = "Enter another ability that will be increased by 1";
    public static final String chooseLanguage = "Choose a language your %s will know";
    public static final String chooseFirstLanguage = "Choose the first language your %s will know";
    public static final String chooseSecondLanguage = "Enter the second language your %s will know";
    public static final String wrongInput = "Sorry, I don't understand. Maybe there is a typo?";
    public static final String chooseSkillProficiency = "Enter a skill that your character will be proficient in: " + allSkills;
    public static final String chooseTraits = "Type any personality traits you'd like to mention";
    public static final String chooseArtisanTools = "Choose any artisan's tools your hero will be proficient with\n" + getAllArtisanTools();
    public static final String chooseArtisanToolPossessionWithPreviousStep = "Choose a set of artisan's tools your hero will have. Might be the same as in the previous step";
    public static final String chooseLuckyCharm = "Choose your lucky charm (a rabbit foot, a small stone with a hole in the center, or any other trinket)";
    public static final String chooseMusicalInstrumentProficiency = "Choose a musical instrument your hero will be proficient with";
    public static final String choosePossessionsForGuildMerchantText = "Would you like to start with artisan's tools of your choice or with a mule and a cart?";
    public static final String chooseFeatureForPirate = format("""
            You can choose between two features:
            1. %s2. %sWhich one do you prefer?
            """, shipsPassageText, badReputationText);
}
