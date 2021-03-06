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

package techreborn.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import techreborn.client.container.*;
import techreborn.client.gui.*;
import techreborn.manual.GuiManual;
import techreborn.tiles.*;
import techreborn.tiles.fusionReactor.TileEntityFusionController;
import techreborn.tiles.generator.*;
import techreborn.tiles.idsu.TileIDSU;
import techreborn.tiles.lesu.TileLesu;
import techreborn.tiles.multiblock.*;
import techreborn.tiles.storage.TileBatBox;
import techreborn.tiles.storage.TileMFE;
import techreborn.tiles.storage.TileMFSU;
import techreborn.tiles.teir1.*;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x,
	                                  final int y, final int z) {

		final EGui gui = EGui.values()[ID];
		final TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

		if (gui.useContainerBuilder() && tile != null)
			return ((IContainerProvider) tile).createContainer(player);

		switch (gui) {
			case AESU:
				return new ContainerAESU((TileAesu) tile, player);
			case DESTRUCTOPACK:
				return new ContainerDestructoPack(player);
			case LESU:
				return new ContainerLESU((TileLesu) tile, player);
			default:
				break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x,
	                                  final int y, final int z) {
		final EGui gui = EGui.values()[ID];
		final TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

		switch (gui) {
			case AESU:
				return new GuiAESU(player, (TileAesu) tile);
			case ALLOY_FURNACE:
				return new GuiAlloyFurnace(player, (TileAlloyFurnace) tile);
			case ALLOY_SMELTER:
				return new GuiAlloySmelter(player, (TileAlloySmelter) tile);
			case ASSEMBLING_MACHINE:
				return new GuiAssemblingMachine(player, (TileAssemblingMachine) tile);
			case BATBOX:
				return new GuiBatbox(player, (TileBatBox) tile);
			case BLAST_FURNACE:
				return new GuiBlastFurnace(player, (TileBlastFurnace) tile);
			case CENTRIFUGE:
				return new GuiCentrifuge(player, (TileCentrifuge) tile);
			case CHARGEBENCH:
				return new GuiChargeBench(player, (TileChargeBench) tile);
			case CHEMICAL_REACTOR:
				return new GuiChemicalReactor(player, (TileChemicalReactor) tile);
			case CHUNK_LOADER:
				return new GuiChunkLoader(player, (TileChunkLoader) tile);
			case COMPRESSOR:
				return new GuiCompressor(player, (TileCompressor) tile);
			case DESTRUCTOPACK:
				return new GuiDestructoPack(new ContainerDestructoPack(player));
			case DIESEL_GENERATOR:
				return new GuiDieselGenerator(player, (TileDieselGenerator) tile);
			case DIGITAL_CHEST:
				return new GuiDigitalChest(player, (TileDigitalChest) tile);
			case ELECTRIC_FURNACE:
				return new GuiElectricFurnace(player, (TileElectricFurnace) tile);
			case EXTRACTOR:
				return new GuiExtractor(player, (TileExtractor) tile);
			case FUSION_CONTROLLER:
				return new GuiFusionReactor(player, (TileEntityFusionController) tile);
			case GAS_TURBINE:
				return new GuiGasTurbine(player, (TileGasTurbine) tile);
			case GENERATOR:
				return new GuiGenerator(player, (TileGenerator) tile);
			case GRINDER:
				return new GuiGrinder(player, (TileGrinder) tile);
			case IDSU:
				return new GuiIDSU(player, (TileIDSU) tile);
			case IMPLOSION_COMPRESSOR:
				return new GuiImplosionCompressor(player, (TileImplosionCompressor) tile);
			case INDUSTRIAL_ELECTROLYZER:
				return new GuiIndustrialElectrolyzer(player, (TileIndustrialElectrolyzer) tile);
			case INDUSTRIAL_GRINDER:
				return new GuiIndustrialGrinder(player, (TileIndustrialGrinder) tile);
			case IRON_FURNACE:
				return new GuiIronFurnace(player, (TileIronFurnace) tile);
			case LESU:
				return new GuiLESU(player, (TileLesu) tile);
			case MANUAL:
				return new GuiManual();
			case MATTER_FABRICATOR:
				return new GuiMatterFabricator(player, (TileMatterFabricator) tile);
			case MFE:
				return new GuiMFE(player, (TileMFE) tile);
			case MFSU:
				return new GuiMFSU(player, (TileMFSU) tile);
			case QUANTUM_CHEST:
				return new GuiQuantumChest(player, (TileQuantumChest) tile);
			case QUANTUM_TANK:
				return new GuiQuantumTank(player, (TileQuantumTank) tile);
			case RECYCLER:
				return new GuiRecycler(player, (TileRecycler) tile);
			case ROLLING_MACHINE:
				return new GuiRollingMachine(player, (TileRollingMachine) tile);
			case SAWMILL:
				return new GuiIndustrialSawmill(player, (TileIndustrialSawmill) tile);
			case SCRAPBOXINATOR:
				return new GuiScrapboxinator(player, (TileScrapboxinator) tile);
			case SEMIFLUID_GENERATOR:
				return new GuiSemifluidGenerator(player, (TileSemifluidGenerator) tile);
			case THERMAL_GENERATOR:
				return new GuiThermalGenerator(player, (TileThermalGenerator) tile);
			case VACUUM_FREEZER:
				return new GuiVacuumFreezer(player, (TileVacuumFreezer) tile);
			default:
				break;

		}
		return null;
	}
}
