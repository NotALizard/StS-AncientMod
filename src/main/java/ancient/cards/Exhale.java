package ancient.cards;

import ancient.powers.*;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ancient.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Exhale extends AbstractAncientCard {
    public static final String ID = "Ancient:Exhale";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/defendAncient.png";
    private static final CardStrings cardStrings;

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int COST = 0;

    public Exhale(){
        super(ID, NAME, ancient.AncientMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.ANCIENT, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if(p.hasPower(FireAffinityPower.POWER_ID)){
            int fireStacks = p.getPower(FireAffinityPower.POWER_ID).amount;
            if (!this.upgraded) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new EmberPower(m, fireStacks), fireStacks));
            } else {
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new EmberPower(mo, fireStacks), fireStacks, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }
        if(p.hasPower(IceAffinityPower.POWER_ID)){
            int iceStacks = p.getPower(IceAffinityPower.POWER_ID).amount;
            if (!this.upgraded) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new SleetPower(m, iceStacks), iceStacks));
            } else {
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new SleetPower(mo, iceStacks), iceStacks, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }
        if(p.hasPower(VenomAffinityPower.POWER_ID)){
            int venomStacks = p.getPower(VenomAffinityPower.POWER_ID).amount;
            if (!this.upgraded) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -venomStacks), -venomStacks));
                boolean regainStrength = false;
                if(!m.hasPower(ArtifactPower.POWER_ID)){
                    regainStrength = true;
                }
                else if(m.getPower(ArtifactPower.POWER_ID).amount < 3){
                    regainStrength = true;
                }
                if(regainStrength){
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new ToxinPower(m, venomStacks), venomStacks));
                }
            } else {
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    boolean regainStrength = false;
                    if(!mo.hasPower(ArtifactPower.POWER_ID)){
                        regainStrength = true;
                    }
                    else if(mo.getPower(ArtifactPower.POWER_ID).amount < 3){
                        regainStrength = true;
                    }
                    if(regainStrength){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new ToxinPower(mo, -venomStacks), -venomStacks, true, AbstractGameAction.AttackEffect.NONE));
                    }

                }
            }
        }


    }

    public AbstractCard makeCopy()
    {
        return new Exhale();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.target = CardTarget.ALL_ENEMY;
            this.rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}
