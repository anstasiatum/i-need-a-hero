package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundFactory;
import player.dndcharacter.background.backgrounds.Acolyte;
import player.dndcharacter.background.backgrounds.Charlatan;
import player.dndcharacter.background.backgrounds.Criminal;
import player.dndcharacter.background.backgrounds.Custom;
import player.dndcharacter.background.backgrounds.Entertainer;
import player.dndcharacter.background.backgrounds.FolkHero;
import player.dndcharacter.background.backgrounds.Gladiator;
import player.dndcharacter.background.backgrounds.GuildArtisan;
import player.dndcharacter.background.backgrounds.GuildMerchant;
import player.dndcharacter.background.backgrounds.Hermit;
import player.dndcharacter.background.backgrounds.Knight;
import player.dndcharacter.background.backgrounds.Noble;
import player.dndcharacter.background.backgrounds.Outlander;
import player.dndcharacter.background.backgrounds.Pirate;
import player.dndcharacter.background.backgrounds.Sage;
import player.dndcharacter.background.backgrounds.Sailor;
import player.dndcharacter.background.backgrounds.Soldier;
import player.dndcharacter.background.backgrounds.Urchin;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.backgroundoptions.SetBackground;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static player.dndcharacter.dndcharacterenums.Background.ACOLYTE;
import static player.dndcharacter.dndcharacterenums.Background.CHARLATAN;
import static player.dndcharacter.dndcharacterenums.Background.CRIMINAL;
import static player.dndcharacter.dndcharacterenums.Background.CUSTOM;
import static player.dndcharacter.dndcharacterenums.Background.ENTERTAINER;
import static player.dndcharacter.dndcharacterenums.Background.FOLK_HERO;
import static player.dndcharacter.dndcharacterenums.Background.GLADIATOR;
import static player.dndcharacter.dndcharacterenums.Background.GUILD_ARTISAN;
import static player.dndcharacter.dndcharacterenums.Background.GUILD_MERCHANT;
import static player.dndcharacter.dndcharacterenums.Background.HERMIT;
import static player.dndcharacter.dndcharacterenums.Background.KNIGHT;
import static player.dndcharacter.dndcharacterenums.Background.NOBLE;
import static player.dndcharacter.dndcharacterenums.Background.OUTLANDER;
import static player.dndcharacter.dndcharacterenums.Background.PIRATE;
import static player.dndcharacter.dndcharacterenums.Background.SAGE;
import static player.dndcharacter.dndcharacterenums.Background.SAILOR;
import static player.dndcharacter.dndcharacterenums.Background.SOLDIER;
import static player.dndcharacter.dndcharacterenums.Background.URCHIN;
import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBackgroundOptions;
import static player.userinputhandler.commands.createnewhero.Options.getCharlatanConItemOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseArtisanTools;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseBackground;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseFirstLanguage;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseGamingSet;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseLanguage;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseLuckyCharm;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseMusicalInstrumentProficiency;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseTraits;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_BACKGROUND;
import static player.userinputhandler.enums.Steps.CHOOSE_CON_FOR_CHARLATAN;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE;

import static org.mockito.Mockito.mock;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_LANGUAGE_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_FOR_CRIMINAL;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_HERMIT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_OUTLANDER;
import static player.userinputhandler.enums.Steps.CHOOSE_LUCKY_CHARM_FOR_PIRATE;
import static player.userinputhandler.enums.Steps.CHOOSE_LUCKY_CHARM_FOR_SAILOR;
import static player.userinputhandler.enums.Steps.CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER;
import static player.userinputhandler.enums.Steps.CHOOSE_WEAPON_FOR_GLADIATOR;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

