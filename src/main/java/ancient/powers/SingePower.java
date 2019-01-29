package ancient.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SingePower extends AbstractPower {
    public static final String POWER_ID = "Ancient:SingePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "AncientImages/powers/placeholder_power.png";

    public SingePower(AbstractCreature ownwer, int newAmount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = ownwer;
        this.amount = newAmount;
        updateDescription();
        type = PowerType.DEBUFF;
        isTurnBased = true;
        img = ImageMaster.loadImage(IMG);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type){
        if(type == DamageInfo.DamageType.NORMAL){
            return damage + this.amount;
        }
        return damage;
    }

    public void stackPower(int stackAmount)
    {
        super.stackPower(stackAmount);
    }

    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
}