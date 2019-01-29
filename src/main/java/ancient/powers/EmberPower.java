package ancient.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EmberPower extends AbstractPower {
    public static final String POWER_ID = "Ancient:EmberPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String IMG = "AncientImages/powers/placeholder_power.png";

    public EmberPower(AbstractCreature ownwer, int newAmount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = ownwer;
        this.amount = newAmount;
        updateDescription();
        type = PowerType.BUFF;
        isTurnBased = true;
        img = ImageMaster.loadImage(IMG);
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + DESCRIPTIONS[3];
        }

        else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2] + DESCRIPTIONS[3];
        }
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target){
        if(target != this.owner && info.type == DamageInfo.DamageType.NORMAL){
            flash();
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, this.owner, new SingePower(target, 1), 1, true));
        }
    }

    public void atEndOfRound()
    {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }
}
