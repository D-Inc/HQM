package hardcorequesting.blocks;

import hardcorequesting.HardcoreQuesting;
import hardcorequesting.items.ModItems;
import hardcorequesting.quests.Quest;
import hardcorequesting.tileentity.TileEntityTracker;
import hardcorequesting.util.Translator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//import net.minecraft.client.renderer.texture.IIconRegister;
//import net.minecraft.util.IIcon;


public class BlockTracker extends BlockContainer {

    public BlockTracker() {
        super(Material.WOOD);
        setRegistryName(BlockInfo.QUEST_TRACKER_UNLOCALIZED_NAME);
        setCreativeTab(HardcoreQuesting.HQMTab);
        setHardness(10f);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityTracker();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (player != null) {
            if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == ModItems.book) {
                if (!world.isRemote) {
                    TileEntity tile = world.getTileEntity(pos);
                    if (tile != null && tile instanceof TileEntityTracker) {
                        if (!Quest.isEditing) {
                            player.addChatComponentMessage(Translator.translateToIChatComponent("tile.hqm:quest_tracker.offLimit"));
                        } else {
                            ((TileEntityTracker) tile).setCurrentQuest();
                            if (((TileEntityTracker) tile).getCurrentQuest() != null) {
                                player.addChatComponentMessage(Translator.translateToIChatComponent("tile.hqm:quest_tracker.bindTo", ((TileEntityTracker) tile).getCurrentQuest().getName()));
                            } else {
                                player.addChatComponentMessage(Translator.translateToIChatComponent("hqm.message.noTaskSelected"));
                            }
                        }

                    }
                }
                return true;
            } else {
                if (!world.isRemote) {
                    TileEntity tile = world.getTileEntity(pos);
                    if (tile != null && tile instanceof TileEntityTracker) {
                        if (!Quest.isEditing) {
                            player.addChatComponentMessage(Translator.translateToIChatComponent("tile.hqm:quest_tracker.offLimit"));
                        } else {
                            ((TileEntityTracker) tile).openInterface(player);
                        }
                    }
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

//    @Override
//    public int isProvidingStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing side) {
//        return state.getBlock().getMetaFromState(state);
//    }
//
//    @Override
//    public int isProvidingWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing side) {
//        return state.getBlock().getMetaFromState(state);
//    }
}
