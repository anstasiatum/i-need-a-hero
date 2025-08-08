package herocreationtests.createnewhero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundFactory;
import player.dndcharacter.characterclass.CharacterClassFactory;
import player.dndcharacter.characteristicsgenerator.BaseCharacteristicsValuesGenerator;
import player.dndcharacter.race.RaceFactory;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.AddSkill;
import player.userinputhandler.commands.createnewhero.ChooseCharacteristicsSettingMethod;
import player.userinputhandler.commands.createnewhero.CreateNewHero;
import player.userinputhandler.commands.createnewhero.SelectClass;
import player.userinputhandler.commands.createnewhero.SelectRace;
import player.userinputhandler.commands.createnewhero.backgroundoptions.ChoosePossessionsForGuildMerchant;
import player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseProficiencyForGuildMerchant;
import player.userinputhandler.commands.createnewhero.backgroundoptions.SetBackground;
import player.userinputhandler.commands.createnewhero.backgroundoptions.SetPirateFeature;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncreaseBaseCharacteristics;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncrementAbility;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;
import player.userinputhandler.enums.Steps;

import java.util.Set;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static player.dndcharacter.dndcharacterenums.Background.ACOLYTE;
import static player.dndcharacter.dndcharacterenums.Background.SAGE;
import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBackgroundOptions;
import static player.userinputhandler.commands.createnewhero.Options.getGamingSetOptions;
import static player.userinputhandler.commands.createnewhero.Options.getPirateFeatureOptions;
import static player.userinputhandler.commands.createnewhero.Options.getPossessionsForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.Options.getProficienciesForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.allBackgrounds;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseArtisanToolPossessionWithPreviousStep;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseArtisanTools;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseFeatureForPirate;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseMusicalInstrumentProficiency;
import static player.userinputhandler.commands.createnewhero.OutputTexts.choosePossessionsForGuildMerchantText;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseSecondLanguage;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseTraits;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ADDITIONAL_LANGUAGE_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_FOLK_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISAN_TOOL_PROFICIENCY_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_BACKGROUND;
import static player.userinputhandler.enums.Steps.CHOOSE_FEATURE_FOR_PIRATE;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_LANGUAGE_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_FOR_NOBLE_AND_KNIGHT;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_HERMIT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT;
import static player.userinputhandler.enums.Steps.CHOOSE_LUCKY_CHARM_FOR_PIRATE;
import static player.userinputhandler.enums.Steps.CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_PRAYER_ITEM_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_LANGUAGE_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_TROPHY_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.ENTER_ALIGNMENT;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

public class HeroCreationAnswerAlignmentAndBackstoryTest {
    private final CharacterDao characterJpaDao = new CharacterDaoImpl();
    private final BaseCharacteristicsValuesGenerator baseCharacteristicsValuesGenerator = new BaseCharacteristicsValuesGenerator();
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod = new ChooseCharacteristicsSettingMethod(baseCharacteristicsValuesGenerator);
    private final IncrementAbility incrementAbility = new IncrementAbility();
    private final IncreaseBaseCharacteristics increaseBaseCharacteristics = new IncreaseBaseCharacteristics(incrementAbility);
    private final RaceFactory raceFactory = new RaceFactory();
    private final SelectRace selectRace = new SelectRace(raceFactory);
    private final AddSkill addSkill = new AddSkill();
    private final CharacterClassFactory characterClassFactory = new CharacterClassFactory();
    private final SelectClass selectClass = new SelectClass(characterClassFactory);
    private final BackgroundFactory backgroundFactory = new BackgroundFactory();
    private final SetBackground setBackgroundSpy = spy(new SetBackground(backgroundFactory));
    private final SetPirateFeature setPirateFeatureSpy = spy(new SetPirateFeature());
    private final ChoosePossessionsForGuildMerchant choosePossessionsForGuildMerchantSpy = spy(new ChoosePossessionsForGuildMerchant());
    private final ChooseProficiencyForGuildMerchant chooseProficiencyForGuildMerchantSpy = spy(new ChooseProficiencyForGuildMerchant());
    private final CreateNewHero createNewHero = new CreateNewHero(characterJpaDao, characteristicsSettingMethod, increaseBaseCharacteristics, selectRace, addSkill, selectClass, setBackgroundSpy, setPirateFeatureSpy, choosePossessionsForGuildMerchantSpy, chooseProficiencyForGuildMerchantSpy);
    private State incomingState;
    private Response expectedResponse;
    private Response actualResponse;
    private State expectedState;
    private DndCharacter dndCharacter = new DndCharacter();
    private final Long chatID = 123L;
    private String userAnswer;

