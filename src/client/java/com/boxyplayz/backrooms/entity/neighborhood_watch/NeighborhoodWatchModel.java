package com.boxyplayz.backrooms.entity.neighborhood_watch;

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class NeighborhoodWatchModel<T extends Entity> extends EntityModel<NeighborhoodWatchRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "neighborhood_watch"), "main");
	@SuppressWarnings("unused")
	private final ModelPart base;
	private final ModelPart legs;
	@SuppressWarnings("unused")
	private final ModelPart left;
	@SuppressWarnings("unused")
	private final ModelPart right;

	public NeighborhoodWatchModel(ModelPart root) {
		super(root);
		this.base = root.getChild("base");
		this.legs = root.getChild("legs");
		this.left = this.legs.getChild("left");
		this.right = this.legs.getChild("right");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-6.0F, -28.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition legs = partdefinition.addOrReplaceChild("legs", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left = legs.addOrReplaceChild("left", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = left.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(32, 24)
						.addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(16, 24).addBox(-1.0F, -10.0F, 7.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(8, 24).addBox(-1.0F, -10.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.0F, -9.0F, -4.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition cube_r2 = left.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(0, 36)
						.addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(24, 24).addBox(-1.0F, -10.0F, 7.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 24).addBox(-1.0F, -10.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, 0.0F, -4.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition right = legs.addOrReplaceChild("right", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = right.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(40, 36)
						.addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(32, 36).addBox(-1.0F, -10.0F, 7.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(16, 36).addBox(-1.0F, -10.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.0F, -9.0F, -4.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition cube_r4 = right.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(40, 24)
						.addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(24, 36).addBox(-1.0F, -10.0F, 7.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(8, 36).addBox(-1.0F, -10.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, 0.0F, -4.0F, 0.0F, 0.0F, -0.2618F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}