public class SetBackgroundTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private Response actualResponse;
    private Response expectedResponse;
    private final BackgroundFactory backgroundFactoryMock = mock(BackgroundFactory.class);
    private final SetBackground setBackground = new SetBackground(backgroundFactoryMock);
    private final Acolyte acolyteSpy = spy(new Acolyte());
    private final Charlatan charlatanSpy = spy(new Charlatan());
    private final Criminal criminalSpy = spy(new Criminal());
    private final Entertainer entertainerSpy = spy(new Entertainer());
    private final Gladiator gladiatorSpy = spy(new Gladiator());
    private final FolkHero folkHeroSpy = spy(new FolkHero());
    private final Noble nobleSpy = spy(new Noble());
    private final Knight knightSpy = spy(new Knight());
    private final Outlander outlanderSpy = spy(new Outlander());
    private final Sage sageSpy = spy(new Sage());
    private final Sailor sailorSpy = spy(new Sailor());
    private final Pirate pirateSpy = spy(new Pirate());
    private final Soldier soldierSpy = spy(new Soldier());
    private final Urchin urchinSpy = spy(new Urchin());
    private final Hermit hermitSpy = spy(new Hermit());
    private final GuildArtisan guildArtisanSpy = spy(new GuildArtisan());
    private final GuildMerchant guildMerchantSpy = spy(new GuildMerchant());
    private final Custom customSpy = spy(new Custom());

    @Test
    @DisplayName("setBackground(): Acolyte")
    void setBackgroundAcolyte() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE, dndCharacter), format(chooseFirstLanguage, "acolyte"));
        when(backgroundFactoryMock.createBackgroundFactory(ACOLYTE)).thenReturn(acolyteSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "acolyte");

        verify(acolyteSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Charlatan")
    void setBackgroundCharlatan() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CON_FOR_CHARLATAN, dndCharacter),"Choose your con item: ten stoppered bottles filled with coloured liquid, a set of weighted dice, a deck of marked cards, or a signet ring of an imaginary duke", getCharlatanConItemOptions());
        when(backgroundFactoryMock.createBackgroundFactory(CHARLATAN)).thenReturn(charlatanSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "charlatan");

        verify(charlatanSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Criminal")
    void setBackgroundCriminal() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_GAMING_SET_FOR_CRIMINAL, dndCharacter),chooseGamingSet);
        when(backgroundFactoryMock.createBackgroundFactory(CRIMINAL)).thenReturn(criminalSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "criminal");

        verify(criminalSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Entertainer")
    void setBackgroundEntertainer() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER, dndCharacter), chooseMusicalInstrumentProficiency);
        when(backgroundFactoryMock.createBackgroundFactory(ENTERTAINER)).thenReturn(entertainerSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "entertainer");

        verify(entertainerSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Gladiator")
    void setBackgroundGladiator() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_WEAPON_FOR_GLADIATOR, dndCharacter),"Enter an inexpensive, but unusual weapon (such as a trident or net) your hero will possess");
        when(backgroundFactoryMock.createBackgroundFactory(GLADIATOR)).thenReturn(gladiatorSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "gladiator");

        verify(gladiatorSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Folk Hero")
    void setBackgroundFolkHero() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO, dndCharacter), chooseArtisanTools, getArtisanToolOptions());
        when(backgroundFactoryMock.createBackgroundFactory(FOLK_HERO)).thenReturn(folkHeroSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "folk hero");

        verify(folkHeroSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Noble")
    void setBackgroundNoble() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT, dndCharacter), format(chooseLanguage, "noble"));
        when(backgroundFactoryMock.createBackgroundFactory(NOBLE)).thenReturn(nobleSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "noble");

        verify(nobleSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Knight")
    void setBackgroundKnight() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT, dndCharacter), format(chooseLanguage, "knight"));
        when(backgroundFactoryMock.createBackgroundFactory(KNIGHT)).thenReturn(knightSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "knight");

        verify(knightSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Outlander")
    void setBackgroundOutlander() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_OUTLANDER, dndCharacter), format(chooseLanguage, "outlander"));
        when(backgroundFactoryMock.createBackgroundFactory(OUTLANDER)).thenReturn(outlanderSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "outlander");

        verify(outlanderSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Sage")
    void setBackgroundSage() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_SAGE, dndCharacter), format(chooseFirstLanguage, "sage"));
        when(backgroundFactoryMock.createBackgroundFactory(SAGE)).thenReturn(sageSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "sage");

        verify(sageSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Sailor")
    void setBackgroundSailor() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_SAILOR, dndCharacter), chooseLuckyCharm);
        when(backgroundFactoryMock.createBackgroundFactory(SAILOR)).thenReturn(sailorSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "sailor");

        verify(sailorSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Pirate")
    void setBackgroundPirate() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_PIRATE, dndCharacter), chooseLuckyCharm);
        when(backgroundFactoryMock.createBackgroundFactory(PIRATE)).thenReturn(pirateSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "pirate");

        verify(pirateSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Soldier")
    void setBackgroundSoldier() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER, dndCharacter), chooseGamingSet);
        when(backgroundFactoryMock.createBackgroundFactory(SOLDIER)).thenReturn(soldierSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "soldier");

        verify(soldierSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Urchin")
    void setBackgroundUrchin() {
        expectedResponse = new Response(new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);
        when(backgroundFactoryMock.createBackgroundFactory(URCHIN)).thenReturn(urchinSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "urchin");

        verify(urchinSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Hermit")
    void setBackgroundHermit() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HERMIT, dndCharacter), format(chooseLanguage, "hermit"));
        when(backgroundFactoryMock.createBackgroundFactory(HERMIT)).thenReturn(hermitSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "hermit");

        verify(hermitSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Guild Artisan")
    void setBackgroundGuildArtisan() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN, dndCharacter), format(chooseLanguage, "guild artisan"));
        when(backgroundFactoryMock.createBackgroundFactory(GUILD_ARTISAN)).thenReturn(guildArtisanSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "guild artisan");

        verify(guildArtisanSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Guild Merchant")
    void setBackgroundGuildMerchant() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT, dndCharacter), format(chooseLanguage, "guild merchant"));
        when(backgroundFactoryMock.createBackgroundFactory(GUILD_MERCHANT)).thenReturn(guildMerchantSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "guild merchant");

        verify(guildMerchantSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Custom")
    void setBackgroundCustom() {
        expectedResponse = new Response(new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);
        when(backgroundFactoryMock.createBackgroundFactory(CUSTOM)).thenReturn(customSpy);

        actualResponse = setBackground.setBackground(dndCharacter, "custom");

        verify(customSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Trim test")
    void setBackgroundTrimTest() {
        expectedResponse = new Response(new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);
        when(backgroundFactoryMock.createBackgroundFactory(CUSTOM)).thenReturn(customSpy);

        actualResponse = setBackground.setBackground(dndCharacter, " cuStOm ");

        verify(customSpy, times(1)).modifyByBackground(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("setBackground(): Invalid background")
    void setBackgroundInvalidBackground() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_BACKGROUND, dndCharacter), chooseBackground, getBackgroundOptions());

        actualResponse = setBackground.setBackground(dndCharacter, "background");

        assertEquals(expectedResponse, actualResponse);
    }
}
