package thut.core.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thut.api.ThutItems;
import thut.api.maths.Vector3;
import thut.core.common.ThutCore;

public class ItemSpreader extends Item {

    public ItemSpreader() {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName("spreader");
        this.setCreativeTab(ThutCore.tabThut);
        ThutItems.spreader = this;
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World worldObj, BlockPos pos, EnumFacing side,
            float hitX, float hitY, float hitZ) {
        Block b = worldObj.getBlockState(pos).getBlock();
        if (b instanceof BlockLiquid && !worldObj.isRemote) {
            BlockLiquid fluid = (BlockLiquid) b;
            if (!fluid.isSideSolid(worldObj, pos, side)) {
                Vector3 spreadVector = Vector3.getNewVector();
                spreadVector.set(hitX, hitY, hitZ);
                fluid.trySpread(worldObj, spreadVector, true);
                return true;
            }
        }
        return false;
    }
}
