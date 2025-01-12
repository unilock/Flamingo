package com.reddit.user.koppeh.flamingo;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class Flamingo implements ModInitializer {

	public static final String MOD_ID = "flamingo";

	public static final Block FLAMINGO_BLOCK = register("flamingo", new FlamingoBlock(AbstractBlock.Settings.create().mapColor(MapColor.PINK).strength(0.8F).hardness(1.5F).sounds(BlockSoundGroup.WOOL).burnable()));
	public static final BlockEntityType<FlamingoBlockEntity> FLAMINGO_BLOCK_ENTITY = register("flamingo", FabricBlockEntityTypeBuilder.create(FlamingoBlockEntity::new, FLAMINGO_BLOCK));

	public static final Item FLAMINGO_ITEM = Items.register(FLAMINGO_BLOCK);

	public static Block register(String name, Block block) {
		return Registry.register(Registries.BLOCK, id(name), block);
	}

	public static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder<T> builder) {
		return Registry.register(Registries.BLOCK_ENTITY_TYPE, id(name), builder.build());
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			if (!player.isSpectator() && !world.isClient && world.getBlockEntity(pos) instanceof FlamingoBlockEntity) {
				world.addSyncedBlockEvent(pos, Flamingo.FLAMINGO_BLOCK, 0, 0);
			}
			return ActionResult.PASS;
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.add(FLAMINGO_ITEM));
	}
}
