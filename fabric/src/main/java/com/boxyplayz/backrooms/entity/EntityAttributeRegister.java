package com.boxyplayz.backrooms.entity;

import com.boxyplayz.backrooms.common.entity.ModEntities;
import com.boxyplayz.backrooms.common.entity.living.Balloon.BalloonEntity;
import com.boxyplayz.backrooms.common.entity.living.NeighborhoodWatch.NeighborhoodWatchEntity;
import com.boxyplayz.backrooms.common.entity.living.Partygoer.PartygoerEntity;
import com.boxyplayz.backrooms.common.entity.living.Partypooper.PartypooperEntity;
import com.boxyplayz.backrooms.common.entity.living.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.common.entity.living.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.common.entity.living.Wretch.WretchEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class EntityAttributeRegister {

	public static void RegisterEntityAttributes() {
		FabricDefaultAttributeRegistry.register(ModEntities.SMILER,
				SmilerEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.SKINSTEALER,
				SkinStealerEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.WRETCH,
				WretchEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.PARTYGOER,
				PartygoerEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.BALLOON,
				BalloonEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.PARTYPOOPER,
				PartypooperEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.NEIGHBORHOOD_WATCH,
				NeighborhoodWatchEntity.createAttributes());
	}
}
