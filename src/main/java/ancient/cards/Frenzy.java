package ancient.cards;

import ancient.actions.unique.FrenzyAction;
import ancient.actions.unique.FrenzyAttackAction;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ancient.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Frenzy extends AbstractAncientCard {
    public static final String ID = "Ancient:Frenzy";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "cards/attackAncient.png";
    private static final CardStrings cardStrings;

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = -1;
    private static final int ATTACK_DMG = 8;
    private static final int UPGRADE_BONUS = 3;

    public Frenzy(){
        super(ID, NAME, ancient.AncientMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.ANCIENT, RARITY, TARGET);

        this.baseDamage = ATTACK_DMG;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        AbstractDungeon.actionManager.addToBottom(new FrenzyAction(p, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}
