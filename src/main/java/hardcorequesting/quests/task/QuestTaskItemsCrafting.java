package hardcorequesting.quests.task;

import hardcorequesting.client.interfaces.edit.GuiEditMenuItem;
import hardcorequesting.event.EventHandler;
import hardcorequesting.quests.Quest;
import hardcorequesting.quests.QuestingData;
import hardcorequesting.quests.data.QuestDataTaskItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class QuestTaskItemsCrafting extends QuestTaskItems {

    public QuestTaskItemsCrafting(Quest parent, String description, String longDescription) {
        super(parent, description, longDescription);
        register(EventHandler.Type.CRAFTING);
    }

    @Override
    public void onUpdate(EntityPlayer player) {

    }

    @Override
    public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        create(event.player, event.crafting);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected GuiEditMenuItem.Type getMenuTypeId() {
        return GuiEditMenuItem.Type.CRAFTING_TASK;
    }

    private void create(EntityPlayer player, ItemStack stack) {
        if (!player.getEntityWorld().isRemote) {
            if (stack != null) {
                //no need for the quest to be active
                //if (parent.isVisible(player) && parent.isEnabled(player) && isVisible(player)) {
                stack = stack.copy();
                if (stack.stackSize == 0) {
                    stack.stackSize = 1;
                }
                increaseItems(new ItemStack[]{stack}, (QuestDataTaskItems) getData(player), QuestingData.getUserUUID(player));
                //}
            }
        }
    }


}
