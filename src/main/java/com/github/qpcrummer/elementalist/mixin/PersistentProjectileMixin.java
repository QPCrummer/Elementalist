package com.github.qpcrummer.elementalist.mixin;

import com.github.qpcrummer.elementalist.magic.Spell;
import com.github.qpcrummer.elementalist.util.TargetEntityAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileMixin extends ProjectileEntity implements TargetEntityAccessor {

    private Spell spell;
    private Vec3d start;

    public PersistentProjectileMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void setStartPos(Vec3d pos) {
        this.start = pos;
    }

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;multiply(D)Lnet/minecraft/util/math/Vec3d;"))
    private double inject(double d) {
        if (Objects.equals(this.getCustomName(), Text.literal("Target"))) {
            return 1.00f;
        } else {
            return d;
        }
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tick(CallbackInfo ci) {
        if (Objects.equals(this.getCustomName(), Text.literal("Target")) && spell != null) {
            spell.tick();
            if ( start.distanceTo(this.getPos()) >= spell.getDistance()) {
                BlockHitResult bhr = new BlockHitResult(this.getPos(), Direction.NORTH, this.getBlockPos(), true);
                spell.onHitBlock(bhr);
                this.discard();
                ci.cancel();
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("HEAD"), cancellable = true)
    private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (Objects.equals(this.getCustomName(), Text.literal("Target"))) {
            spell.onHitEntity(entityHitResult);
            this.discard();
            ci.cancel();
        }
    }

    @Inject(method = "onBlockHit", at = @At("HEAD"), cancellable = true)
    private void onBlockHit(BlockHitResult blockHitResult, CallbackInfo ci) {
        if (Objects.equals(this.getCustomName(), Text.literal("Target"))) {
            spell.onHitBlock(blockHitResult);
            this.discard();
            ci.cancel();
        }
    }
}
