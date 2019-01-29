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

public class SlumberPower extends AbstractPower {
    public static final String POWER_ID = "Ancient:SlumberPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "AncientImages/powers/placeholder_power.png";

    public SlumberPower(AbstractCreature owner, int newAmount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.priority = 0;
        updateDescription();
        type = PowerType.BUFF;
        isTurnBased = true;
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
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner, new FireAffinityPower(owner, amount), amount));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner, new IceAffinityPower(owner, amount), amount));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner, new VenomAffinityPower(owner, amount), amount));

        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,POWER_ID));
    }
}