package player.userinputhandler.commands.createnewhero;

public class OutputTexts {
    public static String notANumberInput = "Please enter a number";
    public static String allClasses = """
            Barbarian
            Bard
            Cleric
            Druid
            Fighter
            Monk
            Paladin
            Ranger
            Rogue
            Sorcerer
            Warlock
            Wizard""";
    public static String chooseClass = "Choose your class:\n" + allClasses;
    public static String allRaces = """
            Dragonborn
            Hill Dwarf
            Mountain Dwarf
            Dark Elf
            High Elf
            Wood Elf
            Forest Gnome
            Rock Gnome
            Half Elf
            Lightfoot Halfling
            Stout Halfling
            Half Orc
            Base Human
            Variant Human
            Tiefling
            """;
    public static String allSkills = """
            Survival
            Stealth
            Sleight of hand
            Religion
            Persuasion
            Performance
            Perception
            Nature
            Medicine
            Investigation
            Intimidation
            Insight
            History
            Deception
            Athletics
            Arcana
            Animal handling
            Acrobatics
            """;
    public static String chooseSecondSkill = "Enter the second skill your hero will be proficient in. Available ones:\n";
    public static String chooseThirdSkill = "Enter the third skill your hero will be proficient in. Available ones:\n";
    public static String wrongSkill = "Cannot understand your input. Please enter a skill\n" + allSkills;
    public static String chooseAlignment = """
            Set your hero's alignment:
            Lawful good
            Neutral good
            Chaotic good
            Lawful neutral
            True neutral
            Chaotic neutral
            Lawful evil
            Neutral evil
            Chaotic evil
            """;
    public static String allBackgrounds = """
            Acolyte,
            Charlatan,
            Criminal,
            Entertainer,
            Folk Hero,
            Guild Artisan,
            Guild Merchant,
            Hermit,
            Noble,
            Outlander,
            Sage,
            Sailor,
            Soldier,
            Urchin,
            Custom (custom editing is not supported yet, but you will be able to edit your character in the exported PDF file)
            """;
    public static String chooseSecondAbilityScore = "Enter another ability that will be increased by 1 (Strength, Wisdom etc)";
    public static String wrongInput = "Sorry, I don't understand. Maybe there is a typo?";
    public static String chooseSkillProficiency = "Enter a skill that your character will be proficient in: " + allSkills;
    public static String chooseTraits = "Type any personality traits you'd like to mention";
    public static String allArtisansTools = """
            Alchemist's supplies
            Brewer's supplies
            Calligrapher's supplies
            Carpenter's tools
            Cartographer's tools
            Cobbler's tools
            Cook's utensils
            Glassblower's tools
            Jeweler's tools
            Leatherworker's tools
            Mason's tools
            Painter's supplies
            Potter's tools
            Smith's tools
            Tinker's tools
            Weaver's tools
            Woodcarver's tools
            """;
}
