package com.boxyplayz.backrooms.datagen;

import java.util.concurrent.CompletableFuture;

import com.boxyplayz.backrooms.enchantments.ModEnchantments;
import com.boxyplayz.backrooms.tags.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.advancements.criterion.DamageSourcePredicate;
import net.minecraft.advancements.criterion.TagPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;

public class EnchantmentsProvider extends FabricDynamicRegistryProvider {
	private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key,
			Enchantment.Builder builder) {
		context.register(key, builder.build(key.identifier()));
	}

	public EnchantmentsProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		return "EnchantProvider";
	}

	@Override
	protected void configure(Provider registries, Entries entries) {
		entries.addAll(registries.lookupOrThrow(Registries.ENCHANTMENT));
	}

	public static void bootstrap(BootstrapContext<Enchantment> context) {
		register(context, ModEnchantments.ANOMALOUS_PROTECTION, Enchantment.enchantment(Enchantment.definition(
				context.lookup(Registries.ITEM).getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
				1,
				2,
				Enchantment.dynamicCost(1, 8),
				Enchantment.dynamicCost(1, 14),
				8,
				EquipmentSlotGroup.ARMOR))
				.withEffect(
						EnchantmentEffectComponents.DAMAGE_PROTECTION,
						new AddValue(LevelBasedValue.perLevel(2.0F)),
						AllOfCondition.allOf(
								DamageSourceCondition.hasDamageSource(
										DamageSourcePredicate.Builder.damageType()
												.tag(TagPredicate.is(ModTags.ANOMALY_DAMAGE))))));
	}

}
