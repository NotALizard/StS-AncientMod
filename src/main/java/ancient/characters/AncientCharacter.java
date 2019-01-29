package ancient.characters;

import ancient.cards.TailWhip;
import ancient.patches.AbstractCardEnum;
import ancient.patches.AncientEnum;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import basemod.abstracts.CustomPlayer;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import ancient.patches.AncientEnum;
import ancient.cards.*;
import com.megacrit.cardcrawl.relics.BurningBlood;

import java.util.ArrayList;

public class AncientCharacter extends CustomPlayer {
    public static Color cardRenderColor = new Color(0.1f, 0.1f, 0.1f,1f);

    public static final int ENERGY_PER_TURN = 3;
    public static final String ANCIENT_SHOULDER_2 = "AncientImages/char/shoulder2.png";
    public static final String ANCIENT_SHOULDER_1 = "AncientImages/char/shoulder1.png";
    public static final String ANCIENT_CORPSE = "AncientImages/char/corpse.png";
    public static final String ANCIENT_SKELETON_ATLAS = "AncientImages/char/skeleton.atlas";
    public static final String ANCIENT_SKELETON_JSON = "AncientImages/char/skeleton.json";

    private static final CharacterStrings charStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public static final String[] orbTextures = {"AncientImages/char/orb/layer1.png", "AncientImages/char/orb/layer2.png", "AncientImages/char/orb/layer3.png",
            "AncientImages/char/orb/layer4.png", "AncientImages/char/orb/layer5.png", "AncientImages/char/orb/layer6.png",
            "AncientImages/char/orb/layer1d.png", "AncientImages/char/orb/layer2d.png", "AncientImages/char/orb/layer3d.png",
            "AncientImages/char/orb/layer4d.png", "AncientImages/char/orb/layer5d.png"};
    public static final String orbVfx = "AncientImages/char/orb/vfx.png";

    public AncientCharacter(String name, PlayerClass setClass){
        super(name, setClass, orbTextures, orbVfx, (String) null, (String) null);

        this.initializeClass((String) null, ANCIENT_SHOULDER_2, ANCIENT_SHOULDER_1, ANCIENT_CORPSE, this.getLoadout(),0,0,300f,180f,new EnergyManager(3) );


        loadAnimation(
                ANCIENT_SKELETON_ATLAS,
                ANCIENT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

    }

    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int MAX_ORBS = 0;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;

    public int getAscensionMaxHPLoss() {
        return 10;
    }

    public CharSelectInfo getLoadout(){
        return new CharSelectInfo(NAME, DESCRIPTION, STARTING_HP,MAX_HP,MAX_ORBS,STARTING_GOLD,HAND_SIZE,this, getStartingRelics(), getStartingDeck(),false);
    }

    public ArrayList<String> getStartingDeck(){
        ArrayList<String> retVal = new ArrayList();
        retVal.add(Strike_Ancient.ID);
        retVal.add(Strike_Ancient.ID);
        retVal.add(Strike_Ancient.ID);
        retVal.add(Strike_Ancient.ID);
        retVal.add(Defend_Ancient.ID);
        retVal.add(Defend_Ancient.ID);
        retVal.add(Defend_Ancient.ID);
        retVal.add(Defend_Ancient.ID);

        retVal.add(TailWhip.ID);
        retVal.add(WingAttack.ID);
        //
        return retVal;
    }

    public ArrayList<String> getStartingRelics(){
        ArrayList<String> retVal = new ArrayList();
        retVal.add(BurningBlood.ID);
        UnlockTracker.markRelicAsSeen(BurningBlood.ID);
        return retVal;
    }

    public String getTitle(PlayerClass playerClass){
        return NAME;
    }

    public AbstractCard.CardColor getCardColor(){
        return AbstractCardEnum.ANCIENT;
    }

    public Color getCardRenderColor(){
        return cardRenderColor;
    }

    public Color getCardTrailColor(){
        return cardRenderColor.cpy();
    }

    public Color getSlashAttackColor() {
        return cardRenderColor.cpy();
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("MONSTER_JAW_WORM_BELLOW", MathUtils.random(-0.6F, -0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return "MONSTER_JAW_WORM_BELLOW";
    }

    public String getLocalizedCharacterName(){
        return NAME;
    }

    public AbstractPlayer newInstance() {
        return new AncientCharacter(NAME, AncientEnum.ANCIENT);
    }

    public String getSpireHeartText() {
        return charStrings.TEXT[1] ;
    }

    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_DIAGONAL,AbstractGameAction.AttackEffect.SLASH_HEAVY,AbstractGameAction.AttackEffect.FIRE,AbstractGameAction.AttackEffect.FIRE,AbstractGameAction.AttackEffect.FIRE,AbstractGameAction.AttackEffect.SLASH_HEAVY};
    }

    public String getVampireText() {
        return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[5];
    }

    public AbstractCard getStartCardForEvent() {
        return new Strike_Ancient();
    }

    static{
        charStrings = CardCrawlGame.languagePack.getCharacterString("Ancient:AncientCharacter");
        NAME = charStrings.NAMES[0];
        DESCRIPTION = charStrings.TEXT[0];
    }

}