    @Test
    @DisplayName("ENTER_ALIGNMENT -> CHOOSE_BACKGROUND")
    void heroCreationAnswer_chooseFirstSkillForBarbarian() {
        incomingState = new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_BACKGROUND, dndCharacter), "Choose the background of your hero\n" + allBackgrounds, getBackgroundOptions());
        userAnswer = "chaotic-good";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getAlignment());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_BACKGROUND starts setBackground()")
    void heroCreationAnswer_chooseBackground() {
        incomingState = new State(CREATE_HERO, CHOOSE_BACKGROUND, dndCharacter);
        userAnswer = "Acolyte";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        verify(setBackgroundSpy, times(1)).setBackground(dndCharacter, userAnswer);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getState().getStepId());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE -> CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE")
    void heroCreationAnswer_chooseFirstLanguageForAcolyte() {
        incomingState = new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE, dndCharacter);
        dndCharacter.getLanguages().add("Common");
        dndCharacter.setBackground(ACOLYTE);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE, dndCharacter), format(chooseSecondLanguage, "acolyte"));
        userAnswer = "Infernal";
        Set<String> expectedLanguages = Set.of("Common", "Infernal");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE -> CHOOSE_PRAYER_ITEM_FOR_ACOLYTE")
    void heroCreationAnswer_chooseSecondLanguageForAcolyte() {
        incomingState = new State(CREATE_HERO, CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE, dndCharacter);
        dndCharacter.getLanguages().add("Common");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_PRAYER_ITEM_FOR_ACOLYTE, dndCharacter), "Enter your prayer item (a book, a wheel, etc.) your hero will possess");
        userAnswer = "Infernal";
        Set<String> expectedLanguages = Set.of("Common", "Infernal");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }

    @ParameterizedTest(name = "{0} -> SET_PERSONALITY_TRAITS")
    @EnumSource(value = Steps.class, names = {
            "CHOOSE_PRAYER_ITEM_FOR_ACOLYTE",
            "CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_FOLK_HERO",
            "CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER",
            "CHOOSE_LUCKY_CHARM_FOR_SAILOR",
            "CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_GUILD_ARTISAN",
            "CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT",
            "CHOOSE_CON_FOR_CHARLATAN",
            "CHOOSE_WEAPON_FOR_GLADIATOR",
            "CHOOSE_MUSICAL_INSTRUMENT_YOU_POSSESS_FOR_ENTERTAINER"
    })
    void heroCreationAnswer_AddEquipmentAndProceedToChooseTraits(Steps step) {
        dndCharacter.setEquipment("Backpack");
        userAnswer = "Holy Symbol";
        expectedResponse = new Response(new State (CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);

        incomingState = new State(CREATE_HERO, step, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals("Backpack Holy Symbol", dndCharacter.getEquipment());
        assertEquals(expectedResponse, actualResponse);
    }

    @ParameterizedTest(name = "{0} -> SET_PERSONALITY_TRAITS")
    @EnumSource(value = Steps.class, names = {
            "CHOOSE_GAMING_SET_FOR_CRIMINAL",
            "CHOOSE_GAMING_SET_FOR_NOBLE_AND_KNIGHT",
            "CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER",
            "CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_OUTLANDER",
            "CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_SAGE"
    })
    void heroCreationAnswer_AddToolProficiencyAndProceedToChooseTraits(Steps step) {
        String userAnswer = "Violin";
        dndCharacter.getToolProficiency().add("Flute");
        expectedResponse = new Response(new State (CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);
        Set<String> expectedToolProficiencies = Set.of("Violin", "Flute");

        incomingState = new State(CREATE_HERO, step, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedToolProficiencies, dndCharacter.getToolProficiency());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO -> CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_FOLK_HERO")
    void heroCreationAnswer_chooseArtisansToolProficiencyForFolkHero() {
        incomingState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO, dndCharacter);
        dndCharacter.getToolProficiency().add("Alchemist's supplies");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_FOLK_HERO, dndCharacter), chooseArtisanToolPossessionWithPreviousStep, getArtisanToolOptions());
        userAnswer = "Tinker's tools";
        Set<String> expectedToolProficiencies = Set.of("Alchemist's supplies", "Tinker's tools");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedToolProficiencies, dndCharacter.getToolProficiency());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_LANGUAGE_FOR_HERMIT -> SET_PERSONALITY_TRAITS")
    void heroCreationAnswer_chooseLanguageForHermit() {
        incomingState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HERMIT, dndCharacter);
        dndCharacter.getLanguages().add("Common");
        expectedResponse = new Response(new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);
        userAnswer = "Infernal";
        Set<String> expectedLanguages = Set.of("Common", "Infernal");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT -> CHOOSE_GAMING_SET_FOR_NOBLE_AND_KNIGHT")
    void heroCreationAnswer_chooseLanguageForNobleAndKnight() {
        incomingState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT, dndCharacter);
        dndCharacter.getLanguages().add("Common");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_GAMING_SET_FOR_NOBLE_AND_KNIGHT, dndCharacter), "Enter a gaming set your hero will be proficient with");
        userAnswer = "Infernal";
        Set<String> expectedLanguages = Set.of("Common", "Infernal");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_FIRST_LANGUAGE_FOR_SAGE -> CHOOSE_SECOND_LANGUAGE_FOR_SAGE")
    void heroCreationAnswer_chooseFirstLanguageForSage() {
        incomingState = new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_SAGE, dndCharacter);
        dndCharacter.getLanguages().add("Common");
        dndCharacter.setBackground(SAGE);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_SECOND_LANGUAGE_FOR_SAGE, dndCharacter), format(chooseSecondLanguage, "sage"));
        userAnswer = "Infernal";
        Set<String> expectedLanguages = Set.of("Common", "Infernal");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_SECOND_LANGUAGE_FOR_SAGE -> CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_SAGE")
    void heroCreationAnswer_chooseSecondLanguageForSage() {
        incomingState = new State(CREATE_HERO, CHOOSE_SECOND_LANGUAGE_FOR_SAGE, dndCharacter);
        dndCharacter.getLanguages().add("Common");
        dndCharacter.setBackground(SAGE);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_SAGE, dndCharacter), chooseMusicalInstrumentProficiency);
        userAnswer = "Infernal";
        Set<String> expectedLanguages = Set.of("Common", "Infernal");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER -> CHOOSE_TROPHY_FOR_SOLDIER")
    void heroCreationAnswer_chooseGamingSetProficiencyForSoldier() {
        incomingState = new State(CREATE_HERO, CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER, dndCharacter);
        dndCharacter.getToolProficiency().add("Dice");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_TROPHY_FOR_SOLDIER, dndCharacter), "Describe a trophy taken from a fallen enemy (e.g. a dagger, broken blade, or piece of a banner) your hero will possess");
        userAnswer = "Cards";
        Set<String> expectedToolProficiencies = Set.of("Dice", "Cards");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedToolProficiencies, dndCharacter.getToolProficiency());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_LUCKY_CHARM_FOR_PIRATE -> CHOOSE_FEATURE_FOR_PIRATE")
    void heroCreationAnswer_chooseLuckyCharmForPirate() {
        incomingState = new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_PIRATE, dndCharacter);
        dndCharacter.setEquipment("Pearl of power");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_FEATURE_FOR_PIRATE, dndCharacter), chooseFeatureForPirate, getPirateFeatureOptions());
        userAnswer = "Rod of the Pact Keeper";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals("Pearl of power Rod of the Pact Keeper", dndCharacter.getEquipment());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_FEATURE_FOR_PIRATE starts setPirateFeature()")
    void heroCreationAnswer_chooseFeatureForPirate() {
        incomingState = new State(CREATE_HERO, CHOOSE_FEATURE_FOR_PIRATE, dndCharacter);
        userAnswer = "ship's passage";
        expectedState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        verify(setPirateFeatureSpy, times(1)).setPirateFeature(userAnswer, dndCharacter);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getState().getStepId());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_TROPHY_FOR_SOLDIER -> CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER")
    void heroCreationAnswer_chooseTrophyForSoldier() {
        incomingState = new State(CREATE_HERO, CHOOSE_TROPHY_FOR_SOLDIER, dndCharacter);
        dndCharacter.setEquipment("Books");
        userAnswer = "Gems";
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER, dndCharacter), "Will your character possess a set of bone dice or a deck of cards?", getGamingSetOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals("Books Gems", dndCharacter.getEquipment());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT -> CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT")
    void heroCreationAnswer_chooseLanguageForGuildMerchant() {
        incomingState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT, dndCharacter);
        dndCharacter.getLanguages().add("Celestial");
        userAnswer = "Elvish";
        Set<String> expectedLanguages = Set.of("Celestial", "Elvish");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT, dndCharacter), "Would you like for your hero to learn an additional language, be proficient in navigator's tools or in one type of artisan's tools?", getProficienciesForGuildMerchantOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN -> CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_GUILD_ARTISAN")
    void heroCreationAnswer_chooseLanguageForGuildArtisan() {
        incomingState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN, dndCharacter);
        dndCharacter.getLanguages().add("Celestial");
        userAnswer = "Elvish";
        Set<String> expectedLanguages = Set.of("Celestial", "Elvish");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_GUILD_ARTISAN, dndCharacter), chooseArtisanTools, getArtisanToolOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_GUILD_ARTISAN -> CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_GUILD_ARTISAN")
    void heroCreationAnswer_chooseArtisansToolProficiencyForGuildArtisan() {
        incomingState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_GUILD_ARTISAN, dndCharacter);
        dndCharacter.getToolProficiency().add("Mason's tools");
        userAnswer = "Tinker's tools";
        Set<String> expectedToolProficiencies = Set.of("Mason's tools", "Tinker's tools");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_GUILD_ARTISAN, dndCharacter), chooseArtisanToolPossessionWithPreviousStep, getArtisanToolOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedToolProficiencies, dndCharacter.getToolProficiency());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT starts chooseProficiencyForGuildMerchant()")
    void heroCreationAnswer_chooseProficiencyForGuildMerchant() {
        incomingState = new State(CREATE_HERO, CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT, dndCharacter);
        userAnswer = "additional language";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        verify(chooseProficiencyForGuildMerchantSpy, times(1)).chooseProficiencyForGuildMerchant(userAnswer, dndCharacter);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getState().getStepId());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_ARTISAN_TOOL_PROFICIENCY_FOR_GUILD_MERCHANT -> CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT")
    void heroCreationAnswer_chooseArtisansToolProficiencyForGuildMerchant() {
        incomingState = new State(CREATE_HERO, CHOOSE_ARTISAN_TOOL_PROFICIENCY_FOR_GUILD_MERCHANT, dndCharacter);
        dndCharacter.getToolProficiency().add("Mason's tools");
        userAnswer = "Tinker's tools";
        Set<String> expectedToolProficiencies = Set.of("Mason's tools", "Tinker's tools");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT, dndCharacter), chooseArtisanToolPossessionWithPreviousStep, getArtisanToolOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedToolProficiencies, dndCharacter.getToolProficiency());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT starts choosePossessionsForGuildMerchant()")
    void heroCreationAnswer_choosePossessionsForGuildMerchant() {
        incomingState = new State(CREATE_HERO, CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT, dndCharacter);
        userAnswer = "a mule and a cart";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        verify(chooseProficiencyForGuildMerchantSpy, times(1)).chooseProficiencyForGuildMerchant(userAnswer, dndCharacter);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getState().getStepId());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_ADDITIONAL_LANGUAGE_FOR_GUILD_MERCHANT -> CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT")
    void heroCreationAnswer_chooseAdditionalLanguageForGuildMerchant() {
        incomingState = new State(CREATE_HERO, CHOOSE_ADDITIONAL_LANGUAGE_FOR_GUILD_MERCHANT, dndCharacter);
        dndCharacter.getLanguages().add("Celestial");
        userAnswer = "Elvish";
        Set<String> expectedLanguages = Set.of("Celestial", "Elvish");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT, dndCharacter), choosePossessionsForGuildMerchantText, getPossessionsForGuildMerchantOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedLanguages, dndCharacter.getLanguages());
        assertEquals(expectedResponse, actualResponse);
    }
}
