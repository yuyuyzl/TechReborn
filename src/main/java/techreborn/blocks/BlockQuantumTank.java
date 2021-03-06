/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2017 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package techreborn.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.blocks.IAdvancedRotationTexture;
import techreborn.Core;
import techreborn.client.EGui;
import techreborn.client.TechRebornCreativeTab;
import techreborn.tiles.TileQuantumTank;

public class BlockQuantumTank extends BlockMachineBase implements IAdvancedRotationTexture {

	private final String prefix = "techreborn:blocks/machine/greg_machines/";

	public BlockQuantumTank() {
		super();
		this.setUnlocalizedName("techreborn.quantumTank");
		this.setHardness(2.0F);
		this.setCreativeTab(TechRebornCreativeTab.instance);
	}

	@Override
	public TileEntity createNewTileEntity(final World p_149915_1_, final int p_149915_2_) {
		return new TileQuantumTank();
	}

	@Override
	public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int side, final float hitX,
	                                final float hitY, final float hitZ) {
		if (this.fillBlockWithFluid(world, new BlockPos(x, y, z), player)) {
			return true;
		}
		if (!player.isSneaking()) {
			player.openGui(Core.INSTANCE, EGui.QUANTUM_TANK.ordinal(), world, x, y, z);
		}
		return true;
	}

	@Override
	public String getFront(final boolean isActive) {
		return "techreborn:blocks/machine/generators/thermal_generator_side_off";
	}

	@Override
	public String getSide(final boolean isActive) {
		return "techreborn:blocks/machine/generators/thermal_generator_side_off";
	}

	@Override
	public String getTop(final boolean isActive) {
		return this.prefix + "quantum_top";
	}

	@Override
	public String getBottom(final boolean isActive) {
		return "techreborn:blocks/machine/generators/thermal_generator_bottom";
	}
}
