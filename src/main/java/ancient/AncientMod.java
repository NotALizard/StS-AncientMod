package ancient;

import ancient.cards.*;
import ancient.characters.AncientCharacter;
import ancient.patches.AbstractCardEnum;
import ancient.patches.AncientEnum;
import basemod.BaseMod;
import basemod.helpers.BaseModCardTags;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;
import java.util.jar.Attributes;

@SpireInitializer
public class AncientMod implements EditCharactersSubscriber, EditStringsSubscriber, EditCardsSubscriber {
    private static final Color ANCIENT_COLOR = com.megacrit.cardcrawl.helpers.CardHelper.getColor(50.0f,50.0f,50.0f);

    private static final String ATTACK_CARD = "512/bg_attack_ancient.png";
    private static final String SKILL_CARD = "512/bg_skill_ancient.png";
    private static final String POWER_CARD = "512/bg_power_ancient.png";
    private static final String ENERGY_ORB = "512/card_ancient_orb.png";
    private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_ancient.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_ancient.png";
    private static final String POWER_CARD_PORTRAIT = "1024/bg_power_ancient.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_ancient_orb.png";

    private static final String CHAR_BUTTON = "charSelect/button.png";
    private static final String CHAR_PORTRAIT = "charSelect/portrait.png";

    public static AncientCharacter ancientCharacter;

    public static final String getResourcePath(String resource){
        return "AncientImages/" + resource;
    }

    public AncientMod(){
        BaseMod.subscribe(this);

        BaseMod.addColor(AbstractCardEnum.ANCIENT,
                ANCIENT_COLOR,ANCIENT_COLOR,ANCIENT_COLOR,ANCIENT_COLOR,ANCIENT_COLOR,ANCIENT_COLOR,ANCIENT_COLOR,
                getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD), getResourcePath(POWER_CARD),
                getResourcePath(ENERGY_ORB),
                getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT), getResourcePath(POWER_CARD_PORTRAIT),
                getResourcePath(ENERGY_ORB_PORTRAIT), getResourcePath(CARD_ENERGY_ORB));


    }

    public static void initialize(){
        new AncientMod();
    }

    @Override
    public void receiveEditCharacters(){
        ancientCharacter = new AncientCharacter("TheAncient", AncientEnum.ANCIENT);
        BaseMod.addCharacter(ancientCharacter,
                getResourcePath(CHAR_BUTTON),
                getResourcePath(CHAR_PORTRAIT),
                AncientEnum.ANCIENT);
    }

    @Override
    public void receiveEditStrings(){
        String language = "eng";

        if (Settings.language == Settings.GameLanguage.ZHS) language = "zhs";
        if (Settings.language == Settings.GameLanguage.ZHT) language = "zht";
        if (Settings.language == Settings.GameLanguage.FRA) language = "fra";
        if (Settings.language == Settings.GameLanguage.KOR) language = "kor";
        if (Settings.language == Settings.GameLanguage.JPN) language = "jpn";

        String relicStrings = Gdx.files.internal("localization/" + language + "/Ancient-Relic-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal("localization/" + language + "/Ancient-Card-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal("localization/" + language + "/Ancient-Power-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String potionStrings = Gdx.files.internal("localization/" + language + "/Ancient-Potion-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
        String orbStrings = Gdx.files.internal("localization/" + language + "/Ancient-Orb-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
        String charStrings = Gdx.files.internal("localization/" + language + "/Ancient-Character-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CharacterStrings.class, charStrings);
    }

    @Override
    public void receiveEditCards(){
        BaseMod.addCard(new Defend_Ancient());
        BaseMod.addCard(new Strike_Ancient());
        BaseMod.addCard(new TailWhip());
        BaseMod.addCard(new Teeth());
        BaseMod.addCard(new WingAttack());
        BaseMod.addCard(new Ignition());

        UnlockTracker.unlockCard(Defend_Ancient.ID);
        UnlockTracker.unlockCard(Strike_Ancient.ID);
        UnlockTracker.unlockCard(TailWhip.ID);
        UnlockTracker.unlockCard(Teeth.ID);
        UnlockTracker.unlockCard(WingAttack.ID);
        UnlockTracker.unlockCard(Ignition.ID);
    }
}
