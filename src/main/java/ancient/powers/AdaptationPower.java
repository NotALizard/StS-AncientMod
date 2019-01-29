package ancient.powers;

import ancient.AncientMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AdaptationPower extends AbstractPower {
    public static final String POWER_ID = "Ancient:AdaptationPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "AncientImages/powers/placeholder_power.png";

    public AdaptationPower(AbstractCreature owner, int newAmount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.priority = 0;
        updateDescription();
        type = PowerType.BUFF;
        isTurnBased = false;
        img = ImageMaster.loadImage(IMG);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void stackPower(int stackAmount)
    {
        super.stackPower(stackAmount);
    }

    public void atStartOfTurn()
    {
        flash();
        for(int i = 0; i < amount; i++){
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner, AncientMod.getRandomAffinity(true), 1));
        }
    }
}