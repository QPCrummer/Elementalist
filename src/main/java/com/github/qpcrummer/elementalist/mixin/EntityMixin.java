package com.github.qpcrummer.elementalist.mixin;

import com.github.qpcrummer.elementalist.magic.TargetEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements Nameable, EntityLike, CommandOutput {

    @Inject(method = "<init>", at = @At("HEAD"))
    private void init(EntityType type, World world, CallbackInfo ci) {
        if (this instanceof TargetEntity) {

        }
    }
}
