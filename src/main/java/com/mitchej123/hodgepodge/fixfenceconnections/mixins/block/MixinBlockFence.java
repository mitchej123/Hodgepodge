package com.mitchej123.hodgepodge.fixfenceconnections.mixins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockFence.class)
public class MixinBlockFence {
    /**
     * @author mitchej123 & leagris
     * @reason Backported Fix fence connections with extra fences, etc
     */
    @Overwrite()
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return block == (BlockFence)(Object) this
            || block instanceof net.minecraft.block.BlockFence && block != Blocks.nether_brick_fence
            || block instanceof net.minecraft.block.BlockFenceGate
            || ((block.blockMaterial.isOpaque() && block.renderAsNormalBlock()) && block.blockMaterial != Material.gourd);
    }
}
