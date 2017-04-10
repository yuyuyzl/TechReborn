package teamreborn.techreborn.tiles;

import net.minecraft.inventory.Slot;
import reborncore.api.rcpower.PowerUtils;
import reborncore.common.logic.LogicControllerRegistry;
import reborncore.common.registration.RebornRegistry;
import teamreborn.techreborn.TechReborn;

import java.util.List;

/**
 * Created by Gigabit101 on 09/04/2017.
 */
@RebornRegistry(modID = TechReborn.MOD_ID)
public class TileCreativeGenerator extends TileBaseMachine {
	@LogicControllerRegistry(name = "techreborn:creative_generator")
	public static TileCreativeGenerator machine;

	@Override
	public int getInvSize() {
		return 0;
	}

	@Override
	public List<Slot> getSlots() {
		return null;
	}

	@Override
	public void update() {
		PowerUtils.distributePowerToAllFaces(world, pos, 50, false);
	}
}
