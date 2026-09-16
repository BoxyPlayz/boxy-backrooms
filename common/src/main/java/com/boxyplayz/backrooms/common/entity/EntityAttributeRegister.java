package com.boxyplayz.backrooms.common.entity;

import com.boxyplayz.backrooms.common.entity.living.Balloon.BalloonEntity;
import com.boxyplayz.backrooms.common.entity.living.NeighborhoodWatch.NeighborhoodWatchEntity;
import com.boxyplayz.backrooms.common.entity.living.Partygoer.PartygoerEntity;
import com.boxyplayz.backrooms.common.entity.living.Partypooper.PartypooperEntity;
import com.boxyplayz.backrooms.common.entity.living.SkinStealer.SkinStealerEntity;
import com.boxyplayz.backrooms.common.entity.living.Smiler.SmilerEntity;
import com.boxyplayz.backrooms.common.entity.living.Wretch.WretchEntity;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;

public class EntityAttributeRegister {

	public static void RegisterEntityAttributes() {

		EntityAttributeRegistry.register(() -> ModEntities.SMILER,
				SmilerEntity::createAttributes);

		EntityAttributeRegistry.register(() -> ModEntities.SKINSTEALER,
				SkinStealerEntity::createAttributes);

		EntityAttributeRegistry.register(() -> ModEntities.WRETCH,
				WretchEntity::createAttributes);

		EntityAttributeRegistry.register(() -> ModEntities.PARTYGOER,
				PartygoerEntity::createAttributes);

		EntityAttributeRegistry.register(() -> ModEntities.BALLOON,
				BalloonEntity::createAttributes);

		EntityAttributeRegistry.register(() -> ModEntities.PARTYPOOPER,
				PartypooperEntity::createAttributes);

		EntityAttributeRegistry.register(() -> ModEntities.NEIGHBORHOOD_WATCH,
				NeighborhoodWatchEntity::createAttributes);
	}
}
