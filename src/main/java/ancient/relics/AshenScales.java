package ancient.relics;

import ancient.powers.FireAffinityPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import ancient.AncientMod;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;

public class AshenScales extends CustomRelic {
    public static final String ID = "Ancient:AshenScales";
    public static final String IMG = AncientMod.getResourcePath("relics/ashen_scales.png");
    public static final String OUTLINE = AncientMod.getResourcePath("relics/outline/ashen_scales.png");

    private static final int ROUNDS = 2;

    public AshenScales(){
        super(ID, ImageMaster.loadImage(IMG), new Texture(OUTLINE), RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public void atBattleStartPreDraw(){
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FireAffinityPower(AbstractDungeon.player, ROUNDS), ROUNDS));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FireBurstParticleEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AshenScales();
    }
}
