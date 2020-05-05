package io.github.ashley1227.mctouchbar.widget.widgets;

import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import io.github.ashley1227.mctouchbar.MCTouchbar;
import io.github.ashley1227.mctouchbar.util.FramebufferUtils;
import io.github.ashley1227.mctouchbar.widget.Widget;
import io.github.ashley1227.mctouchbar.widget.config.WidgetConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SuperDebugWidget extends Widget {
	public TouchBarButton button;
	private ClientPlayerEntity player;

	private ItemStack prev;

	public SuperDebugWidget() {
	}

	@Override
	public void tick(WidgetConfig config, int index) {
		super.tick(config, index);
		if (button != null) {
			if (MCTouchbar.i % 10 == 0) {
				player = MinecraftClient.getInstance().player;
				if(player == null)
					return;
				if (player.inventory == null)
					return;
				int selectedSlot = player.inventory.selectedSlot;
				ItemStack curr = player.inventory.getInvStack(selectedSlot);
//				MCTouchbar.LOGGER.info(selectedSlot);
				if(!curr.equals(prev)) {
					button.setImage(FramebufferUtils.getItemStackImg(player.inventory.getInvStack(selectedSlot), 64));
					prev = curr;
				}

			}
		}
	}

	@Override
	public void addToTouchbar(JTouchBar jTouchBar, int index, WidgetConfig config) {
		super.addToTouchbar(jTouchBar, index, config);
		button = addButtonToTouchbar("widget.mctouchbar.debug", true, e -> {
			MCTouchbar.LOGGER.info("A " + Thread.currentThread());
//			TouchBarManager.reloadTouchbar();
			button.setImage(FramebufferUtils.getItemStackImg(Math.random() > 0.9 ? new ItemStack(Items.ACACIA_LOG) : new ItemStack(Items.ACACIA_DOOR), 64));
//			try {
//				button.setImage(new Image(new FileInputStream("/Users/computer/Desktop/Coding/MinecraftMods/MCTouchbar/src/main/resources/assets/mctouchbar/icon.png")));
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//			FramebufferUtils.saveItemStackPic(new ItemStack(Items.ACACIA_LOG),64);button.setImage(FramebufferUtils.getItemStackImg(Math.random() > 0.9 ? new ItemStack(Items.ACACIA_LOG) : new ItemStack(Items.ACACIA_DOOR), 64));
		});
		MCTouchbar.LOGGER.info("B " + Thread.currentThread());
		button.setImage(FramebufferUtils.getItemStackImg(Math.random() > 0.9 ? new ItemStack(Items.ACACIA_LOG) : new ItemStack(Items.ACACIA_DOOR), 64));
	}
}
