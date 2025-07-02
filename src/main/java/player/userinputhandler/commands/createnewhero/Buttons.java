package player.userinputhandler.commands.createnewhero;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class Buttons {
    public static Keyboard sendReplyKeyboard() {

        // Create keyboard buttons

        // CHOOSE_ROLLING_CHARACTERISTICS_METHOD
        KeyboardButton rollCharacteristicsByUser = new KeyboardButton("Roll for me, bot");
        KeyboardButton rollCharacteristicsByBot = new KeyboardButton("I'll roll myself");

        // CHOOSE_RACE
        KeyboardButton dragonborn = new KeyboardButton("Dragonborn");
        KeyboardButton hillDwarf = new KeyboardButton("Hill Dwarf");
        KeyboardButton mountainDwarf = new KeyboardButton("Mountain Dwarf");
        KeyboardButton darkElf = new KeyboardButton("Dark Elf");
        KeyboardButton highElf = new KeyboardButton("High Elf");
        KeyboardButton woodElf = new KeyboardButton("Wood Elf");
        KeyboardButton forestGnome = new KeyboardButton("Forest Gnome");
        KeyboardButton rockGnome = new KeyboardButton("Rock Gnome");
        KeyboardButton halfElf = new KeyboardButton("Half Elf");
        KeyboardButton lightfootHalfling = new KeyboardButton("Lightfoot Halfling");
        KeyboardButton stoutHalfling = new KeyboardButton("Stout Halfling");
        KeyboardButton halfOrc = new KeyboardButton("Half Orc");
        KeyboardButton baseHuman = new KeyboardButton("Base Human");
        KeyboardButton variantHuman = new KeyboardButton("Variant Human");
        KeyboardButton tiefling = new KeyboardButton("Tiefling");

        // CHOOSE_CLASS
        KeyboardButton barbarian = new KeyboardButton("Barbarian");
        KeyboardButton bard = new KeyboardButton("Bard");
        KeyboardButton cleric = new KeyboardButton("Cleric");
        KeyboardButton druid = new KeyboardButton("Druid");
        KeyboardButton fighter = new KeyboardButton("Fighter");
        KeyboardButton monk = new KeyboardButton("Monk");
        KeyboardButton paladin = new KeyboardButton("Paladin");
        KeyboardButton ranger = new KeyboardButton("Ranger");
        KeyboardButton rogue = new KeyboardButton("Rogue");
        KeyboardButton sorcerer = new KeyboardButton("Sorcerer");
        KeyboardButton warlock = new KeyboardButton("Warlock");
        KeyboardButton wizard = new KeyboardButton("Wizard");

        // Skills
        KeyboardButton survival = new KeyboardButton("Survival");
        KeyboardButton stealth = new KeyboardButton("Stealth");
        KeyboardButton sleightOfHand = new KeyboardButton("Sleight of hand");
        KeyboardButton religion = new KeyboardButton("Religion");
        KeyboardButton persuasion = new KeyboardButton("Persuasion");
        KeyboardButton performance = new KeyboardButton("Performance");
        KeyboardButton perception = new KeyboardButton("Perception");
        KeyboardButton nature = new KeyboardButton("Nature");
        KeyboardButton medicine = new KeyboardButton("Medicine");
        KeyboardButton investigation = new KeyboardButton("Investigation");
        KeyboardButton intimidation = new KeyboardButton("Intimidation");
        KeyboardButton insight = new KeyboardButton("Insight");
        KeyboardButton history = new KeyboardButton("History");
        KeyboardButton deception = new KeyboardButton("Deception");
        KeyboardButton athletics = new KeyboardButton("Athletics");
        KeyboardButton arcana = new KeyboardButton("Arcana");
        KeyboardButton animalHandling = new KeyboardButton("Animal handling");
        KeyboardButton acrobatics = new KeyboardButton("Acrobatics");

        // Alignments
        KeyboardButton lawfulGood = new KeyboardButton("Lawful good");
        KeyboardButton neutralGood = new KeyboardButton("Neutral good");
        KeyboardButton chaoticGood = new KeyboardButton("Chaotic good");
        KeyboardButton lawfulNeutral = new KeyboardButton("Lawful neutral");
        KeyboardButton trueNeutral = new KeyboardButton("True neutral");
        KeyboardButton chaoticNeutral = new KeyboardButton("Chaotic neutral");
        KeyboardButton lawfulEvil = new KeyboardButton("Lawful evil");
        KeyboardButton neutralEvil = new KeyboardButton("Neutral evil");
        KeyboardButton chaoticEvil = new KeyboardButton("Chaotic evil");

        // Backgrounds
        KeyboardButton acolyte = new KeyboardButton("Acolyte");
        KeyboardButton charlatan = new KeyboardButton("Charlatan");
        KeyboardButton criminal = new KeyboardButton("Criminal");
        KeyboardButton entertainer = new KeyboardButton("Entertainer");
        KeyboardButton folkHero = new KeyboardButton("Folk Hero");
        KeyboardButton guildArtisan = new KeyboardButton("Guild Artisan");
        KeyboardButton hermit = new KeyboardButton("Hermit");
        KeyboardButton noble = new KeyboardButton("Noble");
        KeyboardButton outlander = new KeyboardButton("Outlander");
        KeyboardButton sage = new KeyboardButton("Sage");
        KeyboardButton sailor = new KeyboardButton("Sailor");
        KeyboardButton soldier = new KeyboardButton("Soldier");
        KeyboardButton urchin = new KeyboardButton("Urchin");
        KeyboardButton custom = new KeyboardButton("Custom");

        // Abilities
        KeyboardButton strength = new KeyboardButton("Strength");
        KeyboardButton dexterity = new KeyboardButton("Dexterity");
        KeyboardButton constitution = new KeyboardButton("Constitution");
        KeyboardButton intelligence = new KeyboardButton("Intelligence");
        KeyboardButton wisdom = new KeyboardButton("Wisdom");
        KeyboardButton charisma = new KeyboardButton("Charisma");

        // Artisan Tools
        KeyboardButton alchemistSupplies = new KeyboardButton("Alchemist's supplies");
        KeyboardButton brewerSupplies = new KeyboardButton("Brewer's supplies");
        KeyboardButton calligrapherSupplies = new KeyboardButton("Calligrapher's supplies");
        KeyboardButton carpenterTools = new KeyboardButton("Carpenter's tools");
        KeyboardButton cartographerTools = new KeyboardButton("Cartographer's tools");
        KeyboardButton cobblerTools = new KeyboardButton("Cobbler's tools");
        KeyboardButton cookUtensils = new KeyboardButton("Cook's utensils");
        KeyboardButton glassblowerTools = new KeyboardButton("Glassblower's tools");
        KeyboardButton jewelerTools = new KeyboardButton("Jeweler's tools");
        KeyboardButton leatherworkerTools = new KeyboardButton("Leatherworker's tools");
        KeyboardButton masonTools = new KeyboardButton("Mason's tools");
        KeyboardButton painterSupplies = new KeyboardButton("Painter's supplies");
        KeyboardButton potterTools = new KeyboardButton("Potter's tools");
        KeyboardButton smithTools = new KeyboardButton("Smith's tools");
        KeyboardButton tinkerTools = new KeyboardButton("Tinker's tools");
        KeyboardButton weaverTools = new KeyboardButton("Weaver's tools");
        KeyboardButton woodcarverTools = new KeyboardButton("Woodcarver's tools");

        // CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT
        KeyboardButton artisanTools = new KeyboardButton("Artisan's tools");
        KeyboardButton muleAndCart = new KeyboardButton("A mule and a cart");

        // CHOOSE_ENTERTAINER_OR_GLADIATOR
        KeyboardButton gladiator = new KeyboardButton("Gladiator");

        // CHOOSE_NOBLE_OR_KNIGHT_FOR_NOBLE
        KeyboardButton knight = new KeyboardButton("Knight");

        // CHOOSE_TROPHY_FOR_SOLDIER
        KeyboardButton dice = new KeyboardButton("Bone dice");
        KeyboardButton cards = new KeyboardButton("Deck of cards");

        // CHOOSE_ARTISAN_OR_MERCHANT_FOR_GUILD_ARTISAN
        KeyboardButton guildMerchant = new KeyboardButton("Guild merchant");

        // CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT
        KeyboardButton additionalLanguage = new KeyboardButton("Additional language");
        KeyboardButton navigatorTools = new KeyboardButton("Navigator's tools");


        // Create the reply keyboard markup
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                rollCharacteristicsByUser, rollCharacteristicsByBot)
                .resizeKeyboard(true)  // Make keyboard smaller
                .oneTimeKeyboard(true); // Hide after use

        return keyboard;
    }
}

